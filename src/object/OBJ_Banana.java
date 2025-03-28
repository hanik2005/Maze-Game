/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author kring
 */
public class OBJ_Banana extends SuperObject {
     GamePanel gp;
    
     public OBJ_Banana(GamePanel gp){
         this.gp = gp;
     name = "Banana";
        try{
           image = ImageIO.read(getClass().getResourceAsStream("/objects/banana.png"));
           uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
    }
    
    }
}
