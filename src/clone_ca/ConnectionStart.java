/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clone_ca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wandwilson Almeida Da Silva
 * Student name: Carolina Gomes Landim
 */
public class ConnectionStart{
    /*
        Will connect the Java Aplication with the DataBase.
    */
    
    public Connection conectaBD(){

        Connection conn = null;
       
        try {
            String dbName = "systemca";
            String DB_URL = "jdbc:mysql://localhost/" + dbName;
            String USER = "root";
            String PASS = "root";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/", USER, PASS);
           
           
        } catch (SQLException e) {
            System.out.println("Erro on the connection part " + e.getMessage());
        }
        
        return conn;
    }
    
}
