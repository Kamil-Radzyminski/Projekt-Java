package Proj;

import Proj.Listeners.User.UserDeleteClickListener;
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
public class Grid implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwisko, imie, rola, login, haslo;
    private Integer id, sector_id;
    private String lastname, firstname, role, log, pass;

    public void run() {
        JFrame frame = new JFrame("GridLayout Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz osobę");
        frame.add(Glowny);
        Glowny.addActionListener(this);

        frame.add(new JLabel("Sektor"));
        Obcy = new JButton("Wybierz sektor");
        frame.add(Obcy);

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

        frame.pack();

        frame.setVisible(true);

//        haslo.setText("ASDF");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        JFrame jFrame = new JFrame();

        if (zrodlo == Glowny) {
            try {
                List<User> userList = User.getList();
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

                UserDeleteClickListener userDeleteClickListener = new UserDeleteClickListener(jt1, jFrame);
                
                jt1.addMouseListener(userDeleteClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
