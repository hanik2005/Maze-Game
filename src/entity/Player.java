/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import tile.Tile;

/**
 *
 * @author kring
 */
public class Player extends Entity{
    GamePanel gp;
     KeyHandler keyH;
     
     public final int screenX;
     public final int screenY;
     public int hasCoin = 0;
     int secondsCounter = 0;
     boolean secondsBol = false;
     int updateRunner = 0;
     public boolean wallWalk = true; // DEBUGGING PURPOSES
     
     
     
     public Player(GamePanel gp, KeyHandler keyH){
         this.gp = gp;
         this.keyH = keyH;
         
         
         
        solidArea = new Rectangle(0, 0, 32, 32);
        solidArea.x = 8; // 8
        solidArea.y = 8; //8
        solidArea.width = 28; //28
        solidArea.height = 32;//32
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
         
         
         setDefaultValues();
         getPlayerImage();
     }
     public void setDefaultValues() {
        worldX = gp.tileSize * 49;
        worldY = gp.tileSize * 92;
        speed = 4;
        direction = "idle";
        // position of the player
     }
     
      public void getPlayerImage() {
        
        idle = setup("Main_front_1");
        up1 = setup("Main_up_new_1");
        up2 = setup("Main_up_new_2");
        down1 = setup("Main_front_new_1");
        down2 = setup("Main_front_new_2");
        left1 = setup("Main_left_1");
        left2 = setup("Main_left_2");
        right1 = setup("Main_right_1");
        right2 = setup("Main_right_2");
        
        
        
        
        
    }
      public BufferedImage setup(String imageName){
          UtilityTool uTool = new UtilityTool();
          BufferedImage image = null;
        
        
         try{
            
            
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
         return image;
      }
     public void update(){
         
         if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ){
          if(keyH.upPressed == true){
            direction = "up";
           
        }
        else if(keyH.downPressed == true){
            direction = "down";
          
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            
        }
        else if(keyH.rightPressed == true){
            direction = "right";
         
        }
          
         
           
          
          if(!wallWalk){ // DEBUGING PURPOSES
          
            //CHECK TILE COLLISION
           collisionOn = false;
           gp.cChecker.checkTile(this);
          }
           //CHECK OBJECT COLLISION
           int objIndex = gp.cChecker.checkObject(this, true);
           pickupObject(objIndex);
           
           
           //CHECK EVENT
           gp.eHandler.checkEvent();
           
           //IF COLLISION IS FALSE, PLAYER CAN MOVE IF IT IS TRUE ALL THE COLLISION TILES WILL BE TRUE WHICH MEANS THE PLAYER CANNOT MOVE
           //FUN TIP IN JAVA THE UPDOOR RIGHT LEFT CORNER IS X:0 Y:0 THE X VALUES INCREASE ON THE RIGHT AND Y VALUES INCREASE AS THEY GO DOWN
           //PLAYER SPEED MEANS YOU CAN GO 4 PIXELS OR MINUS 4 PIXELS
           if(collisionOn == false || wallWalk ){
               switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
           }
          
         
          spriteCounter++;
          if(spriteCounter > 12){
             if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
          }
         }
         else{
             direction = "idle";
             spriteNum = 1;
         
         }
         
         
         //UPDATE METHOD EXAMPLE
        // System.out.println(updateRunner++);
         
         
         
         //CHECKING THE eMangerActive
         if (secondsBol) { // check if it is true
            secondsCounter--; //decrement seconds counter which is nanaseconds faster
          if (secondsCounter <= 0) { // check if seconds counter less than zero
            gp.eManagerActive = true; // then it will be true which back to lighthing
            secondsBol = false; // reset and back outside
            System.out.println("Check 3: " + secondsBol); // DEBUG
            //secondsCounter = 0; //OLD algorithm the opposite
            }
         }
     }
     public void pickupObject(int i){
     if (i != 999) {
            String objectName = gp.obj[i].name; //specific reaction in each object
            switch (objectName) {
                case "Coin":
                    gp.PlaySE(1);
                    hasCoin++;
                    gp.obj[i] = null;
                    //gp.ui.showMessage("YourMear");
                    break;
                case "Boots":
                    gp.PlaySE(2);
                    speed+=1;
                    gp.obj[i] = null;
                    //gp.ui.showMessage("BOOTS");
                    break;
                case "lastPortal":
                  //  gp.ui.gameFinished = true;
                    gp.gameState = gp.gameFinished;
                    gp.stopMusic();
                    gp.PlaySE(3);
                    break;
                case "Banana":
                    speed-=1;
                    gp.PlaySE(4);
                    gp.obj[i] = null; // the object dissapear
                    break;
                case "questionMark":
                    gp.PlaySE(1);
                    randomizeEffect();
                    gp.obj[i] = null;
                    
                    break;
                case "Brightness":
                    gp.PlaySE(6);
                    System.out.println("Check 1: " + gp.eManagerActive); // DEBUG
                    gp.eManagerActive = false;
                    secondsBol = true;
                    secondsCounter = 120; // 300
                    System.out.println("Check 2: " + gp.eManagerActive); // DEBUG
                    gp.obj[i] = null;
                case "magicalDoor":
                    if(hasCoin >= 10){
                        gp.obj[i] = null;
                        gp.ui.showMessage("YOU OPEN IT");
                    
                    }
                    break;
                case "Traps":
                    gp.gameState = gp.gameOverState;
                    gp.obj[i] = null;
                    
            }
        }
     
     }
    public void randomizeEffect() {
    Random rand = new Random();
    int randomEffect = rand.nextInt(5); 

    switch (randomEffect) {
        case 0:
            speed -= 1;
            gp.ui.showMessage("Speed Decreased!");
            break;
        case 1:
            speed += 1;
            gp.ui.showMessage("Speed Increased!");
            break;
        case 2:
            hasCoin++;
            gp.ui.showMessage("Coin Gained!");
            break;
        case 3:
            if (hasCoin > 0) {
                hasCoin--;
                gp.ui.showMessage("Coin Lost!");
            } else {
                gp.ui.showMessage("No Coins to Lose!");
            }
            break;
        case 4:
            gp.ui.showMessage("SIGHT!");
            gp.eManagerActive = false;
             secondsBol = true;
            secondsCounter = 120;
            break;
    }
}
     public void draw(Graphics2D g2){
       
       BufferedImage image = null;
       
       
     if(direction.equals("idle")){
         image = idle;
     }
     else{

    switch (direction) {
        case "up":
            if(spriteNum == 1){
            image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            break;
        case "down":
            if(spriteNum == 1){
             image = down1;
            }
            if(spriteNum == 2){
                 image = down2;
            }
            break;
        case "right":
            if(spriteNum == 1){
              image = right1;
            }
            if(spriteNum == 2){
              image = right2;
            }
            break;
        case "left":
            if(spriteNum == 1){
            image = left1;
            }
            if(spriteNum == 2){
            image = left2;
            }
            break;
    }
     }
    g2.drawImage(image, screenX, screenY, null);
    
    
    //See the colision area of the player
    g2.setColor(Color.red);
    g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
  
     
     }
}
