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
public class MeuIterador implements Iterador{
    
        private Celula atual;
        
        public MeuIterador(Celula primeira){
            atual = primeira;
        }
    
	public boolean temProximo(){
            return atual != null;
        }

	public Object obterProximo(){
            
            Object retorno = null;
            
            if(temProximo()){
                retorno = atual.getAtual();
                atual = atual.getProx();
            }
            return retorno;
            
        }
    
}
