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
public class Caminho {
    private float distancia;
    private float tempo;
    
    public Caminho(float distancia, float tempo){
        this.distancia=distancia;
        this.tempo=tempo;
    }


    public float getDistancia() {
        return distancia;
    }

    
    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    
    public float getTempo() {
        return tempo;
    }

    
    public void setTempo(float tempo) {
        this.tempo = tempo;
    }
    
}
