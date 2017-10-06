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
    private String name ;

    public Caixa(Queue queue, String name ) {
        this.queue = queue;
        this.name = name;
    }

    
	
	public void startService(){
		current = this.queue.pop();
		System.out.println("Iniciando o atendimento ao cliente " + current.getId() + " no " + this.name);
	}
	
	public void stopService(){
		
		count++;
                System.out.println("Encerrando o atendimento ao cliente " + current.getId()+ " no " + this.name + " de um total de " + count);
                current = null;
	}
	
	public int count(){
		return count;
	}
}
