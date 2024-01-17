package org.game.dialogue;

import org.game.control.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UIScreen {

    // FIELD
    GamePanel gp;
    Font maruMonica;
    int counter = 0;
    int checker = 1;

    public int choose = 1;

    // CONSTRUCTOR
    public UIScreen(GamePanel gp) {

        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    // METHOD
    public void drawGameScreen(Graphics2D g2D) {

        g2D.setFont(maruMonica.deriveFont(Font.BOLD, 85f));
        g2D.setColor(new Color(3, 3, 3, 200));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        String text = "Blue Boy Advanture";
        int x = gp.screenWidth/2 - getTextLength(g2D, text);
        int y = gp.tileSize * 2 + 24;

        g2D.setColor(Color.GRAY);
        g2D.drawString(text, x + 3, y - 4);

        g2D.setColor(Color.WHITE);
        g2D.drawString(text, x, y);

        x = gp.screenWidth/2 - gp.tileSize + 12;
        y = gp.screenHeight/2 - gp.tileSize * 2;

        BufferedImage image = null;
        if (checker == 1) image = gp.player.right1;
        else if (checker ==2) image = gp.player.right11;
        else if (checker == 3) image = gp.player.right2;

        counter++;
        if (counter > 7) {
            counter = 0;
            if (checker < 3) checker++;
            else if (checker == 3) checker = 1;
        }

        g2D.drawImage(image, x, y, gp.tileSize*2 - 24, gp.tileSize*2 - 24, null);
        g2D.drawLine(x - gp.tileSize*3, y + gp.tileSize*2 - 22, x + gp.tileSize*4 + 24, y + gp.tileSize*2 - 22);
        // Draw menu screen
        drawMenuScreen(g2D);
    }

    public void drawMenuScreen(Graphics2D g2D) {

        g2D.setFont(maruMonica.deriveFont(Font.BOLD, 45f));
        String text;
        int x, y;

        text = "PLAY GAME";
        x = gp.screenWidth/2 - getTextLength(g2D, text);
        y = gp.screenHeight/2 + gp.tileSize + 12;

        g2D.drawString(text, x, y);

        text = "LOAD GAME";
        x = gp.screenWidth/2 - getTextLength(g2D, text);
        y = y + gp.tileSize;

        g2D.drawString(text, x, y);

        text = "QUIT";
        x = gp.screenWidth/2 - getTextLength(g2D, text);
        y = y + gp.tileSize;

        g2D.drawString(text, x, y);

        drawChooseScreen(g2D, choose, gp.screenHeight/2 + gp.tileSize + 12);
    }

    public void drawChooseScreen(Graphics2D g2D, int choose, int y) {

        g2D.setFont(maruMonica.deriveFont(Font.BOLD, 55f));

        int x;

        if (choose == 1) {
            x = gp.screenWidth/2 - getTextLength(g2D, "PLAY GAME") - 25;
            g2D.drawString(">", x, y);
        }
        else if (choose == 2) {
            x = gp.screenWidth/2 - getTextLength(g2D, "LOAD GAME") - 25;
            y += gp.tileSize;
            g2D.drawString(">", x, y);
        }
        else if (choose == 3) {
            x = gp.screenWidth/2 - getTextLength(g2D, "QUIT") - 25;
            y += gp.tileSize*2;
            g2D.drawString(">", x, y);
        }
    }

    public void drawLoadMapChoose(Graphics2D g2D) {

        g2D.setFont(maruMonica.deriveFont(Font.BOLD, 45f));
        g2D.setColor(Color.WHITE);

        String text = "SELECT A LEVEL";
        int x = gp.screenWidth/2 - getTextLength(g2D, text);
        int y = gp.screenHeight/2 - gp.tileSize*2 - 12;

        g2D.drawString(text, x, y);
        g2D.drawLine(gp.screenWidth/2 - gp.tileSize*3, gp.screenHeight/2 - gp.tileSize*2,
                gp.screenWidth/2 + gp.tileSize*3, gp.screenHeight/2 - gp.tileSize*2);

        text = "EASY LEVEL";
        x = gp.screenWidth/2 - getTextLength(g2D, text);
        y = y + gp.tileSize + gp.tileSize/2;

        g2D.drawString(text, x, y);

        text = "HARD LEVEL";
        x = gp.screenWidth/2 - getTextLength(g2D, text);
        y = y + gp.tileSize + gp.tileSize/6;

        g2D.drawString(text, x, y);
    }

    public int getTextLength(Graphics2D g2D, String text) {

        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();

        return length/2;
    }
}
