package Proj.GridAdd;

import Proj.Exceptions.ValidationException;
import Proj.Listeners.MagazynAddUserIDClickListener;
import Proj.aplikacja;
import Proj.crud.Models.Magazyn;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Kamil
 */
public class GridMagazynAdd implements ActionListener {

    private JButton Obcy, Dodaj;
    private JTextField nazwa, przeznaczenie;
    private Integer uzytkownik_id = -1;
    private JFrame frame;

    public GridMagazynAdd() {
        this.frame = new JFrame("Dodawanie magazynu");
    }

    public void setUzytkownikID(Integer uzytkownik_id) {
        this.uzytkownik_id = uzytkownik_id;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("Użytkownik"));
        Obcy = new JButton("Wybierz przypisanego użytkownika");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("Przeznaczenie"));
        przeznaczenie = new JTextField();
        frame.add(przeznaczenie);

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
            List<User> userList = User.getList();

            if (zrodlo == Obcy) {
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

                MagazynAddUserIDClickListener magazynAddUserIDClickListener = new MagazynAddUserIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(magazynAddUserIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);
            }

            if (zrodlo == Dodaj) {
                Magazyn magazyn = new Magazyn(
                        this.uzytkownik_id,
                        this.nazwa.getText(),
                        this.przeznaczenie.getText());

                magazyn.create();
                this.frame.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
