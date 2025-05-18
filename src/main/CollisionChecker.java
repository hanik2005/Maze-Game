/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;

/**
 *
 * @author kring
 */
public class CollisionChecker {
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
   public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; // entity left, right, top, bottom world x or y it means detecting based of solid area
                                                                   // it means finding the coordinates col and rows
         int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
         int entityTopWorldY = entity.worldY + entity.solidArea.y;
          int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
          
          int entityLeftCol = entityLeftWorldX/gp.tileSize;
          int entityRightCol = entityRightWorldX/gp.tileSize;
          int entityTopRow = entityTopWorldY/gp.tileSize;
          int entityBottomRow = entityBottomWorldY/gp.tileSize;
          
          int tileNum1, tileNum2;
          
          switch(entity.direction){
              case"up":
                  entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize; // it means predicting where the player move after he moved
                  tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // so we can know what tile player step in num1 or num2
                                                                               // so this mapTile num stores the information where the player is at
                                                                               // the purpose of tileNum2 checking if the rectangle detects to the right
                                                                               // and tile num 1 represent checking if the rectangle detects to the left
                  tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                  if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){ // it means if they check that this tiles collision
                                                                                                              // is true right or left entity.collisionOn = true
                      entity.collisionOn = true;
                      
                  }
               break;
               case"down":
                  entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                  tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                  tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                  if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                      entity.collisionOn = true;
                      
                  }
                break;
                case"left":
                  entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                  tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                  tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                  if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                      entity.collisionOn = true;
                      
                  }
                 break;
                 case"right":
                  entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                  tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                  tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                  if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                      entity.collisionOn = true;
                      
                  }
                  break;
          }
    }
   
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                
                //Entity Solid Area Position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                
                //Get the Object Solid Area Position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }

                        }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    //CHECK NPC OR MONSTERS COLLISION
    public int checkEntity(Entity entity, Entity[] target){
        
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                
                //Entity Solid Area Position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                
                //Get the NPC OR Monster Solid Area Position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                 if (entity.solidArea.intersects(target[i].solidArea)) {
                        if(target[i] != entity){
                            entity.collisionOn = true;
                            index = i;
                        }
                     
                        
                            
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity){
        
        boolean contactPlayer = false;
        
       //Entity Solid Area Position
       entity.solidArea.x = entity.worldX + entity.solidArea.x;
       entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                
       //Get the Player Solid Area Position
       gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
       gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

       switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
       
        if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        return contactPlayer;
    
    }
    
}