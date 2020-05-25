/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners.User;

import Proj.Listeners.AbstractClickAdapter;
import Proj.crud.Models.Gatunek;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * DeleteClickListener - Deletes User when clicked
 *
 * @author Kamil
 */
public class GatunekDeleteClickListener extends AbstractClickAdapter {

    public GatunekDeleteClickListener(JTable jtable) {
        super(jtable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        try {
            Gatunek gatunek = new Gatunek(Integer.parseInt(entryId.toString()));
            gatunek.delete();
//            System.out.println("Usunąłem wiersz o ID:" + entryId.toString());
            JOptionPane.showMessageDialog(null, "Usunięto rekord");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nie możesz usunąć tego rekordu");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
