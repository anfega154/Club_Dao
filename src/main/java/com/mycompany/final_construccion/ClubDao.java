/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_construccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author NMEJIA
 */
public class ClubDao {

    public Map<String, Object> buscarSocio(int cedula) throws SQLException {
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Object> resultado = new HashMap<String, Object>();
        Connection con = Conexion_BD.getConexion();
        try {

            ps = con.prepareStatement("SELECT CEDULA, TIPODESUSCRIPCION,FONDOS,NOMBRE FROM SOCIO WHERE CEDULA = ?");
            //ps.setString(1, Integer.toString(cedula));
            ps.setString(1, cc);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado.put("cedula", rs.getString("CEDULA"));
                resultado.put("fondos", rs.getString("FONDOS"));
                resultado.put("nombre", rs.getString("NOMBRE"));
                resultado.put("tipoDeSuscipcion", rs.getString("TIPODESUSCRIPCION"));
            }

        } finally {
            ps.close();
            rs.close();
            con.close();
        }

        return resultado;
    }

    // Validar con Andres si este Metodo si seria viable 
    public Socio BuscarSocio1(int cedula) throws SQLException {
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = Conexion_BD.getConexion();
        Socio socio = null;
        int cedSocio = 0;
        double fondosSocio = 0;
        try {

            ps = con.prepareStatement("SELECT CEDULA, TIPODESUSCRIPCION,FONDOS,NOMBRE FROM SOCIO WHERE CEDULA = ?");
            //ps.setString(1, Integer.toString(cedula));
            ps.setString(1, cc);
            rs = ps.executeQuery();
            if (rs.next()) {
                try {
                    cedSocio = Integer.parseInt(rs.getString("CEDULA"));
                    fondosSocio = Double.parseDouble(rs.getString("FONDOS"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "El dato guardado en la Base de Datos no es de tipo numerico");
                }
                String nombreSocio = rs.getString("NOMBRE");
                String suscripcion = rs.getString("TIPODESUSCRIPCION");
                socio = new Socio(cedSocio, suscripcion, fondosSocio, nombreSocio);
            }

        } finally {
            ps.close();
            rs.close();
            con.close();
        }

        return socio;
    }

    public boolean DispoSociosVIP() throws SQLException {
        boolean cuposDisponibles = false;
        int cantidad = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = Conexion_BD.getConexion();

        try {
            ps = conexion.prepareStatement("SELECT COUNT(cedula) AS CANTIDAD FROM Socio WHERE tipoDeSuscripcion = 'VIP'");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("CANTIDAD"));
                cantidad = Integer.parseInt(rs.getString("CANTIDAD"));
            }
        } finally {
            ps.close();
            rs.close();
            conexion.close();
        }
        if (cantidad < 3) {
            cuposDisponibles = true;
        }
        return cuposDisponibles;
    }

    public boolean DispoSociosReg() throws SQLException {
        boolean cuposDisponibles = false;
        int cantidad = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = Conexion_BD.getConexion();
        try {

            ps = conexion.prepareStatement("SELECT COUNT(cedula) AS CANTIDAD FROM Socio WHERE tipoDeSuscripcion = 'REGULAR'");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("CANTIDAD"));
                cantidad = Integer.parseInt(rs.getString("CANTIDAD"));
            }
        } finally {
            ps.close();
            rs.close();
            conexion.close();
        }
        if (cantidad < 10) {
            cuposDisponibles = true;
        }
        return cuposDisponibles;
    }

    public boolean BuscarSocio(int cedula) throws SQLException {
        boolean encontrado = false;
        String cc = String.valueOf(cedula);
//        String socio = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = Conexion_BD.getConexion();
        int cantidad = 0;
        try {
            ps = conexion.prepareStatement("SELECT cedula FROM Socio WHERE cedula = ?");
//            ps = conexion.prepareStatement("SELECT COUNT(cedula)AS CANTIDAD  FROM Socio WHERE cedula =?");

            ps.setString(1, cc);
            rs = ps.executeQuery();

            while (rs.next()) {
//                System.out.println(rs.getString("CANTIDAD"));
//                cantidad = Integer.parseInt(rs.getString("CANTIDAD"));
                encontrado = true;
            }

        } finally {
            ps.close();
            rs.close();
            conexion.close();
        }
        return encontrado;
    }

    public void inscribirSocioBDVIP(int cedula, String nombre) throws SQLException {
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        Connection con = Conexion_BD.getConexion();
        try {
            ps = con.prepareStatement("INSERT INTO Socio (cedula, tipoDeSuscripcion, fondos, nombre) VALUES (?,?,?,?)");
            int i = 1;
            ps.setString(i++, cc);
            ps.setString(i++, "VIP");
            ps.setString(i++, "100000");
            ps.setString(i++, nombre);
            ps.executeLargeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado");

        } finally {
            ps.close();
            con.close();
        }
    }

    public void inscribirSocioBDREGULAR(int cedula, String nombre) throws SQLException {
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        Connection con = Conexion_BD.getConexion();
        try {
            ps = con.prepareStatement("INSERT INTO Socio (cedula, tipoDeSuscripcion, fondos, nombre) VALUES (?,?,?,?)");
            int i = 1;
            ps.setString(i++, cc);
            ps.setString(i++, "REGULAR");
            ps.setString(i++, "50000");
            ps.setString(i++, nombre);
            ps.executeLargeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado");

        } finally {
            ps.close();
            con.close();
        }
    }

    public void EliminarSocioBD(int cedula) throws SQLException {
        String cc = String.valueOf(cedula);
        if (BuscarSocio(cedula)) {
            Connection con = Conexion_BD.getConexion();
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement("DELETE FROM Socio  WHERE cedula=? ");
                ps.setString(1, cc);
                ps.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } finally {
                ps.close();
                con.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay un socio registrado con esa celuda");
        }

    }

    public void aumentarRecursosSocioBD(int cedula, double CantidadRecursos) throws SQLException {
        String cc = String.valueOf(cedula);
        //Variable donde se almacenara los nuevos fondos a asignar al Socio
        double fondosNew = 0;
        double fondosActuales = 0;
        String suscripcion = "";
        // condicion para validar si el socio si existe
        if (BuscarSocio(cedula)) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection conexion = Conexion_BD.getConexion();
            /*
            Primero busco el los fondos actuales y el tipo de suscripcion 
            para poder validar si lo actual mas lo que va a ingresar
             */
            try {
                ps = conexion.prepareStatement("SELECT fondos,tipoDeSuscripcion FROM Socio WHERE cedula = ?");
                ps.setString(1, cc);
                rs = ps.executeQuery();

                try {
                    fondosActuales = Double.parseDouble(rs.getString("fondos"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "El dato guardado en la Base de Datos no es de tipo numerico");
                    return;
                }

                suscripcion = rs.getString("tipoDeSuscripcion");
//                while (rs.next()) {
//                    fondosActuales = Double.parseDouble(rs.getString("fondos"));
//                    suscripcion = rs.getString("tipoDeSuscripcion");
//                }

                ps = conexion.prepareStatement("update Socio set fondos=? where cedula=?");
                fondosNew = fondosActuales + CantidadRecursos;
                int i = 1;
                ps.setString(i++, String.valueOf(fondosNew));
                ps.setString(i++, cc);
                ps.executeLargeUpdate();

                if (suscripcion.equals("VIP") && fondosNew <= 5000000) {
                    ps.executeLargeUpdate();
                    JOptionPane.showMessageDialog(null, "Recursos Aumentados a Socio vip");

                } else if (suscripcion.equals("REGULAR") && fondosNew <= 1000000) {
                    ps.executeLargeUpdate();
                    JOptionPane.showMessageDialog(null, "Recursos Aumentados a Socio Regular");

                } else {
                    JOptionPane.showMessageDialog(null, "Supera tope autorizado para el tipo de suscripcion");
                }
            } finally {
                ps.close();
                rs.close();
                conexion.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        }

    }

    public void aumentarFondosSocioBD(int cedula, double fondosNew) throws SQLException {
        String cc = String.valueOf(cedula);
        String fondos = String.valueOf(fondosNew);
        PreparedStatement ps = null;
        Connection conexion = Conexion_BD.getConexion();
        try {
            ps = conexion.prepareStatement("update Socio set fondos=? where cedula=?");
            int i = 1;
            ps.setString(i++, fondos);
            ps.setString(i++, cc);
            ps.executeLargeUpdate();
        } finally {
            ps.close();
            conexion.close();
        }

    }

    public int DispoFacturasSocio(int cedulaSocio) throws SQLException {
        String cc = String.valueOf(cedulaSocio);

//        boolean cuposDisponibles = false;
        int cantidad = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = Conexion_BD.getConexion();
        try {

            ps = conexion.prepareStatement("SELECT COUNT(id_factura) AS NFACTURAS FROM Factura WHERE cedula_socio =?");
            ps.setString(1, cc);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("NFACTURAS"));
                cantidad = Integer.parseInt(rs.getString("NFACTURAS"));
            }

        } finally {
            ps.close();
            rs.close();
            conexion.close();
        }
//        if (cantidad < 20) {
//            cuposDisponibles = true;
//        }
        return cantidad;

    }

    public void RegistrarConsumoBD(String concepto, String valor, String nombre, int cedula) throws SQLException {
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        Connection con = Conexion_BD.getConexion();
        try {
            ps = con.prepareStatement("INSERT INTO Factura (concepto,valor,nombre,cedula_socio) VALUES (?,?,?,?)");
            int i = 1;
            ps.setString(i++, concepto);
            ps.setString(i++, valor);
            ps.setString(i++, nombre);
            ps.setString(i++, cc);
            ps.executeLargeUpdate();
        } finally {
            ps.close();
            con.close();
        }
    }

    public int cantidadAutorizadosBD(int cedula) throws SQLException {
        int cantidad = 0;
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = Conexion_BD.getConexion();
        try {

            ps = con.prepareStatement("SELECT COUNT(cedula) AS NAUTORIZADOS FROM Autorizado WHERE cedula_socio =?");

            ps.setString(1, cc);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("NAUTORIZADOS"));
                cantidad = Integer.parseInt(rs.getString("NAUTORIZADOS"));
            }

        } finally {
            ps.close();
            rs.close();
            con.close();
        }

        return cantidad;
    }

    public ArrayList<Factura> TraerFacturasSocio(int cedula) throws SQLException {
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = Conexion_BD.getConexion();
        try {

            ps = con.prepareStatement("SELECT id_factura, concepto, valor, nombre FROM Factura WHERE cedula_socio = ?");

            ps.setString(1, cc);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id_factura = Integer.parseInt(rs.getString("id_factura"));
                String concepto = rs.getString("concepto");
                double valor = Double.parseDouble(rs.getString("valor"));
                String nombre = rs.getString("nombre");

                Factura facturaAux = new Factura(id_factura, concepto, valor, nombre);
                facturas.add(facturaAux);
            }

        } finally {
            ps.close();
            rs.close();
            con.close();
        }

        return facturas;
    }

//    public Factura EncontrarFacturaSocio(int cedula, int idFactura) throws SQLException {
//        Factura factura = null;
//        String cc = String.valueOf(cedula);
//        String idFac = String.valueOf(idFactura);
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = Conexion_BD.getConexion();
//        try {
//
//        } finally {
//            ps.close();
//            rs.close();
//            con.close();
//        }
//
//        return factura;
//    }

    public void EliminarFacturaSocio(int cedula, int idFactura) throws SQLException {
        String idFac = String.valueOf(idFactura);
        String cc = String.valueOf(cedula);
        PreparedStatement ps = null;
        Connection con = Conexion_BD.getConexion();
        if (BuscarSocio(cedula)) {

            try {
                ps = con.prepareStatement("DELETE FROM Factura  WHERE cedula_socio = ? and id_factura = ?");
                ps.setString(1, cc);
                ps.setString(2, idFac);
                ps.executeLargeUpdate();
//                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } finally {
                ps.close();
                con.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay un socio registrado con esa celuda");
        }

    }

    /*
    
     */
}
