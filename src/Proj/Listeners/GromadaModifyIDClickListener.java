/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;



import Proj.GridModify.GridGromadaModify;
import Proj.crud.Models.Gromada;
import Proj.crud.Models.Rodzina;
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
public class GromadaModifyIDClickListener extends AbstractClickAdapter {

    private final GridGromadaModify grid;
    
    public GromadaModifyIDClickListener(JTable jtable, JFrame jFrame, GridGromadaModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);
        
        Integer gromadaID = Integer.parseInt(entryId.toString());
        Gromada gromada = new Gromada(gromadaID);
        try {
            gromada.getOne();
            grid.setId(gromada.getId());
            grid.getNazwa().setText(gromada.getNazwa());
            grid.getOpis().setText(gromada.getOpis());

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
