package org.game.control;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameFrame extends JFrame {

    private final GamePanel gamePanel;
    private BufferedImage image;

    public GameFrame() {

        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.addKeyListener(gamePanel.keyH);

        this.setTitle("Blue Boy");
        this.setVisible(true);
        this.setResizable(true);
        this.setLocation(400, 70);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(gamePanel.screenWidth, gamePanel.screenHeight));

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/blueboy/boy_right_1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIconImage(image);
    }

    public static void main(String[] args) {

        new GameFrame();
    }
}
