
package br.edu.ifpb.server;

/**
 *
 * @author miolivc
 * @mail miolivc@outlook.com
 * @since 05/10/2017
 */

public interface Persist<T> {

    void save(T t);
    
}
