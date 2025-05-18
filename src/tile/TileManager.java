/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author kring
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public int mapCount = 0;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp){
        this.gp = gp;
        
        //READ THE DATA FILE
        InputStream is = getClass().getResourceAsStream("/maps/originalupdated.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        //GETTING TILE NAME AND COLLISION IN THE DATA FILE
         String line;
         try{
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
         }catch(IOException e){
             e.printStackTrace();
         }
        
        //INIATIALIZE TILE ARRAY BASED ON THE FILENAME
        tile = new Tile[fileNames.size()];
        getTileImage();
        
        is = getClass().getResourceAsStream("/maps/maps.txt");
        br = new BufferedReader(new InputStreamReader(is));
        
        try{
            String line2 = br.readLine();
            String[] maxTile = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
            
            br.close();
        
        }catch(IOException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        //loadMap("/maps/maps.txt");
        checkWhatMap();
        
       
    }
    public void checkWhatMap(){
        if(gp.LevelState == gp.Levels_1){
            loadMap("/maps/maps.txt");
            
        }
        if(gp.LevelState == gp.Levels_2){
            loadMap("/maps/updated_map_level_2.txt");
            
        }
        if(gp.LevelState == gp.Levels_3){
            loadMap("/maps/maps.txt");
            
        }
        if(gp.LevelState == gp.Levels_4){
            loadMap("/maps/first_example.txt");
           
        }
        if(gp.LevelState == gp.Levels_5){
            loadMap("/maps/maps.txt");
            
        }
        if(gp.LevelState == gp.Levels_6){
            loadMap("/maps/first_example.txt");
           
        }
        if(gp.LevelState == gp.tutorial_level){
            loadMap("/maps/maps.txt");
            System.out.println("Debugging");
            
        }
    
    }
    public void getTileImage(){
        
         for (int i = 0; i < fileNames.size(); i++) {
             boolean collision;
             String fileName;
            //GET FILE NAME
            fileName = fileNames.get(i);
            
            //GET A COLLISION STATUS
            if(collisionStatus.get(i).equals("true")){
                collision = true;
            }else{
                collision = false;
            
            }
            setup(i, fileName, collision);
        }
         
           
//           setup(0, "001", true);
//           setup(1, "002", false); // false
//           setup(2, "003", true);
//           setup(3, "004", true);
//           setup(4, "005", true);
//           setup(5, "006", true);
//           setup(6, "007", true);
//           setup(7, "008", true);
//           setup(8, "009", true); 
//           setup(9, "010", true); //
//           setup(10, "011", true);//correct
//           setup(11, "012", true);
//           setup(12, "013", true);
//           setup(13, "014", true);
//           setup(14, "015", true);
//           setup(15, "016", true);
//           setup(16, "017", true);
//           setup(17, "018", true);
//           setup(18, "019", true);
//           setup(19, "020", true);
//           setup(20, "021", true);//correct
//           setup(21, "022", true);//
//           setup(22, "023", true);
//           setup(23, "024", true);
//           setup(24, "025", true);
//           setup(25, "026", true);
//           setup(26, "027", true);
//           setup(27, "028", true);
//           setup(28, "029", true);
//           setup(29, "030", true);
//           setup(30, "031", true);//correct
//           setup(31, "032", true);//
//           setup(32, "033", true);
//           setup(33, "034", true);
//           setup(34, "035", true);
//           setup(35, "036", true);
//           setup(36, "037", true);
//           setup(37, "038", true);
//           setup(38, "039", true);
//           setup(39, "040", true);
//           setup(40, "041", true);//correct
//           setup(41, "042", true);//
//           setup(42, "043", true);
//           setup(43, "044", true);
//           setup(44, "045", true);
//           setup(45, "046", true);
//           setup(46, "047", true);
//           setup(47, "048", true);
//           setup(48, "049", true);
//           setup(49, "050", true);
//           setup(50, "051", true);//correct
//           setup(51, "052", true);//
//           setup(52, "053", true);
//           setup(53, "054", true);
//           setup(54, "055", true);
//           setup(55, "056", true);
//           setup(56, "057", true);
//           setup(57, "058", true);
//           setup(58, "059", true);
//           setup(59, "060", true);
//           setup(60, "061", true);//correct
//           setup(61, "062", true);//
//           setup(62, "063", true);
//           setup(63, "064", true);
//           setup(64, "065", true); 
//           setup(65, "066", true);
//           setup(66, "067", true); // block maze
//           setup(67, "068", true); 
//           setup(68, "069", false); // false
//           setup(69, "070", false); 
           
           

       
    }
    public void setup(int index, String imageName, boolean collision){
        
        UtilityTool uTool = new UtilityTool();
        
        
         try{
            
             // Debug: Check if the image file exists
        String imagePath = "/tiles/" + imageName;
        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is == null) {
            System.out.println("Image not found: " + imagePath);  // Debug statement
        }

        tile[index] = new Tile();
        tile[index].image = ImageIO.read(is);
        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        tile[index].collision = collision;
        
        // Optional: Define the solid area for specific tiles if needed
        if (collision) {
            tile[index].solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    public void loadMap(String filePath){
        try{
            InputStream is =  getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol ){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
                
            }
            
            br.close();
        }catch(Exception e){
            
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow]; // 0 * 48 it means you are in the x = 0, and y=0
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // we minus to return the screen position this is positioning where what player can only see in the world camera
                                                                         // for example if the player position is x = 500, y = 500 then screen position is negative 500 x and y
                                                                         // it means out of game window and there that how only can see depending screen width and screen height
                                                                         // player.screenX because the screen position is always at the center of the screen for example screenX =0
                                                                         // and screen y equals = 0 is top left corner in the game window so still it displayed center of the screen not
                                                                         // the top left because we screenwidth and screen height only to the player
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // this only draws the player that can only see to prevent bigger problems
                                                                                // so we create a boundary that is from center of the screenx and y minus and
                                                                                // plus player screen x and y which the player current position the out of boundary
                                                                                 // of the screen
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            
             //See the colision area of the tiles "DEBUGING ONLY"
               if (tile[tileNum].collision) {
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
               }  
            }
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){ // then we draw one by one and reset one by one and reset maptile array
                worldCol = 0;
           
                worldRow ++;
              
            }
        }
    }
}