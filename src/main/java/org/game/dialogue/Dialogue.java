package org.game.dialogue;

import org.game.control.GamePanel;

import java.awt.*;

public class Dialogue {

    GamePanel gp;

    public Dialogue(GamePanel gp) {

        this.gp = gp;
    }

    public void draw(Graphics2D g2D) {


        // .fillRoundRect(): Vẽ khung hộp thoại hình chữ nhật
        // .setStroke(new BasicStroke(int width)).drawRoundRect() : Vẽ một khung viền hình chữ nhật

        int x = gp.tileSize*4;
        int y = gp.tileSize*2;
        int width = gp.tileSize*8;
        int height = gp.tileSize*5;

        g2D.setColor(new Color(0, 0, 0, 200));
        g2D.fillRoundRect(x, y, width, height, 35, 35);

        g2D.setColor(new Color(255, 255, 255));
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getTextLength(Graphics2D g2D, String text) {

        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();

        return length/2;
    }
}
