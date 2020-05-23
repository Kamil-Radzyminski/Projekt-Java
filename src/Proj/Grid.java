/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Kamil
 */
public class Grid implements ActionListener{
     static JButton Glowny, Obcy;
     static JTextField nazwisko;
     
    public static void Grid() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("GridLayout Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(0, 2, 2, 3));
    
    frame.add(new JLabel("ID"));
    Glowny = new JButton("Wybierz osobę");
    frame.add(Glowny);
    
    frame.add(new JLabel("Sektor"));
    Obcy = new JButton("Wybierz sektor");
    frame.add(Obcy);
        
    frame.add(new JLabel("Nazwisko"));
    nazwisko = new JTextField();
    frame.add(nazwisko);
    
    frame.add(new JButton("Button 3"));
    frame.add(new JButton("Button 4"));
    frame.add(new JButton("Button 5"));
    frame.add(new JButton("Button 6"));
    frame.add(new JButton("Button 7"));
    frame.add(new JButton("Button 8"));
    frame.pack();
    
    frame.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
}

