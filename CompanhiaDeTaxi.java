/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.model;

import grafos.util.ILista;
import grafos.util.Lista;

/**
 *
 * @author User
 */
public class CompanhiaDeTaxi {
    
    private ILista taxistas;
    double valor1km;
    
    public CompanhiaDeTaxi(double v1km){
        taxistas = new Lista();
        valor1km = v1km;
    }

    public ILista getTaxistas() {
        return taxistas;
    }

    public void setTaxistas(ILista taxistas) {
        this.taxistas = taxistas;
    }

    public double getValor1km() {
        return valor1km;
    }

    public void setValor1km(double valor1km) {
        this.valor1km = valor1km;
    }
    
    
    
}
