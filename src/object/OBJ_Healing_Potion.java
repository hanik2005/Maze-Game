package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Healing_Potion extends Entity {

    GamePanel gp;
    int value = 5;
    public OBJ_Healing_Potion(GamePanel gp){
       super(gp);
       this.gp = gp;
       type = type_consumable;
       name = "healing_potion";
       down1 = setup("/objects/healing_potion", gp.tileSize, gp.tileSize);
       description = "[" + name + "]\n heals you when you \nare in Journey.";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the potion";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.PlaySE(2);
    }
}
