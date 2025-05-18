/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import entity.Entity;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Student
 */
public class OBJ_Traps extends Entity {
    
    
    public OBJ_Traps(GamePanel gp){
        super(gp);
        name = "Traps";
        down1 = setup("/objects/Traps", gp.tileSize, gp.tileSize);
    
    }
}
