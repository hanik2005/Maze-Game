
package main;

import object.OBJ_Banana;
import object.OBJ_Darkness;
import object.OBJ_Boots;
import object.OBJ_Brightness;
import object.OBJ_Coin;
import object.OBJ_LastPortal;
import object.OBJ_MagicalDoor;
import object.OBJ_QuestionMark;
import object.OBJ_Traps;


public class AssetSetter {
    GamePanel gp;

    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Coin(gp);
        gp.obj[0].worldX = 58 * gp.tileSize;
        gp.obj[0].worldY = 39 * gp.tileSize;//
        
        gp.obj[1] = new OBJ_Boots(gp);
        gp.obj[1].worldX = 52 * gp.tileSize;
        gp.obj[1].worldY = 39 * gp.tileSize;//
        
        gp.obj[2] = new OBJ_LastPortal(gp);
        gp.obj[2].worldX = 53 * gp.tileSize;
        gp.obj[2].worldY = 17 * gp.tileSize;//
        
        gp.obj[3] = new OBJ_Boots(gp);
        gp.obj[3].worldX = 63 * gp.tileSize;
        gp.obj[3].worldY = 46 * gp.tileSize;//
        
        gp.obj[4] = new OBJ_Banana(gp);
        gp.obj[4].worldX = 53 * gp.tileSize;
        gp.obj[4].worldY = 66 * gp.tileSize;//
        
        gp.obj[5] = new OBJ_Banana(gp);
        gp.obj[5].worldX = 68 * gp.tileSize;
        gp.obj[5].worldY = 58 * gp.tileSize;//
        
        gp.obj[6] = new OBJ_Boots(gp);
        gp.obj[6].worldX = 41 * gp.tileSize;
        gp.obj[6].worldY = 66 * gp.tileSize;//
        
        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 41 * gp.tileSize;
        gp.obj[7].worldY = 53 * gp.tileSize;//
        
        gp.obj[8] = new OBJ_Banana(gp);
        gp.obj[8].worldX = 27 * gp.tileSize;
        gp.obj[8].worldY = 38 * gp.tileSize;//
        
        gp.obj[9] = new OBJ_Banana(gp);
        gp.obj[9].worldX = 32 * gp.tileSize;
        gp.obj[9].worldY = 49 * gp.tileSize;//
        
        gp.obj[10] = new OBJ_Boots(gp);
        gp.obj[10].worldX = 41 * gp.tileSize;
        gp.obj[10].worldY = 35 * gp.tileSize;//
        
        gp.obj[11] = new OBJ_Banana(gp);
        gp.obj[11].worldX = 64 * gp.tileSize;
        gp.obj[11].worldY = 30 * gp.tileSize;//
        
        gp.obj[12] = new OBJ_Boots(gp);
        gp.obj[12].worldX = 62 * gp.tileSize;
        gp.obj[12].worldY = 35 * gp.tileSize;//
        
        gp.obj[13] = new OBJ_Banana(gp);
        gp.obj[13].worldX = 52 * gp.tileSize;
        gp.obj[13].worldY = 35 * gp.tileSize;//
        
        gp.obj[14] = new OBJ_Boots(gp);
        gp.obj[14].worldX = 41 * gp.tileSize;
        gp.obj[14].worldY = 23 * gp.tileSize;//
        
        gp.obj[15] = new OBJ_Banana(gp);
        gp.obj[15].worldX = 29 * gp.tileSize;
        gp.obj[15].worldY = 28 * gp.tileSize;//
        
        gp.obj[16] = new OBJ_QuestionMark(gp);
        gp.obj[16].worldX = 30 * gp.tileSize;
        gp.obj[16].worldY = 53 * gp.tileSize;//
        
        gp.obj[17] = new OBJ_QuestionMark(gp);
        gp.obj[17].worldX = 49 * gp.tileSize;
        gp.obj[17].worldY = 48 * gp.tileSize;//
        
        gp.obj[18] = new OBJ_QuestionMark(gp);
        gp.obj[18].worldX = 47 * gp.tileSize;
        gp.obj[18].worldY = 55 * gp.tileSize;//
        
        gp.obj[19] = new OBJ_QuestionMark(gp);
        gp.obj[19].worldX = 41 * gp.tileSize;
        gp.obj[19].worldY = 69 * gp.tileSize;//
        
        gp.obj[20] = new OBJ_QuestionMark(gp);
        gp.obj[20].worldX = 63 * gp.tileSize;
        gp.obj[20].worldY = 39 * gp.tileSize;//
        
        gp.obj[21] = new OBJ_Boots(gp);
        gp.obj[21].worldX = 42 * gp.tileSize;
        gp.obj[21].worldY = 43 * gp.tileSize;//
        
        gp.obj[22] = new OBJ_Banana(gp);
        gp.obj[22].worldX = 45 * gp.tileSize;
        gp.obj[22].worldY = 43 * gp.tileSize;//
        
        gp.obj[23] = new OBJ_QuestionMark(gp);
        gp.obj[23].worldX = 63 * gp.tileSize;
        gp.obj[23].worldY = 57 * gp.tileSize;//
        
        gp.obj[24] = new OBJ_Banana(gp);
        gp.obj[24].worldX = 50 * gp.tileSize;
        gp.obj[24].worldY = 61 * gp.tileSize;//
        
        gp.obj[25] = new OBJ_Banana(gp);
        gp.obj[25].worldX = 62 * gp.tileSize;
        gp.obj[25].worldY = 68 * gp.tileSize;//
        
        gp.obj[26] = new OBJ_QuestionMark(gp);
        gp.obj[26].worldX = 50 * gp.tileSize;
        gp.obj[26].worldY = 73 * gp.tileSize;//
        
        gp.obj[27] = new OBJ_Banana(gp);
        gp.obj[27].worldX = 27 * gp.tileSize;
        gp.obj[27].worldY = 73 * gp.tileSize;//
        
        gp.obj[28] = new OBJ_Brightness(gp);
        gp.obj[28].worldX = 27 * gp.tileSize;
        gp.obj[28].worldY = 87 * gp.tileSize;//
        
        gp.obj[29] = new OBJ_Boots(gp);
        gp.obj[29].worldX = 32 * gp.tileSize;
        gp.obj[29].worldY = 78 * gp.tileSize;//
        
        gp.obj[30] = new OBJ_QuestionMark(gp);
        gp.obj[30].worldX = 49 * gp.tileSize;
        gp.obj[30].worldY = 87 * gp.tileSize;//
        
        gp.obj[31] = new OBJ_Boots(gp);
        gp.obj[31].worldX = 68 * gp.tileSize;
        gp.obj[31].worldY = 87 * gp.tileSize;//
        
        gp.obj[32] = new OBJ_Banana(gp);
        gp.obj[32].worldX = 68 * gp.tileSize;
        gp.obj[32].worldY = 82 * gp.tileSize;//
        
        gp.obj[33] = new OBJ_QuestionMark(gp);
        gp.obj[33].worldX = 60 * gp.tileSize;
        gp.obj[33].worldY = 73 * gp.tileSize;//
        
        gp.obj[34] = new OBJ_Boots(gp);
        gp.obj[34].worldX = 49 * gp.tileSize;
        gp.obj[34].worldY = 78 * gp.tileSize;//
        
        gp.obj[35] = new OBJ_Banana(gp);
        gp.obj[35].worldX = 46 * gp.tileSize;
        gp.obj[35].worldY = 81 * gp.tileSize;//
        
        gp.obj[36] = new OBJ_Brightness(gp);
        gp.obj[36].worldX = 41 * gp.tileSize;
        gp.obj[36].worldY = 61 * gp.tileSize;//
        
        gp.obj[36] = new OBJ_Brightness(gp);
        gp.obj[36].worldX = 60 * gp.tileSize;
        gp.obj[36].worldY = 78 * gp.tileSize;//
        
        gp.obj[37] = new OBJ_Brightness(gp);
        gp.obj[37].worldX = 59 * gp.tileSize;
        gp.obj[37].worldY = 87 * gp.tileSize;//
        
        gp.obj[38] = new OBJ_Coin(gp);
        gp.obj[38].worldX = 68 * gp.tileSize;
        gp.obj[38].worldY = 72 * gp.tileSize;//
        
        gp.obj[39] = new OBJ_Brightness(gp);
        gp.obj[39].worldX = 59 * gp.tileSize;
        gp.obj[39].worldY = 87 * gp.tileSize;//
        
        gp.obj[40] = new OBJ_Brightness(gp);
        gp.obj[40].worldX = 52 * gp.tileSize;
        gp.obj[40].worldY = 46 * gp.tileSize;//
        
        gp.obj[41] = new OBJ_Brightness(gp);
        gp.obj[41].worldX = 30 * gp.tileSize;
        gp.obj[41].worldY = 61 * gp.tileSize;//
        
        gp.obj[42] = new OBJ_QuestionMark(gp);
        gp.obj[42].worldX = 33 * gp.tileSize;
        gp.obj[42].worldY = 40 * gp.tileSize;//
        
        gp.obj[43] = new OBJ_Brightness(gp);
        gp.obj[43].worldX = 37 * gp.tileSize;
        gp.obj[43].worldY = 28 * gp.tileSize;//
        
        gp.obj[44] = new OBJ_Brightness(gp);
        gp.obj[44].worldX = 53 * gp.tileSize;
        gp.obj[44].worldY = 30 * gp.tileSize;//
        
        gp.obj[45] = new OBJ_Brightness(gp);
        gp.obj[45].worldX = 62 * gp.tileSize;
        gp.obj[45].worldY = 26 * gp.tileSize;//
        
        gp.obj[46] = new OBJ_Traps(gp);
        gp.obj[46].worldX = 68 * gp.tileSize;
        gp.obj[46].worldY = 21 * gp.tileSize;//
        
        gp.obj[47] = new OBJ_Coin(gp);
        gp.obj[47].worldX = 53 * gp.tileSize;
        gp.obj[47].worldY = 25 * gp.tileSize;//
        
        gp.obj[48] = new OBJ_Traps(gp);
        gp.obj[48].worldX = 68 * gp.tileSize;
        gp.obj[48].worldY = 46 * gp.tileSize;//
        
        gp.obj[49] = new OBJ_Coin(gp);
        gp.obj[49].worldX = 55 * gp.tileSize;
        gp.obj[49].worldY = 54 * gp.tileSize;//
        
        gp.obj[50] = new OBJ_Boots(gp);
        gp.obj[50].worldX = 40 * gp.tileSize;
        gp.obj[50].worldY = 85 * gp.tileSize;//
        
        gp.obj[51] = new OBJ_Coin(gp);
        gp.obj[51].worldX = 57 * gp.tileSize;
        gp.obj[51].worldY = 81 * gp.tileSize;//
        
        gp.obj[52] = new OBJ_Traps(gp);
        gp.obj[52].worldX = 68 * gp.tileSize;
        gp.obj[52].worldY = 76 * gp.tileSize;//
        
        gp.obj[53] = new OBJ_Coin(gp);
        gp.obj[53].worldX = 68 * gp.tileSize;
        gp.obj[53].worldY = 65 * gp.tileSize;//
        
        gp.obj[54] = new OBJ_Coin(gp);
        gp.obj[54].worldX = 50 * gp.tileSize;
        gp.obj[54].worldY = 66 * gp.tileSize;//
        
        gp.obj[55] = new OBJ_Coin(gp);
        gp.obj[55].worldX = 28 * gp.tileSize;
        gp.obj[55].worldY = 69 * gp.tileSize;//
        
        gp.obj[56] = new OBJ_Coin(gp);
        gp.obj[56].worldX = 35 * gp.tileSize;
        gp.obj[56].worldY = 61 * gp.tileSize;//
        
        gp.obj[57] = new OBJ_Coin(gp);
        gp.obj[57].worldX = 41 * gp.tileSize;
        gp.obj[57].worldY = 28 * gp.tileSize;//
        
        gp.obj[58] = new OBJ_Coin(gp);
        gp.obj[58].worldX = 47 * gp.tileSize;
        gp.obj[58].worldY = 25 * gp.tileSize;//
        
        gp.obj[59] = new OBJ_Coin(gp);
        gp.obj[59].worldX = 57 * gp.tileSize;
        gp.obj[59].worldY = 35 * gp.tileSize;//
        
        gp.obj[60] = new OBJ_MagicalDoor(gp);
        gp.obj[60].worldX = 53 * gp.tileSize;
        gp.obj[60].worldY = 19 * gp.tileSize;//
        
        gp.obj[61] = new OBJ_Coin(gp);
        gp.obj[61].worldX = 47 * gp.tileSize;
        gp.obj[61].worldY = 33 * gp.tileSize;//
        
        gp.obj[62] = new OBJ_Coin(gp);
        gp.obj[62].worldX = 37 * gp.tileSize;
        gp.obj[62].worldY = 35 * gp.tileSize;//
        
        gp.obj[63] = new OBJ_Coin(gp);
        gp.obj[63].worldX = 41 * gp.tileSize;
        gp.obj[63].worldY = 47 * gp.tileSize;//
        
        gp.obj[64] = new OBJ_Traps(gp);
        gp.obj[64].worldX = 68 * gp.tileSize;
        gp.obj[64].worldY = 50 * gp.tileSize;//
        
        gp.obj[65] = new OBJ_Coin(gp);
        gp.obj[65].worldX = 40 * gp.tileSize;
        gp.obj[65].worldY = 76 * gp.tileSize;//
        
        gp.obj[66] = new OBJ_Coin(gp);
        gp.obj[66].worldX = 41 * gp.tileSize;
        gp.obj[66].worldY = 87 * gp.tileSize;//
        
        gp.obj[67] = new OBJ_Coin(gp);
        gp.obj[67].worldX = 60 * gp.tileSize;
        gp.obj[67].worldY = 64 * gp.tileSize;//
        
        gp.obj[68] = new OBJ_Coin(gp);
        gp.obj[68].worldX = 57 * gp.tileSize;
        gp.obj[68].worldY = 50 * gp.tileSize;//
        
        gp.obj[69] = new OBJ_Coin(gp);
        gp.obj[69].worldX = 27 * gp.tileSize;
        gp.obj[69].worldY = 47 * gp.tileSize;//
        
        gp.obj[70] = new OBJ_Coin(gp);
        gp.obj[70].worldX = 68 * gp.tileSize;
        gp.obj[70].worldY = 37 * gp.tileSize;//
        
        gp.obj[71] = new OBJ_Traps(gp);
        gp.obj[71].worldX = 45 * gp.tileSize;
        gp.obj[71].worldY = 23 * gp.tileSize;//
        
        gp.obj[71] = new OBJ_Traps(gp);
        gp.obj[71].worldX = 55 * gp.tileSize;
        gp.obj[71].worldY = 56 * gp.tileSize;//
        
        gp.obj[71] = new OBJ_Traps(gp);
        gp.obj[71].worldX = 54 * gp.tileSize;
        gp.obj[71].worldY = 61 * gp.tileSize;//
        
        gp.obj[71] = new OBJ_Traps(gp);
        gp.obj[71].worldX = 56 * gp.tileSize;
        gp.obj[71].worldY = 61 * gp.tileSize;//
        
        
        //DEBUGGING
        
       // gp.obj[72] = new OBJ_LastPortal(gp);
        //gp.obj[72].worldX = 51 * gp.tileSize;
        //gp.obj[72].worldY = 92 * gp.tileSize;
        
        
        
    }
    public void makeAllBananaDisappearAcrossAllMaps() {
    for (int mapNum = 0; mapNum < gp.obj.length; mapNum++) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] instanceof OBJ_Banana) {
                gp.obj[i] = null;
            }
        }
    }
    }
    public void makeAllBootsDisappearAcrossAllMaps() {
    for (int mapNum = 0; mapNum < gp.obj.length; mapNum++) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] instanceof OBJ_Boots) {
                gp.obj[i] = null;
            }
        }
    }
    }
    public void makeAllCoinDisappearAcrossAllMaps() {
    for (int mapNum = 0; mapNum < gp.obj.length; mapNum++) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] instanceof OBJ_Coin) {
                gp.obj[i] = null;
            }
        }
    }
    }
     public void makeAllQuestionMarkDisappearAcrossAllMaps() {
    for (int mapNum = 0; mapNum < gp.obj.length; mapNum++) {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] instanceof OBJ_QuestionMark) {
                gp.obj[i] = null;
            }
        }
    }
    }
    
}
