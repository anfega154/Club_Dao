package com.mycompany.final_construccion;

public abstract class Plato extends Consumo {
    private String ingredientes;

    public Plato(String ingredientes, String nombre, double precio) {
        super(nombre, precio);
        this.ingredientes = ingredientes;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    
}
