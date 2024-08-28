/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ggaro
 */
public class Conexion {
    
    public static Connection getConnection(){
        Connection connection = null;
        // definir datos para crear conexion
        var database = "libreria";
        var url = "jdbc:mysql://localhost:3306/" + database;
        var username = "root";
        var password = "753159";

        // Cargar clase del driver mysql en memoria
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }
        //catch(Exception e){
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error en conexion a BD: " + e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args){
        var conexion = getConnection();
        if(conexion != null)
            System.out.println("Conexion a BD libreria satisfactoria!!! " + conexion);
        else
            System.out.println("Error en conexion a BD libreria!!!");
    }
}
