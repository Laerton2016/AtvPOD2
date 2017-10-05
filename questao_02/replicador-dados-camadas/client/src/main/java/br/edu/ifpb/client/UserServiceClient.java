
package br.edu.ifpb.client;

import br.edu.ifpb.shared.User;
import br.edu.ifpb.shared.UserService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class UserServiceClient {

    private final UserService service;

    public UserServiceClient() throws RemoteException, NotBoundException {
        this.service = (UserService) LocateRegistry.getRegistry(10999).lookup("UserService");
    }
    
    public void post(User user) throws RemoteException {
        service.post(user);
    }
    
}
