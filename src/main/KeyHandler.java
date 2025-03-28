/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kring
 */
public class KeyHandler implements KeyListener{ // KeyListener is the listnener interface for receiving keyboard events(key strokes)
                                                // when you implement KeyListener you hace to add three methods which is keyTyped, keyPressed, 
                                                //and keyReleased
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    //DEBUG
    
    boolean checkDrawTime = false;
    GamePanel gp;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns a number of the key that was pressed so the keyCodes associated with integers
        
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 4;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 4) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.nameState;
                      
                }
                if (gp.ui.commandNum == 1) {
                     gp.gameState = gp.instructionState;
                    
                }
                if (gp.ui.commandNum == 2) {
                    // Exit the game

                  gp.gameState = gp.leaderboardState;
                }
                 if (gp.ui.commandNum == 3) {
                    // Exit the game

                   System.exit(0);
                }
                
            }
        }else if (gp.gameState == gp.gameOverState){
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            } 
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0 ) {
                    resetPlayState();
                         
                }
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                    
                }
                if(gp.ui.commandNum == 2){
                    resetTitleState();
                }
                
            }
        } else if(gp.gameState == gp.instructionState){
            
            if (code == KeyEvent.VK_ENTER) {
                
              
                    gp.gameState = gp.titleState;
                    
                
            }
            
        
        } else if(gp.gameState == gp.nameState){
        
            if (Character.isLetterOrDigit(e.getKeyChar()) && gp.playerName.length() < 20) {
            gp.playerName += e.getKeyChar(); // Add typed character to the player's name
            }
            // Handle backspace
            if (code == KeyEvent.VK_BACK_SPACE && gp.playerName.length() > 0) {
            gp.playerName = gp.playerName.substring(0, gp.playerName.length() - 1); // Remove last character
            }
            
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            
            
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            
            
            // Handle Enter key to submit the name
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0 ) {
               
                    resetPlayState();
                }
                if (gp.ui.commandNum == 1 ) {
                gp.gameState = gp.titleState; // Transition to the game state (or to the next state)
                }
            }
        }
        else if (gp.gameState == gp.gameFinished) {
         if (code == KeyEvent.VK_ENTER) {
            resetGameFinishedState();
            resetTitleState();
       
            }
        }
        else if(gp.gameState == gp.leaderboardState){
             if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.titleState;
       
            }
        
        }
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
                gp.stopMusic();
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
        }
        
        //DEBUG
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }
            else if(checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    
    }
    public void resetPlayState(){
       gp.stopMusic();
       gp.playMusic(7);
       gp.resetObject();
       gp.player.setDefaultValues();
       gp.player.hasCoin = 0;
       gp.ui.showMessage("COMPLETE THE MAZE");
       gp.gameState = gp.playState; // Transition to the game state (or to the next state)  
    }
     public void resetTitleState(){
       gp.stopMusic();
       gp.playMusic(0);
       gp.gameState = gp.titleState; // Transition to the game state (or to the next state)  
    }
     public void resetGameFinishedState(){
        gp.leaderboardData.add(gp.playerName + " - Coins: " + gp.player.hasCoin);
        gp.ui.gameFinished = false;       
        gp.playerName = "";     
    }
}
