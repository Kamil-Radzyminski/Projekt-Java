/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;

import Proj.crud.Models.Administrator;
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
public class AdminDeleteClickListener extends AbstractClickAdapter {

    public AdminDeleteClickListener(JTable jtable, JFrame jFrame) {
        super(jtable, jFrame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        try {
            Administrator admin = new Administrator(Integer.parseInt(entryId.toString()));
            admin.delete();
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
