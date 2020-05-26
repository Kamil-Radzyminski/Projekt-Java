package Proj.GridAdd;

import Proj.Listeners.TowarAddMagazynIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Magazyn;
import Proj.crud.Models.Towar;
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
public class GridTowarAdd implements ActionListener {

    private JButton Obcy, Dodaj;
    private JTextField nazwa, ilosc;
    private Integer magazyn_id = -1;
    private JFrame frame;

    public GridTowarAdd() {
        this.frame = new JFrame("GridLayout Test");
    }

    public void setMagazynID(Integer magazyn_id) {
        this.magazyn_id = magazyn_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("Magazyn"));
        Obcy = new JButton("Wybierz magazyn");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("Ilość"));
        ilosc = new JTextField();
        frame.add(ilosc);

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
            List<Magazyn> magazynyList = Magazyn.getList();

            if (zrodlo == Obcy) {
                String[] columns = {"id", "nazwa", "przeznaczenie"};
                String[][] magazynArray = new String[magazynyList.size()][columns.length];

                for (int i = 0; i < magazynyList.size(); i++) {
                    Magazyn currentMagazyn = magazynyList.get(i);

                    String[] singleMagazyn = {
                        currentMagazyn.getId().toString(),
                        currentMagazyn.getNazwa(),
                        currentMagazyn.getPrzeznaczenie(),};
                    magazynArray[i] = singleMagazyn;

                }

                JTable jt1 = new JTable(magazynArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                TowarAddMagazynIDClickListener towarAddMagazynIDClickListener = new TowarAddMagazynIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(towarAddMagazynIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Dodaj) {

                if (this.magazyn_id == -1
                        || this.nazwa.getText().trim().equals("")
                        || this.ilosc.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Proszę wypełnić wszystkie pola");
                } else {

                    Towar towar = new Towar(
                            this.magazyn_id,
                            this.nazwa.getText(),
                            Double.valueOf(this.ilosc.getText())
                    );

                    towar.create();
                    this.frame.dispose();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
