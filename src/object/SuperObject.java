/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;


public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    
    //OJECT MESSAGE
    public String message = "";
    
    
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
     UtilityTool uTool = new UtilityTool();
        
    public void draw(Graphics2D g2, GamePanel gp){
        
        
             int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){
                
                 g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                 
                 
                 //See the colision area of the object
                 g2.setColor(Color.red);
                 g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
    }

}
