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
//import javax.swing.*; 
//import java.awt.*;
//import java.awt.event.*;
//
//public class menu extends JFrame implements ActionListener{
//    JMenuBar menuBar;
//    JMenu Zaloguj, Pomoc, Zaloguj_jako;
//    JMenuItem Wyjscie, Informacja, Admin, Kierownik, Pracownik;
//    
//    JLabel wynik_otworz, wynik_zapisz;
//    
//    static JLabel l;   
//    
//    public menu(){
//        setTitle("Okno logowania");
//        setSize(300,100);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2,
//                 (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
//        
//        menuBar=new JMenuBar(); //utworzenie "paska" menu
//        
//        Zaloguj=new JMenu("Zaloguj"); //utworzenie pierwszej opcji menu
//        Pomoc=new JMenu("Pomoc"); //utworzenie drugiej opcji menu
//        
//        Zaloguj_jako=new JMenu("Zaloguj jako:");
//        //Rozwin.setEnabled(false); //dezaktywacja menu zagnieżdżonego
//        Admin=new JMenuItem("Administrator");
//        Kierownik=new JMenuItem("Kierownik");
//        Pracownik=new JMenuItem("Pracownik");
//        Zaloguj_jako.add(Admin);
//        Zaloguj_jako.add(Kierownik);
//        Zaloguj_jako.add(Pracownik);
//        Wyjscie=new JMenuItem("Wyjdź");
//        Zaloguj.add(Zaloguj_jako); //menu zagnieżdżone
//        Zaloguj.addSeparator(); //dodanie separatora
//        Zaloguj.add(Wyjscie);
//        
//        
//        Admin.addActionListener(this);
//        Kierownik.addActionListener(this);
//        Pracownik.addActionListener(this);
//        Wyjscie.addActionListener(this); //akcja dla opcji "Wyjście"
//        Wyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl X")); //przypisanie skrótu klawiszowego do opcji "Wyjście"
//        
//        //podmenu dla drugiej opcji menu - "Pomoc"
//        Informacja=new JMenuItem("Informacja");
//        Pomoc.add(Informacja);
//        Informacja.addActionListener(this); //akcja dla opcji "Informacja"
//        
//        setJMenuBar(menuBar); //dodanie menuBar do ramki
//        menuBar.add(Zaloguj); //dodanie do menuBar pierwszej opcji "Zaloguj"
//        menuBar.add(Pomoc);
//        
//    }    
//    
//    @Override
//    public void actionPerformed(ActionEvent e){
//        Object zrodlo=e.getSource();
//        if (zrodlo==Wyjscie){
//            //dispose(); //natychmiastowe wyjście z programu
//           int odp= JOptionPane.showConfirmDialog(null,"Czy na pewno?","Pytanie",JOptionPane.YES_NO_OPTION);
//           if (odp==JOptionPane.YES_OPTION){
//               dispose();
//           }         
//           //else if (odp==JOptionPane.CLOSED_OPTION){...} //obsługa "x" okna dialogowego           
//        }
//        if (zrodlo==Informacja){
//            JOptionPane.showMessageDialog(this,"O programie ... \n Aplikacje bazodanowe","Tytuł",JOptionPane.WARNING_MESSAGE); //okienko dialogowe typu "Message"
//            //JOptionPane.WARNING_MESSAGE - "wbudowana" ikona znajdująca się z lewej strony
//        }
//        
//        if (zrodlo == Admin){
//            data.gdzie_proba="admin";
//            logowanie okno=new logowanie(); 
//        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        okno.setVisible(true);
//            
//        }
//        
//        if (zrodlo == Kierownik){
//            data.gdzie_proba="kierownik";
//            logowanie okno=new logowanie(); 
//        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        okno.setVisible(true);
//            
//        }
//        
//        if (zrodlo == Pracownik){
//            data.gdzie_proba="pracownik";
//            logowanie okno=new logowanie(); 
//        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        okno.setVisible(true);
//            
//        }
//    }
//    
//}
