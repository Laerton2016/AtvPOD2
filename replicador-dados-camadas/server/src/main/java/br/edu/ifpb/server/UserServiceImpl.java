/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.server;

import br.edu.ifpb.shared.User;
import br.edu.ifpb.shared.UserService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author miolivc
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    public UserServiceImpl() throws RemoteException {
    }
    
    @Override
    public void persist(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
