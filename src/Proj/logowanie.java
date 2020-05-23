///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Proj;
//
///**
// *
// * @author Kamil
// */
//
//import javax.swing.*; 
//import java.awt.*;
//import java.awt.event.*;
//
//public class logowanie extends JFrame implements ActionListener{
//    JButton Loguj; 
//    JLabel login,wynik_int,haslo,wynik_double;
//    JTextField podaj_login,podaj_haslo;
//    String wprowadzony_login, wprowadzone_haslo;
//
//    
//    public logowanie(){  
//        setSize(400,300);       
//        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2,
//                 (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);       
//        setResizable(false);
//        setLayout(null);
//        
//        login=new JLabel("Wprowadź login");
//        login.setBounds(10,10,300,30);
//        login.setFont(new Font("Arial",Font.BOLD,14));
//        add(login);        
//        
//        podaj_login=new JTextField("");
//        podaj_login.setBounds(10,40,200,30);
//        add(podaj_login);        
//
//        haslo=new JLabel("Wprowadź haslo");
//        haslo.setBounds(10,70,300,30);
//        haslo.setFont(new Font("Arial",Font.BOLD,14));
//        add(haslo);        
//        
//        podaj_haslo=new JTextField("");
//        podaj_haslo.setBounds(10,100,200,30);
//        add(podaj_haslo);     
//        
//        Loguj=new JButton("Loguj");
//        Loguj.setBounds(10,140,80,30); 
//        add(Loguj);        
//        Loguj.addActionListener(this);
//        
//        wynik_int=new JLabel("");
//        wynik_int.setBounds(10,180,200,30); 
//        wynik_int.setFont(new Font("Arial",Font.BOLD,14));
//        add(wynik_int);    
//        
//        wynik_double=new JLabel("");
//        wynik_double.setBounds(10,220,200,30); 
//        wynik_double.setFont(new Font("Arial",Font.BOLD,14));
//        add(wynik_double);         
//    }    
//    
//    @Override
//    public void actionPerformed(ActionEvent e){
//        Object zrodlo=e.getSource();
//        if (zrodlo==Loguj)
//        {         
//            wprowadzony_login=(podaj_login.getText());
//            wprowadzone_haslo=(podaj_haslo.getText()); 
//            
//            if (wprowadzony_login.equals(data.login_admin) && wprowadzone_haslo.equals(data.haslo_admin) && data.gdzie_proba.equals("admin"))
//            {
//                if(data.czy_zalogowany!=true)
//                {
//                    data.czy_zalogowany=true;
//                    data.zalogowany =1;
//                    JOptionPane.showMessageDialog(this,"Wprowadzone dane są prawidłowe!","Sukces!",JOptionPane.WARNING_MESSAGE);
//                    dispose();
//                    aplikacja baza_danych=new aplikacja();        
//                    baza_danych.setVisible(true); 
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(this,"Jesteś już zalogowany!","Błąd!",JOptionPane.ERROR_MESSAGE);
//                    dispose();
//                } 
//            }
//            
//            else if (wprowadzony_login.equals(data.login_kierownik) && wprowadzone_haslo.equals(data.haslo_kierownik) && data.gdzie_proba.equals("kierownik"))
//            {
//                if(data.czy_zalogowany!=true)
//                {
//                    data.czy_zalogowany=true;
//                    data.zalogowany=2;
//                    JOptionPane.showMessageDialog(this,"Wprowadzone dane są prawidłowe!","Sukces!",JOptionPane.WARNING_MESSAGE);
//                    dispose();
//                    aplikacja baza_danych=new aplikacja();        
//                    baza_danych.setVisible(true); 
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(this,"Jesteś już zalogowany!","Błąd!",JOptionPane.ERROR_MESSAGE);
//                    dispose();
//                } 
//            }
//            
//            else if (wprowadzony_login.equals(data.login_pracownik) && wprowadzone_haslo.equals(data.haslo_pracownik) && data.gdzie_proba.equals("pracownik"))
//            {
//                if(data.czy_zalogowany!=true)
//                {
//                    data.czy_zalogowany=true;
//                    data.zalogowany=3;
//                    JOptionPane.showMessageDialog(this,"Wprowadzone dane są prawidłowe!","Sukces!",JOptionPane.WARNING_MESSAGE);
//                    dispose();
//                    aplikacja baza_danych=new aplikacja();        
//                    baza_danych.setVisible(true);
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(this,"Jesteś już zalogowany!","Błąd!",JOptionPane.ERROR_MESSAGE);
//                    dispose();
//                }    
//            }
//            
//            else
//            {    
//                data.zalogowany=0;
//                JOptionPane.showMessageDialog(this,"Wprowadzono niepoprawne dane!","Błąd!",JOptionPane.ERROR_MESSAGE);
//                dispose();
//            }
//        }
//    }
//}
