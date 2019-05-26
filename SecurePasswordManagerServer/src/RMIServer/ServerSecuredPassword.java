/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIServer;

import Security.AES;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author adelali
 */
public class ServerSecuredPassword extends UnicastRemoteObject implements SecuredPassword{

    public ServerSecuredPassword() throws java.rmi.RemoteException
    {
    
    }
    @Override
    public String Decrypt(String SelectedPassword, String UserPassword) {
       return AES.decrypt(SelectedPassword, UserPassword);
    }

    @Override
    public String Encrypt(String SelectedPassword, String UserPassword) {
         return AES.encrypt(SelectedPassword, UserPassword);
    }
    
}
