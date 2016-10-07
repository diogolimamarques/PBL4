/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.util;

/**
 *
 * @author User
 */
public class Celula {
    
    private Object atual;
    private Celula prox;
    
    public Celula(Object o){
        atual = o;
        prox = null;
    }

    public Object getAtual() {
        return atual;
    }

    public void setAtual(Object atual) {
        this.atual = atual;
    }

    public Celula getProx() {
        return prox;
    }

    public void setProx(Celula prox) {
        this.prox = prox;
    }
    
}
