/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.model;

/**
 *
 * @author User
 */
public class Taxista {
    
    private String nome;
    private String placaDoCarro;
    private int id;
    private static int idGeral = 0;
    private String bairroAtual;
    
    public Taxista(String n, String p){
        nome = n;
        placaDoCarro = p;
        id = idGeral++;
        bairroAtual = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlacaDoCarro() {
        return placaDoCarro;
    }

    public void setPlacaDoCarro(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBairroAtual() {
        return bairroAtual;
    }

    public void setBairroAtual(String bairroAtual) {
        this.bairroAtual = bairroAtual;
    }
    
}
