package org.game.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key_OBJ extends SuperObject {

    public Key_OBJ() {

        name = "Key";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));

        }catch (IOException e) {

            e.printStackTrace();
        }
    }
}
