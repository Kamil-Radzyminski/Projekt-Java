package Proj.GridAdd;

import Proj.Exceptions.ValidationException;
import Proj.Listeners.GromadaAddSectorIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Gromada;
import Proj.crud.Models.Sektor;
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
public class GridGromadaAdd implements ActionListener {

    private JButton Obcy, Dodaj;
    private JTextField nazwa, opis;
    private Integer sector_id = -1;
    private JFrame frame;

    public GridGromadaAdd() {
        this.frame = new JFrame("GridLayout Test");
    }

    public void setSectorId(Integer sector_id) {
        this.sector_id = sector_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("Sektor"));
        Obcy = new JButton("Wybierz sektor");
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
            List<Sektor> sektorList = Sektor.getList();

            if (zrodlo == Obcy) {
                String[] columns = {"id", "nazwa", "stan"};
                String[][] sectorsArray = new String[sektorList.size()][columns.length];

                for (int i = 0; i < sektorList.size(); i++) {
                    Sektor currentSektor = sektorList.get(i);

                    String[] singleSektor = {
                        currentSektor.getId().toString(),
                        currentSektor.getNazwa(),
                        currentSektor.getStan()
                    };
                    sectorsArray[i] = singleSektor;

                }

                JTable jt1 = new JTable(sectorsArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                GromadaAddSectorIDClickListener gromadaAddSectorIDClickListener = new GromadaAddSectorIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(gromadaAddSectorIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);
            }

            if (zrodlo == Dodaj) {
                Gromada gromada = new Gromada(
                        this.sector_id,
                        this.nazwa.getText(),
                        this.opis.getText());

                gromada.create();
                this.frame.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
