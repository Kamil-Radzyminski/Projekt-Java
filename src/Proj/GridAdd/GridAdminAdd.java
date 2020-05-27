package Proj.GridAdd;

import Proj.Exceptions.ValidationException;
import Proj.aplikacja;
import Proj.crud.Models.Administrator;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Kamil
 */
public class GridAdminAdd implements ActionListener {

    private JButton Dodaj;
    private JTextField nazwa, login, haslo;
    private JFrame frame;

    public GridAdminAdd() {
        this.frame = new JFrame("Dodawanie administratora");
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("Login"));
        login = new JTextField();
        frame.add(login);

        frame.add(new JLabel("Haslo"));
        haslo = new JTextField();
        frame.add(haslo);

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

        try {

            if (zrodlo == Dodaj) {
                    Administrator admin = new Administrator(
                            this.nazwa.getText(),
                            this.login.getText(),
                            this.haslo.getText());

                    admin.create();
                    this.frame.dispose();
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
