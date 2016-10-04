/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
/**
 *
 * @author user
 */
public class Grafo {
    private Caminho matriz[][];
    private int qtdCaminhos;
    private int qtdVertices;
    
    public Grafo(int qtdVertices){
        this.qtdVertices = qtdVertices;
        matriz = new Caminho[qtdVertices][qtdVertices];
        int qtdCaminhos= 0;        
    }
    public void addCaminho(int v1, int v2,float tempo,float distancia){
        matriz[v1][v2]= matriz[v2][v1]= new Caminho(distancia, tempo);
    }
    
    public Caminho getCaminho(int v1, int v2){
        return matriz[v1][v2];
    }
}
