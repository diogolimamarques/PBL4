/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class Djikstra {
    private float[][] pesos;
    private Caminho[] caminhos; 
    private boolean[] jaVisitados;
    private int chegada; 
    private int partida;
    
    public Djikstra(float [][] pesos){
        this.pesos=pesos;
        caminhos = new Caminho[pesos.length];
        for(int i=0;i<caminhos.length;i++){
            caminhos[i]=new Caminho(-1,Float.MAX_VALUE);
        }
        jaVisitados= new boolean[pesos.length];
        for(int i=0;i<jaVisitados.length;i++){
            jaVisitados[i]=false;
        }
    }
    
    public void calcularMelhorRota(int partida,int chegada){
       this.chegada=chegada;
       this.partida=partida;
       caminhos[partida].setPeso(0);
       caminhos[partida].setAntecessor(partida);
       int atual = prox();
       while(atual!=chegada){
           rotula(atual);
           atual=prox();
       }
    }
    
    public void rotula(int pos){
        for(int i=0;i<pesos.length;i++){
            if(i!=pos&&!jaVisitados[i]){
                float pesoAtual = caminhos [i].getPeso();
                float pesoNovo = caminhos[pos].getPeso() + pesos[pos][i];
                if(pesoNovo<=pesoAtual){
                    caminhos[i].setAntecessor(pos);
                    caminhos[i].setPeso(pesoNovo);
                }
            }
        }
        jaVisitados[pos]=true;
    }
    
    public int prox(){
        int posMenor=chegada;
        if(posMenor<jaVisitados.length){
            for(int i=0;i<caminhos.length;i++){
                if(caminhos[i].getPeso()<caminhos[posMenor].getPeso() && !jaVisitados[i]){
                    posMenor=i;
                }                    
            }
        }
        return posMenor;
    }
    
    public int[] getCaminho(){
        int i =1;
        int a=chegada;
        while(a!=partida){
            a=caminhos[a].getAntecessor();
            i++;
        }
        a=chegada;
        int[] retorno = new int[i];
        for(int j=retorno.length-1;j>-1;j--){
            retorno[j]=a;
            a=caminhos[a].getAntecessor();
        }     
        return retorno;
    }
    public float getPesoCaminho(){
        return caminhos[chegada].getPeso();
    }
    
    public static void main(String[] args) {
        float[][] matriz = new float[6][6];
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                matriz[i][j]=Float.MAX_VALUE;
            }
        }
        matriz[0][1]=3;matriz[1][0]=3;
        matriz[0][2]=1;matriz[2][0]=1;
        matriz[1][3]=4;matriz[3][1]=4;
        matriz[1][2]=1;matriz[2][1]=1;
        matriz[2][4]=4;matriz[4][2]=4;
        matriz[2][3]=3;matriz[3][2]=3;
        matriz[3][5]=5;matriz[5][3]=5;
        matriz[4][5]=1;matriz[5][4]=1;
        Djikstra calc = new Djikstra(matriz);
        calc.calcularMelhorRota(0, 5);
        for(int a:calc.getCaminho())
            System.out.print(a+" ");
        System.out.println("\n"+calc.getPesoCaminho());
    }
    
    private class Caminho{
        private int antecessor;
        private float peso;
        
        public Caminho (int antecessor, float peso){
            this.antecessor = antecessor;
            this.peso= peso;
        }

        public int getAntecessor() {
            return antecessor;
        }

        public void setAntecessor(int antecessor) {
            this.antecessor = antecessor;
        }

        public float getPeso() {
            return peso;
        }

        public void setPeso(float peso) {
            this.peso = peso;
        }
        
    }
    
}
