/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Rectangle;

/**
 *
 * @author kring
 */
public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    
    
    public EventHandler(GamePanel gp){
        
      this.gp = gp;
      eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
      
      int col = 0;
      int row = 0;
      while(col < gp.maxWorldCol && row < gp.maxWorldRow){
        eventRect[col][row] = new EventRect();
        eventRect[col][row].x = 23; // 23
        eventRect[col][row].y = 23;
        eventRect[col][row].width = 2;
        eventRect[col][row].height = 2;
        eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
        eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
        
        col++;
        if(col == gp.maxWorldCol){
            col = 0;
            row++;
        }
      
      }
        
      
        
    
    }
    public void checkEvent(){
         //check if the player character is more than 1 tile away from the last event
         int xDistance = Math.abs(gp.player.worldX - previousEventX);
         int yDistance = Math.abs(gp.player.worldY - previousEventY);
         int distance = Math.max(xDistance, yDistance);
         if(distance > gp.tileSize){
             canTouchEvent = true;
         }
         
          if(canTouchEvent == true){
            if(hit(51, 47, "any") == true){damagePit(51, 47, gp.dialogueState);}
            if(hit(53, 47, "up") == true){healingPool(53, 47, gp.dialogueState);}
            if(hit(52, 46, "any") == true){teleport(52, 46, gp.dialogueState);}
          
          }
         
          
    
    }
    public boolean hit(int col, int row, String reqDirection){
    
        boolean hit = false;
        
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x; //getting current player current solid area position x
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y; //getting current player current solid area position y
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x; //getting event rect solid area position x
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y; //getting event rect solid area position y
        
        
        
        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){   // check if player solid area is coliding with eventrect solid area
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
                
            
            }
        }
        
        gp.player.solidArea.x = gp.player.solidAreaDefaultX; //after checking the collision reset the solid area x and y
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        
        
        
        return hit;
    }
    public void teleport(int col, int row, int gameState){
       
     gp.gameState = gameState;
     gp.ui.currentDialogue = "HAHAHHA YOUVE BEEN TELOPORTED";
     
     gp.player.worldX = gp.tileSize * 58;
     gp.player.worldY = gp.tileSize * 45;
     eventRect[col][row].eventDone = true;
      
    }
    public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "you fall hit loser";
        gp.player.life -= 1;
        eventRect[col][row].eventDone = true;
        
        //only if you want a repetive event but implement one tile protocol
        //canTouchEvent = false;
    }
    public void healingPool(int col, int row, int gameState){
        
        System.out.println("dsadsa");
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "HAAAAA!";
            gp.player.life = gp.player.maxLife;
            eventRect[col][row].eventDone = true;
            //gp.aSetter.setEnemy(); //only use this if you heal the monster respawn
        }
    }
}
