/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.datapostgres;

import br.edu.ifpb.shared.DAOPessoa;
import java.sql.Connection;

/**
 *
 * @author laerton
 */
public class DAOPessoaPSQL extends DAOPessoa{
    
    public DAOPessoaPSQL() {
        super.connect = new FactoryConnection().getConnection();
    }
    
}
