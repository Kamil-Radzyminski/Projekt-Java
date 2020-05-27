/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;



import Proj.GridModify.GridTowarModify;
import Proj.crud.Models.Towar;
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
public class TowarModifyIDClickListener extends AbstractClickAdapter {

    private final GridTowarModify grid;
    
    public TowarModifyIDClickListener(JTable jtable, JFrame jFrame, GridTowarModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);
        
        Integer towarID = Integer.parseInt(entryId.toString());
        Towar towar = new Towar(towarID);
        try {
            towar.getOne();
            grid.setId(towar.getId());
            grid.getNazwa().setText(towar.getNazwa());
            grid.getIlosc().setText(towar.getIlosc().toString());


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
