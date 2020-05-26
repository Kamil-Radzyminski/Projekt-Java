package Proj.GridModify;



import Proj.Listeners.GatunekModifyIDClickListener;
import Proj.Listeners.GatunekModifyRodzinaIDClickListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;



/**
 *
 * @author Kamil
 */
public class GridGatunekModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwa, opis;
    private Integer id, rodzina_id;
    private JFrame frame;

    public GridGatunekModify() {
        this.frame = new JFrame("Modyfikacja gatunku");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRodzinaId(Integer sector_id) {
        this.rodzina_id = rodzina_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz gatunek");
        frame.add(Glowny);
        Glowny.addActionListener(this);

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
            List<Gatunek> gatunekList = Gatunek.getList();
            if (zrodlo == Glowny) {

                String[] columns = {"id","nazwa", "opis"};
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

                GatunekModifyIDClickListener gatunekModifyIDClickListener = new GatunekModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(gatunekModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Obcy) {
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

                GatunekModifyRodzinaIDClickListener gatunekModifyRodzinaIDClickListener = new GatunekModifyRodzinaIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(gatunekModifyRodzinaIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < gatunekList.size(); i++) {
                    if (gatunekList.get(i).getId().equals(this.id)) {
                        Gatunek updatedGatunek = gatunekList.get(i);
                        if (this.rodzina_id != null) {
                            updatedGatunek.setRodzinaID(this.rodzina_id);
                        }
                        if (!this.nazwa.getText().trim().equals("")) {
                            updatedGatunek.setNazwa(this.nazwa.getText());
                        }
                        if (!this.opis.getText().trim().equals("")) {
                            updatedGatunek.setOpis(this.opis.getText());
                        }
                        
                        updatedGatunek.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
