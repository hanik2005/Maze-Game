/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author kring
 */
public class OBJ_LastPortal extends Entity {
   
    
    public OBJ_LastPortal(GamePanel gp){
        super(gp);
        name = "lastPortal";
        down1 = setup("/objects/LastPortal", gp.tileSize, gp.tileSize);
        
    }
}
