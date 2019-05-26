/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Password.Password;
import User.User;
import com.sun.java.accessibility.util.EventID;
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelali
 */
class ServerThread extends Thread {

    public SocketServerManager server = null;
    public Socket socket = null;
    public int ID = -1;
    public User ClientUser = null;
    public ObjectInputStream streamIn = null;
    public ObjectOutputStream streamOut = null;

    public ServerThread(SocketServerManager _server, Socket _socket) {
        super();
        server = _server;
        socket = _socket;

        ID = socket.getPort();
    }

    public void send(Message msg) {
        try {

            server.Announce(msg);
            streamOut.writeObject(msg);
            streamOut.flush();

        } catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }

    public int getID() {
        return ID;
    }

    @SuppressWarnings("deprecation")
    public void run() {

        System.out.println("[Client] Thread " + ID + " Connected.\n");

        this.send(new Message("Connected To Secured Password Manager Server", null));
        while (true) {
            try {

                Message msg = (Message) streamIn.readObject();

                server.Announce(msg);

                server.handle(ID, msg);

            } catch (Exception ioe) {
            }
        }
    }

    public void open() throws IOException {
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
        streamIn = new ObjectInputStream(socket.getInputStream());
    }

    public void close() throws IOException {
        socket.close();
        streamIn.close();
        streamOut.close();
    }
}

public class SocketServerManager implements Runnable {

    public ServerThread clients[];
    public ServerSocket server = null;
    public Thread thread = null;
    public int clientCount = 0, port = 13000;
  
    public SocketServerManager() {

        clients = new ServerThread[50];

        try {
            server = new ServerSocket(port);

            System.out.println("[Server] startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort() + "\n");
            start();
        } catch (IOException ioe) {

            System.out.println("[Error]  Can not bind to port : " + port);
        }

        System.out.println("Waiting For Clients");
    }

    public void run() {
        while (thread != null) {
            try {

                addThread(server.accept());
            } catch (Exception ioe) {

            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @SuppressWarnings("deprecation")
    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }

    private int findClient(int ID) {
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getID() == ID) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void handle(int ID, Message msg) {
      ///////////////////////////////////////////////////
      //\\\\\\\\\\=========USER LOGIN==========/////////
      /////////////////////////////////////////////////
      if (msg.type.equals("[LOGIN]")) {

            User UserToLogin = ((User) msg.content);
            
            User UserToCheckOnDB = DataBase.DataBaseManager.GetUser("Select * From users where userName='"
                    + UserToLogin.userName() + "' and Password = '" + UserToLogin.Password() + "'");

            if (UserToCheckOnDB==null)return;
            
            Announce(new Message(("UserLoginSuccress"), UserToCheckOnDB.firstName()));
            
            clients[findClient(ID)].send(new Message("[LOGIN]", UserToCheckOnDB));
            
            clients[findClient(ID)].ClientUser = UserToCheckOnDB;
       ///////////////////////////////////////////////////
      //\\\\\\\\\\=========CreatePassword==========/////////
      /////////////////////////////////////////////////     
        } else if (msg.type.equals("[CreatePassword]")) {

            if (clients[findClient(ID)].ClientUser != null) {
                DataBase.DataBaseManager.CreatePasswords((Password) msg.content, clients[findClient(ID)].ClientUser);
                UpdateUserPasswords(msg, ID);
            }
      ///////////////////////////////////////////////////
      //\\\\\\\\\\=========GetAllPasswords==========/////////
      /////////////////////////////////////////////////         
        } else if (msg.type.equals("[GetAllPasswords]")) {
            UpdateUserPasswords(msg, ID);
      ///////////////////////////////////////////////////
      //\\\\\\\\\\=========DeletePassword==========/////////
      /////////////////////////////////////////////////            
        } else if (msg.type.equals("[DeletePassword]")) {
            Password p = (Password) msg.content;

            DataBase.DataBaseManager.DeletePassword(p);
            UpdateUserPasswords(msg, ID);

        }

        //[DeletePassword]
    }

    public void UpdateUserPasswords(Message msg, int ID) {
        msg.type = "[GetAllPasswords]";
        msg.content = DataBase.DataBaseManager.GetPasswords(clients[findClient(ID)].ClientUser.id());
        clients[findClient(ID)].send(msg);
    }

    public void Announce(Message msg) {
        try {
            System.out.println(msg.type + " => " + msg.content.toString());
        } catch (Exception e) {
        }

    }

    private void addThread(Socket socket) {
      
            // AnnounceServer("\n[Client accepted]  " + socket.toString());
            clients[clientCount] = new ServerThread(this, socket);
            try {
                clients[clientCount].open();
                clients[clientCount].start();
                clientCount++;
            } catch (IOException ioe) {

            }
       
    }
}
