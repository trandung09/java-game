package org.game.entity;

import org.game.control.GamePanel;
import org.game.control.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    // FIELD
    public BufferedImage left11, right11;

    // FIELD
    int drawCounter = 0;
    int drawChecker = 1;
    int leftRightChecker = 1;
    int leftRightCounter = 0;

    public int hasKey = 0;

    // FIELD: CAMERA SETTINGS
    public final int screenX;
    public final int screenY;

    public KeyHandler keyH;

    // CONSTRUCTOR
    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        setUpForPlayer();
        getPlayerImage();
    }

    public void setUpForPlayer() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        speed = 6;

        direction = "Down";

        solidArea = new Rectangle(12, 8, 24, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_down_2.png"));
            left11 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_left_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_right_2.png"));
            right11 = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_right_1.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "Up";
            }
            else if (keyH.downPressed) {
                direction = "Down";
            }
            else if (keyH.leftPressed) {
                direction = "Left";
            }
            else if (keyH.rightPressed) {
                direction = "Right";
            }

            // Check collison
            collisionOn = false;
            gp.coChecker.checkTile(this);

            // Check and pick ocject
            int index = gp.coChecker.checkObject(this, true);
            pickObject(index);

            // Check collison with other entity
            int k = gp.coChecker.checkEntity(this, gp.npc);

            if (!collisionOn) {
                switch (direction) {
                    case "Up": worldY -= speed; break;
                    case "Down": worldY += speed; break;
                    case "Left": worldX -= speed; break;
                    case "Right": worldX += speed; break;
                }
            }

            drawCounter++;
            leftRightCounter++;

            if (drawCounter > 12) {
                if (drawChecker == 1) drawChecker = 2;
                else drawChecker = 1;
                drawCounter = 0;
            }

            if (leftRightCounter > 7) {
                leftRightCounter = 0;
                if (leftRightChecker < 3) leftRightChecker++;
                else if (leftRightChecker == 3) leftRightChecker = 1;
            }
        }
    }

    public void draw(Graphics2D g2D) {

        BufferedImage image = null;

        if (direction.equals("Up")) {
            if (drawChecker == 1) image = up1;
            else if (drawChecker == 2) image = up2;
        }
        else if (direction.equals("Down")) {
            if (drawChecker == 1) image = down1;
            else if (drawChecker == 2) image = down2;
        }
        else if (direction.equals("Left")) {
            if (leftRightChecker == 1) image = left1;
            else if (leftRightChecker == 2) image = left11;
            else image = left2;
        }
        else if (direction.equals("Right")) {
            if (leftRightChecker == 1) image = right1;
            else if (leftRightChecker == 2) image = right11;
            else image = right2;
        }

        g2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    // METHOD: PICK OBJECT
    public void pickObject(int index) {

        if (index != 999) {
            int i = index;
            String objName = gp.obj[i].name;
            gp.obj[i] = null;

            switch (objName) {
                case "Key":
                    hasKey++;
                    gp.playSE(1);
                    gp.uInterface.scanMessage("You got a key!");
                    gp.obj[i] = null;
                    break;

                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.playSE(3);
                        gp.uInterface.scanMessage("You opened the door!");
                    }
                    break;

                case "Boot":
                    gp.playSE(2);
                    speed += 4;
                    gp.uInterface.scanMessage("Speed up!");
                    gp.obj[i] = null;
                    break;

                case "Chest":
                    gp.playSE(4);
                    gp.stopMusic();
                    gp.gameState = gp.gameNext;
                    gp.obj[i] = null;
                    break;
            }
        }
    }
}
