
package br.edu.ifpb.server;

import br.edu.ifpb.shared.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class PostgresPersist implements Persist<User> {
    
    private Connection connection;

    public PostgresPersist() {
        try {
            String url = "jdbc:postgresql://localhost:5432/pod-consistencia?username=postgresql&password=postgresql";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PostgresPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection
                    .prepareStatement("INSERT INTO tb_user(codigo, name) VALUES(?,?)");
            stmt.setInt(1, user.getCodigo());
            stmt.setString(2, user.getName());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                System.err.println(ex1);
            }
            Logger.getLogger(PostgresPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
