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
        
        dino.requestFocus();        //making game responsive to keys pressed//
        frame.setVisible(true);         //make frame visable//
    }
}

//make able to draw/render graphics//
class DinoClass extends JPanel implements ActionListener, KeyListener{        //extends is an inheratances meaning this new class takes on all the properties of the JPanel//
    //re-define window's size//
    int panWidth = 1000;
    int panHeight = 500;
    
    //names of graphic objects//
    Rectangle dinoGraph, dinoDead, dinoJump, cactus1, cactus2, cactus3;
    
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
    int dinoHeight = 94;
    int dinoX = 50;     //dino starts out 50 pixles to the right in the screen//
    int dinoY = panHeight-dinoHeight;       //putting dino at the bottom since we know the size of the dino//
    
    Block dinoBlock;
    
    //make specifics about the cacti//
    int cactusWidth01 = 34;
    int cactusWidth02 = 69;
    int cactusWidth03 = 102;
    
    int cactusHeight = 70;
    int cactusX = 700;
    int cactusY = panHeight-cactusHeight;
    ArrayList<Block> cactusArray;
    
    //make game physics//
    int velocityY = 0;
    int velocityX = -12;        //rate cactus comes at player//
    int gravity = 1;
    
    //set timers//
    Timer gameLoop;
    Timer cactusTimer;
    
    //set game scoring//
    int score = 0;
   
    //set game over boolean//
    boolean gameOver = false;
    
    //make constructor//
    public DinoClass(){
        //re-define window size and set its color//
        setPreferredSize(new Dimension(panWidth, panHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);
        
        dinoGraph = new Rectangle(50, 100, 100, 100);
        dinoDead = new Rectangle(100, 50, 100, 100);
        dinoJump = new Rectangle(50, 100, 100, 100);
        cactus1 = new Rectangle(200, 50, 50, 50);
        cactus2 = new Rectangle(100, 50, 50, 50);
        cactus3 = new Rectangle(50, 50, 50, 50);
        
        //make dinosaur in screen//
        dinoBlock = new Block(dinoX, dinoY, dinoWidth, dinoHeight, dinoGraph);  //now we can keep track of xy positions//
        //create timer game loop//
        gameLoop = new Timer(1000/60, this); //calls action every 16.6 milliseconds//
        gameLoop.start();
        
        //stuff for cactus//
        cactusArray = new ArrayList<Block>();       //since has 3 different cacti//
        cactusTimer = new Timer(1500, new ActionListener(){   //every 1500 ms a cactus will be placed//
            @Override
            public void actionPerformed(ActionEvent e){
                placeCactus();      //calls function to place cactus//
            }
        });  
        cactusTimer.start();
    }
    
    //place cactus//
    public void placeCactus(){
        //check if gameover//
        if(gameOver){
            return;     //stops any other code to run in this function//
        }
        
        //make chances for different cacti//
        double placeCactusChance = Math.random();
        if(placeCactusChance > 0.9){    //10% chance//
            Block cactusBlock = new Block(cactusX, cactusY, cactusWidth03, cactusHeight, cactus3);
            cactusArray.add(cactusBlock);
        }else if(placeCactusChance > 0.7){      //20% chance//
            Block cactusBlock = new Block(cactusX, cactusY, cactusWidth02, cactusHeight, cactus2);
            cactusArray.add(cactusBlock);
        }else if(placeCactusChance > 0.5){      //20% chance//
            Block cactusBlock = new Block(cactusX, cactusY, cactusWidth01, cactusHeight, cactus1);
            cactusArray.add(cactusBlock);
        }
    }
    
    //render items//
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        g.drawRect(dinoBlock.x, dinoBlock.y, dinoBlock.width, dinoBlock.height);
        
        //cactus//
        for(int i=0; i<cactusArray.size(); i++){
            Block cactusBlock = cactusArray.get(i);
            g.drawRect(cactusBlock.x, cactusBlock.y, cactusBlock.width, cactusBlock.height);
        }
        
        //draw score//
        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        if(gameOver){
            g.drawString("Game Over: "+String.valueOf(score), 10, 35);
            g.drawString("Press space to restart", 10, 65);
        }else{
            g.drawString(String.valueOf(score), 10, 35);
            g.drawString("Press space to jump", 10, 65);
        }
    }

    //update movment of dino based on key movments (like jumping)//
    public void move(){
        velocityY += gravity;
        dinoBlock.y += velocityY;
        
        //check to make sure dino doesn't go through ground//
        if(dinoBlock.y > dinoY){
            dinoBlock.y = dinoY;
            velocityY = 0;
        }
        
        //cactus moving//
        for(int i=0; i<cactusArray.size(); i++){
            Block cactusBlock = cactusArray.get(i);
            cactusBlock.x += velocityX;
        
            //end game if collision//
            if(collision(dinoBlock, cactusBlock)){
                gameOver = true;
                System.out.println("dead");
            }
        }
        
        //update score//
        score++;
    }
    
    //collision detection --> i took this from online//
    boolean collision(Block a, Block b){
        return a.x<b.x + b.width &&
               a.x + b.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }
    
    //create ability to use action listoner//
    @Override
    public void actionPerformed(ActionEvent e) {
        move();     //update position//
        repaint();      //call paint component which will call darw//
        
        if(gameOver){
            cactusTimer.stop();
            gameLoop.stop();
        }
    }
    
    //for game physics//
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //make sure person doesn't jump muttiple times//
            if(dinoBlock.y == dinoY){
                velocityY = -17;
            }
            System.out.println("Jumped");
            
            //reset game with space bar if died//
            if(gameOver){
                dinoBlock.y = dinoY;
                velocityY = 0;
                cactusArray.clear();
                
                score=0;
                gameOver = false;
                gameLoop.start();
                cactusTimer.start();
            }
        }  
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
