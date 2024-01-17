package org.game.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest_OBJ extends SuperObject {

    public Chest_OBJ() {

        name = "Chest";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));

        }catch (IOException e) {

            e.printStackTrace();
        }
    }
}
