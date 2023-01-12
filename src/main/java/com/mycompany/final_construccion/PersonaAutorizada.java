/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_construccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author TdeA
 */
public class PersonaAutorizada {

    private String nombre;
    private Socio socio;
    private Factura[] facturas;
    private int cedula;
    private Club club;

    public PersonaAutorizada(String nombre, int cedula, Socio socio) {
        this.nombre = nombre;
        this.socio = socio;
        this.facturas = new Factura[20];
        this.cedula = cedula;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Factura[] getFacturas() {
        return facturas;
    }

    public void setFacturas(Factura[] facturas) {
        this.facturas = facturas;
    }

    public boolean facturasDisponibles() {
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void registrarConsumoAsociado(int opMenu, int dato, Socio socio) {
        FabricaDeConsumos fabrica = new FabricaDeConsumos();
        Consumo consumo = fabrica.creadorDePlatos(opMenu, dato);
        String concepto = consumo.concepto();
        double valor = consumo.valor();
        if (this.socio.getFondos() >= valor) {
            if (facturasDisponibles()) {
                boolean registro = false;
                for (int i = 0; i < facturas.length; i++) {
                    if (facturas[i] == null && !registro) {
                        Factura factura = new Factura(concepto, valor, this.nombre);
                        facturas[i] = factura;
                        registro = true;
                        JOptionPane.showMessageDialog(null, "se registro con exito consumo del Asociado ");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "el Asociado no puede tener mas facturas sin pagar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "el Socio no tiene fondos suficientes");
        }
    }

    public void pagarFacturaAsociado(Socio cedulaSocio, int cedulaAsociado, int posicion) {
        double fondos = socio.getFondos();

        String factura = mostrarFacturasAsociado();
        if (factura.equals("")) {
            JOptionPane.showMessageDialog(null, "el Asociado no tiene facturas pendientes de pago");
        } else {
            if (posicion >= 0 && posicion <= facturas.length && facturas[posicion] != null) {
                if (facturas[posicion].getValor() <= socio.getFondos()) {
                    fondos -= facturas[posicion].getValor();
                    socio.setFondos(fondos);
                    facturas[posicion] = null;
                    JOptionPane.showMessageDialog(null, "Pagada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "el socio no cuenta con fondos suficientes");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ingrese una posicion valida");
            }
        }

    }

    public String mostrarFacturasAsociado() {
        String factura = "";
        int message = 0;
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                factura += ("\nN° Factura: " + i + "\nConcepto: " + facturas[i].getConcepto() + "\nValor: " + facturas[i].getValor() + "\nNombre: " + facturas[i].getNombre());
                message++;
            }
        }
        if (message < 1) {
            JOptionPane.showMessageDialog(null, " no hay facturas para esta cedula");
        }

        return factura;
    }

    public int contarFacturasAsociado() {
        int contarFact = 0;
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                contarFact++;
            }
        }
        return contarFact;
    }

    public void eliminarFacturaAsociado(Socio cedulaSocio, int cedulaAsociado, int posicion) {

        String factura = mostrarFacturasAsociado();
        if (factura.equals("")) {
            JOptionPane.showMessageDialog(null, "el Asociado no tiene facturas pendientes de pago");
        } else {
            if (posicion >= 0 && posicion <= facturas.length && facturas[posicion] != null) {
                facturas[posicion] = null;
                JOptionPane.showMessageDialog(null, "Factura Eliminada con exito");
            } else {
                JOptionPane.showMessageDialog(null, "ingrese una posicion valida");
            }
        }
    }

    @Override
    public String toString() {
        return "Persona Autorizada" + "\nNombre Asociado: " + nombre + "\nCedula Asociado: " + cedula + "\nSocio: " + socio.getNombre().toString();
    }

    //*********************************************************************************************
    public String ImprimirFacturaAsociado(Socio cedulaSocio, int cedulaAsociado, int posicion) {
        String ImprimirFactura = "";
        double fondos = socio.getFondos();

        String factura = mostrarFacturasAsociado();
        if (factura.equals("")) {
            JOptionPane.showMessageDialog(null, "el Asociado no tiene facturas pendientes de pago");
        } else {
            //int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de factura a pagar \n" + factura));
            if (posicion >= 0 && posicion <= facturas.length && facturas[posicion] != null) {
                ImprimirFactura += "===================================="
                        + "\nNombre Asociado: " + facturas[posicion].getNombre()
                        + "\nCedula Asociado: " + this.cedula
                        + "\nconcepto: " + facturas[posicion].getConcepto()
                        + "\nvalor: " + facturas[posicion].getValor();

            } else {
                ImprimirFactura = "ingrese una posicion valida";
            }
        }
        return ImprimirFactura;

    }


}
