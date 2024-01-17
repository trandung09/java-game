package org.game.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door_OBJ extends SuperObject {

    public Door_OBJ() {

        name = "Door";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));

        }catch (IOException e) {

            e.printStackTrace();
        }
    }
}

