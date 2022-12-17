/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clone_ca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Wandwilson Almeida Da Silva
 * Student name: Carolina Gomes Landim 
 */
public class User_autentication {
    Connection conn;
    PreparedStatement pstm;
    /*
    Start check if have this user on the DataBase and if it is correct.
    */
    
    public ResultSet autenticationUesr(User_get_set objUserDML) {
          conn = new ConnectionStart().conectaBD();
        
        try {
            
            String sql = "select * from users where username = ? and password = ?";
            
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUserDML.getUsername());
            pstm.setString(2, objUserDML.getUser_password());
            
           
            pstm.execute("USE systemca;");
            ResultSet rs = pstm.executeQuery();
            
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
