/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_construccion;

/**
 *
 * @author TdeA
 */

public class Factura {
    
    private int id;
    private String concepto;
    private double valor;
    private String nombre;

    public Factura(String concepto, double valor, String nombre) {
        this.concepto = concepto;
        this.valor = valor;
        this.nombre = nombre;
    }

    public Factura(int id, String concepto, double valor, String nombre) {
        this.id = id;
        this.concepto = concepto;
        this.valor = valor;
        this.nombre = nombre;
    }
    
    

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return   "\n" 
                + "\nid=" + id 
                + "\nconcepto=" + concepto 
                + "\nvalor=" + valor 
                + "\nnombre=" + nombre ;
    }
    
    
    
    
}
