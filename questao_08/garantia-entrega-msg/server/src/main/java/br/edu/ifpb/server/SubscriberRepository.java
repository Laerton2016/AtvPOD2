
package br.edu.ifpb.server;

import br.edu.ifpb.shared.Subscriber;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public class SubscriberRepository {
    
    private Map<String, Subscriber> repository = new HashMap<>();
    
    public void store(String uuid, Subscriber sub) {
        repository.put(uuid, sub);
    }
    
    public Subscriber find(String uuid) {
        return repository.get(uuid);
    }
    
    public String[] listUUIDs() {
        return (String[]) repository.keySet().toArray();
    }
}
