package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Healing_Potion extends Entity {

    GamePanel gp;
    public OBJ_Healing_Potion(GamePanel gp){
       super(gp);
       this.gp = gp;
       type = type_consumable;
       name = "healing_potion";
       value = 5;
       down1 = setup("/Assets/objects/healing_potion", gp.tileSize, gp.tileSize);
       description = "[" + name + "]\n heals you when you \nare in Journey.";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the potion";
        entity.life += value;
        gp.PlaySE(2);
    }
}
