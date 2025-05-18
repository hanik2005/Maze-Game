/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package environment;

import java.awt.Graphics2D;
import main.GamePanel;

/**
 *
 * @author kring
 */
public class EnvironmentManager {
    GamePanel gp;
    Lighting lighting;
    Fog fog;
    
    public EnvironmentManager(GamePanel gp){
        this.gp = gp;
        
        
    }
    public void setup(){
       
            fog = new Fog(gp, 700);
        
            lighting = new Lighting(gp, 150); //350 is the current number 576
        
    }
    public void draw(Graphics2D g2){
         if(gp.environmentState == gp.fogState){
            fog.draw(g2);
        }
        if(gp.environmentState == gp.lightingState){
            lighting.draw(g2);
        }
    }
}
