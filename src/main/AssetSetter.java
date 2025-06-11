
package main;

import entity.NPC_Slime;
import monster.MON_Robot;
import object.*;


public class AssetSetter {
    GamePanel gp;

    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    
    }
    
    public void setObject_1(){
        gp.obj[0] = new OBJ_PistolGun(gp);
        gp.obj[0].worldX = 49 * gp.tileSize;
        gp.obj[0].worldY = 87 * gp.tileSize;//
        
        gp.obj[1] = new OBJ_Heart(gp);
        gp.obj[1].worldX = 54 * gp.tileSize;
        gp.obj[1].worldY = 87 * gp.tileSize;//
        
        gp.obj[2] = new OBJ_Ammo(gp);
        gp.obj[2].worldX = 54 * gp.tileSize;
        gp.obj[2].worldY = 83 * gp.tileSize;//
        
        gp.obj[3] = new OBJ_Mana(gp);
        gp.obj[3].worldX = 49 * gp.tileSize;
        gp.obj[3].worldY = 81 * gp.tileSize;//


        
        
        
        //DEBUGGING
        
       // gp.obj[72] = new OBJ_LastPortal(gp);
        //gp.obj[72].worldX = 51 * gp.tileSize;
        //gp.obj[72].worldY = 92 * gp.tileSize;
        
        
        
    }
    public void setObject_2(){
        gp.obj[0] = new OBJ_Banana(gp);
        gp.obj[0].worldX = 49 * gp.tileSize;
        gp.obj[0].worldY = 87 * gp.tileSize;//
    
    }
    public void setNpc(){
        //gp.npc[0] = new NPC_Slime(gp);
       // gp.npc[0].worldX = 49 * gp.tileSize;
        //gp.npc[0].worldY = 90 * gp.tileSize;
        
//        gp.npc[1] = new NPC_Slime(gp);
//        gp.npc[1].worldX = 54 * gp.tileSize;
//        gp.npc[1].worldY = 87 * gp.tileSize;
//        
//        gp.npc[2] = new NPC_Slime(gp);
//        gp.npc[2].worldX = 44 * gp.tileSize;
//        gp.npc[2].worldY = 87 * gp.tileSize;
    
    }
    public void setEnemy(){
        gp.monster[0] = new MON_Robot(gp);
        gp.monster[0].worldX = 49 * gp.tileSize;
        gp.monster[0].worldY = 90 * gp.tileSize;
        
//        gp.monster[1] = new MON_Robot(gp);
//        gp.monster[1].worldX = 52 * gp.tileSize;
//        gp.monster[1].worldY = 87 * gp.tileSize;
//        
//        gp.monster[2] = new MON_Robot(gp);
//        gp.monster[2].worldX = 57 * gp.tileSize;
//        gp.monster[2].worldY = 87 * gp.tileSize;
        
    
    
    }
    
    
}
