package Proj.GridModify;


import Proj.Listeners.RodzinaModifyGromadaIDClickListener;
import Proj.Listeners.RodzinaModifyIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Gromada;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;



/**
 *
 * @author Kamil
 */
public class GridRodzinaModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwa, opis;
    private Integer id, gromada_id;
    private JFrame frame;

    public GridRodzinaModify() {
        this.frame = new JFrame("Modyfikacja rodziny");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGromadaId(Integer sector_id) {
        this.gromada_id = gromada_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz rodzinę");
        frame.add(Glowny);
        Glowny.addActionListener(this);

        frame.add(new JLabel("Gromada"));
        Obcy = new JButton("Wybierz gromadę");
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
            List<Rodzina> rodzinaList = Rodzina.getList();
            List<Gromada> gromadaList = Gromada.getList();
            if (zrodlo == Glowny) {

                String[] columns = {"id","nazwa", "opis"};
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

                RodzinaModifyIDClickListener rodzinaModifyIDClickListener = new RodzinaModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(rodzinaModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Obcy) {
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

                RodzinaModifyGromadaIDClickListener rodzinaModifyGromadaIDClickListener = new RodzinaModifyGromadaIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(rodzinaModifyGromadaIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < rodzinaList.size(); i++) {
                    if (rodzinaList.get(i).getId().equals(this.id)) {
                        Rodzina updatedRodzina = rodzinaList.get(i);
                        if (this.gromada_id != null) {
                            updatedRodzina.setGromadaID(this.gromada_id);
                        }
                        if (!this.nazwa.getText().trim().equals("")) {
                            updatedRodzina.setNazwa(this.nazwa.getText());
                        }
                        if (!this.opis.getText().trim().equals("")) {
                            updatedRodzina.setOpis(this.opis.getText());
                        }
                        
                        updatedRodzina.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
