/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudsqlserverjava;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author xcheko51x
 */
public class ConexionSQLServer {
    Connection conexion = null;
    
        String usuario = "info";
        String contrasena = "info";
        String db = "dbPruebas";
        String ip = "localhost";
        String puerto = "1433";

        public Connection obtenerConexion() {
            try{
                String cadena = "jdbc:sqlserver://localhost:"+puerto+";"+"databaseName="+db;
                conexion = DriverManager.getConnection(cadena, usuario, contrasena);
                //JOptionPane.showMessageDialog(null, "Conexion exitosa");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: "+e.toString());
            }
            return conexion;
        }
}
