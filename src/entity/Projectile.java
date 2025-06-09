package entity;

import main.GamePanel;

public class Projectile extends Entity{

    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }
    public void set(int worldX, int worldY, String direction,
                    boolean alive, Entity user){
       this.worldX = worldX;
       this.worldY = worldY;
       this.direction = direction;
       this.alive = alive;
       this.user = user;
       this.attack = user.projectileDamage; // Use the player's calculated projectile damage
       this.life = this.maxLife;


    }
    public void update(){

        if(user == gp.player){
            int monster_index = gp.cChecker.checkEntity(this, gp.monster);
            if(monster_index != 999){
                gp.player.damageMonster(monster_index, attack);
                alive = false;
            }
        }
        if(user != gp.player){
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(gp.player.invincible == false && contactPlayer == true){
                damagePlayer(attack);
                alive = false;
            }
        }

        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life--;
        if(life < 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;


        }

    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user){

    }
}
