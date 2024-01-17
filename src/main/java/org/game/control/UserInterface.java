package org.game.control;

import org.game.object.Key_OBJ;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UserInterface {

    // FIELD
    GamePanel gp;

    Font arialB40 = new Font("Arial", Font.BOLD, 45);
    Font arialB35 = new Font("Arial", Font.BOLD, 30);
    Font maruMonica;
    DecimalFormat DeFormat = new DecimalFormat("#0.00");

    BufferedImage keyImage;
    String message;
    boolean messageOn = false;

    public double playTime = 0;
    int count = 0;
    public int nextLevelCounter = 0;

    // CONSTRUCTOR
    public UserInterface(GamePanel gp) {

        this.gp = gp;

        keyImage = new Key_OBJ().image;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void scanMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2D) {

        g2D.setFont(maruMonica.deriveFont(Font.BOLD, 40f));
        g2D.setColor(Color.white);

        for (int i =0 ; i < gp.player.hasKey ; i++) {
            g2D.drawImage(keyImage, gp.tileSize/2 + i*40, gp.tileSize/2, gp.tileSize-8, gp.tileSize-8, null);
        }
        playTime += (double)1/60;
        g2D.drawString("Time: " + DeFormat.format(playTime), gp.tileSize*11+30, 60);

        if (messageOn) {

            g2D.setFont(maruMonica.deriveFont(Font.BOLD, 30f));
            g2D.drawString(message, gp.tileSize/2, gp.tileSize*5);
            count++;

            if (count > 100) {
                count = 0;
                messageOn = false;
            }
        }
    }

    public void drawGamePause(Graphics2D g2D) {

        String text = "Pause";

        g2D.setFont(maruMonica.deriveFont(Font.BOLD, 96F));
        g2D.setColor(Color.WHITE);

        int length = getTextLength(g2D, text);
        int x = gp.screenWidth/2 - length;
        int y = gp.screenHeight/2 - gp.tileSize;

        g2D.drawString(text, x, y);
    }

    public void drawGameFinished(Graphics2D g2D) {

        String text;
        int textLength;
        int x, y;

        g2D.setFont(arialB40);
        g2D.setColor(Color.YELLOW);

        text = "Congratulations!";
        textLength = getTextLength(g2D, text);

        x = gp.screenWidth/2 - textLength;
        y = gp.screenHeight/2 - gp.tileSize*2;

        g2D.drawString(text, x, y);

        g2D.setFont(arialB35);
        g2D.setColor(Color.WHITE);

        text = "You found the treasure!";
        textLength = getTextLength(g2D, text);

        x = gp.screenWidth/2 - textLength;
        y = gp.screenHeight/2 - gp.tileSize;

        g2D.drawString(text, x, y);

        text = "Your time is: " + DeFormat.format(playTime) + "s";
        textLength = getTextLength(g2D, text);

        x = gp.screenWidth/2 - textLength;
        y = gp.screenHeight/2;

        g2D.drawString(text, x, y);

        nextLevelCounter++;

        if (nextLevelCounter == 240) {
            gp.gameState = gp.gameStart;
            nextLevelCounter = 0;
        }
    }

    public int getTextLength(Graphics2D g2D, String text) {

        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();

        return length/2;
    }
}
