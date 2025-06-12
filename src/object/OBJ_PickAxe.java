package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PickAxe extends Entity {


    public OBJ_PickAxe(GamePanel gp){
        super(gp);
        type = type_pickaxe;
        name = "PickAxe";
        down1 = setup("/Assets/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\n To break a certain block.";
    }
}
