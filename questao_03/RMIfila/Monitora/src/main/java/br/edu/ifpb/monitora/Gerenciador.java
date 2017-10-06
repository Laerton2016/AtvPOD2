/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.monitora;

import java.util.Random;

/**
 *
 * @author laerton
 */
public class Gerenciador 
{
        private int count = 0;
	private int fail = 0;
	private final Queue Q0;
        

	private Pessoa createPeople(){
		return new Pessoa(count++);
	}
	
	public Gerenciador(Queue Q0) {
		this.Q0 = Q0;
                
	}
	
	public int exec(){
		//randomizando o número de pessoas a serem criadas (entrada)
		Random r = new Random();
		int qt = r.nextInt(5) + 1;
		//criando e encaminhando para a fila
		for (int i = 0; i < qt; i++) {
			Pessoa p = createPeople();
			fail += Q0.push(p) ? 0 : 1;
		}
		//
		return qt;
	}
	
	public int count(){
		return count;
	}
	
	public int fail(){
		return fail;
	}
    
}
