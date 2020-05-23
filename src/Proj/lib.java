///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Proj;
//
//
//import static Proj.Projekt.con;
//import java.sql.Statement;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//
///**
// *
// * @author Kamil
// */
//public class lib {
//    
//    static void Polacz(String bd,String login,String pass)
//    {
//        try
//        {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"+
//            "databaseName="+bd+";"+
//            "user="+login+
//            ";password="+pass+";");
//            System.out.println("Połączono z bazą danych");     
//        }
//        catch(SQLException error_polaczenie) {
//            System.out.println("Błąd połączenia z bazą danych");}
//        catch(ClassNotFoundException error_sterownik) {
//            System.out.println("Brak sterownika");}
//    }
//        
//    static void Wyswietl()
//    {   
//        JFrame f;
//        f=new JFrame();  
//       
//        //pobranie wybranych kolumn do jednej listy; wszystkie atrybuty przyjmuję jako String
//        List<String[]> lista=new ArrayList<String[]>();
//        
//        //tablica na potrzeby JTable
//        String tab[][];
//        
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://"+
//                "localhost:1433;databaseName=projekt;"+
//                "user=kamiljano99;password=haslo123;");
//         
//            //Zapytanie SQL
//            Statement zapytanie2 = con.createStatement(); 
//            String sql2="select * from uzytkownicy";
//            ResultSet wynik_sql_2 = zapytanie2.executeQuery(sql2);
//            ResultSetMetaData wynik_kol = wynik_sql_2.getMetaData();
//            int ile_kolumn = wynik_kol.getColumnCount(); 
//            //pobranie wybranych kolumn do jednej listy
//            while(wynik_sql_2.next()) {
//                String[] t={wynik_sql_2.getString("id"),wynik_sql_2.getString("imie")};
//                lista.add(t);
//            }            
//            //konwersja listy do tablicy na potrzeby JTable
//            String array[][]=new String[lista.size()][];
//            for (int i=0;i<array.length;i++){
//                String[] row=lista.get(i);
//                array[i]=row;
//            }                   
//            zapytanie2.close(); 
//                    
//            //"ręczne" wprowadzenie nazw kolumn
//            String[] columns={"id","imie"};  
//            
//            //wygenerowanie tabeli
//            JTable jt1=new JTable(array,columns);          
//            JScrollPane sp=new JScrollPane(jt1);           
//            f.add(sp); 
//            f.setLocation(200,50);
//            f.setSize(300,400);   
//            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            f.setVisible(true);      
//            
//            con.close();
//            
//        }
//        catch(SQLException error_polaczenie) {
//            System.out.println("Błąd połączenia z bazą danych");}
//        catch(ClassNotFoundException error_sterownik) {
//                       System.out.println("Brak sterownika");}  
//
//    }
//}
//    
