/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static java.awt.SystemColor.text;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Coin;

/**
 *
 * @author kring
 */
public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, arial_40B, arial_50B, arial_30;
    BufferedImage coinImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;
   
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        arial_40B = new Font("Arial", Font.BOLD, 40);
        arial_50B = new Font("Arial", Font.BOLD, 50);
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        OBJ_Coin coin = new OBJ_Coin(gp);
        coinImage = coin.image;
        playTime = 180; // 10 minutes or 5 mins or 3 mins?
    
    }
    public void showMessage(String text){
        
        
        message = text;
        messageOn = true;
        
        
        
        
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        if(gp.gameState == gp.gameFinished){
            
            
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            
           
            
            String text;
            int textLength;
            int x;
            int y;
            double yDouble = 1.5;
            int minutes = (int) (playTime / 60);
            int seconds = (int) (playTime % 60);
            
            
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
        else{
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(coinImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x = " + gp.player.hasCoin, 74, 65);
        
        //OLD WAY TIME INCREMENT
        
//        if (gp.gameState == gp.playState && !gameFinished) {
//            g2.setFont(arial_40);
//            g2.setColor(Color.black);
//            playTime += (double)1/60;
//            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 15, 65);
//        }

        // NEW WAY TIME DECREMENT WITH MINUTES AND SECONDS
        
        if (gp.gameState == gp.playState && !gameFinished) {
            g2.setFont(arial_40B);
            g2.setColor(Color.white);
    
            // Countdown timer logic
            if (playTime > 0) {
                playTime -= (double)1 / 60;
            }else {
            // Change game state to gameOverState when time is up
                gp.gameState = gp.gameOverState;
                gp.stopMusic();
                gp.PlaySE(5);
                playTime = 180;
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
        
        if(gp.gameState == gp.playState){
           // None so Far
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
        
        }
     
        
        
    }
    public void drawPauseScreen(){
        
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCentered(text);
        
        
        
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
        
    
    
    }
    public void drawTitleScreen(Graphics2D g2) {
        
    g2.setColor(Color.black);
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);     
        
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
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

    text = "NEW GAME";
    x = getXforCentered(text);
    y += gp.tileSize * 3;
    g2.drawString(text, x, y);
    if (commandNum == 0) {
        g2.drawString(">", x - gp.tileSize, y);
    }
    
    text = "HOW TO PLAY";
    x = getXforCentered(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
    if (commandNum == 1) {
        g2.drawString(">", x - gp.tileSize, y);
    }
    
    text = "LEADERBOARD";
    x = getXforCentered(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
    if (commandNum == 2) {
        g2.drawString(">", x - gp.tileSize, y);
    }

    text = "QUIT";
    x = getXforCentered(text);
    y += gp.tileSize;
    g2.drawString(text, x, y);
    if (commandNum == 3) {
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

    // Optionally, you can add a cursor to indicate where the user is typing
    if ((System.currentTimeMillis() / 500) % 2 == 0) { // Blink effect for cursor
        g2.drawString("|", x + g2.getFontMetrics().stringWidth(text), y); // Add cursor after the text
    }

    // Draw the "Back" option
     if (commandNum == 0) {
        
    }
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
    
    
    
    public int getXforCentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    
    }
}
