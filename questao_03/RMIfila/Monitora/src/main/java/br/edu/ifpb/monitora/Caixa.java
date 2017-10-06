/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.monitora;

/**
 *
 * @author laerton
 */
public class Caixa {
    private int count = 0;
    private final Queue queue;
    private Pessoa current = null;

    public Caixa(Queue queue) {
        this.queue = queue;
    }
	
	public void startService(){
		current = this.queue.pop();
		System.out.println("Iniciando o atendimento ao cliente " + current.getId());
	}
	
	public void stopService(){
		System.out.println("Encerrando o atendimento ao cliente " + current.getId());
		current = null;
		count++;
	}
	
	public int count(){
		return count;
	}
}
