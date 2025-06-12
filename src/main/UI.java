/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import object.OBJ_Ammo;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Mana;

/**
 *
 * @author kring
 */
public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, arial_40B, arial_50B, arial_30;
    BufferedImage coinImage, heart_full, heart_half, heart_blank, background_image, ammo_full, ammo_blank, mana_full, mana_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    ArrayList<String> scrollMessage = new ArrayList<>();
    ArrayList<Integer>scrollMessageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public int commandNum = 0;
    public int commandNumDown = 0;
    public int subState;
    public String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;
   
    
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        arial_40B = new Font("Arial", Font.BOLD, 40);
        arial_50B = new Font("Arial", Font.BOLD, 50);
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        Entity coin = new OBJ_Coin(gp);
        coinImage = coin.image;
        //playTime = 180; // 10 minutes or 5 mins or 3 mins? 60 + 60 + 60 = 3 mins
        
        //CREATE HEART OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        //CREATE AMMO OBJECT
        Entity mana = new OBJ_Mana(gp);
        mana_full = mana.image;
        mana_blank = mana.image2;

        //CREATE AMMO OBJECT
        Entity ammo = new OBJ_Ammo(gp);
        ammo_full = ammo.image;
        ammo_blank = ammo.image2;
    
    }
    public void showMessage(String text){
        
        
        message = text;
        messageOn = true;
        
        
        
        
    }
    public void addMessage(String text){
        scrollMessage.add(text);
        scrollMessageCounter.add(0);
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        
        
        
        if(messageOn == true){
            g2.setColor(Color.black);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
            int x = gp.screenWidth/4;
            g2.drawString(message, x, gp.tileSize * 5);
            
            messageCounter++;
            
            if(messageCounter > 50){ // 120
                messageCounter = 0;
                messageOn = false;
            }
        }
        
        //Next line
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        // State Do
        
        if(gp.gameState == gp.playState){
           drawPlayerLife();
           drawPlayerMana();
           drawPlayerAmmo();
           drawCoinAndTimeCount();
           drawMessage();
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOver(g2);
        }
        if(gp.gameState == gp.instructionState){
             drawInstruction(g2);
        }
        if(gp.gameState == gp.nameState){
            drawInputName(g2);
        }
        if(gp.gameState == gp.leaderboardState){
            drawLeaderboard(g2);
        }
        if(gp.gameState == gp.gameMenuState){
            drawSettingState(g2);
        }
        if(gp.gameState == gp.gameLevelsState){
            drawGameLevels(g2);
        
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen(g2);
        
        }
        if(gp.gameState == gp.gameFinishedState){
            drawCongratulationsScreen();
        
        }
        if(gp.gameState == gp.playerStatusState){
            drawStatusScreen();
            drawInventory();
        }
        
        
     
        
        
    }
    public void drawPlayerMana(){
        if(gp.player.type == gp.player.type_rock) {
            int x = gp.tileSize / 2;
            int y = gp.tileSize / 2;
            int i = 0;

            //DRAW MAX BULLET
            x = (gp.tileSize / 2) - 5;
            y = (int) (gp.tileSize * 1.5);
            i = 0;
            while (i < gp.player.maxMana) {
                g2.drawImage(mana_blank, x, y, null);
                i++;
                x += 35;
            }
            //DRAW AMMO
            x = (gp.tileSize / 2) - 5;
            y = (int) (gp.tileSize * 1.5);
            i = 0;
            while (i < gp.player.mana) {
                g2.drawImage(mana_full, x, y, null);
                i++;
                x += 35;
            }
        }
    }
    public void drawPlayerAmmo(){
        if(gp.player.ammoExisted) {
            int x = gp.tileSize / 2;
            int y = gp.tileSize / 2;
            int i = 0;

            //DRAW MAX BULLET
            x = (gp.tileSize / 2) - 5;
            y = (int) (gp.tileSize * 2.5);
            i = 0;
            while (i < gp.player.maxBullet) {
                g2.drawImage(ammo_blank, x, y, null);
                i++;
                x += 35;
            }
            //DRAW AMMO
            x = (gp.tileSize / 2) - 5;
            y = (int) (gp.tileSize * 2.5);
            i = 0;
            while (i < gp.player.bullet) {
                g2.drawImage(ammo_full, x, y, null);
                i++;
                x += 35;
            }
        }

    }
    public void checkTimeLevel(){
       if(gp.LevelState == gp.Levels_1){
           playTime = 180;
       
       }
       if(gp.LevelState == gp.Levels_2){
           playTime = 300;
       
       }
       if(gp.LevelState == gp.Levels_3){
           playTime = 300;
       
       }
       if(gp.LevelState == gp.Levels_4){
           playTime = 180;
       
       }
       if(gp.LevelState == gp.Levels_5){
           playTime = 300;
       
       }
       if(gp.LevelState == gp.Levels_6){
           playTime = 300;
       
       }
 
    
    }
    
    
    public void drawCoinAndTimeCount(){
        
        // COIN COUNT
        //I DIDNT INCLUDE BECAUSE I ADD THE PLAYER STATUS
        
//        if (gp.gameState == gp.playState && !gameFinished) {
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//            g2.drawImage(coinImage, gp.tileSize/2, gp.tileSize * 2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x = " + gp.player.hasCoin, 74, 135); // 74, 135
//        }
        
        
        // TIME COUNT OR DECOUNT
        
        //OLD WAY TIME INCREMENT
        
//        if (gp.gameState == gp.playState && !gameFinished) {
//            g2.setFont(arial_40);
//            g2.setColor(Color.black);
//            playTime += (double)1/60;
//            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 15, 65);
//        }

        // NEW WAY TIME DECREMENT WITH MINUTES AND SECONDS
        // TIME
        if (gp.gameState == gp.playState && !gameFinished && gp.LevelState != gp.tutorial_level) {
            g2.setFont(arial_40B);
            g2.setColor(Color.white);
    
            // Countdown timer logic
            if (playTime > 0) {
                playTime -= (double)1 / 60;
            }
            else {
            // Change game state to gameOverState when time is up
                gp.gameState = gp.gameOverState;
                gp.stopMusic();
                gp.PlaySE(5);
                checkTimeLevel();
                //playTime = 180;
                //gameFinished = true;
            }
    
            // Format time display in minutes and seconds
            int minutes = (int) (playTime / 60);
            int seconds = (int) (playTime % 60);
            String timeText = String.format("Time: %02d:%02d", minutes, seconds);
            g2.drawString(timeText, gp.tileSize * 15, 65);
        }
        
        
        //OLD
        
        //playTime += (double)1/60;
        //g2.drawString("Time:" +dFormat.format(playTime), gp.tileSize*15, 65);
    
    
    }
    
    public void drawStatusScreen(){
        
        //CREATE A FRAME
        final int frameX = gp.tileSize * 2;
        final int frameY = 0;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 13;
        
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35; //SAME WHAT SET YOUR FONT 
        
        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Life", textX, textY);
        textY += lineHeight;

        g2.drawString("Mana", textX, textY);
        textY += lineHeight;

        g2.drawString("Bullet", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;

        g2.drawString("Projectile", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Coin", textX, textY);
        textY += lineHeight; //IF LAST IN TEXT VALUES ADD 20 TO HAVE ROOM OF IMAGES
        
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        
        g2.drawString("Shield", textX, textY);
        textY += lineHeight + 15;

        g2.drawString("Projectile", textX, textY);
        textY += lineHeight + 15;
        
        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        
        //RESET TEXT Y
        textY = frameY + gp.tileSize;
        String value;
        
        
        value = String.valueOf(gp.player.level);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.bullet + "/" + gp.player.maxBullet);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.strength);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.dexterity);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.defense);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.projectileDamage);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.exp);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.coin);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        //DRAW THE WEAPON AND THE SHIELD IMAGE
        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 34, null);
        textY += gp.tileSize;
        
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 40, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentProjectile.down1, tailX - gp.tileSize, textY - 40, null);
        textY += gp.tileSize;
                
    }
    
    public void drawCongratulationsScreen(){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            
           
            
            String text;
            int textLength;
            int x;
            int y;
            double yDouble = 1.5;
            int minutes = (int) (playTime / 60);
            int seconds = (int) (playTime % 60);
            
            
            //CONGRATULATIONS
            
            text = "You Exit the Maze";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);
            
            
            g2.setFont(arial_50B);
            g2.setColor(Color.white);
            
            text = gp.playerName;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight / 3  + (gp.tileSize * 3);
            g2.drawString(text, x, y);
            
            
             g2.setFont(arial_40);
            g2.setColor(Color.white);
            
            text = String.format("You Finished in: %02d:%02d", minutes, seconds);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight * 1 - (gp.tileSize * 3);
            g2.drawString(text, x, y);
            
            
            text = ("Coins you have: " + gp.player.hasCoin);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 5);
            g2.drawString(text, x, y);
            
            
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/3 + (gp.tileSize * 1);
            g2.drawString(text, x, y);
            
           // gp.gameThread = null;
        
    
    }
    
    public void drawPlayerLife(){
        
       // gp.player.life = 5;
    
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        
        //DRAW MAXLIFE
        while(i < gp.player.maxLife / 2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        
        }
        
        //RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        
        //DRAW CURRENT LIFE
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            
            }
            i++;
            x+= gp.tileSize;
        }
    
    }
    
    
    public void drawDialogueScreen(Graphics2D g2){
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line : currentDialogue.split("/n")){
            //System.out.println("dubug");
            g2.drawString(line, x, y);
            y+=40;
        
        }
        
    
    }
    public void drawSubWindow(int x, int y, int width, int height){
       Color c = new Color(0, 0, 0, 210);
       g2.setColor(c);
       g2.fillRoundRect(x, y, width, height, 35, 35);
       
       c = new Color(255, 255, 255);
       g2.setColor(c);
       g2.setStroke(new BasicStroke(5));
       g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    
    }
    public void drawPauseScreen(){
        
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCentered(text);
        
        
        
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
        
    
    
    }
    public void drawBackground(){
        UtilityTool uTool = new UtilityTool();
        try{
            background_image = ImageIO.read(getClass().getResourceAsStream("/Assets/backgrounds/background.png"));
            background_image =  uTool.scaleImage(background_image, gp.tileSize, gp.tileSize);
            g2.drawImage(background_image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }catch(IOException e){
            e.printStackTrace();
        
        }
    }
    public void drawTitleScreen(Graphics2D g2) {
        //Draw the Background of the title State

        drawBackground();





        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Maze Runner";
        int x = gp.tileSize * 4 + 10;
        int y = gp.tileSize * 3;

        g2.setColor(Color.gray);
        g2.drawString(text, x , y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 3;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.idle, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "NEW GAME";
        x = getXforCentered(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "HOW TO PLAY";
        x = getXforCentered(text);
        y += gp.tileSize -2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LEADERBOARD";
        x = getXforCentered(text);
        y += gp.tileSize - 2;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "TUTORIAL";
        x = getXforCentered(text);
        y += gp.tileSize -2;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCentered(text);
        y += gp.tileSize - 2;
        g2.drawString(text, x, y);
        if (commandNum == 4) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Dispose of the temporary graphics object
        g2.dispose();
    
    }
    
    public void drawGameOver(Graphics2D g2) {
     try {
        String text;
        String textTitle = "Game Over";
        
        // Fill the background with black color
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        // Set the font and color for the game over title
        g2.setFont(gp.ui.arial_40); // Ensure you have a reference to the font
        g2.setColor(Color.WHITE);
        
        // Draw the "Game Over" title
        int x = getXforCentered(textTitle);
        int y = gp.tileSize * 3;
        g2.drawString(textTitle, x, y);
        
        // Draw the "Retry" option
        text = "RETRY";
        x = getXforCentered(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        // Draw the "Quit" option
        text = "QUIT";
        x = getXforCentered(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }


        text = "BACK";
        x = getXforCentered(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }



    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void drawInstruction(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        
        
        String title = "Instruction";
        String text = "";
        
        
        //title
        
        int x = gp.tileSize * 7;
        int y = gp.tileSize * 2;
        g2.setColor(Color.white);
        g2.drawString(title, x, y);
        
        
        //text
        g2.setFont(arial_30);
        g2.setColor(Color.white);
        text = "Controls: W, A, S, D(Top, Left, Bottom, Right)";
        x = gp.tileSize * 1;
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        
        text = "Goal: Escape the Maze";
        x = gp.tileSize * 1;
        y += gp.tileSize * 1;
        g2.drawString(text, x, y);
        
        text = "Avoid obstacles(traps that can slow you,time that can kill you,";
        x = gp.tileSize * 1;
        y += gp.tileSize * 1;
        g2.drawString(text, x, y);
        
        text = "and trap that can kill you)";
        x = gp.tileSize * 1;
        y += gp.tileSize * 1;
        g2.drawString(text, x, y);
        
         text = "Good Luck :)";
        x = getXforCentered(text);
        y += gp.tileSize * 1;
        g2.drawString(text, x, y);
        
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 37F));
        text = "BACK";
        x = getXforCentered(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
       
            g2.drawString(">", x - gp.tileSize, y);
        
    
    }
    public void drawInputName(Graphics2D g2){
        
          g2.setColor(Color.BLACK);
          g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String title = "Enter Your Name";
        String text = gp.playerName; // Get the current inputted name

        // Position the title
        int x = gp.tileSize * 5;
        int y = gp.tileSize * 2;
        g2.setColor(Color.white);
        g2.drawString(title, x, y);

        // Draw the inputted name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        x = gp.screenWidth / 2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2;
        y += gp.tileSize * 3; // Move down below the title
        g2.drawString(text, x, y);

        // add a cursor to indicate where the user is typing
         if ((System.currentTimeMillis() / 500) % 2 == 0) { // Blink effect for cursor
            g2.drawString("|", x + g2.getFontMetrics().stringWidth(text), y); // Add cursor after the text
        }

        // Draw the "Back" option
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 37F));
        text = "BACK";
        x = getXforCentered(text);
        y += gp.tileSize * 5;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y); // Indicate "BACK" is selected
        }
        
    
    }
     public void drawLeaderboard(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String title = "Leaderboard";

        // Draw the leaderboard title
        int x = gp.tileSize * 7;
        int y = gp.tileSize * 2;
        g2.setColor(Color.white);
        g2.drawString(title, x, y);

        // Draw leaderboard entries
        g2.setFont(arial_40);
        g2.setColor(Color.yellow);
        x = gp.screenWidth / 4;
        y += gp.tileSize * 3; // Start after the title

        for (int i = 0; i < gp.leaderboardData.size(); i++) {
            String playerInfo = gp.leaderboardData.get(i);
            g2.setFont(arial_30);
            g2.drawString((i + 1) + ". " + playerInfo, x, y);
            x = getXforCentered(playerInfo);
            y += gp.tileSize * 2; // Move down for the next entry
        }

            // Add a BACK option to return to the main menu
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String backText = "BACK";
            x = getXforCentered(backText);
            y += gp.tileSize * 2;
            g2.drawString(backText, x, y);
    
            g2.drawString(">", x - gp.tileSize, y);
    
}
     public void drawSettingState(Graphics2D g2) {
        if(gp.gameState == gp.gameMenuState) {
            int menuWidth = 300;
            int menuHeight = 250;
            int detailsWidth = 500;
            int detailsHeight = 50;

            int menuX = (gp.screenWidth - menuWidth) / 2;
            int menuY = (gp.screenHeight - menuHeight) / 2;
            int detailsX = (gp.screenHeight - detailsWidth) * 3;
            int detailsY = gp.tileSize * 10;

            // Draw the menu window using drawSubWindow instead of fillRect
            drawSubWindow(menuX, menuY, menuWidth, menuHeight);

            g2.setColor(Color.WHITE);
            g2.setFont(arial_30);

            String[] options = {"RESUME", "MUSIC", "SE", "BACK", "EXIT"};

            int optionHeight = 40;
            int currentY = menuY + 40;
            int rectX;
            int rectY;
            int volumeWidth;
            int textX;

            for (int i = 0; i < options.length; i++) {
                String option = options[i];
                int textWidth = g2.getFontMetrics().stringWidth(option);
                textX = menuX + 20;
                g2.drawString(option, textX, currentY);

                if(option.equals("MUSIC")) {
                    rectX = textX + gp.tileSize * 3;
                    rectY = currentY - 24;
                    g2.drawRect(rectX, rectY, 120, 24);
                    volumeWidth = 24 * gp.music.volumeScale;
                    g2.fillRect(rectX, rectY, volumeWidth, 24);
                }
                if(option.equals("SE")) {
                    rectX = textX + gp.tileSize * 3;
                    rectY = currentY - 24;
                    g2.drawRect(rectX, rectY, 120, 24);
                    volumeWidth = 24 * gp.se.volumeScale;
                    g2.fillRect(rectX, rectY, volumeWidth, 24);
                }

                if (commandNum == i) {
                    int arrowX = menuX - 30;
                    g2.drawString(">", arrowX, currentY);
                }
                currentY += optionHeight;
            }

            // Draw the details window using drawSubWindow instead of fillRect
            drawSubWindow(detailsX, detailsY, detailsWidth, detailsHeight);

            //player Name
            g2.setColor(Color.WHITE);
            g2.setFont(arial_40);
            textX = detailsX + 20;
            currentY = detailsY + 40;
            g2.drawString(gp.playerName, textX, currentY);

            //Coins Player have
            g2.setColor(Color.WHITE);
            g2.setFont(arial_30);
            String text = ("Coins: " + gp.player.hasCoin);
            int textCoinX = textX + gp.tileSize * 6;
            int textCoinY = currentY - 3;
            g2.drawString(text, textCoinX, textCoinY);
        }
   }
     
     public void drawGameLevels(Graphics2D g2){
        String text;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String title = "Levels";

        // Draw the GameLevel titles
        int x = getXforCentered(title);
        int y = gp.tileSize * 2;
        g2.setColor(Color.white);
        g2.drawString(title, x, y);
        
        //LEVELS
        g2.setFont(arial_50B);
        g2.setColor(Color.white);
        
        text = "1";
        x = gp.tileSize * 3;
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + gp.tileSize, y);
        }
        
        text = "2";
        x = getXforCentered(text);
        g2.drawString(text, x, y);
         if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + gp.tileSize, y);
        }
         
        text = "3";
        x =  gp.tileSize * 16;
        g2.drawString(text, x, y);
         if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + gp.tileSize, y);
        }
         
        text = "4";
        x = gp.tileSize * 3;
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + gp.tileSize, y);
        }
        
        text = "5";
        x = getXforCentered(text);
        g2.drawString(text, x, y);
         if (commandNum == 4) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + gp.tileSize, y);
        }
         
        text = "6";
        x =  gp.tileSize * 16;
        g2.drawString(text, x, y);
         if (commandNum == 5) {
            g2.drawString(">", x - gp.tileSize, y);
            g2.drawString("<", x + gp.tileSize, y);



        }
     
     }
     public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));

        for(int i  = 0; i < scrollMessage.size(); i++){

            if (scrollMessage.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(scrollMessage.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(scrollMessage.get(i), messageX, messageY);

                int counter = scrollMessageCounter.get(i) + 1; //message ++
                scrollMessageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if(scrollMessageCounter.get(i) > 180){
                    scrollMessage.remove(i);
                    scrollMessageCounter.remove(i);
                }
            }
        }

     }
     public void drawInventory(){
        //FRAME
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
         final int slotXstart = frameX + 20;
         final int slotYstart = frameY + 20;
         int slotX = slotXstart;
         int slotY = slotYstart;
         int slotSize = gp.tileSize;

         //DRAW PLAYER'S ITEMS
         for (int i = 0; i < gp.player.inventory.size(); i++){

             //EQUIP CURSOR
             if (gp.player.inventory.get(i).equals(gp.player.currentWeapon) ||
                     gp.player.inventory.get(i).equals(gp.player.currentShield) || gp.player.inventory.get(i).equals(gp.player.currentProjectile)){
                 //System.out.println("DEBBBBBUG");
                 g2.setColor(new Color(240, 190, 90));
                 g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);

             }

             g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

             slotX += gp.tileSize;

             if (i == 4 || i == 9 || i == 14){
                 slotX = slotXstart;
                 slotY += slotSize;

             }
         }

         //CURSOR
         int cursorX = slotXstart + (slotSize * slotCol);
         int cursorY = slotYstart + (slotSize * slotRow);
         int cursorWidth = gp.tileSize;
         int cursorHeight = gp.tileSize;

         //DRAW CURSOR
         g2.setColor(Color.white);
         g2.setStroke(new BasicStroke(3));
         g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

         //DESCRIPTION FRAME
         int dFrameX = frameX;
         int dFrameY = frameY + frameHeight;
         int dFrameWidth = frameWidth;
         int dFrameHeight = gp.tileSize * 3;
         // drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

         //DRAW DESCRIPTION TEXT
         int textX = dFrameX + 20;
         int textY = dFrameY + gp.tileSize;
         g2.setFont(g2.getFont().deriveFont(20F));

         int itemIndex = getItemIndexOnSlot();

         if(itemIndex < gp.player.inventory.size()){

             drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

             for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                 g2.drawString(line, textX, textY);
                 textY += 32;
             }


         }




     }
     public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow * 5);
        return  itemIndex;
     }
    
    
    
    public int getXforCentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    
    }
    public int getXAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
