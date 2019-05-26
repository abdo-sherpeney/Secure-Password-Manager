/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import UI.LoginForm;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelali
 */
public class SocketClientManager implements Runnable {

    public int port;
    public String serverAddr;
    public Socket socket;

    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public  SocketClientManager(String _ip, int _port) throws IOException {

        this.serverAddr = _ip;
        this.port = _port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);

        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        //Check Server
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {

            try {

                Message msg = (Message) In.readObject();
                securepasswordmanagerclient.SecurePasswordManagerClient.Handle(msg);
            } catch (Exception ex) {
                ex.printStackTrace();
                keepRunning = false;

            }
        }
    }

    public void send(Message msg) {
        try {
            Out.writeObject(msg);
            Out.flush();

        } catch (IOException ex) {

            System.out.println("Exception SocketClient send()");
        }
    }

    public void closeThread(Thread t) {
        t = null;
    }

}
