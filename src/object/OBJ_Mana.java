package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    GamePanel gp;
    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Mana";
        value = 1;
        down1 = setup("/Assets/objects/mana_full", gp.tileSize, gp.tileSize);
        image = setup("/Assets/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setup("/Assets/objects/mana_blank", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.PlaySE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
