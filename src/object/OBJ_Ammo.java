package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ammo extends Entity {

    GamePanel gp;
    public OBJ_Ammo(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        value = 4;
        name = "Ammo";
        down1 = setup("/Assets/objects/ammo_kit", gp.tileSize,
                gp.tileSize); // AMMO KIT
        image = setup("/Assets/objects/full_bullet", gp.tileSize, gp.tileSize);
        image2 = setup("/Assets/objects/blank_bullet", gp.tileSize, gp.tileSize);

    }
    public void use(Entity entity){
        gp.PlaySE(2);
        gp.ui.addMessage("Bullet +" + value);
        entity.bullet += value;
    }
}
