package Proj.GridAdd;

import Proj.Listeners.GatunekAddRodzinaIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Gatunek;
import Proj.crud.Models.Rodzina;
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
public class GridGatunekAdd implements ActionListener {

    private JButton Obcy, Dodaj;
    private JTextField nazwa, opis;
    private Integer rodzina_id = -1;
    private JFrame frame;

    public GridGatunekAdd() {
        this.frame = new JFrame("GridLayout Test");
    }

    public void setRodzinaId(Integer rodzina_id) {
        this.rodzina_id = rodzina_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("Rodzina"));
        Obcy = new JButton("Wybierz rodzinę");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("Opis"));
        opis = new JTextField();
        frame.add(opis);

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
            List<Rodzina> rodzinaList = Rodzina.getList();

            if (zrodlo == Obcy) {
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

                GatunekAddRodzinaIDClickListener gatunekAddRodzinaIDClickListener = new GatunekAddRodzinaIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(gatunekAddRodzinaIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Dodaj) {
                if (this.opis.getText().trim().equals("")
                        || this.nazwa.getText().trim().equals("")
                        || this.rodzina_id == -1) {
                    JOptionPane.showMessageDialog(null, "Proszę wypełnić wszystkie pola");
                } else {

                    Gatunek gatunek = new Gatunek(
                            this.rodzina_id,
                            this.nazwa.getText(),
                            this.opis.getText()
                    );

                    gatunek.create();
                    this.frame.dispose();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
