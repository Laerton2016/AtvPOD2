
package br.edu.ifpb.client;

import br.edu.ifpb.shared.Message;
import br.edu.ifpb.shared.Publisher;
import br.edu.ifpb.shared.Topic;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 06/10/2017
 */

public class App {
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite seu email:");
        String uuid = in.nextLine();
        
        Registry registry = LocateRegistry.getRegistry(10999);
        Topic topic = (Topic) registry.lookup("__ChatServer__");
        Publisher publisher = (Publisher) registry.lookup("__ChatServer__");
        
        ChatClientImpl client = new ChatClientImpl(publisher);
        topic.register(uuid, client);
        
        while(true) {
            
            String text = in.nextLine();
            Message message = new Message(uuid, text);
            
            client.sendMessage(message);
        }
    }
    
}
