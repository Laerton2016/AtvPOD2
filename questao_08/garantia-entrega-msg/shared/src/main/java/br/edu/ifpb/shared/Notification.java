
package br.edu.ifpb.shared;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 06/10/2017
 */

public class Notification {

    private Message message;
    private Subscriber[] subscribers;

    public Notification() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Subscriber[] getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Subscriber[] subscribers) {
        this.subscribers = subscribers;
    }
    
}
