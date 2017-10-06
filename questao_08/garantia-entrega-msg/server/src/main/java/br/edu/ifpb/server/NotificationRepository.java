
package br.edu.ifpb.server;

import br.edu.ifpb.shared.Notification;
import br.edu.ifpb.shared.Subscriber;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class NotificationRepository {
    
    private List<Notification> repository = new ArrayList<>();
    private SubscriberRepository repSubscriber = new SubscriberRepository();
    
    public void store(Notification notification) {
        repository.add(notification);
    }
    
    public Notification[] listNotifications(String uuid) {
        int count = 0;
        Notification[] notifications = null;
        Subscriber sub = repSubscriber.find(uuid);
        for(Notification notification: repository) {
            for (Subscriber subscriber : notification.getSubscribers()) {
                if (subscriber.equals(sub)) {
                    notifications[count++] = notification;
                    break;
                }
            }
        }
        return notifications;
    }
    
    public void removeNotifications(String uuid) {
        int count = 0;
        Subscriber sub = repSubscriber.find(uuid);
        for(Notification notification: repository) {
            for (Subscriber subscriber : notification.getSubscribers()) {
                if (subscriber.equals(sub)) {
                    subscriber = null;
                    break;
                }
            }
        }
    }
}
