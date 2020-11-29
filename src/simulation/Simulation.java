/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhakerkroumi
 * version 23 August 2020
 */
public class Simulation {
    
    public static final int SIMULATION_TIMES = 1000;
    
    
    /** 
     * cette methode permet d'afficher un tableau d'entiers
     * @param tab tableau a afficher
    */
    public static void afficherTableau(int [] tab){
        
        for (int i = 0; i < tab.length; i++){
            System.out.print(tab[i]+"  ");
        }
    }
    
    /** 
     * cette methode permet d'afficher un tableau de reels
     * @param tab tableau a afficher
    */
    public static void afficherTableau(double [] tab){
        
        for (int i = 0; i < tab.length; i++){
            System.out.print(tab[i]+"  ");
        }
    }
    
    /** 
     * cette methode permet d'afficher une matric d<entiers
     * @param tab tableau a afficher
    */
    public static void afficherMatrice(int [][]tab){
        
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
               System.out.print(tab[i][j]+"  "); 
            }
            System.out.print("\n");
        }
    }
    
    
    /** 
     * cette methode permet de simuler un jeu sur un graphe
     * @param G
    */
    public static void playGame (Graph G){
        int position; 
        boolean decide;
        while (!G.isPartieGagnee() && !G.isPartiePerdue()){
           
            position = G.select();
            decide = G.decide(position);
            if (decide){
                G.update(position);
            }
        }
    }
    
    
    
    
    
    public static void main (String [] args){
        
        int size   ;   //Population size
	int degree ;  //the graph degree
	double delta = 0.01;  //selection intensity
        double alphaC = 0.5 ;
        double alphaD = 1 ;
	double R; // the payoff of C vs C
	double S = 0; // the payoff of C vs D
	double T = 0; // the payoff of D vs C
	double P = 1;  // the payoff of D vs D
	int numberWins = 0;
        int numberGames = SIMULATION_TIMES*SIMULATION_TIMES;
        double percentageWin;
        int nbrCoop =1;
        Graph graph ;
        
        double [] tabR =  {0.5,0.6,0.7,0.8,0.9,1,1.1,1.2,1.3,1.4};
        int [] tabDegree ={3};
        
        size = 10;
        String fileName10="random_graph_N=10_k=3.txt";
        try {
            PrintWriter outputStream = new PrintWriter (fileName10);
            System.out.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 10 ----");
            outputStream.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 10 ----");
            for (int p=0; p < tabDegree.length; p++){
                degree = tabDegree[p];
                outputStream.println("---- k = "+degree+" -----");
                System.out.println("---- k = "+degree+" -----");
                for (int q = 0 ; q < tabR.length; q++){
                    R = tabR[q];
            
                    for (int i = 0; i < SIMULATION_TIMES ;i++){  
	                graph = new  Graph (size, degree, nbrCoop , R , S , T ,
                                P , alphaC, alphaD , delta);      
                        
                        playGame (graph);
                        
                        if (graph.isPartieGagnee()){
                                 numberWins++;
                        }
                        
                        for(int j = 1; j < SIMULATION_TIMES  ; j++){
                            graph.reinitialiserGraph();
                            playGame (graph);
                            
                            if (graph.isPartieGagnee()){
                                 numberWins++;
                            }
                        }    
                    }
                    percentageWin = ((double) numberWins)/((double) numberGames);
                    System.out.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    outputStream.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    numberWins =0 ;
                }   
            }
           
            outputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        size=20;
        
        String fileName20="Random-regular-graph_N=20_k=3.txt";
        try {
            PrintWriter outputStream = new PrintWriter (fileName20);
            System.out.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 20 ----");
            outputStream.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 20 ----");
            for (int p=0; p < tabDegree.length; p++){
                degree = tabDegree[p];
                outputStream.println("---- k = "+degree+" -----");
                System.out.println("---- k = "+degree+" -----");
                for (int q = 0 ; q < tabR.length; q++){
                    R = tabR[q];
            
                    for (int i = 0; i < SIMULATION_TIMES ;i++){  
	                graph = new  Graph (size, degree, nbrCoop , R , S , T ,
                                P , alphaC, alphaD , delta);      
                        
                        playGame (graph);
                        
                        if (graph.isPartieGagnee()){
                                 numberWins++;
                        }
                        
                        for(int j = 1; j < SIMULATION_TIMES  ; j++){
                            graph.reinitialiserGraph();
                            playGame (graph);
                            
                            if (graph.isPartieGagnee()){
                                 numberWins++;
                            }
                        }    
                    }
                    percentageWin = ((double) numberWins)/((double) numberGames);
                    System.out.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    outputStream.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    numberWins =0 ;
                }   
            }
           
            outputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
             
        
        
        
        
        
        size =50;
        String fileName50="Random-regular-graph_N=50_k=3.txt";
        try {
            PrintWriter outputStream = new PrintWriter (fileName50);
            System.out.println("----Random-regular-graphh , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 50 ----");
            outputStream.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 50 ----");
            for (int p=0; p < tabDegree.length; p++){
                degree = tabDegree[p];
                outputStream.println("---- k = "+degree+" -----");
                System.out.println("---- k = "+degree+" -----");
                for (int q = 0 ; q < tabR.length; q++){
                    R = tabR[q];
            
                    for (int i = 0; i < SIMULATION_TIMES ;i++){  
	                graph = new  Graph (size, degree, nbrCoop , R , S , T ,
                                P , alphaC, alphaD , delta);      
                        
                        playGame (graph);
                        
                        if (graph.isPartieGagnee()){
                                 numberWins++;
                        }
                        
                        for(int j = 1; j < SIMULATION_TIMES  ; j++){
                            graph.reinitialiserGraph();
                            playGame (graph);
                            
                            if (graph.isPartieGagnee()){
                                 numberWins++;
                            }
                        }    
                    }
                    percentageWin = ((double) numberWins)/((double) numberGames);
                    System.out.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    outputStream.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    numberWins =0 ;
                }   
            }
           
            outputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        
        size =100;
        String fileName100="Random-regular-graph_N=100_k=3.txt";
        try {
            PrintWriter outputStream = new PrintWriter (fileName100);
            System.out.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 100 ----");
            outputStream.println("----Random-regular-graph , alphaC = 0.5 , alphaD = 1 ,"
                    + " N = 100 ----");
            for (int p=0; p < tabDegree.length; p++){
                degree = tabDegree[p];
                outputStream.println("---- k = "+degree+" -----");
                System.out.println("---- k = "+degree+" -----");
                for (int q = 0 ; q < tabR.length; q++){
                    R = tabR[q];
            
                    for (int i = 0; i < SIMULATION_TIMES ;i++){  
	                graph = new  Graph (size, degree, nbrCoop , R , S , T ,
                                P , alphaC, alphaD , delta);      
                        
                        playGame (graph);
                        
                        if (graph.isPartieGagnee()){
                                 numberWins++;
                        }
                        
                        for(int j = 1; j < SIMULATION_TIMES  ; j++){
                            graph.reinitialiserGraph();
                            playGame (graph);
                            
                            if (graph.isPartieGagnee()){
                                 numberWins++;
                            }
                        }    
                    }
                    percentageWin = ((double) numberWins)/((double) numberGames);
                    System.out.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    outputStream.println("-- R -- = "+R+"-- F_C -- = "+ percentageWin);
                    numberWins =0 ;
                }   
            }
           
            outputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
}
