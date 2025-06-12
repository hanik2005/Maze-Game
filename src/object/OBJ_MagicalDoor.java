/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import main.GamePanel;

/**
 *
 * @author kring
 */
public class OBJ_MagicalDoor extends Entity {
    
    public OBJ_MagicalDoor(GamePanel gp){
        super(gp);
        name = "magicalDoor";
        down1 = setup("/Assets/objects/MagicalDoor", gp.tileSize, gp.tileSize);
        collision = true;
        
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX =  solidArea.x;
        solidAreaDefaultY =  solidArea.y;
    
    }
}
