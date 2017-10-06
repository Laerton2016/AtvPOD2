
package br.edu.ifpb.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 06/10/2017
 */

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/pod-chat?user=postgres&password=postgres");
        } catch (SQLException ex) {
            System.err.print("Erro ao se conectar ao database: " + ex);
        }
        return null;
    }
    
}
