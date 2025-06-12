package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Katana extends Entity {

    public OBJ_Katana(GamePanel gp){
        super(gp);
        type = type_katana;
        name = "Katana";
        down1 = setup("/Assets/objects/katana", gp.tileSize, gp.tileSize);
        attackValue = 3; //TEMPORARY VALUE
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\n Sword From Japan.";


    }
}
