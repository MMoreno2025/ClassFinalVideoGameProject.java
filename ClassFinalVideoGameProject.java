//Malia Moreno | 5 December 2024//
package com.mycompany.classfinal.videogame.project;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

//ClassFinalVideoGameProject//

public class ClassFinalVideoGameProject{
    public static void main(String[] args) throws Exception{
        int panWidth = 1000;
        int panHeight = 500;
        
        //making the frame for the game//
        JFrame frame = new JFrame("Game");      //makes game pannel//
        frame.setSize(panWidth, panHeight);
        frame.setLocationRelativeTo(null);      //sets frame into the middle of the monitor of the user//
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        //make able to draw/render graphics//
        DinoClass dino = new DinoClass();
        frame.add(dino);
        frame.pack();   //since using windo pack, need to re-define windo size and color//
        
        
        frame.setVisible(true);         //make frame visable//
    }
}

//make able to draw/render graphics//
class DinoClass extends JPanel{        //extends is an inheratances meaning this new class takes on all the properties of the JPanel//
    //re-define window's size//
    int panWidth = 1000;
    int panHeight = 500;
    
    //names of graphic objects//
    Rectangle dinoGraph, dinoDead, dinoJump, cactus1, cactus2, cactus3;
    
    /*public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        dinoGraph = new Rectangle(100, 100, 100, 100);
        //g2.draw(dinoGraph);
    }*/
    
    //make class for all aspecets of rectanle: position//
    class Block{
        int x;
        int y;
        int width;
        int height;
        Rectangle rect;
        
        Block(int x, int y, int width, int height, Rectangle rect){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.rect = rect;
        }
    }
    
    //specific info about the dino//
    int dinoWidth = 88;
    
    //make constructor//
    public DinoClass(){
        //re-define window size and set its color//
        setPreferredSize(new Dimension(panWidth, panHeight));
        setBackground(Color.lightGray);
        
        dinoGraph = new Rectangle(50, 100, 100, 100);
        dinoDead = new Rectangle(100, 50, 100, 100);
        dinoJump = new Rectangle(50, 100, 100, 100);
        cactus1 = new Rectangle(200, 50, 50, 50);
        cactus2 = new Rectangle(100, 50, 50, 50);
        cactus3 = new Rectangle(50, 50, 50, 50);
    }
}
