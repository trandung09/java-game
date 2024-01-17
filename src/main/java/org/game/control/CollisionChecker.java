package org.game.control;

import org.game.entity.Entity;

public class CollisionChecker {

    // FIELD
    GamePanel gp;

    // CONSTRUCTOR
    public CollisionChecker(GamePanel gp) {

        this.gp = gp;
    }

    // METHOD: CHECK TILE COLLISION
    public void checkTile(Entity entity) {

        // Xác định hàng cột theo  worldX và worldY

        int worldLeftX = entity.worldX + entity.solidArea.x;
        int worldRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int worldTopY = entity.worldY + entity.solidArea.y;
        int worldBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int playerLeftCol = worldLeftX / gp.tileSize;
        int playerRightCol = worldRightX / gp.tileSize;
        int playerTopRow = worldTopY / gp.tileSize;
        int playerBottomRow = worldBottomY / gp.tileSize;

        int tile1, tile2;

        switch (entity.direction) {
            case "Up":
                playerTopRow = (worldTopY - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapNums[playerTopRow][playerLeftCol];
                tile2 = gp.tileM.mapNums[playerTopRow][playerRightCol];
                break;

            case "Down":
                playerBottomRow = (worldBottomY + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapNums[playerBottomRow][playerLeftCol];
                tile2 = gp.tileM.mapNums[playerBottomRow][playerRightCol];
                break;

            case "Left":
                playerLeftCol = (worldLeftX - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapNums[playerTopRow][playerLeftCol];
                tile2 = gp.tileM.mapNums[playerBottomRow][playerLeftCol];
                break;

            case "Right":
                playerRightCol = (worldRightX + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.mapNums[playerTopRow][playerRightCol];
                tile2 = gp.tileM.mapNums[playerBottomRow][playerRightCol];
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + entity.direction);
        }

        if (gp.tileM.tiles[tile1].collision || gp.tileM.tiles[tile2].collision) {
            entity.collisionOn = true;
        }
    }

    // METHOD: CHECK OBJECT COLLiSION (PLAYER)
    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0 ; i < gp.obj.length ; i++) {

            if (gp.obj[i] != null) {

                // Get position of Rectangle
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "Up":
                        entity.solidArea.y -= entity.speed;
                        break;

                    case "Down":
                        entity.solidArea.y += entity.speed;
                        break;

                    case "Left":
                        entity.solidArea.x -= entity.speed;
                        break;

                    case "Right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) index = i;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    //
    public int checkEntity(Entity entity, Entity[] target) {

        int index = 999;

        for (int i = 0 ; i < target.length ; i++) {

            if (target[i] != null) {

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (target[i].direction) {
                    case "Up":
                        entity.solidArea.y -= entity.speed;
                        break;

                    case "Down":
                        entity.solidArea.y += entity.speed;
                        break;

                    case "Left":
                        entity.solidArea.x -= entity.speed;
                        break;

                    case "Right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(target[i].solidArea)) {
                    entity.collisionOn = true;
                    index = i;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public void checkPlayer(Entity entity) {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "Up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;

            case "Down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    System.out.println(true);
                }
                break;

            case "Left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;

            case "Right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}
