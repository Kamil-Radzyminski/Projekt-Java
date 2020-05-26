package Proj.GridModify;

import Proj.Listeners.MagazynModifyIDClickListener;
import Proj.Listeners.MagazynModifyUserIDClickListener;
import Proj.Listeners.TowarModifyIDClickListener;
import Proj.Listeners.TowarModifyMagazynIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Magazyn;
import Proj.crud.Models.Towar;
import Proj.crud.Models.User;
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
public class GridTowarModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwa, ilosc;
    private Integer id, magazyn_id;
    private JFrame frame;

    public GridTowarModify() {
        this.frame = new JFrame("GridLayout Test");
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMagazynID(Integer magazyn_id) {
        this.magazyn_id = magazyn_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz towar");
        frame.add(Glowny);
        Glowny.addActionListener(this);

        frame.add(new JLabel("Magazyn"));
        Obcy = new JButton("Wybierz magazyn");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("ilosc"));
        ilosc = new JTextField();
        frame.add(ilosc);

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
            List<Magazyn> magazynyList = Magazyn.getList();
            List<Towar> towaryList = Towar.getList();
            if (zrodlo == Glowny) {

                String[] columns = {"id","nazwa", "ilosc"};
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

                TowarModifyIDClickListener towarModifyIDClickListener = new TowarModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(towarModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

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

                TowarModifyMagazynIDClickListener towarModifyMagazynIDClickListener = new TowarModifyMagazynIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(towarModifyMagazynIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < towaryList.size(); i++) {
                    if (towaryList.get(i).getId().equals(this.id)) {
                        Towar updatedTowar = towaryList.get(i);
                        if (this.magazyn_id != null) {
                            updatedTowar.setMagazynID(this.magazyn_id);
                        }
                        if (!this.nazwa.getText().trim().equals("")) {
                            updatedTowar.setNazwa(this.nazwa.getText());
                        }
                        if (!this.ilosc.getText().trim().equals("")) {
                            updatedTowar.setIlosc(Double.valueOf(this.ilosc.getText()));
                        }

                        updatedTowar.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
