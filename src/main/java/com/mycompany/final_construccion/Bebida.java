/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_construccion;

/**
 *
 * @author NMEJIA
 */
public abstract class Bebida extends Consumo{
    private double tamanio;

    public Bebida( String nombre, double precio, double tamanio) {
        super(nombre, precio);
        this.tamanio = tamanio;
    }

    public double getTamaño() {
        return tamanio;
    }

    public void setTamaño(double tamaño) {
        this.tamanio = tamaño;
    }
        

    
}
