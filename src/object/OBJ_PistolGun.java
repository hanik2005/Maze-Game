package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PistolGun extends Entity {
    public OBJ_PistolGun(GamePanel gp) {
        super(gp);
        type = type_gun;
        name = "PistolGun";
        projectileAttackValue = 3;
        down1 = setup("/objects/pistol_gun", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n Modern Weapon.";
    }
}
