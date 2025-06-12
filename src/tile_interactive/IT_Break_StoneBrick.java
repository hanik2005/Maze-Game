package tile_interactive;

import main.GamePanel;

public class IT_Break_StoneBrick extends InteractiveTile{
    GamePanel gp;
    public IT_Break_StoneBrick(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        down1 = setup("/Assets/tiles_interactive/destroyed_stone_block", gp.tileSize, gp.tileSize);
        destructible = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
