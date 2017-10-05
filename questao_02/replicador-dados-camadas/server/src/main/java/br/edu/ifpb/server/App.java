
package br.edu.ifpb.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class App {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        
        UserServiceImpl service = new UserServiceImpl();
        Registry registry = LocateRegistry.createRegistry(10999);
        registry.bind("UserService", service);
        System.out.println("Servidor ativo!");
        
    }
    
}
