/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import entity.Entity;
import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Mana;

/**
 *
 * @author Nick Charles Clarito
 */
public class MON_Robot extends Entity {
    
    GamePanel gp;
    
    public MON_Robot(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_monster;
        name = "Robot";
        speed = 2;
        maxLife = 3;
        life = maxLife;
        attack = 1;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Fireball(gp);
        
        //COLLISIONS
        solidArea.x = 8; // 8
        solidArea.y = 8; //8
        solidArea.width = 28; //28
        solidArea.height = 32;//32
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    public void getImage(){
        up1 = setup("/enemy/robot_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/robot_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/enemy/robot_Front_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/robot_Front_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/robot_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/robot_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/robot_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/robot_right_2", gp.tileSize, gp.tileSize);
    
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
        int i = new Random().nextInt(100) + 1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    
    }
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop(){

        //CAST A DIE
        int i = new Random().nextInt(100) + 1;

        //GET THE MONSTER DROP
        if(i < 50){
            dropItem(new OBJ_Coin(gp));
        }
        if (i > 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new OBJ_Mana(gp));
        }
    }
    
}
