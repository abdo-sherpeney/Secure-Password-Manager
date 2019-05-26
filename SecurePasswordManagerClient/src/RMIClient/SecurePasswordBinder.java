/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIClient;

import RMIServer.SecuredPassword;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adelali
 */
public class SecurePasswordBinder {

  public   SecuredPassword FromServerSecuredPassword;

    public SecurePasswordBinder() {

       
      try {
          
            java.rmi.registry.Registry Reg = java.rmi.registry.LocateRegistry.getRegistry("localhost", 5000);
             FromServerSecuredPassword = (SecuredPassword) Reg.lookup("SecuredPassword");
             
      } catch (RemoteException ex) {
          Logger.getLogger(SecurePasswordBinder.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NotBoundException ex) {
          Logger.getLogger(SecurePasswordBinder.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }

}
