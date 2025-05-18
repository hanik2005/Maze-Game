/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
import main.GamePanel;

/**
 *
 * @author Nick Charles Clarito
 */
public class Fog {
    GamePanel gp;
    BufferedImage fogFilter;
    
    public Fog(GamePanel gp, int fogDensity) {
        
        // Create the fog image
        fogFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) fogFilter.getGraphics();

        // Create fog color with different levels of opacity
        Color color[] = new Color[8];
        float fraction[] = new float[8];

        // Define color and opacity (increased opacity for heavier fog)
        color[0] = new Color(200, 200, 200, 100); //20  // Very light fog 200
        color[1] = new Color(180, 180, 180, 120); //40  // Light fog    220
        color[2] = new Color(160, 160, 160, 240);  //60 // Moderate fog
        color[3] = new Color(140, 140, 140, 255); //80  // Thick fog
        color[4] = new Color(120, 120, 120, 255); //100 // Denser fog
        color[5] = new Color(100, 100, 100, 255); //120 // Very dense fog
        color[6] = new Color(80, 80, 80, 255);   //150  // Heavy fog
        color[7] = new Color(60, 60, 60, 255);   //180  // Thickest fog

        // Define fog intensity spread
        fraction[0] = 0f;
        fraction[1] = 0.1f;
        fraction[2] = 0.3f;
        fraction[3] = 0.5f;
        fraction[4] = 0.65f;
        fraction[5] = 0.75f;
        fraction[6] = 0.85f;
        fraction[7] = 1f;

        // Center point for the fog effect
        int centerX = gp.screenWidth / 2;
        int centerY = gp.screenHeight / 2;

        // Create a radial gradient for the fog
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, fogDensity, fraction, color);
        g2.setPaint(gPaint);

        // Fill the entire screen with fog
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        //System.out.print("sus");
        g2.drawImage(fogFilter, 0, 0, null);
    }
}
