/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.shared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class DAOPessoa implements IDAO<Pessoa>{
    
    protected Connection connect ;

    public DAOPessoa() {
    }

    
    
    @Override
    public Pessoa salvar(Pessoa obj) {
        String SQL = "";
        if (obj.getId() == 0){
            SQL = "Insert into Pessoa (id, nome) values (?,?)";
        }else{
            SQL = "Update Pessoa set (nome =?) where id =?";
        }
        return persiste(SQL, obj);
    }

    @Override
    public Pessoa find(int id) {
        Pessoa p = null;
        String SQL = "Select * from pessoa where id = ?";
        PreparedStatement ptmt;
        try {
            ptmt = this.connect.prepareStatement(SQL);
            ptmt.setInt(0, id);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                p = montaPessoa(rs);
            }
            rs.close();
            ptmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }

    @Override
    public void rem(Pessoa obj) {
        String SQL = "Delete from pessoa where id =?";
        try {
            PreparedStatement ptmt = this.connect.prepareStatement(SQL);
            ptmt.setInt(0, obj.getId());
            ptmt.execute();
            ptmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private Pessoa persiste(String SQL, Pessoa obj) {
        try {
            PreparedStatement stmt = this.connect.prepareStatement(SQL);
            stmt.setInt(0, obj.getId());
            stmt.setString(1, obj.getNome());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    private Pessoa montaPessoa(ResultSet rs) throws SQLException {
        return new Pessoa(rs.getInt(0), rs.getString(1));
    }
    
}
