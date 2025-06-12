package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_StoneBrick extends InteractiveTile{
    GamePanel gp;
    public IT_StoneBrick(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        down1 = setup("/Assets/tiles_interactive/crack_stone_block", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
        //down2 = setup("Ässets/tiles_interactive/destroyed_stone_block", gp.tileSize, gp.tileSize);
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_pickaxe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
    public void PlaySE(){
        gp.PlaySE(14);
    }
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_Break_StoneBrick(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
