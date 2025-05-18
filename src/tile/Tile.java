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
   public boolean collision = false; // it means if the collision is false the player can move around with it and if it is collision true then it has
                                     // collision it means there the detection you cannot go there
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Adjust size if needed
}