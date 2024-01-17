package org.game.object;

import org.game.control.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    // FIELD
    public String name;
    public BufferedImage image;

    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    // METHOD
    public void draw(Graphics2D g2D, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
