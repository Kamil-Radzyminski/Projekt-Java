package Proj;


import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Start extends JFrame implements ItemListener{
    JButton btn_pr, btn_adm;
    JLabel login_pr,haslo_pr,login_adm,haslo_adm;
    JTextField logintf_pr, haslotf_pr,logintf_adm, haslotf_adm;
    JPanel cards,card1,card2,combobox;
    
    public Start(){
        login_pr = new JLabel("Login:",SwingConstants.LEFT);
        haslo_pr = new JLabel("Haslo:",SwingConstants.LEFT);
        login_adm = new JLabel("Login:",SwingConstants.LEFT);
        haslo_adm = new JLabel("Haslo:",SwingConstants.LEFT);
        
        logintf_pr = new JTextField(20);
        haslotf_pr = new JTextField(20);
        logintf_adm = new JTextField(20);
        haslotf_adm = new JTextField(20);
        
        btn_pr = new JButton("Zaloguj");
        btn_adm = new JButton("Zaloguj");
        
        cards = new JPanel(new CardLayout());
        
        card1 = new JPanel(new GridLayout(5,1));
        card2 = new JPanel(new GridLayout(5,1));
        cards.add(card1,"Pracownik");
        cards.add(card2,"Administrator");
        
        combobox = new JPanel();
        String[] cbitems = {"Pracownik/Kierownik","Administrator"};
        JComboBox cb = new JComboBox(cbitems);
        cb.addItemListener(this);
        combobox.add(cb);
        
        add(combobox, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);
        
        card1.add(login_pr);
        card1.add(logintf_pr);
        card1.add(haslo_pr);
        card1.add(haslotf_pr);
        card1.add(btn_pr);
        
        card2.add(login_adm);
        card2.add(logintf_adm);
        card2.add(haslo_adm);
        card2.add(haslotf_adm);
        card2.add(btn_adm);
        
        btn_adm.addActionListener(new ZalogujAdministrator());
        btn_pr.addActionListener(new ZalogujUzytkownik());
        getRootPane().setDefaultButton(btn_pr);
        
    }
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    
    class ZalogujUzytkownik implements ActionListener{
        Connection con;
        public void actionPerformed(ActionEvent e){
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://"
                    + "localhost:1433;databaseName=projekt;" +
                    "user=kamiljano99;password=haslo123;");
            
            Statement ask = con.createStatement();
            String sql = "select rola,login,haslo from uzytkownicy";
            
            ResultSet wynik = ask.executeQuery(sql);
            String rol = "";
            String log = "";
            String has = "";
//            int id_pr = -1;
            
            while(wynik.next()) {
                rol = wynik.getString("rola");
                log = wynik.getString("login");
                has = wynik.getString("haslo");
//                id_pr = Integer.parseInt(wynik.getString("id"));
                if(logintf_pr.getText().equals(log)){
                    if (haslotf_pr.getText().equals(has)
                    && rol.equals("kierownik")){
                    dispose();
                    data.zalogowany=2;
                    aplikacja baza_danych=new aplikacja();        
                    baza_danych.setVisible(true);
                    break;
                    }
                    else if(haslotf_pr.getText().equals(has)
                    && rol.equals("pracownik")){
                    dispose();
                    data.zalogowany=3;
                    aplikacja baza_danych=new aplikacja();        
                    baza_danych.setVisible(true);
                    break;
                    }
                }
            }
            if(logintf_pr.getText().equals(log) == false || haslotf_pr.getText().equals(has) == false)
                JOptionPane.showConfirmDialog(null, "Błędne dane logowania",
                        "", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            ask.close();
            con.close();
        }
        catch(SQLException error_polaczenie) {
            System.out.println("Błąd połączenia z bazą danych " + error_polaczenie);}
        catch(ClassNotFoundException error_sterownik) {
            System.out.println("Brak sterownika");}    
        }
    }
    
    class ZalogujAdministrator implements ActionListener {
        Connection con;
            public void actionPerformed(ActionEvent e){
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://"
                    + "localhost:1433;databaseName=projekt;" +
                    "user=kamiljano99;password=haslo123;");
            
            Statement ask = con.createStatement();
            String sql = "select login, haslo from administratorzy ";
            
            ResultSet wynik = ask.executeQuery(sql);
            String log = "";
            String has = "";
            
            while(wynik.next()) {
                log = wynik.getString(("login"));
                has = wynik.getString(("haslo"));
                if(logintf_adm.getText().equals(log))
                    if(haslotf_adm.getText().equals(has)){
                    dispose();
                    data.zalogowany=1;
                    aplikacja baza_danych=new aplikacja();        
                    baza_danych.setVisible(true); 
                    break;
                }
            }
            
            
            if(logintf_adm.getText().equals(log) == false || haslotf_adm.getText().equals(has) == false)
                JOptionPane.showConfirmDialog(null, "Błędny login lub hasło",
                        "", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            
            ask.close();
            con.close();
        }
        catch(SQLException error_polaczenie) {
            System.out.println("Błąd połączenia z bazą danych " + error_polaczenie);}
        catch(ClassNotFoundException error_sterownik) {
            System.out.println("Brak sterownika");}    
    }
   }
    
    
}
