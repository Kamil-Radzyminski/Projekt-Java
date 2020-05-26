/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.Listeners;

import Proj.GridModify.GridUserModify;
import Proj.crud.Models.User;
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
public class UserModifyIDClickListener extends AbstractClickAdapter {

    private final GridUserModify grid;

    public UserModifyIDClickListener(JTable jtable, JFrame jFrame, GridUserModify grid) {
        super(jtable, jFrame);
        this.grid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object entryId = this.getJtable().getModel().getValueAt(this.getJtable().getSelectedRow(), 0);

        Integer userID = Integer.parseInt(entryId.toString());
        User user = new User(userID);
        try {
            user.getOne();
            grid.setId(user.getId());
            grid.getImie().setText(user.getFirstname());
            grid.getNazwisko().setText(user.getLastname());
            grid.getRola().setText(user.getRole());
            grid.getLogin().setText(user.getLogin());
            grid.getHaslo().setText(user.getPassword());
            grid.setSectorId(user.getSectorId());

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
