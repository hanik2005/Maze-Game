package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Bullet extends Projectile {

    GamePanel gp;
    public OBJ_Bullet(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Bullet";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        useCost = 1;
        alive = false;
        getImage();

    }
    public void getImage(){
        up1 = setup("/projectiles/Bullets/bullet_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectiles/Bullets/bullet_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectiles/Bullets/bullet_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectiles/Bullets/bullet_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectiles/Bullets/bullet_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectiles/Bullets/bullet_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectiles/Bullets/bullet_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectiles/Bullets/bullet_right_2", gp.tileSize, gp.tileSize);
    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.bullet >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user){
        user.bullet -= useCost;
    }
}
