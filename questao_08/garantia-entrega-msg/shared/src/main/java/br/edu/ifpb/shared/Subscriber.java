
package br.edu.ifpb.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public interface Subscriber extends Remote {

    void update(Message msg) throws RemoteException;
    
}
