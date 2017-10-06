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
public class Main {
    
    public static void main(String[] args) {
        Queue Q0 =new Queue(100);
        Queue Q1 =new Queue(50);
        Caixa cx1 = new Caixa(Q0, "Caixa1");
        Caixa cx2 = new Caixa(Q0, "Caixa2");
        Caixa cx3 = new Caixa(Q1, "Caixa3");
        Gerenciador G1 = new Gerenciador(Q0);
        Gerenciador G2 = new Gerenciador(Q1);
        Engine eng = new Engine(G1, G2, cx1, cx2, cx3, Q0, Q1);
        eng.exec();
    }
    
}
