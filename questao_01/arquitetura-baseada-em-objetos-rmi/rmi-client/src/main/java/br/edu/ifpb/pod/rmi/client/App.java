/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author miolivc
 */
public class App {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client = new Client();
        double x, y, result;
        int optCalc = 0;
        
        Scanner in = new Scanner(System.in);
        
        try {
            System.out.print("Digite uma opção:\n 1- Multiplicar numeros com 2: "
                    + "\n 2- Multiplicar com 2 e dividir pelo segundo numero: \n");
            optCalc = in.nextInt(); in.nextLine();
            
            System.out.print("\n Digite o primeiro número:");
            x = in.nextDouble(); in.nextLine();
            
            System.out.print("\n Digite o segundo número:");
            y = in.nextDouble(); in.nextLine();
            
            switch(optCalc){
                case 1: {
                    result = client.calculaSoma(x, y);
                    System.out.println("O resultado da opçao 1 eh: " + result);
                } break;
                case 2: {
                    result = client.calculaDiferenca(x, y);
                    System.out.println("O resultado da  opcao 2 eh: " + result);
                } break;
                default: {
                    System.out.println("Você não escolheu uma opção válida "
                            + "então o sistema será encerrado...");
                    System.exit(0);
                } break;
            }
           
        } catch (Exception ex){
            System.err.println("Erro no cliente: " + ex.toString());
        }
    }
}
