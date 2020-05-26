package Proj.GridModify;

import Proj.Listeners.UserModifySectorIDClickListener;
import Proj.Listeners.UserModifyIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Sektor;
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
public class GridUserModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwisko, imie, rola, login, haslo;
    private Integer id, sector_id;
    private JFrame frame;

    public GridUserModify() {
        this.frame = new JFrame("GridLayout Test");
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
        Glowny = new JButton("Wybierz osobę");
        frame.add(Glowny);
        Glowny.addActionListener(this);

        frame.add(new JLabel("Sektor"));
        Obcy = new JButton("Wybierz sektor");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Nazwisko"));
        nazwisko = new JTextField();
        frame.add(nazwisko);

        frame.add(new JLabel("Imię"));
        imie = new JTextField();
        frame.add(imie);

        frame.add(new JLabel("Rola"));
        rola = new JTextField();
        frame.add(rola);

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
            List<Sektor> sektorList = Sektor.getList();
            List<User> userList = User.getList();
            if (zrodlo == Glowny) {

                String[] columns = {"id", "imie", "nazwisko", "rola"};
                String[][] usersArray = new String[userList.size()][columns.length];

                for (int i = 0; i < userList.size(); i++) {
                    User currentUser = userList.get(i);

                    String[] singleUser = {
                        currentUser.getId().toString(),
                        currentUser.getFirstname(),
                        currentUser.getLastname(),
                        currentUser.getRole()
                    };
                    usersArray[i] = singleUser;

                }

                JTable jt1 = new JTable(usersArray, columns);

                TableColumnModel tcm = jt1.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                UserModifyIDClickListener userModifyIDClickListener = new UserModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(userModifyIDClickListener);

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

                UserModifySectorIDClickListener userModifySectorIDClickListener = new UserModifySectorIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(userModifySectorIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);
            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getId().equals(this.id)) {
                        User updatedUser = userList.get(i);
                        if (this.sector_id != null) {
                            updatedUser.setSectorId(this.sector_id);
                        }
                        if (!this.imie.getText().trim().equals("")) {
                            updatedUser.setFirstname(this.imie.getText());
                        }
                        if (!this.nazwisko.getText().trim().equals("")) {
                            updatedUser.setLastname(this.nazwisko.getText());
                        }
                        if (!this.rola.getText().trim().equals("")) {
                            updatedUser.setRole(this.rola.getText());
                        }
                        if (!this.login.getText().trim().equals("")) {
                            updatedUser.setLogin(this.login.getText());
                        }
                        if (!this.haslo.getText().trim().equals("")) {
                            updatedUser.setPassword(this.haslo.getText());
                        }
                        updatedUser.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
