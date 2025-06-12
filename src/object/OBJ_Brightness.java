/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import entity.Entity;
import main.GamePanel;

/**
 *
 * @author Student
 */
public class OBJ_Brightness extends Entity {
    
    public OBJ_Brightness(GamePanel gp){
        super(gp);
        name = "Brightness";
        down1 = setup("/Assets/objects/Brightness", gp.tileSize, gp.tileSize);
       
    
    }
    
    }
    

