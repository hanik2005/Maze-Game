/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import main.KeyHandler;
import main.UI;
import object.*;


public class Player extends Entity{
    KeyHandler keyH;
    UI ui;

    public final int screenX; // purpose where we draw the character on the screen so our plan is to put in the center of the screen
    // it means screen position of the player doesnt change so we use final int
    public final int screenY;
    public int hasCoin = 0;
    int secondsCounter = 0;
    boolean secondsBol = false;
    int updateRunner = 0;
    public boolean wallWalk = false; // DEBUGGING PURPOSES
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20; // THE SIZE OF THE INVENTORY
    public boolean projectileExist = false;
    public boolean ammoExisted = false;



    public Player(GamePanel gp, KeyHandler keyH, UI ui){
        super(gp);
        this.keyH = keyH;
        this.ui = ui;



        //COLLISIONS
        solidArea = new Rectangle(); // this is the position of the rectangle of the player to detect collision
        solidArea.x = 13; // 8
        solidArea.y = 15; //8
        solidArea.width = 23; //28
        solidArea.height = 20;//32
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // (gp.tileSize / 2) it means it totaly fixed player at the center
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //ATTACK AREA COLLISION
        //attackArea.width = 36;
        //attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues() {
        // position of the player
        PlayerPositionMap();


        //PLAYER STATUS

        maxLife = 6;
        life = maxLife;
        level = 1;
        strength = 1; // THE MORE STRENGTH THE PLAYER HAS THE MORE DAMAGE THE PLAYER HAS
        dexterity = 1; // THE MORE DEXTERITY THE PLAYER HAS THE LESS DAMAGE HE RECIEVES
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        //Mana
        maxMana = 4; //temporary
        mana = maxMana;
        //Bullets
        maxBullet = 4;
        bullet = maxBullet;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentProjectile = new OBJ_Rock(gp);




        //projectile = new OBJ_Fireball(gp);
        attack = getAttack(); // THE TOTAL ATTACK VALUE IS DECIDED BY STRENGTH THIS MEANS IT UPDATES ONLY IN ATTACKVALUE
        defense = getDefense();  // THE TOTAL DEXTERITY VALUE IS DECIDED BY DEFENCE THIS MEANS IT UPDATES ONLY IN DEFENCEVALUE
        projectileDamage = getProjectileAttack();

        projectileExist = true;
        projectile = new OBJ_RockThrow(gp);
        type = type_rock;
    }
    public void setItems(){

        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(currentProjectile);
        inventory.add(new OBJ_Coin(gp));
        inventory.add(new OBJ_Healing_Potion(gp));
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getProjectileAttack() {
        if (currentProjectile != null) {
            return strength * currentProjectile.projectileAttackValue;
        }
        return 0; // Default value if no projectile equipped
    }

    public int getDefense(){return defense = dexterity * currentShield.defenseValue;}


    public void PlayerPositionMap(){
        if(gp.LevelState == gp.Levels_1){
            worldX = gp.tileSize * 49;
            worldY = gp.tileSize * 92;
            speed = 4;
            direction = "idle";

        }if(gp.LevelState == gp.Levels_2){

            worldX = gp.tileSize * 52;
            worldY = gp.tileSize * 47;
            speed = 4;
            direction = "idle";
        }if(gp.LevelState == gp.Levels_3){

            worldX = gp.tileSize * 49;
            worldY = gp.tileSize * 92;
            speed = 4;
            direction = "idle";
        }if(gp.LevelState == gp.Levels_4){

            worldX = gp.tileSize * 49;
            worldY = gp.tileSize * 92;
            speed = 4;
            direction = "idle";
        }if(gp.LevelState == gp.Levels_5){

            worldX = gp.tileSize * 49;
            worldY = gp.tileSize * 92;
            speed = 4;
            direction = "idle";
        }if(gp.LevelState == gp.Levels_6){

            worldX = gp.tileSize * 49;
            worldY = gp.tileSize * 92;
            speed = 4;
            direction = "idle";

        }if(gp.LevelState == gp.tutorial_level){
            worldX = gp.tileSize * 49;
            worldY = gp.tileSize * 92;
            speed = 4;
            direction = "idle";

        }
    }

    public void getPlayerImage() {

        idle = setup("/Assets/player/Main_front_1", gp.tileSize, gp.tileSize);
        up1 = setup("/Assets/player/Main_up_new_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Assets/player/Main_up_new_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Assets/player/Main_front_new_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Assets/player/Main_front_new_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Assets/player/Main_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Assets/player/Main_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Assets/player/Main_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Assets/player/Main_right_2", gp.tileSize, gp.tileSize);





    }
    public void getPlayerAttackImage(){
        System.out.println("Loading attack images for weapon type: " + currentWeapon.type);
            if (currentWeapon.type == type_sword) {
                attackUp1 = setup("/Assets/player/attack/player_up_attack_1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/Assets/player/attack/player_up_attack_2", gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/Assets/player/attack/player_front_attack_1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/Assets/player/attack/player_front_attack_2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/Assets/player/attack/player_left_attack_1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/Assets/player/attack/player_left_attack_2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/Assets/player/attack/player_right_attack_1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/Assets/player/attack/player_right_attack_2", gp.tileSize * 2, gp.tileSize);
            }
            if (currentWeapon.type == type_katana) {
                attackUp1 = setup("/Assets/player/attack/player_up_attack_1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/Assets/player/attack/player_up_attack_2", gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/Assets/player/katana_attack/player_front_attack_1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/Assets/player/katana_attack/player_front_attack_3", gp.tileSize, gp.tileSize * 2);
                attackDown3 = setup("/Assets/player/katana_attack/player_front_attack_2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/Assets/player/attack/player_left_attack_1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/Assets/player/attack/player_left_attack_2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/Assets/player/attack/player_right_attack_1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/Assets/player/attack/player_right_attack_2", gp.tileSize * 2, gp.tileSize);
            }
            if (currentWeapon.type == type_pickaxe) {
                attackUp1 = setup("/Assets/player/pickaxe_animation/player_up_pick_1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/Assets/player/pickaxe_animation/player_up_pick_2", gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/Assets/player/pickaxe_animation/player_front_pick_1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/Assets/player/pickaxe_animation/player_front_pick_2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/Assets/player/pickaxe_animation/player_left_pick_1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/Assets/player/pickaxe_animation/player_left_pick_2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/Assets/player/pickaxe_animation/player_right_pick_1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/Assets/player/pickaxe_animation/player_right_pick_2", gp.tileSize * 2, gp.tileSize);
            }

            if(projectileExist && currentProjectile.type == type_rock){
                System.out.println("animation");
                shootUp1 = setup("/Assets/player/rockThrow_animation/player_up_attack_1", gp.tileSize, gp.tileSize * 2);
                shootUp2 = setup("/Assets/player/rockThrow_animation/player_up_attack_2", gp.tileSize, gp.tileSize * 2);
                shootDown1 = setup("/Assets/player/rockThrow_animation/player_front_attack_1", gp.tileSize, gp.tileSize * 2);
                shootDown2 = setup("/Assets/player/rockThrow_animation/player_front_attack_2", gp.tileSize, gp.tileSize * 2);
                shootLeft1 = setup("/Assets/player/rockThrow_animation/player_left_attack_1", gp.tileSize * 2, gp.tileSize);
                shootLeft2 = setup("/Assets/player/rockThrow_animation/player_left_attack_2", gp.tileSize * 2, gp.tileSize);
                shootRight1 = setup("/Assets/player/rockThrow_animation/player_right_attack_1", gp.tileSize * 2, gp.tileSize);
                shootRight2 = setup("/Assets/player/rockThrow_animation/player_right_attack_2", gp.tileSize * 2, gp.tileSize);
            }

            if(projectileExist && currentProjectile.type == type_gun){
                System.out.println("animation");
                shootUp1 = setup("/Assets/player/pistol_animation/player_up_attack_1", gp.tileSize, gp.tileSize * 2);
                shootUp2 = setup("/Assets/player/pistol_animation/player_up_attack_2", gp.tileSize, gp.tileSize * 2);
                shootDown1 = setup("/Assets/player/pistol_animation/player_front_attack_1", gp.tileSize, gp.tileSize * 2);
                shootDown2 = setup("/Assets/player/pistol_animation/player_front_attack_2", gp.tileSize, gp.tileSize * 2);
                shootLeft1 = setup("/Assets/player/pistol_animation/player_left_attack_1", gp.tileSize * 2, gp.tileSize);
                shootLeft2 = setup("/Assets/player/pistol_animation/player_left_attack_2", gp.tileSize * 2, gp.tileSize);
                shootRight1 = setup("/Assets/player/pistol_animation/player_right_attack_1", gp.tileSize * 2, gp.tileSize);
                shootRight2 = setup("/Assets/player/pistol_animation/player_right_attack_2", gp.tileSize * 2, gp.tileSize);
            }
    }
    public void update(){

        //THIS EXPERIMENTAL PURPOSES THE CONDITION LIFE

        //WHEN PLAYER HAS NO HEALTH
        if(life == 0){
            resGameOver();

        }

        if(attacking == true){
            attacking();

        }
        else if(shooting == true) {
            shooting();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true
                || keyH.enterPressed == true ){
            if(keyH.upPressed == true){
                direction = "up";

            }
            else if(keyH.downPressed == true){
                direction = "down";

            }
            else if(keyH.leftPressed == true){
                direction = "left";

            }
            else if(keyH.rightPressed == true){
                direction = "right";

            }




            if(!wallWalk){ // DEBUGING PURPOSES

                //CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkTile(this);
            }
            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickupObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNpc(npcIndex);

            //CHECK ENEMY COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            //CHECK EVENT
            //System.out.println("dmaksmdsak");
            gp.eHandler.checkEvent();






            //IF COLLISION IS FALSE, PLAYER CAN MOVE IF IT IS TRUE ALL THE COLLISION TILES WILL BE TRUE WHICH MEANS THE PLAYER CANNOT MOVE
            //FUN TIP IN JAVA THE UPDOOR RIGHT LEFT CORNER IS X:0 Y:0 THE X VALUES INCREASE ON THE RIGHT AND Y VALUES INCREASE AS THEY GO DOWN
            //PLAYER SPEED MEANS YOU CAN GO 4 PIXELS OR MINUS 4 PIXELS
            // THIS IF STATEMENT CHECKING ONLY THE PLAYER CAN MOVE IF THE TILES IS FALSE
            if(collisionOn == false && keyH.enterPressed == false || wallWalk ){
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }


            if(keyH.enterPressed == true && attackCanceled == false && isMeleeWeapon()){
                gp.PlaySE(11);
                attacking = true;
                shooting = false;
                spriteCounter = 0;

            }


            attackCanceled = false;

            //CHECK ALL TRUE ENTER PRESSED AND TURN IT FALSE WHEN PLAYER UPDATES
            gp.keyH.enterPressed = false;


            //SPRITES ANIMATION UPDATES

            spriteCounter++;
            if(spriteCounter > 12){
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                //NEW NOT TRY YET NEW MOVEMENT
//                 else if (spriteNum == 3){
//                     spriteNum = 2;
//                 }
                spriteCounter = 0;

            }
        }

        //Debuggigng
//        System.out.println("Shoot key pressed: " + gp.keyH.shootKeyPressed);
//        System.out.println("Projectile alive: " + projectile.alive);
//        System.out.println("Shot counter: " + shotAvailableCounter);
//        System.out.println("Has resource: " + projectile.haveResource(this));
//        System.out.println("Projectile exists: " + projectileExist);

        //CHECK IF KEY PRESSED F AND THE PROJECTILE IS STILL ALIVE
        if(gp.keyH.shootKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
        && projectile.haveResource(this) == true && projectileExist == true){
            if(currentProjectile != null) {
                shooting = true;  // Add this line
                spriteCounter = 0;

                //SET DEFAULT COORDINATES, DIRECTION AND USER
                projectile.set(worldX, worldY, direction, true, this);

                //SUBTRACT THE COST OF THE PROJECTILE
                projectile.subtractResource(this);

                //ADD IT TO THE LIST
                gp.projectileList.add(projectile);

                shotAvailableCounter = 0;

                gp.PlaySE(13);
            }

        }


        //ONLY YOU WANT IDLE ANIMATION WHEN THE PLAYER STOPS
//         else{
//             direction = "idle";
//             spriteNum = 1;
//
//         }
        // PLAYER BECOME INVINSIBLE IN FEW SECONDS
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;

            }

        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }

        //TO NOT OVERLOAD PLAYER LIFE, MANA AND BULLET
        if (life > maxLife){
            life = maxLife;
        }
        if (mana > maxMana){
            mana = maxMana;
        }
        if (bullet > maxBullet){
            bullet = maxBullet;
        }


        //UPDATE METHOD EXAMPLE
        // System.out.println(updateRunner++);



        //CHECKING THE eMangerActive
        if (secondsBol) { // check if it is true
            secondsCounter--; //decrement seconds counter which is nanaseconds faster
            if (secondsCounter <= 0) { // check if seconds counter less than zero
                gp.eManagerActive = true; // then it will be true which back to lighthing
                secondsBol = false; // reset and back outside
                System.out.println("Check 3: " + secondsBol); // DEBUG
                //secondsCounter = 0; //OLD algorithm the opposite
            }
        }
    }

    public void shooting() {
        spriteCounter++;

        // Animation frames
        if(spriteCounter <= 5) {
            spriteNum = 1;
        }
        else if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Fire projectile on the appropriate frame
            if(spriteCounter == 10 && !projectile.alive && projectile.haveResource(this)) {
                projectile.attack = projectileDamage;
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                projectile.subtractResource(this);
                gp.PlaySE(13);
            }
        }
        else if(spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            shooting = false;
        }
    }


    public void attacking(){

        spriteCounter ++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;


            //SAVE THE CURRENT WORLD X, WORLDY, SOLID AREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //ADJUST PLAYERS WORLD X/Y FOR THE ATTACK AREA

            switch(direction){
                case "up":
                    worldY-= attackArea.height;
                    break;
                case "down":
                    worldY+= attackArea.height;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;


            }
            //ATTACK AREA BECOMES SOLID AREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //CHECK MONSTER COLLISION WITH THE UPDATED WOORLD X AND Y, SOLID AREA
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            //AFTER CHECKING THE ENTITY COLLISION WE RESET THE CURRENT X AND Y AND
            //THE SOLID AREA WIDTH AND HEIGHT
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void contactMonster(int i){
        if(i != 999){

            if(invincible == false && gp.monster[i].dying == false){
                gp.PlaySE(10);
                int damage = gp.monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }

                life -= damage;
                invincible = true;

            }

        }

    }
    public void damageMonster(int i, int attack){

        if(i != 999){
            if(gp.monster[i].invincible == false){

                gp.PlaySE(9);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }


                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " " + "damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("killed the" + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + " " + gp.monster[i].exp + "!");
                    exp += gp.monster[i].exp;
                    checkLevelUp();

                }

            }

        }
    }
    public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[i].destructible == true &&
                gp.iTile[i].isCorrectItem(this) == true && gp.iTile[i].invincible == false){

            gp.iTile[i].PlaySE();
            gp.iTile[i].life --;
            gp.iTile[i].invincible = true;

            //GENERATE PARTICLE
            generateParticle(gp.iTile[i], gp.iTile[i]);

            if (gp.iTile[i].life == 0){
                gp.iTile[i] = gp.iTile[i].getDestroyedForm();
            }

        }
    }
    public void checkLevelUp(){
        if (exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength ++;
            dexterity ++;
            attack = getAttack();
            defense = getDefense();

            gp.PlaySE(3);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You've level up to level" + level;
        }
    }
    public void interactNpc(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }


    }
    public void pickupObject(int i){
        if (i != 999) {
            //PICK UP ONLY ITEMS
            if (gp.obj[i].type == type_pickUpOnly) {

                gp.obj[i].use(this);
                gp.obj[i] = null;

            }

            //INVENTORY ITEMS
            else {
                String text;
                if (inventory.size() != maxInventorySize) {

                    inventory.add(gp.obj[i]);
                    gp.PlaySE(1);
                    text = "Got a " + gp.obj[i].name + "!";
                } else {
                    text = "You cannot carry any more";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;

            }
        }

    }
    public void randomizeEffect() {
        Random rand = new Random();
        int randomEffect = rand.nextInt(5);

        switch (randomEffect) {
            case 0:
                if(speed > 1){
                    speed -= 1;
                    gp.ui.showMessage("Speed Decreased!");
                    break;
                }
                else{
                    resGameOver();
                }
            case 1:
                speed += 1;
                gp.ui.showMessage("Speed Increased!");
                break;
            case 2:
                hasCoin++;
                gp.ui.showMessage("Coin Gained!");
                break;
            case 3:
                if (hasCoin > 0) {
                    hasCoin--;
                    gp.ui.showMessage("Coin Lost!");
                } else {
                    gp.ui.showMessage("No Coins to Lose!");
                }
                break;
            case 4:
                gp.ui.showMessage("SIGHT!");
                gp.eManagerActive = false;
                secondsBol = true;
                secondsCounter = 120;
                break;
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword ||
                    selectedItem.type == type_katana || selectedItem.type == type_pickaxe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                System.out.println("DEbug");
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_rock ||
                    selectedItem.type == type_gun){

                currentProjectile = selectedItem;
                projectileDamage = getProjectileAttack();
                projectileExist = true;  // Ranged weapon selected


                if (selectedItem.type == type_rock) {
                    projectile = new OBJ_RockThrow(gp);
                    ammoExisted = false;
                }
                if (selectedItem.type == type_gun) {
                    projectile = new OBJ_Bullet(gp);
                    ammoExisted = true;
                }
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_consumable){

                selectedItem.use(this);
                inventory.remove(itemIndex);

            }
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;


        if(direction.equals("idle")){
            image = idle;
        }
        else{
            if(attacking) {
                // Melee attack animations
                switch(direction) {
                    case "up":
                        tempScreenY = screenY - gp.tileSize;
                        if(spriteNum == 1) image = attackUp1;
                        if(spriteNum == 2) image = attackUp2;
                        break;
                    case "down":
                        if(spriteNum == 1) image = attackDown1;
                        if(spriteNum == 2) image = attackDown2;
                        break;
                    case "left":
                        tempScreenX = screenX - gp.tileSize;
                        if(spriteNum == 1) image = attackLeft1;
                        if(spriteNum == 2) image = attackLeft2;
                        break;
                    case "right":
                        if(spriteNum == 1) image = attackRight1;
                        if(spriteNum == 2) image = attackRight2;
                        break;
                }
            }
            else if(shooting) {
                // Ranged attack animations
                switch(direction) {
                    case "up":
                        tempScreenY = screenY - gp.tileSize;
                        if(spriteNum == 1) image = shootUp1;
                        if(spriteNum == 2) image = shootUp2;
                        break;
                    case "down":
                        if(spriteNum == 1) image = shootDown1;
                        if(spriteNum == 2) image = shootDown2;
                        break;
                    case "left":
                        tempScreenX = screenX - gp.tileSize;
                        if(spriteNum == 1) image = shootLeft1;
                        if(spriteNum == 2) image = shootLeft2;
                        break;
                    case "right":
                        if(spriteNum == 1) image = shootRight1;
                        if(spriteNum == 2) image = shootRight2;
                        break;
                }
            }
            else {
                // Normal movement animations
                switch(direction) {
                    case "up":
                        if(spriteNum == 1) image = up1;
                        if(spriteNum == 2) image = up2;
                        break;
                    case "down":
                        if(spriteNum == 1) image = down1;
                        if(spriteNum == 2) image = down2;
                        break;
                    case "left":
                        if(spriteNum == 1) image = left1;
                        if(spriteNum == 2) image = left2;
                        break;
                    case "right":
                        if(spriteNum == 1) image = right1;
                        if(spriteNum == 2) image = right2;
                        break;
                }
            }


        }
        //OPACITY WHEN PLAYER IS DAMAGE

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //RESET OPACITY
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG
        //    g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //    g2.setColor(Color.black);
        //    g2.drawString("InvincibleCounter:" + invincibleCounter, 10, 400);

        //See the colision area of the player
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


    }
    public boolean isMeleeWeapon() {
        return (currentWeapon != null &&
                (currentWeapon.type == type_sword ||
                        currentWeapon.type == type_katana ||
                        currentWeapon.type == type_pickaxe));
    }
    public void resGameOver(){
        gp.gameState = gp.gameOverState;
        gp.stopMusic();
        gp.PlaySE(5);
        gp.ui.playTime = 180;
    }
}