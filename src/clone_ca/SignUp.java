/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clone_ca;

import java.util.Scanner;

/**
 *
 * @author Wandwilson Almeida Da Silva
 * Student name: Carolina Gomes Landim
 */
public class SignUp{
    
    Scanner input = new Scanner(System.in);
    User_get_set objRegularUserDML = new User_get_set();
    DDL_DML_DQL objUserDDL = new DDL_DML_DQL();
    
    public void Sign_upUsers(){
        String username, password;
       
        System.out.println("Username");
        username = input.nextLine();
        System.out.println("password");
        password = input.nextLine();
        User_get_set signUP = new User_get_set();
        signUP.setUsername(username);
        signUP.setUser_password(password);
        
       
        objUserDDL.Sign_UPUser(signUP);
        
    }
    
    public void update(int id){
        
            String username, password, surname, email, contact;
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("New username: ");
            username = input.next();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("New password: ");
            password = input.next();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Surname: ");
            surname = input.next();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Email: ");
            email = input.next();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Contact: ");
            contact = input.next();
            
            objRegularUserDML.setId_user(id);
            objRegularUserDML.setUsername(username);
            objRegularUserDML.setUser_password(password);
            objRegularUserDML.setSurname(surname);
            objRegularUserDML.setEmail(email);
            objRegularUserDML.setContact(contact);
            

            objUserDDL.updateUser(objRegularUserDML);
           
    }
    
    public void delete(){
         int user_id;
         System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
         System.out.println("Type the id that would you like to delete: ");
         user_id = input.nextInt();
         
         objRegularUserDML.setId_user(user_id);
         
         objUserDDL.delete_user(objRegularUserDML);
    }
    
}
