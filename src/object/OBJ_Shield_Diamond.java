package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Diamond extends Entity{

    public OBJ_Shield_Diamond(GamePanel gp){
        super(gp);
        type = type_shield;
        name = "Diamond Shield";
        down1 = setup("/objects/shield_diamond", gp.tileSize, gp.tileSize);
        defenseValue = 2; //TEMPORARY VALUE
        description = "[" + name + "]\nStrongest Shield has the \nfinest diamond.";
    }
}
