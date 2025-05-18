/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author kring
 */
public class Main {
    
    public static void main(String[] args){
        
       startGame(); // method 
    }
    public static void startGame(){
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to properly close the program
        window.setResizable(false); // cannot resize the window
        window.setTitle("Maze Game"); // set title in window
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // add the gamepanel as a Jpanel
        
        window.pack(); // causes this window to be sized to fit the prefered size and layouts of its subcomponents or subclass which is the gamepanel
        
        window.setLocationRelativeTo(null); // to display the window only on the center of the screen
        window.setVisible(true); // we can see the window
        
        gamePanel.setupGame();
        
        
        gamePanel.startGameThread();
    
    
    }
    
  
    
   
    
}
