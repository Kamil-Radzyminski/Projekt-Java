package Proj.Listeners;

import Proj.crud.Models.Zwierze;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * DeleteClickListener - Deletes User when clicked
 *
 * @author Kamil
 */
public class ZwierzeDeleteClickListener extends AbstractClickAdapter {

    public ZwierzeDeleteClickListener(JTable jtable, JFrame jFrame) {
        super(jtable, jFrame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        try {
            Zwierze zwierze = new Zwierze(Integer.parseInt(entryId.toString()));
            zwierze.delete();
//            System.out.println("Usunąłem wiersz o ID:" + entryId.toString());
            JOptionPane.showMessageDialog(null, "Usunięto rekord");
            this.getJframe().dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nie możesz usunąć tego rekordu");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
