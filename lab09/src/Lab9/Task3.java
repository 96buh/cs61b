package Lab9;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task3 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int WORLD_WIDTH = 30;
    private static final int WORLD_HEIGHT = 20;

    private static final long SEED = 1;
    private static final Random RANDOM = new Random(SEED);


    private static void fillWithTrees(TETile[][] world) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 15; j++) {
                world[i][j] = Tileset.TREE;
            }
        }
    }

    private static void drawSquare(TETile[][] world, int startX, int startY, int size, TETile tile) {
        for (int x = startX; x < (startX + size); x++) {
            if (x > world.length - 1) {
                break;
            }
            for (int y = startY; y > (startY - size); y--) {
                if (y < 0) {
                    break;
                }
                world[x][y] = tile;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            default -> Tileset.NOTHING;
        };
    }

    private static void addRandomSquare(TETile[][] world, Random rand) {
        int size = RandomUtils.uniform(rand, 3, 8);
        int randomX = RandomUtils.uniform(rand, 0, WORLD_WIDTH - 1);
        int randomY = RandomUtils.uniform(rand, 0, 15);
        drawSquare(world, randomX, randomY, size, randomTile());
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WORLD_WIDTH, WORLD_HEIGHT);
        int counter = 0;

        TETile[][] world = new TETile[30][15];
        fillWithTrees(world);

        char c;
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);


                switch (c) {
                    case 'n':
                        Random r = new Random();
                        addRandomSquare(world, r);
                        counter += 1;
                        break;
                    case 'q':
                        System.exit(0); // Closes the game window and quits the game.
                        break;
                    default:
                        break;
                }
            }
            ter.renderFrame(world);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.textLeft(1, 17, "Numbers of squares: " + counter);
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}