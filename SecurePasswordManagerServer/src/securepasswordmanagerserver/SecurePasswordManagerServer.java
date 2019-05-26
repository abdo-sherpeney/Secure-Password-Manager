/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securepasswordmanagerserver;

import RMIServer.SecurePasswordbinder;
import Socket.SocketServerManager;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author adelali
 */
public class SecurePasswordManagerServer {

    public static void main(String args[]) {

        DataBase.DataBaseManager.DBPort =JOptionPane.showInputDialog("Enter Data Base Local Host Port");

        try {
            SecurePasswordbinder SecureCipherRMIServer = new SecurePasswordbinder();
        } catch (RemoteException ex) {
            Logger.getLogger(SecurePasswordManagerServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(SecurePasswordManagerServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        SocketServerManager Socket = new SocketServerManager();
        Socket.start();

    }
}
