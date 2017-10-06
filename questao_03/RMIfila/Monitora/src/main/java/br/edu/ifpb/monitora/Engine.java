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
        private double vx = 0;
	
	/***
         * Printa o resultado 
         */
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
	
	/***
         * Controla o tempo do cliclo de vida da aplicacao fixo em 600 Segundos
         */
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
					if(timef == 59 && printed == false){
						fim = true;
						printed = true;
						System.out.println("[T0] The end!");
					}
					if(timef == 60){
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
        /***
         * Obtem valor de X
         * @return X sedo 0<=X<1
         */
        private double obterX (){
            return Math.random();
        }
        /**
         * Retorna o  tempo de entrada de pessoa na Q0 
         * @param x 0<=x<1
         * @return Numero de pessoas 
         */
        private int getFx(double  x)
        {
            return (int) Math.round(0.833*(Math.exp(x*-1))) ;
        }
        /***
         * Encontra o numero de pessoas por segundo que entra na fila Q1
         * @param x = 0<=x<1
         * @return Numero de pessoas 
         */
        private int getGx(double x)
        {
            return (int)  Math.round(2*x);
        }
        
        /***
         * Encontra o numero de pessoas por segundo que sai das filas 
         * @param x 0<=x<1
         * @return Numero de pessoas
         */
        private double getHx(Double x)
        {
            return (0.3* (Math.pow(x, x)));
        }
        
        /***
         * Gerencia a entrada de pessoas nas filas 
         * @param valorX Valor de X entre 0<=x<1
         */
	private void gerenciadorDeEntrada(){
            Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int time = 0;
                double x = 0;
                
                while(fim == false){
                    x = obterX();
                    System.out.println("valor de X: " + x);
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
                        int qtQ0 = gerQ0.exec(getFx(x));
                        int qtQ1 = gerQ1.exec(getGx(x));
                        time = 0;
                        System.out.println("[T1] Ocorreu a entrada de " + qtQ0 + " pessoa(s) na fila Q0.");
                        System.out.println("[T1] Ocorreu a entrada de " + qtQ1 + " pessoa(s) ba fila Q1.");
                        obterX();
                    }
                    else {
                        System.out.println("[T1] Aguardando a entrada de pessoas.");
                    }
		}
            }
	};
	Thread t1 = new Thread(r1);
        t1.start();
    }
        
    /***
     * Gerencia o atendimentos por cada caixa de pessoas das filas
     * @param valorX Valor de X 0<=x<1 
     */
    private void atendimento()
    {
        double tempoX = getHx(obterX());
        Runnable r2 = criaAtendimento(this.caixa1,tempoX);
        Runnable r3 = criaAtendimento(this.caixa2, tempoX);
        Runnable r4 = criaAtendimento(this.caixa3, tempoX);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        t2.start();
        t3.start();
        t4.start();

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
            gerenciadorDeEntrada(); // Repassa ovalor de X para o Gerenciador
            atendimento(); // Repassa o valor de X para Atendimento
    }   

    private Runnable criaAtendimento(Caixa caixa, double tempoX) {
        return new Runnable() {
            @Override
            public void run() {
                int x = (int) Math.round(1/tempoX);//Encontra o tempo em segundos de atedimento.
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
                        //Tempo varia de acordo com tempoX (H(x)) variando a pouco mais de 1s a 4s por pessoa
                        if (++time == x){
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
