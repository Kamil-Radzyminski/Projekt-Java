package Proj.GridModify;

import Proj.Exceptions.ValidationException;
import Proj.Listeners.ZwierzeModifyGatunekIDClickListener;
import Proj.Listeners.ZwierzeModifyIDClickListener;
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
public class GridZwierzeModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField plec, imie, wiek_lata, waga_kg;
    private Integer id, gatunek_id = -1;
    private JFrame frame;

    public GridZwierzeModify() {
        this.frame = new JFrame("Modyfikacja zwierzęcia");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGatunekID(Integer sector_id) {
        this.gatunek_id = sector_id;
    }
    
    public JTextField getPlec(){
        return this.plec;
    }
    
    public JTextField getImie(){
        return this.imie;
    }
    
    public JTextField getWiek(){
        return this.wiek_lata;
    }
    
    public JTextField getWaga(){
        return this.waga_kg;
    }
    
    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz zwierze");
        frame.add(Glowny);
        Glowny.addActionListener(this);

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

        Aktualizuj = new JButton("Aktualizuj");
        frame.add(Aktualizuj);
        Aktualizuj.addActionListener(this);

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
            List<Zwierze> zwierzeList = Zwierze.getList();
            if (zrodlo == Glowny) {

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

                ZwierzeModifyIDClickListener zwierzeModifyIDClickListener = new ZwierzeModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(zwierzeModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

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

                ZwierzeModifyGatunekIDClickListener zwierzeModifyGatunekIDClickListener = new ZwierzeModifyGatunekIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(zwierzeModifyGatunekIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < zwierzeList.size(); i++) {
                    if (zwierzeList.get(i).getId().equals(this.id)) {
                        String wiek = (this.wiek_lata.getText().equals("") ? "0" : this.wiek_lata.getText());
                        String waga = (this.waga_kg.getText().equals("") ? "0" : this.waga_kg.getText());

                        Zwierze updatedZwierze = zwierzeList.get(i);
                        updatedZwierze.setGatunekID(this.gatunek_id);
                        updatedZwierze.setImie(this.imie.getText());
                        updatedZwierze.setPlec(this.plec.getText());
                        updatedZwierze.setWiek(Integer.valueOf(wiek));
                        updatedZwierze.setWaga(Integer.valueOf(waga));

                        updatedZwierze.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
