package Proj.GridModify;

import Proj.Exceptions.ValidationException;
import Proj.Listeners.MagazynModifyIDClickListener;
import Proj.Listeners.MagazynModifyUserIDClickListener;
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
public class GridMagazynModify implements ActionListener {

    private JButton Glowny, Obcy, Aktualizuj;
    private JTextField nazwa, przeznaczenie;
    private Integer id, uzytkownik_id = -1;
    private JFrame frame;

    public GridMagazynModify() {
        this.frame = new JFrame("Modyfikacja Magazynu");
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUzytkownikID(Integer uzytkownik_id) {
        this.uzytkownik_id = uzytkownik_id;
    }
    
    
    public JTextField getNazwa(){
        return this.nazwa;
    }
    
    public JTextField getPrzeznaczenie(){
        return this.przeznaczenie;
    }

    public void run() {
        frame.setLayout(new GridLayout(0, 2, 2, 3));

        frame.add(new JLabel("ID"));
        Glowny = new JButton("Wybierz magazyn");
        frame.add(Glowny);
        Glowny.addActionListener(this);

        frame.add(new JLabel("Uzytkownik"));
        Obcy = new JButton("Wybierz przypisanego użytkownika");
        frame.add(Obcy);
        Obcy.addActionListener(this);

        frame.add(new JLabel("Nazwa"));
        nazwa = new JTextField();
        frame.add(nazwa);

        frame.add(new JLabel("Przeznaczenie"));
        przeznaczenie = new JTextField();
        frame.add(przeznaczenie);


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
            List<User> userList = User.getList();
            if (zrodlo == Glowny) {

                
                String[] columns = {"id","nazwa", "przeznaczenie"};
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

                MagazynModifyIDClickListener magazynModifyIDClickListener = new MagazynModifyIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(magazynModifyIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);

            }

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

                MagazynModifyUserIDClickListener magazynModifyUserIDClickListener = new MagazynModifyUserIDClickListener(jt1, jFrame, this);

                jt1.addMouseListener(magazynModifyUserIDClickListener);

                JScrollPane sp = new JScrollPane(jt1);
                jFrame.add(sp);
                jFrame.setLocation(200, 50);
                jFrame.setSize(300, 400);
                jFrame.setVisible(true);
            }

            if (zrodlo == Aktualizuj) {
                for (int i = 0; i < magazynyList.size(); i++) {
                    if (magazynyList.get(i).getId().equals(this.id)) {
                        Magazyn updatedMagazyn = magazynyList.get(i);
                            updatedMagazyn.setUzytkownikID(this.uzytkownik_id);
                            updatedMagazyn.setNazwa(this.nazwa.getText());
                            updatedMagazyn.setPrzeznaczenie(this.przeznaczenie.getText());
                        
                        
                        updatedMagazyn.update();
                        this.frame.dispose();
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(aplikacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
