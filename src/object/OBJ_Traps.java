/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Student
 */
public class OBJ_Traps extends SuperObject {
    GamePanel gp;
    
    public OBJ_Traps(GamePanel gp){
        this.gp = gp;
     name = "Traps";
        try{
           image = ImageIO.read(getClass().getResourceAsStream("/objects/Traps.png"));
           uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
    }
    
    }
}
