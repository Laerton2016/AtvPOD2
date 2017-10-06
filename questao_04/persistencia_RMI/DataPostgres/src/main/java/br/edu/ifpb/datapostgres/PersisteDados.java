/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.datapostgres;

import br.edu.ifpb.shared.Observable;
import br.edu.ifpb.shared.Observer;
import br.edu.ifpb.shared.IDAO;
import br.edu.ifpb.shared.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laerton
 */
public class PersisteDados implements Observable{
    
    private boolean mudou = false;
    private IDAO<Pessoa> dao = new DAOPessoaPSQL();
    
    public void salvar(Pessoa p){
        dao.salvar(p);
        mudou = true;
        this.notifyObservers();
    }
    
    public void remove(Pessoa p){
        dao.rem(p);
        mudou = true;
        this.notifyObservers();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
