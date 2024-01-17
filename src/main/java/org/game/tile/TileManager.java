package org.game.tile;

import org.game.control.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TileManager {

    // FIELD
    GamePanel gp;

    public Tile[] tiles;
    public int[][] mapNums;

    // CONSTRUCTOR
    public TileManager(GamePanel gp) {

        this.gp = gp;

        tiles = new Tile[50];
        mapNums = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap();
        /**
         *  loadMap(int level) được sử dụng tại Lớp KeyHandler
         *  khi đó ta bắt đầu khởi tạo đối tượng keyH trong GamePanel
         *  và loadMap tránh trường hợp map luôn được chỉ định ở mức 1
         */
    }

    public void getTileImage() { // Read image of object

        for (int i = 0 ; i < 50 ; i++) {
            tiles[i] = new Tile();
            if (i == 41 || i == 40 || (i > 11 && i < 26)) tiles[i].collision = true;
            else tiles[i].collision = false;
        }

        try {

            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/newtile/grass00.png"));
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/newtile/grass00.png"));
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/newtile/grass00.png"));
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/newtile/grass00.png"));

            // new Tiles
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/newtile/grass00.png"));
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/newtile/grass01.png"));
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water00.png"));
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water01.png"));
            tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water02.png"));
            tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water03.png"));
            tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water04.png"));
            tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water05.png"));
            tiles[18].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water06.png"));
            tiles[19].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water07.png"));
            tiles[20].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water08.png"));
            tiles[21].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water09.png"));
            tiles[22].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water10.png"));
            tiles[23].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water11.png"));
            tiles[24].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water12.png"));
            tiles[25].image = ImageIO.read(getClass().getResourceAsStream("/newtile/water13.png"));
            tiles[26].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road00.png"));
            tiles[27].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road01.png"));
            tiles[28].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road02.png"));
            tiles[29].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road03.png"));
            tiles[30].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road04.png"));
            tiles[31].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road05.png"));
            tiles[32].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road06.png"));
            tiles[33].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road07.png"));
            tiles[34].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road08.png"));
            tiles[35].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road09.png"));
            tiles[36].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road10.png"));
            tiles[37].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road11.png"));
            tiles[38].image = ImageIO.read(getClass().getResourceAsStream("/newtile/road12.png"));
            tiles[39].image = ImageIO.read(getClass().getResourceAsStream("/newtile/earth.png"));
            tiles[40].image = ImageIO.read(getClass().getResourceAsStream("/newtile/wall.png"));
            tiles[41].image = ImageIO.read(getClass().getResourceAsStream("/newtile/tree.png"));
            tiles[42].image = ImageIO.read(getClass().getResourceAsStream("/newtile/tree.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void loadMap() { // Read map data

        try {

            File file = null;
            file = new File("D:\\LEARN JAVA (Intelij)\\JAVA GAME\\BlueBoyGame (1) - Has Font Create\\src\\main\\resources\\map\\map2.txt");

            BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);

            int row = 0;
            String line = null;

            while(true) {

                line = br.readLine();
                if (line == null) break;

                String[] nums = line.split(" ");

                for (int i = 0 ; i < gp.maxWorldCol ; i++) {
                    mapNums[row][i] = Integer.parseInt(nums[i]);
                }

                row++;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) { // Draw a map

        int row = 0, col = 0;

        while(row < gp.maxWorldRow && col < gp.maxWorldCol) {

            int tileNum = mapNums[row][col];

            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2D.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            col++;

            if (col == gp.maxWorldCol) {

                col = 0;
                row++;
            }
        }
    }
}
