
package br.edu.ifpb.server;

import br.edu.ifpb.shared.Message;
import br.edu.ifpb.shared.Notification;
import br.edu.ifpb.shared.Publisher;
import br.edu.ifpb.shared.Subscriber;
import br.edu.ifpb.shared.Topic;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TopicManager extends UnicastRemoteObject implements Publisher, Topic {
    
    private final NotificationRepository repNotification = new NotificationRepository();;
    private final SubscriberRepository repSubscriber = new SubscriberRepository();

    public TopicManager() throws RemoteException {
    }

    @Override
    public void publish(Message msg) throws RemoteException {
        String[] uuids = repSubscriber.listUUIDs();
        Subscriber[] subscribers = null;
        int cont = 0;
        for (String uuid : uuids) {
            if (uuids[cont].equals(msg.getFrom())) { 
                subscribers[cont] = repSubscriber.find(uuids[cont]);
                cont++;
            }
        }
        Notification notification = new Notification();
        notification.setMessage(msg);
        notification.setSubscribers(subscribers);
        repNotification.store(notification);
    }
 
    @Override
    public void register(String uuid, Subscriber sub) throws RemoteException {
        repSubscriber.store(uuid, sub);
    }
    
    public void notifySubscribers() throws RemoteException {
        String[] uuids = repSubscriber.listUUIDs();
        for (String uuid : uuids) {
            Notification[] listNotifications = repNotification.listNotifications(uuid);
            Subscriber subscriber = repSubscriber.find(uuid);
            for (Notification notification : listNotifications) {
                subscriber.update(notification.getMessage());
            }
            repNotification.removeNotifications(uuid);
        }
    }

}
