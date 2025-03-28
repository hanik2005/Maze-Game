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

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[80];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/maps.txt");
    }
    public void getTileImage(){
         
           
           setup(0, "001", true);
           setup(1, "002", false); // false
           setup(2, "003", true);
           setup(3, "004", true);
           setup(4, "005", true);
           setup(5, "006", true);
           setup(6, "007", true);
           setup(7, "008", true);
           setup(8, "009", true); 
           setup(9, "010", true); //
           setup(10, "011", true);
           setup(11, "012", true);
           setup(12, "013", true);
           setup(13, "014", true);
           setup(14, "015", true);
           setup(15, "016", true);
           setup(16, "017", true);
           setup(17, "018", true);
           setup(18, "019", true);
           setup(19, "020", true);
           setup(20, "021", true);
           setup(21, "022", true);//
           setup(22, "023", true);
           setup(23, "024", true);
           setup(24, "025", true);
           setup(25, "026", true);
           setup(26, "027", true);
           setup(27, "028", true);
           setup(28, "029", true);
           setup(29, "030", true);
           setup(30, "031", true);
           setup(31, "032", true);//
           setup(32, "033", true);
           setup(33, "034", true);
           setup(34, "035", true);
           setup(35, "036", true);
           setup(36, "037", true);
           setup(37, "038", true);
           setup(38, "039", true);
           setup(39, "040", true);
           setup(40, "041", true);
           setup(41, "042", true);//
           setup(42, "043", true);
           setup(43, "044", true);
           setup(44, "045", true);
           setup(45, "046", true);
           setup(46, "047", true);
           setup(47, "048", true);
           setup(48, "049", true);
           setup(49, "050", true);
           setup(50, "051", true);
           setup(51, "052", true);//
           setup(52, "053", true);
           setup(53, "054", true);
           setup(54, "055", true);
           setup(55, "056", true);
           setup(56, "057", true);
           setup(57, "058", true);
           setup(58, "059", true);
           setup(59, "060", true);
           setup(60, "061", true);
           setup(61, "062", true);//
           setup(62, "063", true);
           setup(63, "064", true);
           setup(64, "065", true); 
           setup(65, "064", true);
           setup(66, "065", true); // block maze
           setup(67, "066", true); 
           setup(68, "067", false); // false
           setup(69, "068", true); 
           setup(70, "069", true); // here last
           
           
           

       
    }
    public void setup(int index, String imageName, boolean collision){
        
        UtilityTool uTool = new UtilityTool();
        
        
         try{
            
             // Debug: Check if the image file exists
        String imagePath = "/tiles/" + imageName + ".png";
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
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
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
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
           
                worldRow ++;
              
            }
        }
    }
}