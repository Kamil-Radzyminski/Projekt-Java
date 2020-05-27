/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;



import Proj.GridModify.GridRodzinaModify;
import Proj.crud.Models.Rodzina;
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
public class RodzinaModifyIDClickListener extends AbstractClickAdapter {

    private final GridRodzinaModify grid;
    
    public RodzinaModifyIDClickListener(JTable jtable, JFrame jFrame, GridRodzinaModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);
        
        Integer rodzinaID = Integer.parseInt(entryId.toString());
        Rodzina rodzina = new Rodzina(rodzinaID);
        try {
            rodzina.getOne();
            grid.setId(rodzina.getId());
            grid.getNazwa().setText(rodzina.getNazwa());
            grid.getOpis().setText(rodzina.getOpis());

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
