package Proj.GridModify;

import Proj.Listeners.GromadaModifyIDClickListener;
import Proj.Listeners.GromadaModifySectorIDClickListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;



/**
 *
 * @author Kamil
 */
public class GridGromadaModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwa, opis;
    private Integer id, sector_id;
    private JFrame frame;

    public GridGromadaModify() {
        this.frame = new JFrame("Modyfikacja gromady");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSectorId(Integer sector_id) {
        this.sector_id = sector_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz gromadę");
        frame.add(Glowny);
        Glowny.addActionListener(this);

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
            List<Sektor> sektorList = Sektor.getList();
            List<Gromada> gromadaList = Gromada.getList();
            if (zrodlo == Glowny) {

                String[] columns = {"id","nazwa", "opis"};
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

                GromadaModifyIDClickListener gromadaModifyIDClickListener = new GromadaModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(gromadaModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

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

                GromadaModifySectorIDClickListener gromadaModifySectorIDClickListener = new GromadaModifySectorIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(gromadaModifySectorIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);
            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < gromadaList.size(); i++) {
                    if (gromadaList.get(i).getId().equals(this.id)) {
                        Gromada updatedGromada = gromadaList.get(i);
                        if (this.sector_id != null) {
                            updatedGromada.setSektorID(this.sector_id);
                        }
                        if (!this.nazwa.getText().trim().equals("")) {
                            updatedGromada.setNazwa(this.nazwa.getText());
                        }
                        if (!this.opis.getText().trim().equals("")) {
                            updatedGromada.setOpis(this.opis.getText());
                        }
                        
                        updatedGromada.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
