package org.game.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {

        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (gp.gameState != gp.gameStart) {
            if (keyCode == KeyEvent.VK_UP) {
                upPressed = true;
            }
            else if (keyCode == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            else if (keyCode == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            else if (keyCode == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            else if (keyCode == KeyEvent.VK_P) {
                if (gp.gameState == gp.gamePlay) {
                    gp.gameState = gp.gamePause;
                }
                else if (gp.gameState == gp.gamePause) {
                    gp.gameState = gp.gamePlay;
                }
            }
        }
        else if (gp.gameState == gp.gameStart) {
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gp.uiScreen.choose == 1) {
                    if (gp.gameState == gp.gameStart) {
                        gp.gameState = gp.gamePlay;
                    }
                }
                else if (gp.uiScreen.choose == 2) {

                }
                else if (gp.uiScreen.choose == 3) {
                    System.exit(0);

                }
            }
            else if (keyCode == KeyEvent.VK_UP) {
                if (gp.uiScreen.choose == 1) gp.uiScreen.choose = 3;
                else gp.uiScreen.choose--;
            }
            else if (keyCode == KeyEvent.VK_DOWN) {
                if (gp.uiScreen.choose == 3) gp.uiScreen.choose = 1;
                else gp.uiScreen.choose++;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
