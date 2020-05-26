/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Kamil
 */
public class Projekt {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame f = new Start();
        f.setVisible(true);
        f.setSize(300, 200);
        f.setTitle("Logowanie");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
