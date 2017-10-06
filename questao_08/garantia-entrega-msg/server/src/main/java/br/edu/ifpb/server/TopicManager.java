
package br.edu.ifpb.server;

import br.edu.ifpb.shared.Message;
import br.edu.ifpb.shared.Publisher;
import br.edu.ifpb.shared.Subscriber;
import br.edu.ifpb.shared.Topic;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TopicManager extends UnicastRemoteObject implements Publisher, Topic {
    
    private NotificationRepository repNotification;
    private SubscriberRepository repSubscriber;

    public TopicManager() throws RemoteException {
    }

    @Override
    public void publish(Message msg) throws RemoteException {
        
    }

    @Override
    public void register(String uuid, Subscriber sub) throws RemoteException {
        
    }
    
    public void notifySubscribers() {
        
    }

}
