/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
/**
 *
 * @author user
 */
public class test extends javax.swing.JFrame{
    private mxGraphComponent graphComponent;
    private mxGraph graph;
    
    public test(){
        graph = new mxGraph();
        graphComponent= new mxGraphComponent(graph);
        setSize(800,600);
        setLocationRelativeTo(null);
        graphComponent = new mxGraphComponent(graph); //cria o componente de interface grafica
        graphComponent.setPreferredSize(new Dimension(800, 800));
        getContentPane().add(graphComponent); //adiciona o componente ao container 
       
    }
    
    public static void main(String args[]){
        test novo = new test();
        novo.setVisible(true);
    }
}
