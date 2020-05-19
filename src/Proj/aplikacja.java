/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;

import Proj.crud.Models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamil
 */
public class aplikacja extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu Dodaj, Wyswietl, Modyfikuj, Usun, Pomoc, Wyjscie;
    JMenu Dodaj_Zwierzeta, Dodaj_Osobe, Dodaj_Zasoby;
    JMenu Wyswietl_Zwierzeta, Wyswietl_Osobe, Wyswietl_Zasoby;
    JMenu Modyfikuj_Zwierzeta, Modyfikuj_Osobe, Modyfikuj_Zasoby;
    JMenu Usun_Zwierzeta, Usun_Osobe, Usun_Zasoby;

    //ITEMS INNE-------------------------------------
    JMenuItem Wyjdz, Informacja;

    //ITEMS DODAJ------------------------------------
    JMenuItem Dodaj_Przedmiot, Dodaj_Magazyn, Dodaj_Pracownika, Dodaj_Kierownika, Dodaj_Zwierze, Dodaj_Gatunek, Dodaj_Rodzine, Dodaj_Gromade;

    //ITEMS WYSWIETL---------------------------------
    JMenuItem Wyswietl_Zwierze, Wyswietl_Gatunek, Wyswietl_Rodzine, Wyswietl_Gromade, Wyswietl_Przedmiot, Wyswietl_Magazyn, Wyswietl_Pracownika, Wyswietl_Kierownika;

    //ITEMS MODYFIKUJ--------------------------------
    JMenuItem Modyfikuj_Przedmiot, Modyfikuj_Magazyn, Modyfikuj_Pracownika, Modyfikuj_Kierownika, Modyfikuj_Zwierze, Modyfikuj_Gatunek, Modyfikuj_Rodzine, Modyfikuj_Gromade;

    //ITEMS USUN-------------------------------------
    JMenuItem Usun_Przedmiot, Usun_Magazyn, Usun_Pracownika, Usun_Kierownika, Usun_Zwierze, Usun_Gatunek, Usun_Rodzine, Usun_Gromade;

    JLabel wynik_otworz, wynik_zapisz;

    static JLabel l;

    public aplikacja() {
        setTitle("Baza danych");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        // MENU---------------------------------
        menuBar = new JMenuBar();

        Dodaj = new JMenu("Dodaj");
        Wyswietl = new JMenu("Wyświetl");
        Modyfikuj = new JMenu("Modyfikuj");
        Usun = new JMenu("Usuń");
        Pomoc = new JMenu("Pomoc");
        Wyjscie = new JMenu("Wyjdź");
//   ------------------------------------     

        Wyjdz = new JMenuItem("Wyjdź");
        Informacja = new JMenuItem("Informacja");

// DODAJ ITEMS ---------------------------------       
        Dodaj_Zwierzeta = new JMenu("Zwierzęta");

        Dodaj_Zwierze = new JMenuItem("Zwierzę");
        Dodaj_Gatunek = new JMenuItem("Gatunek");
        Dodaj_Rodzine = new JMenuItem("Rodzinę");
        Dodaj_Gromade = new JMenuItem("Gromadę");
        Dodaj_Zwierzeta.add(Dodaj_Zwierze);
        Dodaj_Zwierzeta.add(Dodaj_Gatunek);
        Dodaj_Zwierzeta.add(Dodaj_Rodzine);
        Dodaj_Zwierzeta.add(Dodaj_Gromade);

        Dodaj_Zasoby = new JMenu("Zasoby");

        Dodaj_Przedmiot = new JMenuItem("Przedmiot");
        Dodaj_Magazyn = new JMenuItem("Magazyn");
        Dodaj_Zasoby.add(Dodaj_Przedmiot);
        Dodaj_Zasoby.add(Dodaj_Magazyn);

        Dodaj_Osobe = new JMenu("Osobę");

        Dodaj_Pracownika = new JMenuItem("Pracownika");
        Dodaj_Kierownika = new JMenuItem("Kierownika");
        Dodaj_Osobe.add(Dodaj_Pracownika);
        Dodaj_Osobe.add(Dodaj_Kierownika);

        // WYSWIETL ITEMS ---------------------------------   
        Wyswietl_Zwierzeta = new JMenu("Zwierzęta");

        Wyswietl_Zwierze = new JMenuItem("Zwierzęta");
        Wyswietl_Gatunek = new JMenuItem("Gatunki");
        Wyswietl_Rodzine = new JMenuItem("Rodziny");
        Wyswietl_Gromade = new JMenuItem("Gromady");
        Wyswietl_Zwierzeta.add(Wyswietl_Zwierze);
        Wyswietl_Zwierzeta.add(Wyswietl_Gatunek);
        Wyswietl_Zwierzeta.add(Wyswietl_Rodzine);
        Wyswietl_Zwierzeta.add(Wyswietl_Gromade);

        Wyswietl_Zasoby = new JMenu("Zasoby");

        Wyswietl_Przedmiot = new JMenuItem("Przedmioty");
        Wyswietl_Magazyn = new JMenuItem("Magazyny");
        Wyswietl_Zasoby.add(Wyswietl_Przedmiot);
        Wyswietl_Zasoby.add(Wyswietl_Magazyn);

        Wyswietl_Osobe = new JMenu("Osoby");

        Wyswietl_Pracownika = new JMenuItem("Pracowników");
        Wyswietl_Kierownika = new JMenuItem("Kierowników");
        Wyswietl_Osobe.add(Wyswietl_Pracownika);
        Wyswietl_Osobe.add(Wyswietl_Kierownika);

        //   ------------------------------------------
        // MODYFIKUJ ITEMS ---------------------------------  
        Modyfikuj_Zwierzeta = new JMenu("Zwierzęta");

        Modyfikuj_Zwierze = new JMenuItem("Zwierzę");
        Modyfikuj_Gatunek = new JMenuItem("Gatunek");
        Modyfikuj_Rodzine = new JMenuItem("Rodzinę");
        Modyfikuj_Gromade = new JMenuItem("Gromadę");
        Modyfikuj_Zwierzeta.add(Modyfikuj_Zwierze);
        Modyfikuj_Zwierzeta.add(Modyfikuj_Gatunek);
        Modyfikuj_Zwierzeta.add(Modyfikuj_Rodzine);
        Modyfikuj_Zwierzeta.add(Modyfikuj_Gromade);

        Modyfikuj_Zasoby = new JMenu("Zasoby");

        Modyfikuj_Przedmiot = new JMenuItem("Przedmiot");
        Modyfikuj_Magazyn = new JMenuItem("Magazyn");
        Modyfikuj_Zasoby.add(Modyfikuj_Przedmiot);
        Modyfikuj_Zasoby.add(Modyfikuj_Magazyn);

        Modyfikuj_Osobe = new JMenu("Osobę");

        Modyfikuj_Pracownika = new JMenuItem("Pracownika");
        Modyfikuj_Kierownika = new JMenuItem("Kierownika");
        Modyfikuj_Osobe.add(Modyfikuj_Pracownika);
        Modyfikuj_Osobe.add(Modyfikuj_Kierownika);

        //   ------------------------------------------
        // USUN ITEMS ---------------------------------        
        Usun_Zwierzeta = new JMenu("Zwierzęta");
        Usun_Zwierze = new JMenuItem("Zwierzę");
        Usun_Gatunek = new JMenuItem("Gatunek");
        Usun_Rodzine = new JMenuItem("Rodzinę");
        Usun_Gromade = new JMenuItem("Gromadę");
        Usun_Zwierzeta.add(Usun_Zwierze);
        Usun_Zwierzeta.add(Usun_Gatunek);
        Usun_Zwierzeta.add(Usun_Rodzine);
        Usun_Zwierzeta.add(Usun_Gromade);

        Usun_Zasoby = new JMenu("Zasoby");
        Usun_Przedmiot = new JMenuItem("Przedmiot");
        Usun_Magazyn = new JMenuItem("Magazyn");
        Usun_Zasoby.add(Usun_Przedmiot);
        Usun_Zasoby.add(Usun_Magazyn);

        Usun_Osobe = new JMenu("Osobę");
        Usun_Pracownika = new JMenuItem("Pracownika");
        Usun_Kierownika = new JMenuItem("Kierownika");
        Usun_Osobe.add(Usun_Pracownika);
        Usun_Osobe.add(Usun_Kierownika);

        //   ------------------------------------------
        Wyjscie.add(Wyjdz);
        Pomoc.add(Informacja);

        Dodaj.add(Dodaj_Zasoby);
        Dodaj.add(Dodaj_Zwierzeta);
        Dodaj.add(Dodaj_Osobe);

        Wyswietl.add(Wyswietl_Zasoby);
        Wyswietl.add(Wyswietl_Zwierzeta);
        Wyswietl.add(Wyswietl_Osobe);

        Modyfikuj.add(Modyfikuj_Zasoby);
        Modyfikuj.add(Modyfikuj_Zwierzeta);
        Modyfikuj.add(Modyfikuj_Osobe);

        Usun.add(Usun_Zasoby);
        Usun.add(Usun_Zwierzeta);
        Usun.add(Usun_Osobe);

// ACTION LISTENERS --------------------------------------------------------------       
        Dodaj_Zwierze.addActionListener(this);
        Dodaj_Gatunek.addActionListener(this);
        Dodaj_Rodzine.addActionListener(this);
        Dodaj_Gromade.addActionListener(this);

        Dodaj_Przedmiot.addActionListener(this);
        Dodaj_Magazyn.addActionListener(this);

        Dodaj_Pracownika.addActionListener(this);
        Dodaj_Kierownika.addActionListener(this);
        Wyswietl_Pracownika.addActionListener(this);

        Modyfikuj_Pracownika.addActionListener(this);
        Usun_Pracownika.addActionListener(this);

        Informacja.addActionListener(this);
        Wyjdz.addActionListener(this);

//--------------------------------------------------------------------------------        
        Wyjdz.setAccelerator(KeyStroke.getKeyStroke("ctrl X")); //przypisanie skrótu klawiszowego do opcji "Wyjście"

        setJMenuBar(menuBar); //dodanie menuBar do ramki

        menuBar.add(Dodaj);
        menuBar.add(Wyswietl);
        menuBar.add(Modyfikuj);
        menuBar.add(Usun);
        menuBar.add(Pomoc);
        menuBar.add(Wyjscie);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        JFrame jFrame = new JFrame();
        //System.out.println(zrodlo.toString());
//DODAJ-------------------------------------------------------------------------        
        if (zrodlo == Dodaj_Zwierze) {

        }

        if (zrodlo == Dodaj_Gatunek) {

        }

        if (zrodlo == Dodaj_Rodzine) {

        }

        if (zrodlo == Dodaj_Gromade) {

        }

        if (zrodlo == Dodaj_Przedmiot) {

        }

        if (zrodlo == Dodaj_Magazyn) {

        }

        if (zrodlo == Dodaj_Pracownika) {

            try {
                User user = new User("bolek", "lolek", "pracownik", "bo", "lo", 1); //@TODO dodać prawdziwe dane z formularza
                user.create();
            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (zrodlo == Dodaj_Kierownika) {

        }

        if (zrodlo == Modyfikuj_Pracownika) {
            try {
                User user = new User(6); //@TODO pobrany id z formatki
                user.getOne();
                user.setLastname("Malinowsky");
                user.update();
            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//WYSWIETL----------------------------------------------------------------------        
        if (zrodlo == Wyswietl_Zwierze) {

        }

        if (zrodlo == Wyswietl_Gatunek) {

        }

        if (zrodlo == Wyswietl_Rodzine) {

        }

        if (zrodlo == Wyswietl_Gromade) {

        }

        if (zrodlo == Wyswietl_Przedmiot) {

        }

        if (zrodlo == Wyswietl_Magazyn) {

        }

        if (zrodlo == Usun_Pracownika) {
            try {
                User user = new User(4); //@TODO tutaj ma być id
                user.delete();
            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Pracownika) {
            try {
                List<User> userList = User.getList();
                String[] columns = {"imie", "nazwisko", "rola"};
                String[][] usersArray = new String[userList.size()][columns.length];

                for (int i = 0; i < userList.size(); i++) {
                    User currentUser = userList.get(i);

                    String[] singleUser = {
                        currentUser.getFirstname(),
                        currentUser.getLastname(),
                        currentUser.getRole()
                    };
                    usersArray[i] = singleUser;

                }

                JTable jt1 = new JTable(usersArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Kierownika) {

        }

//------------------------------------------------------------------------------
        if (zrodlo == Wyjdz) {
            int odp = JOptionPane.showConfirmDialog(null, "Czy na pewno?", "Pytanie", JOptionPane.YES_NO_OPTION);
            if (odp == JOptionPane.YES_OPTION) {
                dispose();
            }
        }

    }
}
