package org.game.control;

import org.game.dialogue.Dialogue;
import org.game.dialogue.UIScreen;
import org.game.entity.Entity;
import org.game.object.SuperObject;
import org.game.tile.TileManager;
import org.game.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int scale = 3;
    final int edgeTileSize = 16;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int tileSize = scale * edgeTileSize;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    // MAP SETTINGs
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS SETTINGS
    public final int FPS = 60;

    // PLAY OR PAUSE SETTINGS (Game State = Trạng thái trò chơi)
    public int gameState = 1;
    public final int gameStart = 0;
    public final int gamePlay = 1;
    public final int gamePause = 2;
    public final int gameNext = 3;

    // OBJECT OF THE GAME
    Thread gameThread;
    public KeyHandler keyH = new KeyHandler(this);
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker coChecker = new CollisionChecker(this);
    public SuperObject[] obj = new SuperObject[20];
    public Entity[] npc = new Entity[20];
    Sound sound = new Sound();

    public AssetSetter assetSetter = new AssetSetter(this);
    public UserInterface uInterface = new UserInterface(this);
    public UIScreen uiScreen = new UIScreen(this);
    public Dialogue dialogue = new Dialogue(this);

    // CONSTRUCTOR
    public GamePanel() {

        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.requestFocusInWindow(true);

        this.addKeyListener(keyH);

        startGameThread();
        setUpGameObject();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGameObject() {

        assetSetter.setEntityNPC();
        assetSetter.setObject();

        gameState = gameStart;
        playMusic(0);
    }

    // METHOD: RUNNABLE
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS; // nano seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; // milis second

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public void update() {

        if (gameState == gamePlay) {

            player.update();
            for (Entity entity : npc) {
                if (entity != null) entity.update();
            }
        }
    }

    public void paintComponent(Graphics gr) {

        super.paintComponent(gr);

        Graphics2D g2D = (Graphics2D) gr;

        if (gameState != gameStart) {
            // Draw a map
            tileM.draw(g2D);

            // Draw player
            player.draw(g2D);

            // Draw user interface
            if (gameState == gamePlay) {
                uInterface.draw(g2D);
            } else if (gameState == gameNext){
                uInterface.drawGameFinished(g2D);
            }
            else if (gameState == gamePause) {
                uInterface.drawGamePause(g2D);
            }

            // Draw entity NPC
            for (Entity entity : npc) {

                if (entity != null) entity.draw(g2D);
            }

            // Draw object
            for (SuperObject sObj : obj) {

                if (sObj != null) sObj.draw(g2D, this);
            }
        }
        else if (gameState == gameStart) {
            uiScreen.drawGameScreen(g2D);
        }

        g2D.dispose();
    }

    // METHOD: SOUND SETTINGS
    public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {

        sound.stop();
    }

    public void playSE(int i) {

        Sound sound_ = new Sound();
        sound_.setFile(i);
        sound_.play();
    }
}
