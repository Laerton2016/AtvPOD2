/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author miolivc
 */
public interface UserService extends Remote {
    
    void post(User user) throws RemoteException;
    
}
