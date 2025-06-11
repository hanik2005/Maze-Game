/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Heart extends Entity{
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
        
    
    }
    public void use(Entity entity){
        gp.PlaySE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
    }
}
