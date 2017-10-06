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
public class Engine {
        private Gerenciador gerQ0, gerQ1;
	private Caixa caixa1, caixa2, caixa3;
	private Queue q0, q1;
	
	private Thread t0 = null;
	private boolean fim = false;
	
	//
	private void printAll(){
		System.out.println("-------------------------------------------------------");
		System.out.println("Quantidade de pessoas que chegaram na Q0: " + gerQ0.count());
		System.out.println("Quantidade de pessoas que foram embora na Q0: " + gerQ0.fail());
                System.out.println("Quantidade de pessoas que chegaram na Q1: " + gerQ1.count());
		System.out.println("Quantidade de pessoas que foram embora na Q1: " + gerQ1.fail());
		System.out.println("Quantidade de pessoas na fila Q0: " + q0.size());
                System.out.println("Quantidade de pessoas na fila Q1: " + q1.size());
		System.out.println("Quantidade de pessoas atendidas Caixa1: " + caixa1.count());
                System.out.println("Quantidade de pessoas atendidas Caixa2: " + caixa2.count());
                System.out.println("Quantidade de pessoas atendidas Caixa3: " + caixa3.count());
		System.out.println("-------------------------------------------------------");
	}
	
	
	private void temporizador(){
		Runnable r0 = new Runnable() {
			@Override
			public void run() {
				//
				long ti = System.currentTimeMillis();
				//
				int timef = 0;
				boolean printed = false;
				//
				long time0 = System.currentTimeMillis();
				long time1 = System.currentTimeMillis();
				while(true){
					time1 = System.currentTimeMillis();
					if (time1 - time0 >= 1000){
						timef++;
						time0 = time1;
						synchronized (t0) {
							t0.notifyAll();
						}
						System.out.println("[T0] Time: " + timef + "s");
					}
					if(timef == 599 && printed == false){
						fim = true;
						printed = true;
						System.out.println("[T0] The end!");
					}
					if(timef == 600){
						printAll();
						break;
					}
				}
				//
				long tf = System.currentTimeMillis();
				System.out.println("Tempo total de simulação (ms): " + (tf-ti));
			}
		};
		//
		t0 = new Thread(r0);
		t0.start();
	}
        
        private double obterx (){
            Random gerador = new Random(100);
            return gerador.nextInt()/100;
        }
        
        private int getFx(double  x)
        {
            return (int) (0.833*(Math.exp(x*-1))) ;
        }
        
        private int getGx(double x)
        {
            return (int) (3*(Math.pow(x, 2)) + 5);
        }
        
        private int getHx(Double x)
        {
            return (int) (15* (Math.pow(x, x)));
        }
        
	private void gerenciadorDeEntrada(){
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				int time = 0;
                                double x = 0;
				while(fim == false){
					//
					synchronized (t0) {
						try {
							t0.wait();
						} 
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					if (++time == 1){
                                                //Obtem valores de X e numeros de pessoas para cada pessoa.
                                                double valorX = obterx();
						int qtQ0 = gerQ0.exec(getFx(valorX));
                                                int qtQ1 = gerQ1.exec(getGx(valorX));
						time = 0;
						System.out.println("[T1] Ocorreu a entrada de " + qtQ0 + " pessoa(s) na fila Q0.");
                                                System.out.println("[T1] Ocorreu a entrada de " + qtQ1 + " pessoa(s) ba fila Q1.");
					}
					else {
						System.out.println("[T1] Aguardando a entrada de pessoas.");
					}
				}
			}
		};
		//
		Thread t1 = new Thread(r1);
		t1.start();
	}
	
private void atendimento(){
    Runnable r2 = criaAtendimento(this.caixa1);
        Thread t2 = new Thread(r2);
        t2.start();
    }

        
    public Engine(Gerenciador gerQ0, Gerenciador gerQ1, Caixa caixa1, Caixa caixa2,Caixa caixa3, Queue q0, Queue q1) {
        this.gerQ0 = gerQ0;
        this.gerQ1 = gerQ1;
        this.caixa1 = caixa1;
        this.caixa2 = caixa2;
        this.caixa3 = caixa3;
        this.q0 = q0;
        this.q1 = q1;
    }

	
        
	public void exec(){
		temporizador();
		gerenciadorDeEntrada();
		atendimento();
	}   

    private Runnable criaAtendimento(Caixa caixa) {
        return new Runnable() {
        @Override
	public void run() {
	   while(fim == false){
	        synchronized (t0) {
                    try {
			t0.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            try {
		System.out.println("[T2] Verifacar se há atendimento para caixa.");
		System.out.print  ("[T2] ");
		caixa.startService();
            } 
	    catch (RuntimeException e){
                System.out.println("Ninguém para atendimento.");
                continue;
            }
            int time = 0;
            while(true){
                synchronized (t0) {
                    try {
			t0.wait();
                    } catch (InterruptedException e) {
			e.printStackTrace();
                    }
		}
		
		if (++time == 1){
                    System.out.print("[T2] ");
                    caixa.stopService();
                    break;
		}
            }
        }
        }
    };
    }
}
