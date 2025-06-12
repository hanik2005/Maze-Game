/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import tile.TileManager;
import tile_interactive.InteractiveTile;

/**
 *
 * @author kring
 */
public class GamePanel extends JPanel implements Runnable{ // inherits jPanel and to run a thread we must implement the runnable
    
    //DIFFERENT
    
    //SCREEN SETTINGS
    
    public final int originalTileSize = 16; // 16x16 
    public final int scale = 3; // 16 times 3 now it looks like 48 by 48
    
    public final int tileSize = originalTileSize * scale; // 48x48 means scale and more clear the Assets.tiles and characters
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    
    //WORLD SETTINGS
    public int maxWorldCol = 100; //50 original col
    public int maxWorldRow = 100; // 50 original row
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60; // updates the screen 60 times per second which mean there 60 static images moving
    
    //SYSTEM
    public TileManager tileM = new TileManager(this); 
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread; // Thread is something you can start and once a thread start it keeps your program running until the user stop it
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    public EnvironmentManager eManager =  new EnvironmentManager(this);
    
    
    //ENVIRONMENT
    public boolean eManagerActive = false;
    
    //ENVIRONMENT STATE
    public int environmentState;
    public final int fogState = 0;
    public final int lightingState = 1;
    
    
    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH, ui);
    public Entity npc[] = new Entity[11];
    public Entity monster[] = new Entity[20];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    public Entity obj[] = new Entity[100];
    public Entity entity = new Entity(this);
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    
    
    //GAME STATE
    // DIFFERENT STATE SCREEN
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameMenuState = 3;
    public final int instructionState = 4;
    public final int nameState = 5;
    public final int leaderboardState = 6;
    public final int gameFinishedState = 7;
    public final int gameOverState = 8;
    public final int gameLevelsState = 9;
    public final int dialogueState = 10;
    public final int playerStatusState = 11;
    
    
    //NAME OF PLAYER
    // STORE PLAYER INFORMATION 
    
     public String playerName = "";
     public List<String> leaderboardData = new ArrayList<>();
    
     //LEVELS STATE
     public int LevelState;
     public final int Levels_1 = 0;
     public final int Levels_2 = 1;
     public final int Levels_3 = 2;
     public final int Levels_4 = 3;
     public final int Levels_5 = 4;
     public final int Levels_6 = 5;
     public final int tutorial_level = 6;
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of the jpanel
        this.setBackground(Color.black); // default
        this.setDoubleBuffered(true); // improve rendering performance
        this.addKeyListener(keyH); // this gamepanel can recognize keyInputs
        this.setFocusable(true);// this gamePanel can be focused to recieve keyInputs
        System.out.println("HELLO WELCOME");
        
    }
    public void setupGame(){
        System.out.println("Debug: "+ LevelState);
        
        //Set the State
        gameState = titleState;
        
        //SET ENVIRONMENT MANAGER
        eManager.setup();
        
        //Debugging State
        // gameState = gameLevelsState;
         playMusic(0);
    }
    public void setMap(){
        switch (LevelState) {
            case Levels_1:
                aSetter.setObject_1();
                aSetter.setNpc();
                aSetter.setEnemy();
                aSetter.setInteractiveTile();
                eManagerActive = false;
                environmentState = fogState;
                break;
            case Levels_2:
                aSetter.setObject_2();
                eManagerActive = true;
                environmentState = lightingState;
                break;
            case Levels_3:
                aSetter.setObject_2();
                eManagerActive = true;
                break;
            case Levels_4:
                break;
            case Levels_5:
                break;
            case Levels_6:
                break;
            case tutorial_level:
                aSetter.setNpc();
                break;
        }
    
    
    }
    
    public void startGameThread(){ // here is the method of the startGameThread
        
        gameThread = new Thread(this); // we passing gamepanel class to this thread constructor
        gameThread.start(); // call the start method to run the run method
    }

    @Override
    public void run() {  // when you implement runnable it automatically add run method this is where you can run the Thread which means it keeps 
                         //running the program until the user stop it.
                        // when we start this Thread it automitically called the run method so we create a method in the gamepanel 
                        //class called startGameThread
                        
                        
        //THIS IS THE DELTA OR ACCUMULATOR METHOD ALGORITHM
        
        double drawInterval = 1000000000 / FPS;  // IT MEANS one seconds divide fps which is 60 it means we draw it every 0.016 seconds 
                                                //so we can run the screen 60 times per seconds 
        double delta = 0;
        long lastTime = System.nanoTime(); // 1 billion nano second is equivalent to one second
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while(gameThread != null){ // as long as gameThread exits it will run inside the while loop 
            currentTime = System.nanoTime(); // WE CHECK THE CURRENT TIME
            delta += (currentTime - lastTime) / drawInterval; // IT MEANS HOW MUCH TIME IS PAST, IT MEANS EVERY LOOP WE ADD THE LAST TIME DIVIDE TO THE
                                                              // DRAW INTERVAL TO DELTA AND WHEN THIS DELTA REACHED THIS DRAW INTERVAL THEN WE UPDATE AND
                                                              // REPAINT THEN RESET THIS DELTA AT EVERY DRAW INTERVAL
            timer += (currentTime - lastTime); // IT MEANS CHECKING IT RUN 60 FPS IT MEANS IN EVERY LOOP WE ADD THE PAST TIME TO THIS TIMER 
            lastTime = currentTime;
            
            if(delta >= 1){ // WHEN THE TIMER REACHED ONE SECONDS IT UPDATES AND REPAINT AND RESET DELTA
            update();// update information such as character position and collision of the character
            repaint(); // this is how you call paint component method and draw screen with the updated information
            delta--;
            drawCount++;
            } 
            
            if(timer >= 1000000000){ // WHEN THE TIMER REACHED THIS IS THE REPRESENTATION IT RUNS 60 TIMES PER SECOND
                //System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
    }
    public void update(){
      if(gameState == playState){
        //PLAYER UPDATE MOVEMENT
        player.update();
        //NPC UPDATE MOVEMENT
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].update();
            }
        
        }
        //MONSTER UPDATE MOVEMENT
        for(int i = 0; i < monster.length; i++){
            if(monster[i] != null){
               if(monster[i].alive == true && monster[i].dying == false){
                   monster[i].update();
               }
               if(monster[i].alive == false){
                   monster[i].checkDrop();
                   monster[i] = null;
               }
            }
        
        }

        //UPDATE PROJECTILES
          for(int i = 0; i < projectileList.size(); i++){
              if(projectileList.get(i) != null){
                  if(projectileList.get(i).alive == true){
                      projectileList.get(i).update();
                  }
                  if(projectileList.get(i).alive == false){
                      projectileList.remove(i);
                  }
              }

          }
        //UPDATES DESTRUCTIBLE OBJECTS
        for(int i = 0; i < iTile.length; i++){
            if(iTile[i] != null){
                iTile[i].update();
            }
        }






      }
      if(gameState == pauseState){
        
      }
    }
    public void paintComponent(Graphics g){ // paintComponent built in method in java and the Graphics class has function to draw objects on the screen
        
        super.paintComponent(g); // super means the parent class so painComponent is our parent class and gamepanel is a subclass of Jpanel
        
        Graphics2D g2 = (Graphics2D)g; // means changing g to 2d class which make it broader or it has more functions it provide geometry coordinate 
                                        //transformation, color manegment, text layout, and more
        
        
        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true){
            drawStart = System.nanoTime();
        }
        
        if(gameState == titleState){
            ui.draw(g2);
        
        }else{
        
        
        //TILES
        tileM.draw(g2);

        for (int i = 0; i < iTile.length; i++){
            if (iTile[i] != null){
                iTile[i].draw(g2);
            }
        }
        
        
        //ADD ENTITIES TO THE LIST OR WHAT WE CALL ARRAYLIST
        //PLAYER
        entityList.add(player);
        
        //NPC
        for(int i = 0; i<npc.length; i++){
            if(npc[i] != null){
                entityList.add(npc[i]);
            }
        }
        
        //MONSTER
        for(int i = 0; i<monster.length; i++){
            if(monster[i] != null){
                entityList.add(monster[i]);
            }
        }
        
        //OBJECT
        for(int i = 0; i < obj.length; i++){
             if(obj[i] != null){
                 entityList.add(obj[i]);
             }
        }
        //PROJECTILES

            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }



        
        //SORT THE LIST
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });
        
        //DRAW ENTITIES
        for(int i = 0; i < entityList.size(); i++){
            entityList.get(i).draw(g2);
        
        }
        //EMPTY ENTITY LIST
         entityList.clear();
        
        
        
        
        
        
        
        //ENVIRONMENT
        if(eManagerActive == true){
            //System.out.println("working");
            eManager.draw(g2);
        }
        
        //UI
        ui.draw(g2);
        
        
        
        
        //EXPERIMENTATION TIME
        

        
        //DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose(); // dispose of this graphics context and release any system resources that it is using
        }
    }
    //problem
    public void reset(){
        //REMOVE OBJECTS
         for (int i = 0; i < obj.length; i++) {
            obj[i] = null;
        }
        //RESET NPCS 
         for(int i = 0; i<npc.length; i++){
             npc[i] = null;
         }
         
         // RESET MAP
         //tileM.resetMap();
    
    
    }
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void PlaySE(int i){
        se.setFile(i);
        se.play();
    }
    
}
