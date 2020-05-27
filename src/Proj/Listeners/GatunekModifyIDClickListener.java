/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;



import Proj.GridModify.GridGatunekModify;
import Proj.crud.Models.Gatunek;
import Proj.crud.Models.Gromada;
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
public class GatunekModifyIDClickListener extends AbstractClickAdapter {

    private final GridGatunekModify grid;
    
    public GatunekModifyIDClickListener(JTable jtable, JFrame jFrame, GridGatunekModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);
        
        Integer gatunekID = Integer.parseInt(entryId.toString());
        Gatunek gatunek = new Gatunek(gatunekID);
        try {
            gatunek.getOne();
            grid.setId(gatunek.getId());
            grid.getNazwa().setText(gatunek.getNazwa());
            grid.getOpis().setText(gatunek.getOpis());

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
