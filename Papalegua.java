/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import util.*;
/**
 *
 * @author user
 */
import jxl.Workbook;
import jxl.read.biff.BiffException;
import view.Principal;
import model.*;

public class Papalegua {
    private Grafo grafo ;
    private int qtdBairros;
    private int qtdCaminhos;
    private Principal tela;
    private String[]bairros;
    private int[]rota;
    private float custo;
    
    public Papalegua() {
       custo = 1;
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
    public float caminhoMaisRapido(int v1,int v2){
        Djikstra calcula = new Djikstra(gerarMatrizTempo());
        calcula.calcularMelhorRota(v1, v2);
        rota = calcula.getCaminho();
        return calcula.getPesoCaminho();
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
    public float getCusto() {
        return custo;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(float custo) {
        this.custo = custo;
    }
    
    
}
