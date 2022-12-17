/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clone_ca;

import java.sql.SQLException;

/**
 *
 * @author Wandwilson Almeida Da Silva
 * Student name: Carolina Gomes Landim
 */
public class Clone_ca {

    /**
     * This code don't have a calculator Please just use this code to help do not copy and paste because you have to change some methods. 
     * This code will not work if you do put your connector on the Libraries.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        // Create the DataBase and add the Admin user
        DDL_DML_DQL createDB = new DDL_DML_DQL();
        createDB.create_schema();
        
        // Show the menu for login or sign up.
        login log = new login();
        log.menu();
    }
    
}
