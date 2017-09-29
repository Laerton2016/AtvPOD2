/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.rmi.shared;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author miolivc
 */
public class Provider {
    private static int server;
    private final Registry registry;
    
    public Provider() throws RemoteException {
        registry = getRegistry();
    }
    
    public Calculadora getCalculadora() throws NotBoundException, RemoteException {
        Calculadora calculadora = (Calculadora) registry.lookup("Calculadora");
        System.out.print("O server escolhido foi: " + server);
        return calculadora;
    }
    
    public Calculadora getCalculadora(String serverName) throws RemoteException, NotBoundException {
        Calculadora calculadora = (Calculadora) registry.lookup("Calculadora");
        System.out.print("O server escolhido foi: " + server);
        return calculadora;
    }
    
    private static Registry getRegistry() throws RemoteException {
        server = new Servidores().getServer();
        return LocateRegistry.getRegistry(server);
    }
    
    private static Registry getRegistry(String serverName) throws RemoteException {
        server = new Servidores().getServer(serverName);
        return LocateRegistry.getRegistry(server);
    }
}
