/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clone_ca;

import clone_ca.User_autentication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

/**
 *
 * @author Wandwilson Almeida Da Silva
 * Student name: Carolina Gomes Landim
 */
public class login{
    Scanner user_input = new Scanner(System.in);
    DDL_DML_DQL list = new DDL_DML_DQL();
    SignUp update = new SignUp();
    Connection conn;
    
   
    /*
        get the user input to log_in
    */

    
    public void menu() throws SQLException{
        boolean start = true; 
       
         System.out.println("Welcome to equation Database: ");
         System.out.println("[1] to Login");
         System.out.println("[2] to registar new users");
         System.out.println("please choosen one of the option:");
       
         String option = user_input.next();

         while (start){
         
             switch (option){

                case "1": 
                  
                       conn = new ConnectionStart().conectaBD();
                        String username, password;
                        int id;

                        System.out.println("Username:");
                        username = user_input.next();

                        System.out.println("Password:");
                        password = user_input.next();

                        User_get_set objuserdml = new User_get_set();
                        objuserdml.setUsername(username);
                        objuserdml.setUser_password(password);

                        User_autentication objautentication = new User_autentication();
                        ResultSet rs = objautentication.autenticationUesr(objuserdml);
                        id = list.take_id(username, password);



                        if (rs.next()) {

                            System.out.println("Loggin sucefull");


                        } else {

                            System.out.println("Usename or password is invalid");

                       }
                        list.check_user(id, username, password);

                                 
                    
                case "2":
                   
                    SignUp signUP = new SignUp();
                    signUP.Sign_upUsers();
                    
                    menu();
                default:
                    
                    System.out.println("Sorry, Wrong option try again...");
                    menu();
                

            }
         }   
    }
    
    /*
    admin menu
    */
    public void admin(int id){
        boolean start = true; 
       
        
      
     
         System.out.println("What would you like to do? ");
         System.out.println("[1] modify profile");
         System.out.println("[2] see the users list");
         System.out.println("[3] remove users");
         System.out.println("[4] see the operations ");
         System.out.println("[5] log out. ");
         System.out.println("please choosen one of the option:");
   
         String option = user_input.next();
         while (start){
         
             switch (option){

                case "1": 
                  
                    System.out.println(id);
                    update.update(id);
   
                    admin(id);
                    
                    
                case "2":
                  

                    list.LIST_USERS(id);
                    admin(id);

                case "3":
                  
                    list.LIST_USERS(id);  
                    SignUp del = new SignUp();
                  
                    del.delete();
                  
                    System.out.println("User deleted!");
                  
                    list.LIST_USERS(id);
                    admin(id);
       
                case "4":
                   
//                    System.out.println("It is not working yet.");
                    list.seeEquation();
                    admin(id);

                case "5":
                  
                    System.out.println("Bye see you soon");
                    menu();

                default:
                   
                    System.out.println("Sorry, Wrong option try again...");
                    admin(id);
            }
         }
        
    }
    
    /**
     * User menu
     * @param id  get the id from the user is log in to check if he would like to update profile
     */

    public void menu_user(int id) throws SQLException{
        boolean start = true; 
        
         
         System.out.println("What would you like to do? ");
         System.out.println("[1] Modify profile");
         System.out.println("[2] Solve systems of linear equations");
         System.out.println("[3] Log out");
         System.out.println("please choosen one of the option:");
         
         String option = user_input.next();
         while (start){
         
             switch (option){

                case "1": 
             
                    update.update(id);
                    menu_user(id);
                    
//                    break;
                    
                case "2":
             
                    System.out.println("sorry is not work yet..");
//                  put your calculator here.
                    menu_user(id);
                case "3":
             
                    System.out.println("Bye see you soon");
                    menu();
                default:
             
                    System.out.println("Sorry, Wrong option try again...");
                    menu_user(id);
            }
         }
        
    }
    
}
