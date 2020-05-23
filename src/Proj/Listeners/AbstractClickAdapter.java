package Proj.Listeners;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;

/**
 *
 * @author Kamil
 */
abstract public class AbstractClickAdapter extends MouseAdapter implements ActionListener {

    private final JTable jtable;

    public AbstractClickAdapter(JTable jtable) {
        this.jtable = jtable;
    }
    
    /**
     * Return JTable
     * @return 
     */
    public JTable getJtable(){
        return this.jtable;
    }
}
