/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.util;

/*Esta lista foi implementada com o auxílio do aluno Matheus Galvão*/

public class Lista implements ILista {
	private Celula head = null;
	private Celula tail = null;
	private int size = 0;
	

	public boolean estaVazia() {
		return size == 0;
	}

	public int obterTamanho() {
		return size;
	}

	public void inserirInicio(Object o) {
            Celula nova = new Celula(o);
        
            if(head == null){
                head = nova;
                tail = nova;
            }
            else{
                nova.setProx(head);
                head = nova;
            }
            size++;
        }

        public void inserirFinal(Object o) {
            Celula nova = new Celula(o);
        
            if(head == null){
                head = nova;
                tail = nova;
            }
            else{
                tail.setProx(nova);
            }
            size++;
        }

	public void inserir(Object o, int index) {
	   
		if(index == 0){
                    inserirInicio(o);
                }    
		else if(index == size-1){
                    inserirFinal(o);
                }
                else if(estaVazia()){
                    return;
                } 
		else {
                    Celula novo = new Celula(o);
                    Celula anteriorNovo = (Celula) getCelula(index - 1);
                    Celula proximoNovo = (Celula) getCelula(index + 1);
			
                    anteriorNovo.setProx(novo);
                    novo.setProx(proximoNovo);
			
                    size++;
		}
	}

	public Object removerInicio() {
		if(estaVazia()){
                    return null;
                }
                Celula removido = head;
		head = head.getProx();
		size--;
                if(estaVazia()){
                    tail = null;
                    head = null;
                }
		return removido.getAtual();
	}

	public Object removerFinal() {
		if(estaVazia()){
			return null;
                }        

		Celula antesTail = (Celula) getCelula(size - 2);
		Celula removido = tail;
		tail = antesTail;
		tail.setProx(null);
		size--;
		return removido.getAtual();
	}

	public Object remover(int index) {
		if(estaVazia() || (index < 0 || index >= size))
			return null;
		if(index == 0)
			return removerInicio();
		if(index == size - 1)
			return removerFinal();
		
		Celula antesRemoved = (Celula) getCelula(index - 1);
		Celula removed = (Celula) getCelula(index);
		
		antesRemoved.setProx(removed.getProx());
		removed.setProx(null);
		
		size--;
		return removed.getAtual();
	}

	private Object getCelula(int index) {
		if(index < 0 || index >= size) 
			return null;
		
		Celula aux = head;
		
		for(int i = 0; i < index; i++) {
			aux = aux.getProx();
		}
		
		return aux.getAtual();
	}
	
	public Object recuperar(int index) {
		if(index < 0 || index >= size)
			return null;
		return ((Celula) getCelula(index)).getAtual();
	}

	public Iterador iterador() {
		return new MeuIterador(head);
	}

}