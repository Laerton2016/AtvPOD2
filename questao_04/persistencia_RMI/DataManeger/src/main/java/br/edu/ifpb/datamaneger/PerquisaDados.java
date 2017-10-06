/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.datamaneger;

import br.edu.ifpb.shared.DAOPessoa;
import br.edu.ifpb.shared.IDAO;
import br.edu.ifpb.shared.Pessoa;

/**
 *
 * @author laerton
 */
public class PerquisaDados {
    private IDAO<Pessoa> dao; 
    
    public Pessoa find(int id){
        return dao.find(id);
    }
    
}
