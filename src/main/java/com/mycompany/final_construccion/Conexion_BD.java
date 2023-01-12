package com.mycompany.final_construccion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;





/**
 *
 * @author NMEJIA
 */
public class Conexion_BD {
    
    
    public static Connection getConexion(){
                //jdbc:sqlserver://localhost:1433;databaseName=CRUD_JAVA_ESCUELA [sa on db_owner];
        
        String consqlnew = "jdbc:sqlserver://localhost:1433;user=rrrr;password=Aa5592770;database=Club;encrypt=true;trustServerCertificate=true";
        
        try{
          Connection con = DriverManager.getConnection(consqlnew);          
          System.out.println("Conexion Exitosa");
          return con;
        }catch(SQLException e){
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error");
            return null;
        }
    }
    
    
}
