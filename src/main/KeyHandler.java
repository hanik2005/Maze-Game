/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kring
 */
public class KeyHandler implements KeyListener{ // KeyListener is the listnener interface for receiving keyboard events(key strokes)
                                                // when you implement KeyListener you hace to add three methods which is keyTyped, keyPressed, 
                                                //and keyReleased
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shootKeyPressed;
    
    //DEBUG
    
    boolean checkDrawTime = false;
    GamePanel gp;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // wlay
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns a number of the key that was pressed so the keyCodes associated with integers
        
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        else if(gp.gameState == gp.instructionState){
            instructionState(code);
        }
        else if(gp.gameState == gp.nameState){
            nameState(code, e);
        }
        else if (gp.gameState == gp.gameFinishedState) {
            gameFinishedState(code);
        }
        else if(gp.gameState == gp.leaderboardState){
            leaderboardState(code);
        }
        else if(gp.gameState == gp.gameMenuState){
            gameMenuState(code);
        }
        else if(gp.gameState == gp.gameLevelsState){
            gameLevelState(code);
        }
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        else if(gp.gameState == gp.playerStatusState){
            playerStatusState(code);
        }
        else if(gp.gameState == gp.playState){
            playState(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        
        // W, A, S, D CONTROLS RELEASE
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
        
        // UP, DOWN, LEFT RIGHT CONROLS RELEASE
        
        if(code == KeyEvent.VK_UP){
            upPressed = false;
        
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        
        }

        //PROJECTILES
        if(code == KeyEvent.VK_F){
            shootKeyPressed = false;
        }
    
    }
    //STATE LOCATED
    
     public void titleState(int code){
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

                  gp.gameState = gp.playState;
                  gp.LevelState = gp.tutorial_level;
                  resetPlayState();
                }
                 if (gp.ui.commandNum == 4) {
                    // Exit the game

                   System.exit(0);
                }
                
            }
     
     }
     public void gameOverState(int code){
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
     
     }
     public void nameState(int code, KeyEvent e){
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
               
                    //resetPlayState();
                    gp.gameState = gp.gameLevelsState;
                }
                if (gp.ui.commandNum == 1 ) {
                gp.gameState = gp.titleState; // Transition to the game state (or to the next state)
                }
            }
     
     }
     
     public void gameMenuState(int code){
         if (code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
         if (code == KeyEvent.VK_ENTER){
             enterPressed = true;
         }
         int maxCommandNum = 0;
         switch (gp.ui.subState){
             case 0: maxCommandNum  = 5;break;
             case 3: maxCommandNum = 1;break;
         }


            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                gp.PlaySE(9);
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = maxCommandNum;
                }
            } 
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                gp.PlaySE(9);
                if (gp.ui.commandNum > maxCommandNum){
                    gp.ui.commandNum = 0;
                }
            }
            
            if(code == KeyEvent.VK_A){
                if (gp.ui.subState == 0){
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                        gp.music.volumeScale --;
                        gp.music.checkVolume();
                     }

                    if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
                        gp.se.volumeScale --;
                    }
                 }
            }
            if(code == KeyEvent.VK_D){

                if (gp.ui.subState == 0) {

                    if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                    }


                    if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                        gp.se.volumeScale++;
                    }
                }
            }

     
     }
     public void playerStatusState(int code){
         if(code == KeyEvent.VK_C){
                gp.gameState = gp.playState;
            }
         if(code == KeyEvent.VK_W){
             if(gp.ui.slotRow != 0){
                 gp.ui.slotRow --;
                 gp.PlaySE(12);
             }

         }
         if(code == KeyEvent.VK_A){
             if(gp.ui.slotCol != 0){
                 gp.ui.slotCol --;
                 gp.PlaySE(12);
             }

         }
         if(code == KeyEvent.VK_S){
             if(gp.ui.slotRow != 3){
                 gp.ui.slotRow ++;
                 gp.PlaySE(12);
             }

         }
         if(code == KeyEvent.VK_D){
             if(gp.ui.slotCol != 4){
                 gp.ui.slotCol ++;
                 gp.PlaySE(12);
             }

         }
         if (code == KeyEvent.VK_ENTER){
             gp.player.selectItem();
         }
     
     }
     public void instructionState(int code){
         if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState; 
            }
     }
     public void dialogueState(int code){
          if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            
            }
     }
     public void leaderboardState(int code){
          if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
       
            }
     
     }
     public void gameFinishedState(int code){
         if (code == KeyEvent.VK_ENTER) {
                resetGameFinishedState();
                resetTitleState();
       
            }
     }
     public void gameLevelState(int code){
            if (code == KeyEvent.VK_A) {
                // Move left
                if (gp.ui.commandNum % 3 != 0) {
                    gp.ui.commandNum--;
                } else {
                    gp.ui.commandNum += 2; // Wrap to the rightmost in the row
                }
                System.out.println("A: " + gp.ui.commandNum);
            }

            if (code == KeyEvent.VK_D) {
                // Move right
                if (gp.ui.commandNum % 3 != 2) {
                    gp.ui.commandNum++;
                } else {
                    gp.ui.commandNum -= 2; // Wrap to the leftmost in the row
                }
                System.out.println("D: " + gp.ui.commandNum);
            }

            if (code == KeyEvent.VK_W) {
                // Move up
                if (gp.ui.commandNum >= 3) {
                    gp.ui.commandNum -= 3;
                } else {
                    gp.ui.commandNum += 3; // Wrap to bottom row
                }
                System.out.println("W: " + gp.ui.commandNum);
            }

            if (code == KeyEvent.VK_S) {
                // Move down
                if (gp.ui.commandNum < 3) {
                    gp.ui.commandNum += 3;
                } else {
                    gp.ui.commandNum -= 3; // Wrap to top row
                }
                System.out.println("S: " + gp.ui.commandNum);
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                   gp.LevelState = gp.Levels_1;
                   //gp.tileM.checkWhatMap();
                   resetPlayState();
                   //gp.setObjectMap();
                   System.out.println(gp.LevelState);
                
                }
                 if(gp.ui.commandNum == 1){
                   gp.LevelState = gp.Levels_2;
                  // gp.tileM.checkWhatMap();
                    resetPlayState();
                   // gp.setObjectMap();
                    System.out.println(gp.LevelState);
                
                }
                  if(gp.ui.commandNum == 2){
                   gp.LevelState = gp.Levels_3;
                  // gp.tileM.checkWhatMap();
                    resetPlayState();
                   // gp.setObjectMap();
                    System.out.println(gp.LevelState);
                
                }
                  if(gp.ui.commandNum == 3){
                   gp.LevelState = gp.Levels_4;
                  // gp.tileM.checkWhatMap();
                    resetPlayState();
                   // gp.setObjectMap();
                    System.out.println(gp.LevelState);
                
                }
            
            }
     
     }
     public void playState(int code){
            // TWO CONTROLS WASD AND UPDOWNLEFTRIGHT CONTROL IN PLAYER MOVEMENT
            // W, A, S, D CONTROLS
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

            // UP, DOWN, RIGHT, LEFT

            if(code == KeyEvent.VK_UP){
                upPressed = true;

            }
            if(code == KeyEvent.VK_DOWN){
                downPressed = true;

            }
            if(code == KeyEvent.VK_LEFT){
                leftPressed = true;

            }
            if(code == KeyEvent.VK_RIGHT){
                rightPressed = true;

            }


            //DIFFERENT KEYS STATES


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
            if(code == KeyEvent.VK_ENTER && !enterPressed ){
                    //System.out.println("asdada");
                    enterPressed = true;
            }
            if(code == KeyEvent.VK_F){
                shootKeyPressed = true;
            }

            //PRESSED SETTING STATE
            if(code == KeyEvent.VK_ESCAPE){
                if(gp.gameState == gp.playState){
                gp.gameState = gp.gameMenuState;
                }

            }

            //PRESSED PLAYER STATUS STATE
            if(code == KeyEvent.VK_C){
                gp.gameState = gp.playerStatusState;

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
    
    
    
    public void resetPlayState(){
       gp.stopMusic();
       musicSectionMap();
       gp.player.life = gp.player.maxLife;
       gp.player.invincible = false;
      // gp.resetObject();
       gp.tileM.checkWhatMap(); //check what map level
       gp.setMap(); // check and set what object map
       gp.ui.checkTimeLevel();
       gp.player.setDefaultValues();
       gp.player.hasCoin = 0;
       gp.ui.showMessage("COMPLETE THE MAZE");
       gp.gameState = gp.playState; // Transition to the game state (or to the next state)  
    }
     public void resetTitleState(){
       System.out.println("debug2: " + gp.LevelState);
       gp.reset();
       gp.ui.commandNum = 0;
       //gp.tileM.resetMap();
       gp.stopMusic();
       gp.playMusic(0);
       gp.playerName = "";  
       gp.gameState = gp.titleState; // Transition to the game state (or to the next state)  
    }
     public void resetGameFinishedState(){
        gp.leaderboardData.add(gp.playerName + " - Coins: " + gp.player.hasCoin);
        gp.ui.gameFinished = false;       
        gp.playerName = "";     
    }
     public void musicSectionMap(){
         if(gp.LevelState == gp.Levels_1){
            gp.playMusic(7); 
         }
         if(gp.LevelState == gp.Levels_2){
         
         }
         if(gp.LevelState == gp.Levels_3){
         
         }
         if(gp.LevelState == gp.Levels_4){
         
         }
         if(gp.LevelState == gp.Levels_5){
         
         }
         if(gp.LevelState == gp.Levels_6){
         
         }
         if(gp.LevelState == gp.tutorial_level){
         
         }
     }
     
}
