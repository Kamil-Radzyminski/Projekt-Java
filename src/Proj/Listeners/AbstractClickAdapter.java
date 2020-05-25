package Proj.Listeners;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Kamil
 */
abstract public class AbstractClickAdapter extends MouseAdapter implements ActionListener {

    private final JTable jtable;
    private final JFrame jFrame;

    public AbstractClickAdapter(JTable jtable, JFrame jFrame) {
        this.jtable = jtable;
        this.jFrame = jFrame;
    }
    
    /**
     * Return JTable
     * @return 
     */
    public JTable getJtable(){
        return this.jtable;
    }
    
    public JFrame getJframe(){
        return this.jFrame;
    }
}
