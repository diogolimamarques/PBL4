/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import util.*;
import model.*;

public class Papalegua {
    private Grafo grafo ;
    private String[]bairros;
    private int[]rota;
    private float distRota;
    private float custoRota;
    private CompanhiaDeTaxi company;
    
    public Papalegua() {
       company = new CompanhiaDeTaxi(1.0);
       carregarDados();
    }
    
    private void carregarDados(){        
        try {
            grafo = new Grafo(50);
            bairros=new String[50]; 
            Workbook wb = Workbook.getWorkbook(new File("bairros.xls"));
            Sheet sh = wb.getSheet(0);
            for(int i=0;i<50;i++){
                Cell ce =sh.getCell(i+1,0);
                bairros[i]=ce.getContents();
            }
            for(int i=0;i<50;i++){
                for(int j=0;j<50;j++){
                    Cell ce = sh.getCell(i+1, j+1);
                    if(ce.getContents().length()>0){
                        String[] retorno = ce.getContents().trim().split(",");
                        String distancia = (retorno[0].split("="))[1];
                        String tempo = (retorno[1].split("="))[1];
                        getGrafo().addCaminho(i, j, Float.parseFloat(tempo), Float.parseFloat(distancia));
                    }
                }
            }
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(Papalegua.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Papalegua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private float[][] gerarMatrizTempo(){
        float [][] retorno = new float[50][50];
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                Caminho aux = grafo.getCaminho(i, j);
                if(aux!=null){
                    retorno[i][j]=aux.getTempo();
                }
                else{
                    retorno[i][j]=Float.MAX_VALUE;
                }
            }
        }
        return retorno;
    }
    
    private float[][] gerarMatrizDistancia(){
        float [][] retorno = new float[50][50];
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                Caminho aux = grafo.getCaminho(i, j);
                if(aux!=null){
                    retorno[i][j]=aux.getDistancia();
                }
                else{
                    retorno[i][j]=Float.MAX_VALUE;
                }
            }
        }
        return retorno;
    }
    
    public float caminhoMaisRapido(int v1,int v2){
        Djikstra calcula = new Djikstra(gerarMatrizTempo());
        calcula.calcularMelhorRota(v1, v2);
        rota = calcula.getCaminho();
        distRota = calculaDistanciaDoCaminho();
        custoRota = calculaCusto(distRota);
        return calcula.getPesoCaminho();
    }
    
    public float caminhoMaisCurto(int v1,int v2){
        Djikstra calcula = new Djikstra(gerarMatrizDistancia());
        calcula.calcularMelhorRota(v1, v2);
        rota = calcula.getCaminho();
        distRota = calcula.getPesoCaminho();
        return distRota;
    }
    
    /*Pega a ultima rota e calcula a distância da mesma*/
    
    public float calculaDistanciaDoCaminho(){
        int max = rota.length;
        int a, b;
        float distTotal = 0;
        
        for(int i = 0; i<max-1; i++){
            a = rota[i];
            b = rota[i+1];
            distTotal = distTotal + grafo.getCaminho(a, b).getDistancia();
        }
        
        return distTotal;
    }
    
    /*Recebe um nome e gera um novo taxista*/
    
    public void geraTaxista(String nome){
        company.getTaxistas().inserirFinal(new Taxista(nome, geraPlacaAleatoria()));
    }
    
    /*Randomiza uma placa de carro no formato brasileiro*/
    
    private String geraPlacaAleatoria(){
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", numeros = "0123456789";
        String placaDoCarro = new String();
        Random rn = new Random();
        int aux;
        char aux2;
        
        for(int i = 0; i<3; i++){
            aux = rn.nextInt(26);
            aux2 = alfabeto.charAt(aux);
            placaDoCarro = placaDoCarro + aux2;
        }
        placaDoCarro = placaDoCarro + "-";
        
        for(int i = 0; i<4; i++){
            aux = rn.nextInt(10);
            aux2 = numeros.charAt(aux);
            placaDoCarro = placaDoCarro + aux2;
        }
        
        return placaDoCarro;
    }
    
    /*Gera dez taxistas padronizados*/
    
    public void geraDezTaxistas(){
        
        company.getTaxistas().inserirFinal(new Taxista("Pedro", "OUU-5662"));
        company.getTaxistas().inserirFinal(new Taxista("Carlos", "JQK-1234"));
        company.getTaxistas().inserirFinal(new Taxista("Sandra", "JQK-5566"));
        company.getTaxistas().inserirFinal(new Taxista("Helio", "UVJ-2347"));
        company.getTaxistas().inserirFinal(new Taxista("Julio", "APQ-5623"));
        company.getTaxistas().inserirFinal(new Taxista("Angelo", "OUI-3820"));
        company.getTaxistas().inserirFinal(new Taxista("Maria", "UNV-7854"));
        company.getTaxistas().inserirFinal(new Taxista("Adeobaldo", "JPQ-9301"));
        company.getTaxistas().inserirFinal(new Taxista("Alfredo", "IJK-8012"));
        company.getTaxistas().inserirFinal(new Taxista("Hugo", "AOL-2900"));

    }
    
    /*Atribui a dez taxistas um "bairro atual" aleatorio. O retorno é composto de um
      array de 50 posições, cada uma representando um bairro, e, nestas posições, nos
      bairros em que não há taxista, haverá 'null'. Nos bairros em que haverão, existirá
      um objeto do tipo taxista, que representa o taxista ali presente.*/
    
     public Taxista[] posicionaTaxistasAleatoriamente(){
         
         if(company.getTaxistas().obterTamanho() < 10){ //Verifica se a lista de taxistas tem o numero minimo.
             geraDezTaxistas();                         //Se nao tiver, acrescenta o numero minimo
         }
        
         Iterador it = company.getTaxistas().iterador();
         Taxista vetorPosicao[] = new Taxista[50];
         int i = 0, j = 0, cont = 0;
         
         int[] arrayBairrosSorteados = geraArrayAleatorio(50, 10);  //Gera um array com os 10 bairros em que haverao taxistas
         int[] arrayTaxistasSorteados = geraArrayAleatorio(company.getTaxistas().obterTamanho(), 10);   //Gera um array com as posicoes dos 10 taxistas na lista
         
         for(i = 0; i < 50; i++){                       //Inicializa o vetor com nulo em todas as posicoes
             vetorPosicao[i] = null;
         }
         
         i = 0;
         
         while(cont < 10){  //Atribui cada taxista a uma posicao, que representa o bairro
             while(arrayTaxistasSorteados[i] == 0){
                 i++;
             }
             
             while(arrayBairrosSorteados[j] == 0){
                 j++;
             }
             vetorPosicao[j] = (Taxista)company.getTaxistas().recuperar(i);
             cont++;
         }
         
         return vetorPosicao;
    }
     
    /*Método que gera um array de 1 e 0 aleatório. Ele recebe o tamanho do array 
     desejado e a quantidade de '1' que deseja que apareça.*/
     
    private int[] geraArrayAleatorio(int tam, int qnt){ 
        int[] array = new int[tam];
        Random rn = new Random();
        int aux;
        
        for(int i = 0; i<tam; i++){ //Inicializa o array com 0
            array[i] = 0;
        }
        
        for(int i = 0; i<qnt; i++){ //Randomiza as posicoes dos '1'
            aux = rn.nextInt(tam);
            
            if(array[aux] == 0){    //Muda de 0 para 1
                array[aux] = 1;
            }
            else{                   //Caso a posicao ja seja 1, volta 1 etapa
                i--;
            }
        }
        
        return array;
    }
     
    /*Altera o custo por quilÃ´metro*/
     
    public void alteraCusto(double novoCusto){
        company.setValor1km(novoCusto);
    }
    
    /*Calcula o custo, em reais, dada uma certa distania*/
    
    public float calculaCusto(float distancia){
        return distancia*((float)company.getValor1km());
    }
    
    /*Guarda as informaÃ§Ãµes da viagem num arquivo na pasta do projeto*/
    
    public void guardaViagem(String cliente, Taxista taxista, float distancia, float tempo, String origem, String destino) throws IOException{
        float custoViagem = calculaCusto(distancia);
        BufferedWriter escritor = new BufferedWriter(new FileWriter("Viagens.txt"));
            
        escritor.append("Cliente: "+cliente+"Motorista: "+taxista.getNome()+"Origem: "+origem+"Destino: "+destino+
                        "DistÃ¢ncia percorrida"+distancia+"Tempo: "+tempo+"PreÃ§o: R$"+custoViagem+"Data: "+getDataHora()); //Escreve
        escritor.newLine(); //Pula a linha

        escritor.close(); //Fecha o BufferedWriter
    }
    
    
    
    
    /*Método que colhe a data do sistema e a concatena numa String*/
        
    private String getDataHora(){
        String data = "";
        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
        data = formata.format(new Date());							
        formata = new SimpleDateFormat("hh:mm");
        data = data + " - "+formata.format(new Date());
        return data;
    }
   
    /**
     * @return the grafo
     */
    public Grafo getGrafo() {
        return grafo;
    }

    /**
     * @return the bairros
     */
    public String[] getBairros() {
        return bairros;
    }

    /**
     * @return the rota
     */
    public int[] getRota() {
        return rota;
    }

    /**
     * @return the custo
     */

    public CompanhiaDeTaxi getCompany(){
        return company;
    }

    /**
     * @return the custoRota
     */
    public float getCustoRota() {
        return custoRota;
    }
    
}
