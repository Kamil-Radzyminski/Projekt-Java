package Proj.Listeners.User;

import Proj.Listeners.AbstractClickAdapter;
import Proj.crud.Models.User;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 * DeleteClickListener - Deletes User when clicked
 *
 * @author Kamil
 */
public class DeleteClickListener extends AbstractClickAdapter {

    public DeleteClickListener(JTable jtable) {
        super(jtable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        try {
            User user = new User(Integer.parseInt(entryId.toString()));
            user.delete();
            System.out.println("Usunąłem wiersz o ID:" + entryId.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DeleteClickListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
