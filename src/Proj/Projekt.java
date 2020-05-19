/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import javax.swing.JFrame;


/**
 *
 * @author Kamil
 */

public class Projekt {
     static Connection con;  
     
    public static void main(String[] args){
//        try{
        //lib.Polacz("projekt","kamiljano99","haslo123");
        
        JFrame f = new AStart();
        f.setVisible(true);
        f.setSize(300,200);
        f.setTitle("Logowanie");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        menu logowanie=new menu();        
//        logowanie.setVisible(true); 
//        
//        con.close();
//        }
//        catch(SQLException error_polaczenie) {
//            System.out.println("Błąd połączenia z bazą danych");}
//        
    }


}