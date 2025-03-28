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
       
        
        lighting = new Lighting(gp, 576); //350 is the current number
       // fog = new Fog(gp, 700);
    }
    public void draw(Graphics2D g2){
        lighting.draw(g2);
        //fog.draw(g2);
    }
}
