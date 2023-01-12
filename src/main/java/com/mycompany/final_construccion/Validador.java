/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.final_construccion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author nmejia
 */
public class Validador {

    private Club club;
    private Socio socio;

    public Validador() {
        if (club == null) {
            this.club = new Club("NO TENEMOS SALVACION");
            System.out.println("se a creado la capa de validacion");
        }

    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String validarMostrarSocio(String cedula) {
        try {
            int cc = Integer.parseInt(cedula);
            return club.mostrarCedula(cc);
        } catch (Exception e) {
            return "Ingrese un numero de Cedula Valido";
        }
    }

    public void registrarSocio(String cedula, String tipoDeSuscripcion, String nombre) {
        int cc = 0;
        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "El nombre del socio no puede ser un espacio Vacio");
            return;
        }

        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }
        try {
            club.inscribirSocio(cc, tipoDeSuscripcion, nombre);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            return;
        }

    }

    public void registrarPersonaAutorizada(String nombre, String cedulaAutorizada, String cedulaSocio) {
        int ccAutorizada = 0;
        int ccSocio = 0;

        if (cedulaSocio.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio vacio");
            return;
        }
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "El nombre de la persona Autorizada no puede ser un espacio vacio");
            return;
        }
        if (cedulaAutorizada.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula de la persona Autorizada no puede ser un espacio vacio");
            return;
        }

        try {
            ccAutorizada = Integer.parseInt(cedulaAutorizada);
            ccSocio = Integer.parseInt(cedulaSocio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al convertir cedula de la persona autorizada o el socio a entero");
            return;
        }
        try {
            club.inscribirPersonaAutorizada(nombre, ccAutorizada, ccSocio);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la persona autrizada");
        }

    }

    public String MostrarPersonaAutorizada(String cedulaAutorizada, String cedulaSocio) {

        if (cedulaSocio.equals("")) {
            //JOptionPane.showMessageDialog(null,"El numero de cedula no puede ser un espacio vacio");
            return "Ingrese un numero de cedula del socio Valido";
        }
        if (cedulaAutorizada.equals("")) {
            //JOptionPane.showMessageDialog(null,"El nombre de la persona Autorizada no puede ser un espacio vacio");
            return "Ingrese un numero de cedula de la persona autorizada Valido";
        }

        try {
            int cc1 = Integer.parseInt(cedulaAutorizada);
            int cc2 = Integer.parseInt(cedulaSocio);
            //System.out.println("Cedula Socio"+ cc2);
            return club.mostrarCedulaAutorizada(cc1, cc2);
        } catch (Exception e) {
            return "Ingrese un numero de Cedula Persona Autorizada Valido";
        }
    }

    public void aumentarRecursos(String cedula, String newFondos) throws SQLException {

        double fondos = 0;
        int cedulaSocio = 0;

        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }
        if (newFondos.equals("")) {
            JOptionPane.showMessageDialog(null, "Los Fondos a ingresar no puede ser un espacio Vacio");
            return;

        }

        try {
            cedulaSocio = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }

        try {
            fondos = Double.parseDouble(newFondos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor de Fondos Valido");
            return;
        }
        try {
            //club.aumentarRecursos(cedulaSocio, fondos);
            club.aumentarRecursosSocioBD(cedulaSocio, fondos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado");
            System.out.println(e.toString());
            return;
        }

    }

    public void inscribirFacturaSocio(String cedula, String TipoPlato, String OpcionMenu) {

        int cc = 0;
        double val = 0;
        int dato = 0;
        int opcionMenu = 0;

        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }

        if (!TipoPlato.equals("Caliente") && !TipoPlato.equals("Alcoholica") && !TipoPlato.equals("NoAlcoholica")) {
            switch (OpcionMenu) {

                case "Empanadax2": {
                    dato = 2;
                    break;
                }
                case "Empanadax10": {
                    dato = 1;
                    break;
                }
                case "TresLeches": {
                    dato = 1;
                    break;
                }
                case "Limon": {
                    dato = 2;
                    break;
                }
                case "Ensalada Cesar": {
                    dato = 1;
                    break;
                }
                case "Menú Italiano": {
                    dato = 2;
                    break;
                }
                case "Margarita": {
                    dato = 1;
                    break;
                }
                case "Punta de Anca": {
                    dato = 1;
                    break;
                }
                case "Bandeja Paisa": {
                    dato = 2;
                    break;
                }
                case "Picada Mixta": {
                    dato = 3;
                    break;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Debe selecionar una opcion para del plato");

            }
        }

        if (TipoPlato.equals("Entrada")) {
            opcionMenu = 2;
        } else if (TipoPlato.equals("Postre")) {
            opcionMenu = 1;
        } else if (TipoPlato.equals("PlatoVegano")) {
            opcionMenu = 3;
        } else if (TipoPlato.equals("PlatoMixto")) {
            opcionMenu = 5;
        } else if (TipoPlato.equals("Caliente")) {
            dato = Integer.parseInt(OpcionMenu) + 1;
            opcionMenu = 7;
        } else if (TipoPlato.equals("Alcoholica")) {
            dato = (Integer.parseInt(OpcionMenu)) + 1;
            opcionMenu = 4;
        } else if (TipoPlato.equals("NoAlcoholica")) {
            dato = Integer.parseInt(OpcionMenu) + 1;
            opcionMenu = 6;
        }

        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }

        try {
            //club.registrarConsumoSocio(cc, opcionMenu, dato);
            club.RegistrarConsumoBD(cc, opcionMenu, dato);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado. en el validador");
            System.out.println(e.toString());
            System.out.println(e.getLocalizedMessage());
            return;
        }

    }

    public void eliminarfacturaS(String cedula, String posicion) {
        int cc = 0;
        int num = 0;
        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }
        if (posicion.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de factura no puede ser un espacio vacio");
            return;
        }
        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }
        try {
            num = Integer.parseInt(posicion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de factura valido");
            return;
        }
        try {

            club.EliminarFacturaSocioBD(cc, num);
            //club.eliminarFacturaSocio(cc, num);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            return;
        }
    }

    public void pagarfacturaS(String cedula, String posicion) {
        int cc = 0;
        int num = 0;
        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }
        if (posicion.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de factura no puede ser un espacio vacio");
            return;
        }
        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }
        try {
            num = Integer.parseInt(posicion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de factura valido");
            return;
        }
        try {
            club.pagarFacturaSocioBD(cc, num);
            //club.pagarFactura(cc, num);
            //club.imprimirFactura(num);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            return;
        }
    }

    public String BuscarfacturaSocio(String cedula) {
        int cc = 0;
        if (cedula.equals("")) {
            //JOptionPane.showMessageDialog(null,"El numero de cedula no puede ser un espacio Vacio");
            return "El numero de cedula no puede ser un espacio Vacio";
        }
        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"Ingrese un numero de cedula valido");
            return "Ingrese un numero de cedula valido";
        }
        try {
            return club.BuscarfacturaSocio(cc);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            return "";
        }

    }

    public void registrarConsumoAsociado(String cedulaSocio, String cedulaAsociado, String TipoPlato, String OpcionMenu) {
        int socio = 0;
        int asociado = 0;

        int dato = 0;
        int opcionMenu = 0;
        if (!TipoPlato.equals("Caliente") && !TipoPlato.equals("Alcoholica") && !TipoPlato.equals("NoAlcoholica")) {
            switch (OpcionMenu) {

                case "Empanadax2": {
                    dato = 2;
                    break;
                }
                case "Empanadax10": {
                    dato = 1;
                    break;
                }
                case "TresLeches": {
                    dato = 1;
                    break;
                }
                case "Limon": {
                    dato = 2;
                    break;
                }
                case "Ensalada Cesar": {
                    dato = 1;
                    break;
                }
                case "Menú Italiano": {
                    dato = 2;
                    break;
                }
                case "Margarita": {
                    dato = 1;
                    break;
                }
                case "Mojito": {
                    dato = 2;
                    break;
                }
                case "Piña Colada": {
                    dato = 3;
                    break;
                }
                case "Jugo del Día": {
                    dato = 1;
                    break;
                }
                case "Soda": {
                    dato = 2;
                    break;
                }
                case "Gaseosa": {
                    dato = 3;
                    break;
                }
                case "Punta de Anca": {
                    dato = 1;
                    break;
                }
                case "Bandeja Paisa": {
                    dato = 2;
                    break;
                }
                case "Picada Mixta": {
                    dato = 3;
                    break;
                }

                default:
                    JOptionPane.showMessageDialog(null, "Debe selecionar una opcion para del plato");

            }
        }

        if (TipoPlato.equals("Entrada")) {
            opcionMenu = 2;
        } else if (TipoPlato.equals("Postre")) {
            opcionMenu = 1;
        } else if (TipoPlato.equals("PlatoVegano")) {
            opcionMenu = 3;
        } else if (TipoPlato.equals("Cócteles")) {
            opcionMenu = 4;
        } else if (TipoPlato.equals("PlatoMixto")) {
            opcionMenu = 5;
        } else if (TipoPlato.equals("Caliente")) {
            dato = Integer.parseInt(OpcionMenu) + 1;
            opcionMenu = 7;
        } else if (TipoPlato.equals("Alcoholica")) {
            dato = (Integer.parseInt(OpcionMenu)) + 1;
            opcionMenu = 4;
        } else if (TipoPlato.equals("NoAlcoholica")) {
            dato = Integer.parseInt(OpcionMenu) + 1;
            opcionMenu = 6;
        }

        if (cedulaAsociado.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula del Asociado no puede ser un espacio Vacio");
            return;
        }
        if (cedulaSocio.equals("")) {
            JOptionPane.showMessageDialog(null, "la cedula del Socio no puede ser un espacio Vacio");
            return;

        }

        try {
            socio = Integer.parseInt(cedulaSocio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }

        try {
            asociado = Integer.parseInt(cedulaAsociado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }

        try {
            club.registrarConsumoAsociado(socio, asociado, opcionMenu, dato);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al registrar consumo de Asociado");
            return;
        }

    }

    public String mostrarConsumoAsociado(String cedulaSocio, String cedulaAsociado) {

        if (cedulaSocio.equals("")) {
            //JOptionPane.showMessageDialog(null,"El numero de cedula del Asociado no puede ser un espacio Vacio");
            return "la cedula del Socio no puede ser un espacio Vacio";
        }
        if (cedulaAsociado.equals("")) {
            //JOptionPane.showMessageDialog(null,"la cedula del Socio no puede ser un espacio Vacio");
            return "El numero de cedula del Asociado no puede ser un espacio Vacio";

        }
        try {
            int cc1 = Integer.parseInt(cedulaSocio);

        } catch (Exception e) {
            return "Ingrese un numero de cedula valido del Socio ";
        }

        try {
            int cc2 = Integer.parseInt(cedulaAsociado);

        } catch (Exception e) {
            return "Ingrese un numero de cedula valido de la persona Autorizada";
        }

        try {
            int cc1 = Integer.parseInt(cedulaSocio);
            int cc2 = Integer.parseInt(cedulaAsociado);

            return club.buscarfacturaAsociado(cc1, cc2);
        } catch (Exception e) {

            //JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            return e.getLocalizedMessage();
        }
    }

    public void pagarConsumoAsociados(String socio, String asociado, String posicion) {
        int cedulaSocio = 0;
        int cedulaAsociado = 0;
        int nFactura = 0;

        if (asociado.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula del Asociado no puede ser un espacio Vacio");
            return;
        }
        if (socio.equals("")) {
            JOptionPane.showMessageDialog(null, "la cedula del Socio no puede ser un espacio Vacio");
            return;

        }
        if (posicion.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de factura no puede estar vacío");
            return;

        }

        try {
            cedulaSocio = Integer.parseInt(socio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula del Socio valido");
            return;
        }

        try {
            cedulaAsociado = Integer.parseInt(asociado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula del Asociado valido");
            return;
        }

        try {
            nFactura = Integer.parseInt(posicion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de Factura Valido");
            return;
        }

        try {
            club.imprimirFacturaAsociado(cedulaSocio, cedulaAsociado, nFactura);
            club.pagarFacturaAsociado(cedulaSocio, cedulaAsociado, nFactura);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al registrar consumo de Asociado");
            return;
        }

    }

    public void eliminarFacturaAsociado(String cedulaSocio, String cedulaAsociado, String posicion) {
        int socio = 0;
        int asociado = 0;
        int nFactura = 0;

        if (cedulaAsociado.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula del Asociado no puede ser un espacio Vacio");
            return;
        }

        if (cedulaSocio.equals("")) {
            JOptionPane.showMessageDialog(null, "la cedula del Socio no puede ser un espacio Vacio");
            return;

        }

        if (posicion.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de factura no puede estar vacío");
            return;

        }

        try {

            socio = Integer.parseInt(cedulaSocio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula del Socio valido");
            return;
        }

        try {
            asociado = Integer.parseInt(cedulaAsociado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula del Asociado valido");
            return;
        }

        try {
            nFactura = Integer.parseInt(posicion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de Factura Valido");
            return;
        }

        try {
            club.eliminarFacturaAsociado(socio, asociado, nFactura);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al registrar consumo de Asociado");
            return;
        }

    }

    public void EliminarSocio(String cedula) {

        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de factura no puede estar vacío");
            return;
        }

        try {
            int socio = Integer.parseInt(cedula);
            club.EliminarSocio(socio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar la Cedula del Socio");
            return;
        }

    }

    public void eliminarPersonaAutorizada(String Socio, String PersonaAutorizada) {

        try {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar la Cedula del Socio");
            return;
        }
        int pAutorizado = Integer.parseInt(PersonaAutorizada);
        int cSocio = Integer.parseInt(Socio);
        club.eliminarAsociado(cSocio, pAutorizado);

    }

    //*********************************************************************************************
    public void CargarPersonaAutorizada() throws Exception {
        String ruta = "./carpetaErrores";
        String contenido = "se presentaron los siguientes errores \n";
        File file = new File(ruta);
        if (file.mkdir()) {
            System.out.println("carpeta creada");
        } else {

            System.out.println("La carpeta ya existe");
        };
        // Si el archivo no existe es creado
        ruta += "/erroresCargaAsociados" + (new Date()).toString().replaceAll("/", "-").replaceAll(":", "-") + ".txt";
        file = new File(ruta);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            File doc = new File("./carpetaCargaAsociados/asociados.txt");

            BufferedReader obj = new BufferedReader(new FileReader(doc));

            String linea;
            while ((linea = obj.readLine()) != null) {
                //System.out.println(linea);
                try {
                    String[] datos = linea.split(",");
                    int cedula = Integer.parseInt(datos[1]);
                    int cedulaSocio = Integer.parseInt(datos[2]);
                    String nombre = datos[0];
                    club.CargarPersonaAutorizada(nombre, cedula, cedulaSocio);
                    //club.inscribirPersonaAutorizada(nombre, cedula, cedulaSocio);                                
                    System.out.println(datos[0]);
                } catch (Exception f) {
                    //JOptionPane.showMessageDialog(null, "el error es " + f.getMessage());
                    contenido += linea + "|" + f.getMessage() + "\n";
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println("no se encontro el archivo");
        } finally {
            bw.write(contenido);
            bw.close();
        }

    }

    //*******************Metodos a la Base de Datos **********************
//    public void CantidadSociosVIP() {
//        club.CantidadSociosVIP();
//        
//    }
//    
//    public void Buscarsocio(int cedula) {
//        club.BuscarSocio(cedula);
//    }
    public void registrarSocioBD(String cedula, String tipoDeSuscripcion, String nombre) {
        int cc = 0;
        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "El nombre del socio no puede ser un espacio Vacio");
            return;
        }

        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }
        try {
            club.inscribirSocioBD(cc, tipoDeSuscripcion, nombre);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            System.out.println(e.getMessage().toString());
            return;
        }

    }

    public void EliminarSocioBD(String cedula) {

        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero cedula no puede estar vacío");
            return;
        }

        try {
            int socio = Integer.parseInt(cedula);
            club.EliminarSocioBD(socio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar la Cedula del Socio");
            return;
        }

    }

    public void aumentarRecursosSocioBD(String cedula, String newFondos) {

        double fondos = 0;
        int cedulaSocio = 0;

        if (cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de cedula no puede ser un espacio Vacio");
            return;
        }
        if (newFondos.equals("")) {
            JOptionPane.showMessageDialog(null, "Los Fondos a ingresar no puede ser un espacio Vacio");
            return;
        }

        try {
            cedulaSocio = Integer.parseInt(cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cedula valido");
            return;
        }

        try {
            fondos = Double.parseDouble(newFondos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor de Fondos Valido");
            return;
        }

        try {
            //club.aumentarRecursosSocioBD(cedulaSocio, fondos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado-Validador");
            return;
        }

    }

    public String BuscarfacturaSocioBD(String cedula) {
        int cc = 0;
        if (cedula.equals("")) {
            //JOptionPane.showMessageDialog(null,"El numero de cedula no puede ser un espacio Vacio");
            return "El numero de cedula no puede ser un espacio Vacio";
        }
        try {
            cc = Integer.parseInt(cedula);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"Ingrese un numero de cedula valido");
            return "Ingrese un numero de cedula valido";
        }
        try {
            return club.clubMostrarfacturaSocioBD(cc);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            return "";
        }

    }

}
