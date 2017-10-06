
package br.edu.ifpb.client;

import br.edu.ifpb.shared.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.VM;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class Main {

    private static Object lock = new Object();
    private static volatile int codigo = 0;
    
    public static void testaCarga100Users() throws RemoteException, NotBoundException {
        UserServiceClient service = new UserServiceClient();
        long initial = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            User user = new User(i, "nome qualquer");
            service.post(user);
        }
        long finalz = System.currentTimeMillis();
        System.out.println("Tempo para 100 users em milisegundos: " + (finalz - initial));
    }
    
    public static void testaCarga1000Users() throws RemoteException, NotBoundException {
        UserServiceClient service = new UserServiceClient();
        long initial = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User user = new User(i, "nome qualquer");
            service.post(user);
        }
        long finalz = System.currentTimeMillis();
        System.out.println("Tempo para 1000 users em milisegundos: " + (finalz - initial));
    }
    
    public static void testaCarga1000UsersWithThread() throws RemoteException, NotBoundException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable runnable = () -> {
            try {
                System.out.println("Tempo inicial para a Thread:" + System.currentTimeMillis());
                UserServiceClient service = new UserServiceClient();
                User user = new User(getCodigo(), "nome qualquer");
                for(int i = 0; i < 100; i++) {
                    service.post(user);
                }
                System.out.println("Tempo final para a Thread:" + System.currentTimeMillis());
            } catch (RemoteException | NotBoundException ex) {
                System.err.println(ex);
            }
        };
        executor.submit(runnable);
    }
    
    private static int getCodigo() {
        synchronized(lock) {
            return ++codigo;
        }
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        testaCarga100Users();
        testaCarga1000Users();
        testaCarga1000UsersWithThread();
    }
    
}
