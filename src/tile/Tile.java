/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author kring
 */
public class Tile {
   public BufferedImage image;
   public boolean collision = false; 
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Adjust size if needed
}
