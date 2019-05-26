/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 *
 * @author adelali
 */
public class SecurePasswordbinder {

    public SecurePasswordbinder() throws RemoteException, AlreadyBoundException {
            ServerSecuredPassword ServerBinder= new ServerSecuredPassword();
            java.rmi.registry.Registry Reg=  java.rmi.registry.LocateRegistry.createRegistry(5000);
            Reg.bind("SecuredPassword", ServerBinder);
    }
}
