package org.game.entity;

import org.game.control.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    // FIELD
    GamePanel gp;

    // FIELD: ENTITY SETTINGS
    public int worldX, worldY; // Character position
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction; // direction = phương hướng

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean collisionOn = false;

    public int drawCounter = 0;
    boolean drawChecker = true;
    int actionLookCounter = 0;

    // CONSTRUCTOR
    public Entity(GamePanel gp) {

        this.gp = gp;
    }

    // METHOD: SETACTION FOR THE ENTITY (NPC OR MONSTER) = Hành động
    public void setAction() {

        // Overrride by separate entities
    }

    // METHOD: UPDATE FOR NPC OR MONSTER
    public void update() {
        setAction();

        collisionOn = false;
        gp.coChecker.checkTile(this);
        gp.coChecker.checkPlayer(this);
        int k = gp.coChecker.checkObject(this, false);

        if (!collisionOn) {
            switch (direction) {
                case "Up": worldY -= speed; break;
                case "Down": worldY += speed; break;
                case "Left": worldX -= speed; break;
                case "Right": worldX += speed; break;
            }
        }

        actionLookCounter++;

        if (actionLookCounter > 10) {
            actionLookCounter = 0;
            drawChecker = !drawChecker;
        }
    }

    // METHOD: DRAW NPC OR MONSTER ENTITY
    public void draw(Graphics2D g2D) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null;

        if (direction.equals("Up")) {
            if (drawChecker) image = up1;
            else image = up2;
        }
        else if (direction.equals("Down")) {
            if (drawChecker) image = down1;
            else image = down2;
        }
        else if (direction.equals("Left")) {
            if (drawChecker) image = left1;
            else image = left2;
        }
        else if (direction.equals("Right")) {
            if (drawChecker) image = right1;
            else image = right2;
        }

        g2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
