package org.game.object;

import org.game.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Boot_OBJ extends SuperObject {

    public Boot_OBJ() {

        name = "Boot";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));

        }catch (IOException e) {

            e.printStackTrace();
        }
    }
}
