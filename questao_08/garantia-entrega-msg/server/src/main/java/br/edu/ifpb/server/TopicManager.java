
package br.edu.ifpb.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class TopicManager extends UnicastRemoteObject {
    
    private NotificationRepository notifRepository;
    private SubscriberRepository subsRepository;
    
    public void notifySubscribers() throws RemoteException {
        
    }

}
