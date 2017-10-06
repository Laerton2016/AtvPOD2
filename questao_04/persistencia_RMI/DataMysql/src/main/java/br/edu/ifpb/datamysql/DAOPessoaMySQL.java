/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.datamysql;

import br.edu.ifpb.shared.DAOPessoa;

/**
 *
 * @author laerton
 */
public class DAOPessoaMySQL extends DAOPessoa{

    public DAOPessoaMySQL() {
        super.connect = new FactoryConnection().getConnection();
    }
    
}
