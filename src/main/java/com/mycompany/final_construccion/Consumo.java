package com.mycompany.final_construccion;

public abstract class Consumo {
    private String nombre;
    private double precio;

    public Consumo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public abstract String concepto();
    public abstract double valor();
    
}
