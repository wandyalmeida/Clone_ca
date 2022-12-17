/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clone_ca;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Wandwilson Almeida Da Silva
 * Student name: Carolina Gomes Landim
 */
public class DDL_DML_DQL{
    
    /*
    comands that will be using on the all methods.
    */
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    
    
    /*
     Start create the schema and the tables on the DataBase.
    */
    public boolean create_schema(){
        try {
           conn = new ConnectionStart().conectaBD();
           Statement stmt = conn.createStatement();
            
           /*
           Please put a name to your schema
           */
            stmt.execute("CREATE SCHEMA IF NOT EXISTS YOUR SCHEMA NAME;");//check if the data base have this schema. if no they will create the schema.
            stmt.execute("USE YOUR SCHEMA NAME;");
            
            /*
            Start create the tables on the DataBase.
            */
            
            /*
            Please put a name to your table and change the attributes
            user_id INT(20) PRIMARY KEY AUTO_INCREMENT,
            username VARCHAR(30),
            password VARCHAR(30),
            email VARCHAR(100),
            contact VARCHAR(15);
            */

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS YOUR  TABLE  NAME ("
                            + "user_id INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                            + "username VARCHAR(30),"
                            + "password VARCHAR(30),"
                            + "surname VARCHAR(40),"
                            + "email VARCHAR(100),"
                            + "contact VARCHAR(15));"
            );
            
            /*
            Please put a name to your table and change the attributes
            equation_id INT(20) PRIMARY KEY AUTO_INCREMENT,
            equations VARCHAR(100),
            solution VARCHAR(100),
            users_id INT(20) NOT NULL;
            */
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS YOUR  TABLE NAME("
                            + "equation_id INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                            + "equations VARCHAR(100),"
                            + "solution VARCHAR(100),"
                            + "users_id INT(20) NOT NULL,"
                            + "CONSTRAINT users_id FOREIGN KEY (users_id) REFERENCES users(user_id));"
                            
            );
            
            /*
            Please put a name to your table and change the attributes
            admin_id INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
            users_id INT(20) NOT NULL,
            equation_id INT(20) NOT NULL;
            */
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS YOUR NAME TABLE  ("
                            + "admin_id INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                            + "userS_id INT(20) NOT NULL,"
                            + "equation_id INT(20) NOT NULL,"
                            + "CONSTRAINT user_id FOREIGN KEY (userS_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,"
                            + "CONSTRAINT equation_id FOREIGN KEY (equation_id) REFERENCES equation(equation_id) ON DELETE CASCADE ON UPDATE CASCADE);"
                            
            );
           
            insert_admin();
            return true; // return if the data was created.
        } catch (SQLException e) {
            System.out.println("Create Schema and Tables: " + e);
            return false; // return false if the data  is already created.
        }
    }

    /*
    insert the new Users on the DataBase.
    */

    public void Sign_UPUser(User_get_set objsign_up){
       
       String sql = "insert into  users (username, password) values (?, ?)";// insert the new user on the DataBase.

        conn = new ConnectionStart().conectaBD();

        try {
            /*
            PSTM = Prepare Statment.
            RS = ResultSet
            CONN = Connection
            */
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objsign_up.getUsername());// set and get the username.
            pstm.setString(2, objsign_up.getUser_password());// set and get the password.
            
            pstm.execute("USE systemca;");
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("Sign UP User: " + e);
        }
    }
    
    /*
    Add the equation on the DataBase.
    */
    public void addEquation(String equation, String solution, int id){
        // Start the variable;
        int id_equation = 0;
        String sql = "insert into  equation (equations, solution, users_id) values (?, ?, ?)";//inset the equation on the DataBase.
        String sq2 = "select * from equation where equation_id";//Get the equation_id.
       
        conn = new ConnectionStart().conectaBD();//connect on the DataBase.

        try {
            /*
            PSTM = Prepare Statment.
            RS = ResultSet
            CONN = Connection
            */
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, equation);
            pstm.setString(2, solution);
            pstm.setInt(3, id);
            
            pstm.execute("USE systemca;");
            pstm.execute();
            pstm = conn.prepareStatement(sq2);
            rs = pstm.executeQuery();
            /*
              Get the equation_id to put in Admin_user Table.
            using while loop to get the last equation add on the equation table.
            */
            while(rs.next()){
                id_equation = rs.getInt("equation_id");                    
            }
            
            insert_adminfk(id_equation); //will move to insert_adminfk and insert the equation_id on the Admin_user.
            
        } catch (SQLException e) {
            System.out.println("add equation: " + e);
        }
    }
    
    /*
     insert the admin foreign key on the admin_user table.
    */

    public void insert_adminfk(int id_equation) {
       
        String sql = "insert  into  admin_user (userS_id, equation_id) values (1, ?)";// this will set the equation_id the userS_id will be always the admin id.

        conn = new ConnectionStart().conectaBD();

        try {
            /*
            PSTM = Prepare Statment.
            CONN = Connection
            */
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id_equation);

            
            pstm.execute("USE systemca;");
            pstm.execute();
            pstm.close();  
            
        } catch (SQLException e) {
            System.out.println("insert admin foreign key: " + e);
        }
    }
   
    /*
    insert the CCT user as admin of the DataBase.
    */
      
    
    public void insert_admin(){ 
       String sql = "insert into  users(username, password) values (?, ?)"; // will set the CCT as a Admin when the code start on the first time.
       String delete = "delete from users where user_id != '1' and username = 'CCT' and password = 'Dublin'";
       


        try {
            /*
            PSTM = Prepare Statment.
            CONN = Connection.
            */
            conn = new ConnectionStart().conectaBD();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "CCT");
            pstm.setString(2, "Dublin");
                    
            pstm.execute("USE systemca;");
            pstm.execute();
            pstm = conn.prepareStatement(delete);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("insert Admin: " + e);// show this message if this method get a error.
        }
    }

    /*
       Start Check_user if it is admin or it is a regular user. 
    */

    public void check_user(int id, String username, String password){
        String sql = "select * from users where user_id = '1' and username = ? and password = ?";// will check if the user is an admin or a regular user.
        login lg = new login(); // call the menu option.

   
        try {
             /*
            PSTM = Prepare Statment.
            CONN = Connection.
            */
            conn = new ConnectionStart().conectaBD();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, username);
            pstm.setString(2, password);
            
            pstm.execute("USE systemca;");
            rs = pstm.executeQuery();
           
            if(rs.next()){// this command will get the first id that was set it is an Admin
                System.out.println("Welcome back "+ username);
                lg.admin(id);
                               
            }else{ // if is not an Admin they willl use this part for regular user
                System.out.println("Welcome back "+ username);
                lg.menu_user(id);
            }
            
        } catch (SQLException e) {
            System.out.println("Check user: " + e);// show this message if this method get a error.
        }
    }

    /*
        Will show a list with the user is on the DataBase.
    */

    public void LIST_USERS(int id) {
        String sql = "select * from users where user_id != 1"; // will get the user list.
        /*
        Start variable
        */
        String username,user_password, surname, email, contact;
        int id_user;
        
        
        
        try {
             /*
            PSTM = Prepare Statment.
            CONN = Connection.
            */
            conn = new ConnectionStart().conectaBD();
            pstm = conn.prepareStatement(sql);
            
            pstm.execute("USE systemca;");
            rs = pstm.executeQuery();
            
            System.out.println("|ID\t" + "|NAME\t  " + "\t|SURNAME" + "\t|CONTACT" );
             System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            while(rs.next()){
                  
                   id_user = rs.getInt("user_id");
                   username = rs.getString("username");
                   surname = rs.getString("surname");
                   contact = rs.getString("contact");
                   
                   if (username.length() <= 6){
                       System.out.println("|" +id_user +"\t|"+username +"\t \t|"+surname +"\t \t\t|"+contact);// show the table.
                        
                   }
                   if(username.length() >= 6){
                       System.out.println("|" +id_user +"\t|"+username +"\t|"+surname + "\t|"+contact);// show the table.
                   }                   
                                 
            }
            
        } catch (SQLException e) {
            System.out.println("Find User: " + e);// show this message if this method get a error.
        }
    }
    
    public void seeEquation(){
        String sql = "select * from equation ";
        String username,user_password;
        int id_user, equation_id;

        
        
        try {
            conn = new ConnectionStart().conectaBD();
            pstm = conn.prepareStatement(sql);
            
            pstm.execute("USE systemca;");
            rs = pstm.executeQuery();
            
            System.out.println("id  " + "  name  " + " solution  " + "User_ID" );

            while(rs.next()){
                  
                   equation_id = rs.getInt("equation_id");
                   username = rs.getString("equations");
                   user_password = rs.getString("solution");
                   id_user = rs.getInt("users_id");
                   System.out.println(equation_id +" | "+username +" | "+user_password+" | "+id_user );
                   
                   
                   
               
            }
        } catch (SQLException e) {
            System.out.println("See equation: " + e);// show this message if this method get a error.
        }
    }

    public int getId(String username, String password){
        String sql = "select * from users where username = ? and password = ?";

        int id_user;
        
        
        try {
            conn = new ConnectionStart().conectaBD();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, username);
            pstm.setString(2, password);
            
            pstm.execute("USE systemca;");
            rs = pstm.executeQuery();
            if(rs.next()){
                id_user = rs.getInt("user_id");
                return id_user; 
                               
            }
        } catch (SQLException e) {
            System.out.println("Get ID: " + e);
        }
        return 0;
    }

    public void updateUser(User_get_set objupdateDML){
        String sql = "update users set username = ?, password = ?, surname = ?, email = ?, contact = ?  where user_id = ? ";
        conn = new ConnectionStart().conectaBD();
         try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objupdateDML.getUsername());
            pstm.setString(2, objupdateDML.getUser_password());
            pstm.setString(3, objupdateDML.getSurname());
            pstm.setString(4, objupdateDML.getEmail());
            pstm.setString(5, objupdateDML.getContact());
            pstm.setInt(6, objupdateDML.getId_user());

            pstm.execute("USE systemca;");
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("Update User: " + e);
        }
    }


    public void delete(User_get_set objdeleteDML){
        String sql = "delete from users where user_id = ? ";

        
        
        try {
            if (objdeleteDML.getId_user() == 1){
                System.out.println("Sorry you cant delete this user!");
                login login = new login();
                login.admin(objdeleteDML.getId_user());
            }else{
                conn = new ConnectionStart().conectaBD();
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, objdeleteDML.getId_user());

                pstm.execute("USE systemca;");
                pstm.execute();
                pstm.close();
            }
            
        } catch (SQLException e) {
            System.out.println("Delete usuario: " + e);
        }
    }
    
}
