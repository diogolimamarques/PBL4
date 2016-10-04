package view;

import com.mxgraph.model.mxIGraphModel;
import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import controller.Papalegua;
import java.awt.event.ComponentAdapter;
import java.io.IOException;
import javax.swing.plaf.basic.BasicSliderUI;
import jxl.read.biff.BiffException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Principal extends javax.swing.JFrame{
    private JButton botaoIr;
    private mxGraphComponent graphComponent;
    private mxGraph graph;
    private Papalegua controller;
    private Object[] vertices;
    private Object[] arestas;
    private int vertice1;
    private int vertice2;
            
    public Principal(){
        vertices = new Object[50];
        controller = new Papalegua();
        vertice1=-1;
        vertice2=-1;
        iniComponents();  
        
    }    
    //busca um vertice no vetor que guarda os vertices e retorna o seu indice se não existir retorn -1
    public int buscaVertice(Object o){
        for(int i =0;i<50;i++){
            if(vertices[i]==o){
                return i;
            }
        }
        return -1;
    }
    
    public void iniComponents(){
        //configura tela
        setSize(800,600);
        setLocationRelativeTo(null);
        //cria componente de interface grafica do grafico
        graph = new mxGraph();
        graphComponent= new mxGraphComponent(graph);
         //cria botao
        botaoIr = new JButton("Ir");
        //define posição e altura e largura
        botaoIr.setBounds(20, 500, 80, 20);
        //adiciona botao a jframe
        getContentPane().add(botaoIr);
        
        graph.setCellsMovable(false);  //impede as celulas do grafo de serem movidadas em tempo de execução
        graph.setCellsResizable(false);//impede as celulas do grafo de terem seu tamanho alterado em tempo de execução
        graph.setEdgeLabelsMovable(false);//impede as arestas de serem movidas em tempo de execução
        //adiciona o grafo a jframe
        getContentPane().add(graphComponent);
        
        
        Object parent = graph.getDefaultParent();  
        //esse bloco adiciona os vertices na tela        
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                //calcula posicões na tela
                int x =50+(120*j);
                int y =20+(70*i);         
                //adiciona os vertices ao grafo e guarda a referencia para os vertices em um vetor
                vertices[i*10+j]=graph.insertVertex(parent, null,controller.getBairros()[i*10+j], x, y, 100,50);
            }
        }
        //cria um listener do mouse
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
               
                
            }
            @Override
            public void mousePressed(MouseEvent me) {
                
                
            }

            @Override
            //dispara ao clickar no graphComponent
            public void mouseReleased(MouseEvent me) {
                //obtem o vertice que está na posição do mouse
                Object aux = graphComponent.getCellAt(me.getX(), me.getY());
                if(vertice1==-1){
                    vertice1= buscaVertice(aux);
                }
                else if(vertice2==-1){
                    vertice2=buscaVertice(aux);
                }
                else{
                    vertice1=buscaVertice(aux);
                    vertice2=-1;
                }
             
            }   

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });   
        
        botaoIr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //se vertice1 e vertice2 "apontam" para algum dos vertices do grafo
                if(vertice1!=-1&vertice2!=-1){
                    //remove as arestas que estão desenhadas na tela
                    if(arestas!=null){
                        for(Object o:arestas){
                            graph.getModel().remove(o);
                        }
                    }
                    //obtem o tempo do percurso
                    float tempo = controller.caminhoMaisRapido(vertice1, vertice2);
                    //cria as arestas de acordo com o percurso
                    for(int i=0;i<controller.getRota().length-1;i++){
                        //cria vetor de arestas
                        arestas = new Object[controller.getRota().length-1];
                        //cria aresta entre os vertices do percurso
                        arestas[i]=graph.insertEdge(parent, null, null, vertices[controller.getRota()[i]] ,vertices[controller.getRota()[i+1]] );
                    }
                    //cria tela que mostra os dados
                    TelaResultado result = new TelaResultado(controller.getBairros()[vertice1], controller.getBairros()[vertice2], tempo, tempo);
                    //torna tela visivel
                    result.setVisible(true);
                }
            }
        });
                
                
        //graph.insertEdge(parent, null, "Hello",cell1 ,cell3 );
        
        
        
    }
    
    public static void main (String[] args) {
        Principal tela = new Principal();
        tela.setVisible(true);
    }
}
