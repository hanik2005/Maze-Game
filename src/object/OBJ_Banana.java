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
public class OBJ_Banana extends Entity{
    
     public OBJ_Banana(GamePanel gp){
         super(gp);
         
        name = "Banana";
        
        down1 = setup("/Assets/objects/banana", gp.tileSize, gp.tileSize);
        
       
    
    }
}
