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
public class OBJ_Darkness extends Entity {
    
    public OBJ_Darkness(GamePanel gp){
        super(gp);
        name = "Sight";
        down1 = setup("/Assets/objects/Blindness", gp.tileSize, gp.tileSize);
        
    
    }
}
