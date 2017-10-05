
package br.edu.ifpb.server;

import br.edu.ifpb.shared.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class GerenciadorPersistencia {

    private List<Persist> databases = new ArrayList<>();

    public GerenciadorPersistencia() {
        databases.add(new PostgresPersist());
        databases.add(new MySQLPersist());
    }
    
    public void persist(User user) {
        databases.forEach((database) -> {
            database.save(user);
        });
    }
    
}
