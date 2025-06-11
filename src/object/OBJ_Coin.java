/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Coin extends Entity{
    GamePanel gp;
    
    public OBJ_Coin(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_pickUpOnly;
        name = "Coin";
        value = 1;
        down1 = setup("/objects/coin_new", gp.tileSize, gp.tileSize);
        image = setup("/objects/coin_new", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nImportant Coin.";

       
    
    }
    public void use(Entity entity){
       gp.PlaySE(1);
       gp.ui.addMessage("Coin +" + value);
       gp.player.coin += value;
    }
}
