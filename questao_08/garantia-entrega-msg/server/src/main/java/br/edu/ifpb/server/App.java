
package br.edu.ifpb.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class App {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        
        final TopicManager manager = new TopicManager();
        
        Registry registry = LocateRegistry.createRegistry(10999);
        registry.bind("__ChatServer__", manager);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    manager.notifySubscribers();
                } catch (RemoteException ex) {
                    System.err.println("Erro ao notificar: " + ex);
                }
            }
        }, 1000, 10000);
    }
    
}
