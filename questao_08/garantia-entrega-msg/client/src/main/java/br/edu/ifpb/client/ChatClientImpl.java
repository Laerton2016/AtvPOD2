
package br.edu.ifpb.client;

import br.edu.ifpb.shared.Message;
import br.edu.ifpb.shared.Publisher;
import br.edu.ifpb.shared.Subscriber;
import java.rmi.RemoteException;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 06/10/2017
 */

public class ChatClientImpl implements Subscriber {

    private final Publisher publisher;
    
    public ChatClientImpl(Publisher publisher) {
        this.publisher = publisher;
    }

    public void sendMessage(Message message) throws RemoteException {
        publisher.publish(message);
    }

    @Override
    public void update(Message msg) throws RemoteException {
        
    }

}
