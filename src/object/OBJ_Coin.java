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
public class OBJ_Coin extends Entity{
    
    public OBJ_Coin(GamePanel gp){
        super(gp);
        name = "Coin";
        down1 = setup("/objects/coin_new", gp.tileSize, gp.tileSize);
        image = setup("/objects/coin_new", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nImportant Coin.";

       
    
    }
}
