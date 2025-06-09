package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    GamePanel gp;
    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana";
        image = setup("/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/mana_blank", gp.tileSize, gp.tileSize);
    }
}
