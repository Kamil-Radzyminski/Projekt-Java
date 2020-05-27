/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;



import Proj.GridModify.GridAdminModify;
import Proj.crud.Models.Administrator;
import Proj.crud.Models.Magazyn;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Kamil
 */
public class AdminModifyIDClickListener extends AbstractClickAdapter {

    private final GridAdminModify grid;
    
    public AdminModifyIDClickListener(JTable jtable, JFrame jFrame, GridAdminModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        Integer adminId = Integer.parseInt(entryId.toString());
        Administrator administrator = new Administrator(adminId);
        try {
            administrator.getOne();
            grid.setId(administrator.getId());
            grid.getNazwa().setText(administrator.getNazwa());
            grid.getLogin().setText(administrator.getLogin());
            grid.getHaslo().setText(administrator.getPassword());

        } catch (SQLException ex) {
            Logger.getLogger(UserModifyIDClickListener.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.getJframe().dispose();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
