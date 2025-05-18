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
public class OBJ_QuestionMark extends Entity {
    
    public OBJ_QuestionMark(GamePanel gp){
        super(gp);
        name = "questionMark";
        down1 = setup("/objects/questionMark", gp.tileSize, gp.tileSize);
        
        
    
    }
}
