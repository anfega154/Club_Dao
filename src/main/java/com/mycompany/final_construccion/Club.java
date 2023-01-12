/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_construccion;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TdeA
 */
public class Club {

    private String nombre;
    private Socio[] sociosVip;
    private Socio[] sociosRegulares;
    private ClubDao clubd;

    public Club(String nombre) {
        this.clubd = new ClubDao();
        this.nombre = nombre;
        this.sociosVip = new Socio[3];
        this.sociosRegulares = new Socio[10];
        System.out.println("se a creado el club");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Socio[] getSociosVip() {
        return sociosVip;
    }

    public void setSociosVip(Socio[] sociosVip) {
        this.sociosVip = sociosVip;
    }

    public Socio[] getSociosRegulares() {
        return sociosRegulares;
    }

    public void setSociosRegulares(Socio[] sociosRegulares) {
        this.sociosRegulares = sociosRegulares;
    }

    public Socio buscarCedula(int cedula) {

        /*Socio encontrado = null;
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula() == cedula) {
                encontrado = sociosVip[i];
            }
        }
        if (encontrado == null) {
            for (int i = 0; i < sociosRegulares.length; i++) {
                if (sociosRegulares[i] != null && sociosRegulares[i].getCedula() == cedula) {
                    encontrado = sociosRegulares[i];
                }
            }
        }*/
        ClubDao clubDao = new ClubDao();
        try {
            Map<String, Object> resultado = clubDao.buscarSocio(cedula);
            if (!resultado.isEmpty()) {

                Socio socio1 = new Socio(Integer.parseInt(resultado.get("cedula").toString()), resultado.get("tipoDeSuscripcion").toString(), Double.parseDouble(resultado.get("fondos").toString()), resultado.get("nombre").toString());
                return socio1;
                //return socio1;
            }
        } catch (SQLException e) {
            System.out.println("Error en buscar cedula Mapa");

        }

        return null;
    }

    public boolean sociosVipNulo() {
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] == null) {
                return true;
            }
        }
        return false;
    }

    public boolean sociosRegularNulo() {
        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void inscribirSocio(int cedula, String tipoDeSuscripcion, String nombre) {
        if (buscarCedula(cedula) == null) {
            boolean registro = false;
            //int tipo = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese: \n 1. para Vip \n 2. para regular"));
            if (tipoDeSuscripcion.equals("VIP") && sociosVipNulo()) {
                //String nombre = JOptionPane.showInputDialog(null, "ingrese su nombre");
                Socio socio = new Socio(cedula, "Vip", 100000, nombre);
                for (int i = 0; i < sociosVip.length; i++) {
                    if (sociosVip[i] == null && !registro) {
                        sociosVip[i] = socio;

                        registro = true;
                        JOptionPane.showMessageDialog(null, "Se Registro el Socio correctamente");
                    }
                }
            }
            if (tipoDeSuscripcion.equals("REGULAR") && sociosRegularNulo() && !registro) {
                //String nombre = JOptionPane.showInputDialog(null, "ingrese su nombre");
                Socio socio = new Socio(cedula, "Regular", 50000, nombre);
                for (int i = 0; i < sociosRegulares.length; i++) {
                    if (sociosRegulares[i] == null && !registro) {
                        sociosRegulares[i] = socio;
                        registro = true;
                        JOptionPane.showMessageDialog(null, "Se Registro el Socio correctamente");
                    }
                }
            }
            if (tipoDeSuscripcion.equals("VIP") && !sociosVipNulo() && sociosRegularNulo() && !registro) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo VIP, por favor hacer el registro regular");
            }
            if (tipoDeSuscripcion.equals("REGULAR") && !sociosRegularNulo() && sociosVipNulo() && !registro) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo Regular \n ingrese 1 para hacer el registro Vip");
            }
            if (!sociosRegularNulo() && !sociosVipNulo()) {
                JOptionPane.showMessageDialog(null, "no hay cupos disponibles");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ya existe alguien con esa cedula");
        }
    }

    public void registrarConsumoSocio(int cedula, int opMenu, int dato) {
        //int cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese su cedula"));
        Socio socio = buscarCedula(cedula);

        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            socio.registrarConsumo(opMenu, dato);
        }
    }

    public void registrarConsumoAsociado(int cedulaSocio, int cedulaAsociado, int opMenu, int dato) {

        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                asociado.registrarConsumoAsociado(opMenu, dato, socio);
                //JOptionPane.showInputDialog(null,"Se a registrado consumo");
                //System.out.println(asociado.mostrarFacturasAsociado().toString());
            } else {
                JOptionPane.showInputDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showInputDialog(null, "no Existe un socio registrado con esa Cedula");
        }

    }

    public String BuscarfacturaSocio(int cedula) {

        Socio socio = buscarCedula(cedula);

        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
            return "no hay ningun socio registrado con la cedula";
        } else {
            return socio.mostrarFacturas();

        }

    }

    public String buscarfacturaAsociado(int cedulaSocio, int cedulaAsociado) {
        String encontrado = "no existe el socio";
        Socio socio = buscarCedula(cedulaSocio);
        PersonaAutorizada personaAutorizada = socio.buscarCedula(cedulaAsociado);

        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                encontrado = asociado.mostrarFacturasAsociado();
                //JOptionPane.showInputDialog(null,"Se a registrado consumo");
            } else {
                JOptionPane.showInputDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showInputDialog(null, "no Existe un socio registrado con esa Cedula");
        }

        return encontrado;

    }

    public void pagarFactura(int cedula, int posicion) {
        //int cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese su cedula"));
        Socio socio = buscarCedula(cedula);
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            this.imprimirFactura(cedula, posicion);
            socio.pagarFacturaSocio(cedula, posicion);

        }
    }

    public void pagarFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {

        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                asociado.pagarFacturaAsociado(socio, cedulaAsociado, posicion);

            } else {
                JOptionPane.showMessageDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no Existe un socio registrado con esa Cedula");
        }
    }

    public void eliminarFacturaSocio(int cedula, int posicion) {
        //int cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese su cedula"));
        Socio socio = buscarCedula(cedula);
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            socio.eliminarFacturaSocio(cedula, posicion);

        }
    }

    public void eliminarFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {

        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                asociado.eliminarFacturaAsociado(socio, cedulaAsociado, posicion);

            } else {
                JOptionPane.showMessageDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no Existe un socio registrado con esa Cedula");
        }
    }

    @Override
    public String toString() {
        return "Club: " + nombre;
    }

    public String mostrarCedula(int cedula) throws SQLException {

        String encontrado = "no existe el socio";
        Socio socio = clubd.BuscarSocio1(cedula);
//        Socio socio = buscarCedula(cedula);
        if (socio != null) {
            encontrado = socio.toString();
        }
        return encontrado;
    }

    public void aumentarRecursos(int cedula, double CantidadRecursos) {
        // Variable para idenditicar el Socio
        Socio socio = buscarCedula(cedula);
        //Variable donde se almacenara los nuevos fondos a asignar al Socio
        double fondosNew = 0;

        // condicion para validar si el socio si existe
        if (socio != null) {
            // Variable para traer los Fondos Actuales del Socio    
            double fondosActuales = socio.getFondos();
            // Variable para traer el tipo de suscripcion actual del socio 
            String suscripcion = socio.getTipoDeSuscripcion();

            //Operacion para Calcular en cuanto quedarian los fondos, teniendo encuenta los actuales mas los que se quieren ingresar 
            fondosNew = fondosActuales + CantidadRecursos;

            switch (suscripcion) {
                case "Vip":
                    if (fondosNew <= 5000000) {
                        socio.setFondos(fondosNew);
                        JOptionPane.showMessageDialog(null, "Se a cargado correctamente los Fondos al Socio " + socio.getNombre().toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede cargar por que excedería el tope autorizado para el Socio ");
                    }
                    break;
                case "Regular":
                    if (fondosNew <= 1000000) {
                        socio.setFondos(fondosNew);
                        JOptionPane.showMessageDialog(null, "Se a cargado correctamente los Fondos al Socio " + socio.getNombre().toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede cargar por que excedería el tope autorizado por Socio ");

                    }
                    break;
            }

        } else {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        }

    }

    public String mostrarCedulaAutorizada(int cedulaPersonaAutorizada, int cedulaSocio) {

        String encontrado = "no existe el socio";

        Socio socio = buscarCedula(cedulaSocio);
        PersonaAutorizada personaAutorizada = socio.buscarCedula(cedulaPersonaAutorizada);
        if (personaAutorizada != null) {
            encontrado = personaAutorizada.toString();
        }
        return encontrado;
    }

    public void EliminarSocio(int cedula) {

        String encontrado = null;
        Socio socio = buscarCedula(cedula);
        int finCiclo = 0;

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula() == cedula) {

                encontrado = "encontrado";
                JOptionPane.showMessageDialog(null, "Los Socios VIP no se pueden Eliminar");
            }
        }

        if (encontrado == null) {

            for (int i = 0; i < sociosRegulares.length; i++) {
                if (sociosRegulares[i] != null && sociosRegulares[i].getCedula() == cedula) {
                    if (sociosRegulares[i].buscarFacturas().equals("") && sociosRegulares[i].contarAsociados() <= 1) {
                        if (sociosRegulares[i].contarAsociados() == 1) {
                            PersonaAutorizada personaAutorizada = socio.buscarCedula(socio.traerAsociado());
                            //int cAutorizada = personaAutorizada.getCedula();
                            if (personaAutorizada.contarFacturasAsociado() == 0) {
                                sociosRegulares[i] = null;
                                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Socio  y el Asociado");
                                return;
                                //finCiclo = 1;

                            } else {
                                JOptionPane.showMessageDialog(null, "El asociado aun tiene facturas pendientes");
                            }
                        } else {
                            sociosRegulares[i] = null;
                            JOptionPane.showMessageDialog(null, "Se ha Eliminado el Socio");
                            return;
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar");
                    }
                }

            }

        }

    }

    public void eliminarAsociado(int socio, int cedulaAsociado) {

        Socio socioP = buscarCedula(socio);

        if (socioP != null) {
            PersonaAutorizada personaAutorizada = socioP.buscarCedula(cedulaAsociado);
            if (personaAutorizada != null && personaAutorizada.mostrarFacturasAsociado().equals("")) {
                socioP.eliminarPersonaAutorizada(cedulaAsociado);

            } else {
                JOptionPane.showMessageDialog(null, "No se puede eliminar ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no Existe un socio registrado con esa Cedula");
        }

    }

    public void inscribirPersonaAutorizada(String nombreAut, int cedulaAutorizada, int cedulaSocio) {

        if (buscarCedula(cedulaAutorizada) == null) {
            Socio socio = buscarCedula(cedulaSocio);
            if (socio == null) {
                JOptionPane.showMessageDialog(null, "no existe un socio con esa cedula");
            } else {
                socio.registrarPersonaAutorizada(nombreAut, cedulaAutorizada, cedulaSocio);

                System.out.println(socio.traerAsociado());

            }
        } else {
            JOptionPane.showMessageDialog(null, "esa cedula pertenece al socio " + buscarCedula(cedulaAutorizada).getNombre());
        }
    }

    //**************************************************************************************************
    public void CargarPersonaAutorizada(String nombreAut, int cedulaAutorizada, int cedulaSocio) throws IllegalAccessException {

        if (buscarCedula(cedulaAutorizada) == null) {
            Socio socio = buscarCedula(cedulaSocio);
            if (socio == null) {
                throw new IllegalAccessException("no existe un socio con esa cedula");
            } else {
                //socio.registrarPersonaAutorizada(nombreAut,cedulaAutorizada,cedulaSocio );
                socio.CargarPersonaAutorizada(nombreAut, cedulaAutorizada, cedulaSocio);
                System.out.println(socio.traerAsociado());
            }
        } else {
            throw new IllegalAccessException("esa cedula pertenece al socio " + buscarCedula(cedulaAutorizada).getNombre());
        }
    }

    public String EncontrarFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {
        String Encontrado = "";
        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                Encontrado = asociado.ImprimirFacturaAsociado(socio, cedulaAsociado, posicion);

            } else {
                Encontrado = "no existe un Asociado r"
                        + "egistrado con esa Cedula";
            }
        } else {
            Encontrado = "no Existe un socio registrado con esa Cedula";
        }

        return Encontrado;
    }

    public String EncontrarfacturaSocio(int cedula, int posicion) {

        Socio socio = buscarCedula(cedula);
        if (socio == null) {
            return "no hay ningun socio registrado con la cedula";
        } else {

            return socio.EncontrarFactura(cedula, posicion);

        }

    }

    public void imprimirFactura(int cedula, int fac) {
        try {
            String rutaArchivo = "./Facturas";
            String contenido = this.EncontrarfacturaSocio(cedula, fac);
            File factura = new File(rutaArchivo);
            if (factura.mkdir()) {
                System.out.println("Carpeta Creada");
            } else {
                System.out.println("La carpeta ya existe");
            }
            rutaArchivo += "/nombreArchivo.txt";
            factura = new File(rutaArchivo);
            if (!factura.exists()) {
                factura.createNewFile();
            }
            FileWriter fw = new FileWriter(factura);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimirFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {
        try {
            String rutaArchivo = "./FacturasAsociados";
            String contenido = this.EncontrarFacturaAsociado(cedulaSocio, cedulaAsociado, posicion);
            File factura = new File(rutaArchivo);
            if (factura.mkdir()) {
                System.out.println("Carpeta Creada");
            } else {
                System.out.println("La carpeta ya existe");
            }
            rutaArchivo += "/Factura_Asociado.txt";
            factura = new File(rutaArchivo);
            if (!factura.exists()) {
                factura.createNewFile();
            }
            FileWriter fw = new FileWriter(factura);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //**************************************Base de Datos*****************************************
    public void inscribirSocioBD(int cedula, String tipoDeSuscripcion, String nombre) throws SQLException {
//        ClubDao clubd = new ClubDao();
//        String cc = String.valueOf(cedula);
        if (!clubd.BuscarSocio(cedula)) {
            boolean registro = false;
            if (tipoDeSuscripcion.equals("VIP") && clubd.DispoSociosVIP()) {
                clubd.inscribirSocioBDVIP(cedula, nombre);
                registro = true;
            }
            if (tipoDeSuscripcion.equals("REGULAR") && clubd.DispoSociosReg()) {
                clubd.inscribirSocioBDREGULAR(cedula, nombre);
                registro = true;
            }
            if (tipoDeSuscripcion.equals("VIP") && !registro && !clubd.DispoSociosVIP()) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo VIP");
//               throw new IllegalAccessException("lastimosamente no hay cupo VIP");
            }
            if (tipoDeSuscripcion.equals("REGULAR") && !clubd.DispoSociosReg() && !registro) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo Regular");
            }
            if (!clubd.DispoSociosVIP() && !clubd.DispoSociosReg()) {
                JOptionPane.showMessageDialog(null, "no hay cupos disponibles");
            }

        } else {
            JOptionPane.showMessageDialog(null, "ya existe alguien con esa cedula");
        }

    }

    public void RegistrarConsumoBD(int cedula, int opMenu, int dato) throws SQLException {
        //ClubDao clubd = new ClubDao();
//        Socio socio = buscarCedula(cedula);
        Socio socio = clubd.BuscarSocio1(cedula);
//        if (clubd.BuscarSocio(cedula)) {
        if (socio != null) {
            if (clubd.DispoFacturasSocio(cedula) < 20) {
                boolean registro = false;
                FabricaDeConsumos fabrica = new FabricaDeConsumos();
                Consumo consumo = fabrica.creadorDePlatos(opMenu, dato);
                String concepto = consumo.concepto();
                double valor = consumo.valor();
                String valorConsumo = String.valueOf(valor);
                if (socio.getFondos() >= valor) {

                    clubd.RegistrarConsumoBD(concepto, valorConsumo, socio.getNombre(), socio.getCedula());

                    registro = true;
                    JOptionPane.showMessageDialog(null, "se registro con exito ");

                } else {
                    JOptionPane.showMessageDialog(null, "No tiene fondos suficientes");
                }
            } else {
                System.out.println(clubd.DispoFacturasSocio(cedula));
                JOptionPane.showMessageDialog(null, "el socio no puede tener mas facturas sin pagar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        }
//        }else{
//            JOptionPane.showMessageDialog(null, "no encontro en el metodo booleano clubdao buscar socio");
//        }

    }

    public void aumentarRecursosSocioBD(int cedula, double CantidadRecursos) throws SQLException {
        Socio socio = null;
        socio = clubd.BuscarSocio1(cedula);
        if (socio != null) {
            double fondosNew = 0;
            boolean registro = false;
            fondosNew = socio.getFondos() + CantidadRecursos;
            if (socio.getTipoDeSuscripcion().equals("VIP") && fondosNew <= 5000000 && !registro) {
                clubd.aumentarFondosSocioBD(cedula, fondosNew);
                registro = true;
            } else if (socio.getTipoDeSuscripcion().equals("REGULAR") && fondosNew <= 1000000 && !registro) {
                clubd.aumentarFondosSocioBD(cedula, fondosNew);
                registro = true;
            } else {
                JOptionPane.showMessageDialog(null, "Supera tope autorizado para el tipo de suscripcion");
            }
        } else {
            System.out.println("no se creo el socio en clubd.BuscarSocio1 ");
        }
    }

    public void EliminarSocioBD(int cedula) throws SQLException {
        Socio socio = null;
        socio = clubd.BuscarSocio1(cedula);

        if (socio != null) {
            if (socio.getTipoDeSuscripcion().equalsIgnoreCase("VIP")) {
                JOptionPane.showMessageDialog(null, "los socios VIP no se pueden eliminar ");
            } else {

                if (clubd.DispoFacturasSocio(cedula) == 0) {
                    if (clubd.cantidadAutorizadosBD(cedula) == 0) {
                        clubd.EliminarSocioBD(cedula);
                        System.out.println(clubd.DispoFacturasSocio(cedula));
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "el Socio tiene Facturas pendientes por pagar");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "El Socio no Existe");
        }
    }

    public String clubMostrarfacturaSocioBD(int cedula) throws SQLException {
        Socio socio = clubd.BuscarSocio1(cedula);
        String mFactura = "";

        if (socio != null) {

            mFactura = socio.MostrarfacturaSocioBD(cedula);

        } else {
            return "no hay ningun socio registrado con la cedula";
        }
        return mFactura;

    }

    public void EliminarFacturaSocioBD(int cedula, int posicion) throws SQLException {

        Socio socio = clubd.BuscarSocio1(cedula);
        Factura factura = socio.BuscarFacturaSocioBD(cedula, posicion);

        if (factura == null) {
            JOptionPane.showMessageDialog(null, "Factura no Existe");
        } else {
            clubd.EliminarFacturaSocio(cedula, posicion);
            System.out.println("Factura Eliminada\n" + factura.toString());
        }

    }

//    public void imprimirFacturaSocioBD(int cedula, int fac) throws SQLException {
//        Socio socio = clubd.BuscarSocio1(cedula);
//        Factura fact = socio.BuscarFacturaSocioBD(cedula, fac);
//
//        try {
//            String rutaArchivo = "./Facturas";
//            String contenido = fact.toString();
//            File factura = new File(rutaArchivo);
//            if (factura.mkdir()) {
//                System.out.println("Carpeta Creada");
//            } else {
//                System.out.println("La carpeta ya existe");
//            }
//            rutaArchivo += "/nombreArchivo.txt";
//            factura = new File(rutaArchivo);
//            if (!factura.exists()) {
//                factura.createNewFile();
//            }
//            FileWriter fw = new FileWriter(factura);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(contenido);
//            bw.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void pagarFacturaSocioBD(int cedula, int posicion)throws SQLException {
       
        Socio socio = clubd.BuscarSocio1(cedula);
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            socio.pagarFacturaSocioBD(cedula, posicion);
        }
    }

}
