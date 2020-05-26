package Proj.GridModify;

import Proj.Listeners.AdminModifyIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Administrator;
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
public class GridAdminModify implements ActionListener {

    private JButton Glowny, Aktualizuj;
    private JTextField nazwa, login, haslo;
    private Integer id;
    private JFrame frame;

    public GridAdminModify() {
        this.frame = new JFrame("GridLayout Test");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz osobę");
        frame.add(Glowny);
        Glowny.addActionListener(this);

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("Login"));
        login = new JTextField();
        frame.add(login);

        frame.add(new JLabel("Haslo"));
        haslo = new JTextField();
        frame.add(haslo);

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
//            List<Sektor> sektorList = Sektor.getList();
            List<Administrator> administratorList = Administrator.getList();
            if (zrodlo == Glowny) {

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

                AdminModifyIDClickListener adminModifyIDClickListener = new AdminModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(adminModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < administratorList.size(); i++) {
                    if (administratorList.get(i).getId().equals(this.id)) {
                        Administrator updatedAdministrator = administratorList.get(i);
                        if (!this.nazwa.getText().trim().equals("")) {
                            updatedAdministrator.setNazwa(this.nazwa.getText());
                        }
                        if (!this.login.getText().trim().equals("")) {
                            updatedAdministrator.setLogin(this.login.getText());
                        }
                        if (!this.haslo.getText().trim().equals("")) {
                            updatedAdministrator.setPassword(this.haslo.getText());
                        }
                        updatedAdministrator.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
