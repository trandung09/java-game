package org.game.control;

import org.game.entity.OldMan;
import org.game.object.Boot_OBJ;
import org.game.object.Chest_OBJ;
import org.game.object.Door_OBJ;
import org.game.object.Key_OBJ;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Key_OBJ();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Key_OBJ();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new Key_OBJ();
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new Door_OBJ();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new Door_OBJ();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new Door_OBJ();
        gp.obj[5].worldX = 13 * gp.tileSize;
        gp.obj[5].worldY = 23 * gp.tileSize;

        gp.obj[6] = new Chest_OBJ();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 8 * gp.tileSize;

        gp.obj[7] = new Boot_OBJ();
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    }

    public void setEntityNPC() {

        gp.npc[0] = new OldMan(gp);
        gp.npc[0].worldX = 21 * gp.tileSize;
        gp.npc[0].worldY = 21 * gp.tileSize;
    }
}
