package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Rock extends Entity {
    public OBJ_Rock(GamePanel gp) {
        super(gp);
        type = type_rock;
        name = "Rock";
        projectileAttackValue = 1;
        down1 = setup("/projectiles/Rock/rock_down_1", gp.tileSize, gp.tileSize);
        attackValue = 1; //TEMPORARY VALUE
        description = "[" + name + "]\n Old Rock.";

    }

}
