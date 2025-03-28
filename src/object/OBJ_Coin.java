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
public class OBJ_Coin extends SuperObject{
    GamePanel gp;
    
    public OBJ_Coin(GamePanel gp){
        this.gp = gp;
     name = "Coin";
        try{
           image = ImageIO.read(getClass().getResourceAsStream("/objects/coin_new.png"));
           uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
    }
    
    }
}
