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
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    
    
    
    public EventHandler(GamePanel gp){
        
      eventRect = new Rectangle();
      eventRect.x = 23;
      eventRect.y = 23;
      eventRect.width = 2;
      eventRect.height = 2;
      eventRectDefaultX = eventRect.x;
      eventRectDefaultY = eventRect.y;
        
      this.gp = gp;
    
    }
    public void checkEvent(){
         //if(hit(48, 39, "left")== true){
           //   teleport(gp.playState);
          //} 
    
    }
    public boolean hit(int eventCol, int eventRow, String reqDirection){
    
        boolean hit = false;
        
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x; //getting current player current solid area position x
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y; //getting current player current solid area position y
        eventRect.x = eventCol * gp.tileSize + eventRect.x; //getting event rect solid area position x
        eventRect.y = eventCol * gp.tileSize + eventRect.y; //getting event rect solid area position y
        
        
        
        if(gp.player.solidArea.intersects(eventRect)){   // check if player solid area is coliding with eventrect solid area
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            
            }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX; //after checking the collision reset the solid area x and y
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        }
        return hit;
    }
    public void teleport(int gameState){
       
     gp.gameState = gameState;
     
     gp.player.worldX = gp.tileSize * 48;
     gp.player.worldY = gp.tileSize * 43;
      
    }
    public void teleport1(int col, int row){
      
      gp.player.worldX = gp.tileSize * col;
      gp.player.worldY = gp.tileSize * row;
      eventRectDefaultX = gp.player.worldX;
      eventRectDefaultY = gp.player.worldY;
      //canTouchEvent = false;
    
    }
}
