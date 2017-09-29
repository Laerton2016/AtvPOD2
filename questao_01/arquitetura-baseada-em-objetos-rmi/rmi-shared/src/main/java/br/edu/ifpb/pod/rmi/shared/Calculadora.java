
package br.edu.ifpb.pod.rmi.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {
    double multt(double x, double y) throws RemoteException;
    double divv(double x, double y) throws RemoteException;
}
