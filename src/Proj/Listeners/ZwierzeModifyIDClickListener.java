/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;



import Proj.GridModify.GridZwierzeModify;
import Proj.crud.Models.User;
import Proj.crud.Models.Zwierze;
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
public class ZwierzeModifyIDClickListener extends AbstractClickAdapter {

    private final GridZwierzeModify grid;
    
    public ZwierzeModifyIDClickListener(JTable jtable, JFrame jFrame, GridZwierzeModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);
        
        Integer zwierzeID = Integer.parseInt(entryId.toString());
        Zwierze zwierze = new Zwierze(zwierzeID);
        try {
            zwierze.getOne();
            grid.setId(zwierze.getId());
            grid.getImie().setText(zwierze.getImie());
            grid.getPlec().setText(zwierze.getPlec());
            grid.getWaga().setText(zwierze.getWaga().toString());
            grid.getWiek().setText(zwierze.getWiek().toString());

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
