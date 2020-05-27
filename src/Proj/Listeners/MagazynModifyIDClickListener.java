/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;

import Proj.GridModify.GridMagazynModify;
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
public class MagazynModifyIDClickListener extends AbstractClickAdapter {

    private final GridMagazynModify grid;

    public MagazynModifyIDClickListener(JTable jtable, JFrame jFrame, GridMagazynModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        Integer magazynID = Integer.parseInt(entryId.toString());
        Magazyn magazyn = new Magazyn(magazynID);
        try {
            magazyn.getOne();
            grid.setId(magazyn.getId());
            grid.getNazwa().setText(magazyn.getNazwa());
            grid.getPrzeznaczenie().setText(magazyn.getPrzeznaczenie());

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
