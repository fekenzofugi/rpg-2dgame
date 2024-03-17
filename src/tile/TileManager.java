package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        // 10 tile types
        tile = new Tile[10];

        mapTileNumber = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    // load tile image
    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].setImage(ImageIO.read(this.getClass().getResourceAsStream("/tiles/grass.png")));
            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(this.getClass().getResourceAsStream("/tiles/wall.png")));
            tile[2] = new Tile();
            tile[2].setImage(ImageIO.read(this.getClass().getResourceAsStream("/tiles/water.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {

            // import text file
            InputStream inputStream = this.getClass().getResourceAsStream(filePath);

            // read the content of the text file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {

                // read a single line from the text file and store it on String line
                String line = bufferedReader.readLine();

                while (col < gp.getMaxScreenCol()) {

                    // splits the string around matches of the given regular expression.
                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gp.getMaxScreenCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {

            int tileNumber = mapTileNumber[col][row];

            g2.drawImage(tile[tileNumber].getImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();

            if (col == gp.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }

        }
    }
}
