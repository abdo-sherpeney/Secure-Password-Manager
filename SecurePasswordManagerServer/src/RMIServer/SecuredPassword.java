/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author adelali
 */
public interface SecuredPassword extends Remote{
    
    public String Decrypt(String SelectedPassword, String UserPassword)throws RemoteException;;
    public String Encrypt(String SelectedPassword, String UserPassword)throws RemoteException;;
}
