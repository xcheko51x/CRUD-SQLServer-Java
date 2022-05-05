package com.mycompany.crudsqlserverjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mycompany.Usuario;

/**
 *
 * @author xcheko51x
 */

public class CRUDSQLServerJava {

    static List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public static void main(String[] args) {
        ConexionSQLServer con = new ConexionSQLServer();
        Connection conexion = con.obtenerConexion();

        //Usuario usuario = new Usuario("xcheko51x", "Sergio", "xcheko51devx@gmail.com");
        //Usuario usuario2 = new Usuario("luisrm", "Luis", "luis123@local.com");
        //Usuario usuario3 = new Usuario("mariazr", "Maria", "maria987@local.com");

        //insertarUsuario(conexion, usuario);
        //insertarUsuario(conexion, usuario2);
        //insertarUsuario(conexion, usuario3);

        //obtenerUsuarios(conexion);

        String user = "xcheko51x";
        //obtenerUsuario(conexion, user);

        //actualizarUsuario(conexion, user);

        //borrarUsuaario(conexion, user);

        obtenerUsuarios(conexion);
        
    }

    public static void insertarUsuario(Connection conexion, Usuario usuario) {
        
        String sql = "INSERT INTO usuarios VALUES(?,?,?);";

        try {

            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, usuario.usuario);
            statement.setString(2, usuario.nombre);
            statement.setString(3, usuario.correo);

            int registroAdd = statement.executeUpdate();

            if(registroAdd > 0) {
                System.out.println("REGISTRO EXITOSO");
                JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        

    }
    
    public static List<Usuario> obtenerUsuarios(Connection conexion) {

        String sql = "SELECT * FROM usuarios;";

        try {

            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                listaUsuarios.add(
                    new Usuario(
                        rs.getString("usuario"), 
                        rs.getString("nombre"),
                        rs.getString("correo"))
                );
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        mostrarLista(listaUsuarios);

        return listaUsuarios;
    }

    public static void obtenerUsuario(Connection conexion, String user) {
        
        String sql = "SELECT * FROM usuarios WHERE usuario='"+user+"';";

        try {

            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                System.out.println(
                    "Usuario: "+rs.getString("usuario")+
                    " , Nombre: "+rs.getString("nombre")+
                    " , Correo: "+rs.getString("correo")                    
                );

                JOptionPane.showMessageDialog(
                    null, 
                    "Usuario: "+rs.getString("usuario")+
                    " , Nombre: "+rs.getString("nombre")+
                    " , Correo: "+rs.getString("correo")
                );
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarUsuario(Connection conexion, String user) {

        String sql = "UPDATE usuarios SET nombre=?, correo=? WHERE usuario=?;";
 
        try {

            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, "Sergio Peralta");
            statement.setString(2, "xcheko51devx@local.com");
            statement.setString(3, user);
            
            int registroActualizado = statement.executeUpdate();
            
            if (registroActualizado > 0) {
                System.out.println("Se actualizo el registro exitosamente");
                JOptionPane.showMessageDialog(null, "Se actualizo el registro exitosamente");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarUsuaario(Connection conexion, String user) {

        try {
            String sql = "DELETE FROM usuarios WHERE usuario=?";
 
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, user);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El registro se borro exitosamente");
                JOptionPane.showMessageDialog(null, "El registro se borro exitosamente");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public static void mostrarLista(List<Usuario> listaUsuarios) {

        String cadena = "";

        for (Usuario usuario : listaUsuarios) {
            cadena = cadena+"Usuario: "+usuario.usuario+" , Nombre: "+usuario.nombre+" , Correo: "+usuario.correo+"\n";
        }

        System.out.println(cadena);

        JOptionPane.showMessageDialog(null, cadena);

    }
    
}
