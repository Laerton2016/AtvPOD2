/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.node_1;

import br.edu.ifpb.pod.rmi.shared.Calculadora;
import br.edu.ifpb.pod.rmi.shared.Provider;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author miolivc
 */
public class Multiplicador extends UnicastRemoteObject implements Calculadora {
    
    public Multiplicador() throws RemoteException {};
    
    @Override
    public double multt(double x, double y) throws RemoteException {
        return 2 * y * x;
    }

    @Override
    public double divv(double x, double y) throws RemoteException {
        Calculadora calculadora = null;
        try {
            calculadora = new Provider().getCalculadora("node_3");
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        }
        return calculadora.divv(x, y);
    }
    
}
