/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.datamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author laerton
 */
public class FactoryConnection {
    
    private String driver = "com.mysql.jdbc.Driver";
    private String user = "root";
    private String senha = "sil495798";
    private String url = "jdbc:mysql://localhost:3306/pod";
    
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
