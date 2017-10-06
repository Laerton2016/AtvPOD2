/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.shared;

/**
 *
 * @author laerton
 */
public interface IDAO<T> {
    T salvar(T obj);
    T find (int id);
    void rem (T obj);
}
