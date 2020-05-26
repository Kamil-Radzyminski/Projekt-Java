/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;

import Proj.GridAdd.GridZwierzeAdd;
import Proj.GridAdd.GridTowarAdd;
import Proj.GridAdd.GridAdminAdd;
import Proj.GridAdd.GridGatunekAdd;
import Proj.GridAdd.GridGromadaAdd;
import Proj.GridAdd.GridMagazynAdd;
import Proj.GridAdd.GridRodzinaAdd;
import Proj.GridAdd.GridUserAdd;
import Proj.GridModify.GridZwierzeModify;
import Proj.GridModify.GridGatunekModify;
import Proj.GridModify.GridMagazynModify;
import Proj.GridModify.GridAdminModify;
import Proj.GridModify.GridGromadaModify;
import Proj.GridModify.GridRodzinaModify;
import Proj.GridModify.GridTowarModify;
import Proj.GridModify.GridUserModify;
import Proj.Listeners.ZwierzeDeleteClickListener;
import Proj.Listeners.GatunekDeleteClickListener;
import Proj.Listeners.RodzinaDeleteClickListener;
import Proj.Listeners.GromadaDeleteClickListener;
import Proj.Listeners.MagazynDeleteClickListener;
import Proj.Listeners.AdminDeleteClickListener;
import Proj.Listeners.TowarDeleteClickListener;
import Proj.Listeners.UserDeleteClickListener;
import javax.swing.table.TableColumnModel;
import Proj.crud.Models.User;
import Proj.crud.Models.Administrator;
import Proj.crud.Models.Gatunek;
import Proj.crud.Models.Rodzina;
import Proj.crud.Models.Gromada;
import Proj.crud.Models.Zwierze;
import Proj.crud.Models.Magazyn;
import Proj.crud.Models.Towar;
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
    JMenuItem Dodaj_Przedmiot, Dodaj_Magazyn, Dodaj_Pracownika, Dodaj_Administratora, Dodaj_Zwierze, Dodaj_Gatunek, Dodaj_Rodzine, Dodaj_Gromade;

    //ITEMS WYSWIETL---------------------------------
    JMenuItem Wyswietl_Zwierze, Wyswietl_Gatunek, Wyswietl_Rodzine, Wyswietl_Gromade, Wyswietl_Przedmiot, Wyswietl_Magazyn, Wyswietl_Pracownika, Wyswietl_Administratora;

    //ITEMS MODYFIKUJ--------------------------------
    JMenuItem Modyfikuj_Przedmiot, Modyfikuj_Magazyn, Modyfikuj_Pracownika, Modyfikuj_Administratora, Modyfikuj_Zwierze, Modyfikuj_Gatunek, Modyfikuj_Rodzine, Modyfikuj_Gromade;

    //ITEMS USUN-------------------------------------
    JMenuItem Usun_Przedmiot, Usun_Magazyn, Usun_Pracownika, Usun_Administratora, Usun_Zwierze, Usun_Gatunek, Usun_Rodzine, Usun_Gromade;

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
        Dodaj_Administratora = new JMenuItem("Administratora");
        Dodaj_Osobe.add(Dodaj_Pracownika);
        Dodaj_Osobe.add(Dodaj_Administratora);

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
        Wyswietl_Administratora = new JMenuItem("Administratorów");
        Wyswietl_Osobe.add(Wyswietl_Pracownika);
        Wyswietl_Osobe.add(Wyswietl_Administratora);

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
        Modyfikuj_Administratora = new JMenuItem("Administratora");
        Modyfikuj_Osobe.add(Modyfikuj_Pracownika);
        Modyfikuj_Osobe.add(Modyfikuj_Administratora);

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
        Usun_Administratora = new JMenuItem("Administratora");
        Usun_Osobe.add(Usun_Pracownika);
        Usun_Osobe.add(Usun_Administratora);

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
        Dodaj_Administratora.addActionListener(this);
        // --------------------------------------------------------------      

        Wyswietl_Zwierze.addActionListener(this);
        Wyswietl_Gatunek.addActionListener(this);
        Wyswietl_Rodzine.addActionListener(this);
        Wyswietl_Gromade.addActionListener(this);

        Wyswietl_Przedmiot.addActionListener(this);
        Wyswietl_Magazyn.addActionListener(this);

        Wyswietl_Pracownika.addActionListener(this);
        Wyswietl_Administratora.addActionListener(this);

// --------------------------------------------------------------   
        Modyfikuj_Zwierze.addActionListener(this);
        Modyfikuj_Gatunek.addActionListener(this);
        Modyfikuj_Rodzine.addActionListener(this);
        Modyfikuj_Gromade.addActionListener(this);

        Modyfikuj_Przedmiot.addActionListener(this);
        Modyfikuj_Magazyn.addActionListener(this);

        Modyfikuj_Pracownika.addActionListener(this);
        Modyfikuj_Administratora.addActionListener(this);

//--------------------------------------------------------------   
        Usun_Zwierze.addActionListener(this);
        Usun_Gatunek.addActionListener(this);
        Usun_Rodzine.addActionListener(this);
        Usun_Gromade.addActionListener(this);

        Usun_Przedmiot.addActionListener(this);
        Usun_Magazyn.addActionListener(this);

        Usun_Pracownika.addActionListener(this);
        Usun_Administratora.addActionListener(this);

        //--------------------------------------------------------------
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
            try {
                GridZwierzeAdd grid = new GridZwierzeAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Dodaj_Gatunek) {
            try {
                GridGatunekAdd grid = new GridGatunekAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Dodaj_Rodzine) {
            try {
                GridRodzinaAdd grid = new GridRodzinaAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Dodaj_Gromade) {
            try {
                GridGromadaAdd grid = new GridGromadaAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Dodaj_Przedmiot) {
            try {
                GridTowarAdd grid = new GridTowarAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Dodaj_Magazyn) {
            try {
                GridMagazynAdd grid = new GridMagazynAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Dodaj_Pracownika) {

            try {
                GridUserAdd grid = new GridUserAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }

        }

        if (zrodlo == Dodaj_Administratora) {

            try {
                GridAdminAdd grid = new GridAdminAdd();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }

        }
//WYSWIETL----------------------------------------------------------------------        
        if (zrodlo == Wyswietl_Zwierze) {
            try {
                List<Zwierze> zwierzeList = Zwierze.getList();
                String[] columns = {"plec", "imie", "wiek_lata", "waga_kg"};
                String[][] zwierzetaArray = new String[zwierzeList.size()][columns.length];

                for (int i = 0; i < zwierzeList.size(); i++) {
                    Zwierze currentZwierze = zwierzeList.get(i);

                    String[] singleZwierze = {
                        currentZwierze.getPlec(),
                        currentZwierze.getImie(),
                        currentZwierze.getWiek().toString(),
                        currentZwierze.getWaga().toString()
                    };
                    zwierzetaArray[i] = singleZwierze;

                }

                JTable jt1 = new JTable(zwierzetaArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Gatunek) {
            try {
                List<Gatunek> gatunekList = Gatunek.getList();
                String[] columns = {"nazwa", "opis"};
                String[][] gatunekArray = new String[gatunekList.size()][columns.length];

                for (int i = 0; i < gatunekList.size(); i++) {
                    Gatunek currentGatunek = gatunekList.get(i);

                    String[] singleGatunek = {
                        currentGatunek.getNazwa(),
                        currentGatunek.getOpis()
                    };
                    gatunekArray[i] = singleGatunek;

                }

                JTable jt1 = new JTable(gatunekArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Rodzine) {
            try {
                List<Rodzina> rodzinaList = Rodzina.getList();
                String[] columns = {"nazwa", "opis"};
                String[][] rodzinaArray = new String[rodzinaList.size()][columns.length];

                for (int i = 0; i < rodzinaList.size(); i++) {
                    Rodzina currentRodzina = rodzinaList.get(i);

                    String[] singleRodzina = {
                        currentRodzina.getNazwa(),
                        currentRodzina.getOpis()
                    };
                    rodzinaArray[i] = singleRodzina;

                }

                JTable jt1 = new JTable(rodzinaArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Gromade) {
            try {
                List<Gromada> gromadaList = Gromada.getList();
                String[] columns = {"nazwa", "opis"};
                String[][] gromadaArray = new String[gromadaList.size()][columns.length];

                for (int i = 0; i < gromadaList.size(); i++) {
                    Gromada currentGromada = gromadaList.get(i);

                    String[] singleGromada = {
                        currentGromada.getNazwa(),
                        currentGromada.getOpis()
                    };
                    gromadaArray[i] = singleGromada;

                }

                JTable jt1 = new JTable(gromadaArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Przedmiot) {
            try {
                List<Towar> towaryList = Towar.getList();
                String[] columns = {"nazwa", "ilosc"};
                String[][] towarArray = new String[towaryList.size()][columns.length];

                for (int i = 0; i < towaryList.size(); i++) {
                    Towar currentTowar = towaryList.get(i);

                    String[] singleTowar = {
                        currentTowar.getNazwa(),
                        currentTowar.getIlosc().toString()
                    };
                    towarArray[i] = singleTowar;

                }

                JTable jt1 = new JTable(towarArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Wyswietl_Magazyn) {
            try {
                List<Magazyn> magazynyList = Magazyn.getList();
                String[] columns = {"nazwa", "przeznaczenie"};
                String[][] magazynArray = new String[magazynyList.size()][columns.length];

                for (int i = 0; i < magazynyList.size(); i++) {
                    Magazyn currentMagazyn = magazynyList.get(i);

                    String[] singleMagazyn = {
                        currentMagazyn.getNazwa(),
                        currentMagazyn.getPrzeznaczenie(),};
                    magazynArray[i] = singleMagazyn;

                }

                JTable jt1 = new JTable(magazynArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

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

        if (zrodlo == Wyswietl_Administratora) {
            try {
                List<Administrator> administratorList = Administrator.getList();
                String[] columns = {"nazwa"};
                String[][] administratorsArray = new String[administratorList.size()][columns.length];

                for (int i = 0; i < administratorList.size(); i++) {
                    Administrator currentAdministrator = administratorList.get(i);

                    String[] singleAdministrator = {
                        currentAdministrator.getNazwa()
                    };
                    administratorsArray[i] = singleAdministrator;

                }

                JTable jt1 = new JTable(administratorsArray, columns);
                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//USUN--------------------------------------------------------------------------
        if (zrodlo == Usun_Zwierze) {
            try {
                List<Zwierze> zwierzeList = Zwierze.getList();
                String[] columns = {"id", "plec", "imie", "wiek_lata", "waga_kg"};
                String[][] zwierzetaArray = new String[zwierzeList.size()][columns.length];

                for (int i = 0; i < zwierzeList.size(); i++) {
                    Zwierze currentZwierze = zwierzeList.get(i);

                    String[] singleZwierze = {
                        currentZwierze.getId().toString(),
                        currentZwierze.getPlec(),
                        currentZwierze.getImie(),
                        currentZwierze.getWiek().toString(),
                        currentZwierze.getWaga().toString()
                    };
                    zwierzetaArray[i] = singleZwierze;

                }

                JTable jt1 = new JTable(zwierzetaArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                ZwierzeDeleteClickListener zwierzeDeleteClickListener = new ZwierzeDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(zwierzeDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Gatunek) {
            try {
                List<Gatunek> gatunekList = Gatunek.getList();
                String[] columns = {"id", "nazwa", "opis"};
                String[][] gatunekArray = new String[gatunekList.size()][columns.length];

                for (int i = 0; i < gatunekList.size(); i++) {
                    Gatunek currentGatunek = gatunekList.get(i);

                    String[] singleGatunek = {
                        currentGatunek.getId().toString(),
                        currentGatunek.getNazwa(),
                        currentGatunek.getOpis()
                    };
                    gatunekArray[i] = singleGatunek;

                }

                JTable jt1 = new JTable(gatunekArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                GatunekDeleteClickListener gatunekDeleteClickListener = new GatunekDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(gatunekDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Pracownika) {
            try {
                List<User> userList = User.getList();
                String[] columns = {"id", "imie", "nazwisko", "rola"};
                String[][] usersArray = new String[userList.size()][columns.length];

                for (int i = 0; i < userList.size(); i++) {
                    User currentUser = userList.get(i);

                    String[] singleUser = {
                        currentUser.getId().toString(),
                        currentUser.getFirstname(),
                        currentUser.getLastname(),
                        currentUser.getRole()
                    };
                    usersArray[i] = singleUser;

                }

                JTable jt1 = new JTable(usersArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                UserDeleteClickListener userDeleteClickListener = new UserDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(userDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Rodzine) {
            try {
                List<Rodzina> rodzinaList = Rodzina.getList();
                String[] columns = {"id", "nazwa", "opis"};
                String[][] rodzinaArray = new String[rodzinaList.size()][columns.length];

                for (int i = 0; i < rodzinaList.size(); i++) {
                    Rodzina currentRodzina = rodzinaList.get(i);

                    String[] singleRodzina = {
                        currentRodzina.getId().toString(),
                        currentRodzina.getNazwa(),
                        currentRodzina.getOpis()
                    };
                    rodzinaArray[i] = singleRodzina;

                }

                JTable jt1 = new JTable(rodzinaArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                RodzinaDeleteClickListener rodzinaDeleteClickListener = new RodzinaDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(rodzinaDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Gromade) {
            try {
                List<Gromada> gromadaList = Gromada.getList();
                String[] columns = {"id", "nazwa", "opis"};
                String[][] gromadaArray = new String[gromadaList.size()][columns.length];

                for (int i = 0; i < gromadaList.size(); i++) {
                    Gromada currentGromada = gromadaList.get(i);

                    String[] singleGromada = {
                        currentGromada.getId().toString(),
                        currentGromada.getNazwa(),
                        currentGromada.getOpis()
                    };
                    gromadaArray[i] = singleGromada;

                }

                JTable jt1 = new JTable(gromadaArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                GromadaDeleteClickListener gromadaDeleteClickListener = new GromadaDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(gromadaDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Przedmiot) {
            try {
                List<Towar> towaryList = Towar.getList();
                String[] columns = {"id", "nazwa", "ilosc"};
                String[][] towarArray = new String[towaryList.size()][columns.length];

                for (int i = 0; i < towaryList.size(); i++) {
                    Towar currentTowar = towaryList.get(i);

                    String[] singleTowar = {
                        currentTowar.getId().toString(),
                        currentTowar.getNazwa(),
                        currentTowar.getIlosc().toString()
                    };
                    towarArray[i] = singleTowar;

                }

                JTable jt1 = new JTable(towarArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                TowarDeleteClickListener towarDeleteClickListener = new TowarDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(towarDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Magazyn) {
            try {
                List<Magazyn> magazynyList = Magazyn.getList();
                String[] columns = {"id", "nazwa", "przeznaczenie"};
                String[][] magazynArray = new String[magazynyList.size()][columns.length];

                for (int i = 0; i < magazynyList.size(); i++) {
                    Magazyn currentMagazyn = magazynyList.get(i);

                    String[] singleMagazyn = {
                        currentMagazyn.getId().toString(),
                        currentMagazyn.getNazwa(),
                        currentMagazyn.getPrzeznaczenie()
                    };
                    magazynArray[i] = singleMagazyn;

                }

                JTable jt1 = new JTable(magazynArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                MagazynDeleteClickListener magazynDeleteClickListener = new MagazynDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(magazynDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (zrodlo == Usun_Administratora) {
            try {
                List<Administrator> administratorList = Administrator.getList();
                String[] columns = {"id", "nazwa"};
                String[][] administratorsArray = new String[administratorList.size()][columns.length];

                for (int i = 0; i < administratorList.size(); i++) {
                    Administrator currentAdministrator = administratorList.get(i);

                    String[] singleAdministrator = {
                        currentAdministrator.getId().toString(),
                        currentAdministrator.getNazwa()
                    };
                    administratorsArray[i] = singleAdministrator;

                }

                JTable jt1 = new JTable(administratorsArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                AdminDeleteClickListener adminDeleteClickListener = new AdminDeleteClickListener(jt1, jFrame);

                jt1.addMouseListener(adminDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//MODYFIKUJ-------------------------------------------------------------------------

        if (zrodlo == Modyfikuj_Zwierze) {
            try {
                GridZwierzeModify grid = new GridZwierzeModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Modyfikuj_Rodzine) {
            try {
                GridRodzinaModify grid = new GridRodzinaModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Modyfikuj_Gatunek) {
            try {
                GridGatunekModify grid = new GridGatunekModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Modyfikuj_Gromade) {
            try {
                GridGromadaModify grid = new GridGromadaModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Modyfikuj_Przedmiot) {
            try {
                GridTowarModify grid = new GridTowarModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Modyfikuj_Magazyn) {
            try {
                GridMagazynModify grid = new GridMagazynModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

        if (zrodlo == Modyfikuj_Pracownika) {
            try {
                GridUserModify grid = new GridUserModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }

        }

        if (zrodlo == Modyfikuj_Administratora) {
            try {
                GridAdminModify grid = new GridAdminModify();
                grid.run();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
            }
        }

//------------------------------------------------------------------------------        
        if (zrodlo == Wyjdz) {
            int odp = JOptionPane.showConfirmDialog(null, "Czy na pewno?", "Pytanie", JOptionPane.YES_NO_OPTION);
            if (odp == JOptionPane.YES_OPTION) {
                dispose();
            }
        }

        if (zrodlo == Informacja) {
            JOptionPane.showMessageDialog(this, "W tym menu możesz dodać, wyświetlić, zmodyfikować lub usunąć rekord. Zablokowane są akcje, do kórych nie masz uprawnień.",
                    "Informacja", JOptionPane.WARNING_MESSAGE); //okienko dialogowe typu "Message"
            //JOptionPane.WARNING_MESSAGE - "wbudowana" ikona znajdująca się z lewej strony
        }

    }
}
