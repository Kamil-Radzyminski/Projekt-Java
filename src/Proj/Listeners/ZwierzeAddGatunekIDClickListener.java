/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;

import Proj.GridAdd.GridZwierzeAdd;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Kamil
 */
public class ZwierzeAddGatunekIDClickListener extends AbstractClickAdapter {

    private final GridZwierzeAdd grid;

    public ZwierzeAddGatunekIDClickListener(JTable jtable, JFrame jFrame, GridZwierzeAdd grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        grid.setGatunekId(Integer.parseInt(entryId.toString()));

        this.getJframe().dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
