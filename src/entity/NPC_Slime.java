/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UI;
import main.UtilityTool;

/**
 *
 * @author Nick Charles Clarito
 */
public class NPC_Slime extends Entity {
    boolean pauseAfterCollision = false;
    int pauseCounter = 0;
    //GamePanel gp;
    //UI ui;
    
    public NPC_Slime(GamePanel gp){
        super(gp);
        //this.gp = gp;
        
        
        direction = "down";
        speed = 2;
        
          //COLLISIONS
        solidArea.x = 8; // 8
        solidArea.y = 15; //8
        solidArea.width = 28; //28
        solidArea.height = 20;//32
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
        setDialogue();
    }
    public void getImage() {
        
        //idle = setup("/npc/front_1");
        up1 = setup("/npc/slime/npc_slime_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/slime/npc_slime_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/slime/npc_slime_front_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/slime/npc_slime_front_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/slime/npc_slime_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/slime/npc_slime_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/slime/npc_slime_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/slime/npc_slime_right_2", gp.tileSize, gp.tileSize);
        
        
        
        
        
    }
    public void setDialogue(){
        dialogues[0] = "Hello, I am the tutorial guide.";
        dialogues[1] = "so, create a game to master all the fundamentals to /ncontinue everything What I said so help me.";
        dialogues[2] = "to guide us please.";
        dialogues[3] = "Thank You.";
    
    }
    

    
    
    public void setAction(){
        
        actionLockCounter ++;
        
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number 1 to 100
        
            if(i <= 25){
                direction = "up";
        
            }
            if(i > 25 && i <= 50){
                direction = "down";
        
            }
            if(i > 50 && i <= 75){
                direction = "left";
        
            }
            if(i > 75 && i<=100){
                direction = "right";
        
            }
             
             
             
            actionLockCounter = 0;
        }
    }
    public void speak(){
       super.speak();
    }
    
}
