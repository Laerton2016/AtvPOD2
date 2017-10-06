/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.datapostgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author laerton
 */
public class FactoryConnection {
    
    private String driver = "org.postgresql.Driver";
    private String user = "postgres";
    private String senha = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/projeto";
    
    public Connection getConnection() {
       Connection con = null;
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage());
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
        return con;
    }
    
}
