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
public class Queue {
    	private int count = 0;
	private Pessoa[] peoples ;
        private int quantidade;

    public Queue(int quantidade) {
        this.quantidade = quantidade;
        peoples = new Pessoa[quantidade];
    }
	
        
	private void organize(){
		for (int i = 1; i < peoples.length; i++) {
			Pessoa p = peoples[i];
			peoples[i-1] = p;
		}
		peoples[peoples.length-1] = null;
	}
	
	public boolean push(Pessoa p){
		synchronized (peoples) {
			if (isEmpty()){
				peoples[count] = p;
				count++;
				return true;
			}
			else if (!isFull()){
				peoples[count-1] = p;
				count++;
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public Pessoa pop(){
		synchronized (peoples) {
			if (!isEmpty()){
				Pessoa p = peoples[0];
				peoples[0] = null;
				organize();
				count--;
				return p;
			}
			else {
				throw new RuntimeException("A fila está vazia.");
			}
		}
	}
	
	public int size(){
		return count;
	}
	
	public boolean isFull(){
		return count == quantidade;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}

}
