package org.game.entity;

import org.game.control.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class OldMan extends Entity {

    public OldMan(GamePanel gp) {

        super(gp);
        direction = "Down";
        speed = 1;

        setOldManImage();
    }

    public void setOldManImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {

        drawCounter++;

        if (drawCounter == 120) {
            drawCounter = 0;

            Random random = new Random();
            int ran = random.nextInt(100);

            if (ran < 25) direction = "Up";
            else if (ran >= 25 && ran < 50) direction = "Down";
            else if (ran >= 50 && ran < 75) direction = "Left";
            else direction = "Right";
        }
    }
}
