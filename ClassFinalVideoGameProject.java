//Malia Moreno | 5 December 2024//
package com.mycompany.classfinal.videogame.project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassFinalVideoGameProject extends JPanel implements ActionListener{
    public ClassFinalVideoGameProject(){
        super.setDoubleBuffered(true);
    }

    public void paintComponent(Graphics g){
        g.drawRect(40, 40, 40, 40);
    }

    public static void main(String arg[]){
        JFrame frame = new JFrame("BasicJPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        ClassFinalVideoGameProject panel = new ClassFinalVideoGameProject();

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
