/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securepasswordmanagerclient;

import RMIClient.SecurePasswordBinder;
import Socket.Message;
import Socket.SocketClientManager;
import UI.CreatePassword;
import UI.LoginForm;
import UI.PasswordManager;
import User.User;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelali
 */
public class SecurePasswordManagerClient {

    /**
     * @param args the command line arguments
     */
    public static Thread clientThread;
    public static SocketClientManager CLient;
    public static User LoggedUser = null;
    public static LoginForm Login;
    public static PasswordManager PassManager;


    public static void main(String[] args) {
        Login = new LoginForm();
        Login.setVisible(true);
        clientThread = new Thread(CLient);
        clientThread.start();

    }
    public static void Handle(Message msg) {
        try {

            System.out.println(msg.type + " => " + msg.content.toString());
            if (msg.type.equals("[LOGIN]")) {

                if (msg.content != null) {
                    LoggedUser = (User) msg.content;
                    PassManager = new PasswordManager();
                    PassManager.setVisible(true);
                    Login.setVisible(false);
                    System.out.println("LOGIN SUCCESS");
                }
            }else  if (msg.type.equals("[GetAllPasswords]")) {
                PassManager.LoadPasswords(msg.content);
                
            }
        } catch (Exception e) {
            System.out.println(msg.type.toString());
        }
    }

}
