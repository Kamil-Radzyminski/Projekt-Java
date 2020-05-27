package Proj.GridAdd;

import Proj.Exceptions.ValidationException;
import Proj.Listeners.ZwierzeAddGatunekIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Gatunek;
import Proj.crud.Models.Zwierze;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Kamil
 */
public class GridZwierzeAdd implements ActionListener {

    private JButton Obcy, Dodaj;
    private JTextField plec, imie, wiek_lata, waga_kg;
    private Integer gatunek_id = -1;
    private JFrame frame;

    public GridZwierzeAdd() {
        this.frame = new JFrame("Dodawanie zwierzęcia");
    }

    public void setGatunekId(Integer gatunek_id) {
        this.gatunek_id = gatunek_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("Gatunek"));
        Obcy = new JButton("Wybierz gatunek");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Płeć (m/f)"));
        plec = new JTextField();
        frame.add(plec);

        frame.add(new JLabel("Imię"));
        imie = new JTextField();
        frame.add(imie);

        frame.add(new JLabel("Wiek w latach"));
        wiek_lata = new JTextField();
        frame.add(wiek_lata);

        frame.add(new JLabel("Waga w kilogramach"));
        waga_kg = new JTextField();
        frame.add(waga_kg);

        Dodaj = new JButton("Dodaj");
        frame.add(Dodaj);
        Dodaj.addActionListener(this);

        frame.pack();

        frame.setVisible(true);

//        haslo.setText("ASDF");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        JFrame jFrame = new JFrame();

        try {
            List<Gatunek> gatunekList = Gatunek.getList();

            if (zrodlo == Obcy) {
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

                ZwierzeAddGatunekIDClickListener zwierzeAddGatunekIDClickListener = new ZwierzeAddGatunekIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(zwierzeAddGatunekIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Dodaj) {
                String wiek = (this.wiek_lata.getText().equals("") ? "0" : this.wiek_lata.getText());
                String waga = (this.waga_kg.getText().equals("") ? "0" : this.waga_kg.getText());

                Zwierze zwierze = new Zwierze(
                        this.plec.getText(),
                        this.imie.getText(),
                        Integer.valueOf(wiek),
                        Integer.valueOf(waga),  
                        this.gatunek_id);

                zwierze.create();
                this.frame.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
