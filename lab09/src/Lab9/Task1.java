package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world initially full of trees.
 */
public class Task1 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int WORLD_WIDTH = 30;
    private static final int WORLD_HEIGHT = 20;


    private static void fillWithTrees(TETile[][] world) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                world[i][j] = Tileset.TREE;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WORLD_WIDTH, WORLD_HEIGHT);

        TETile[][] world = new TETile[30][15];
        fillWithTrees(world);

        ter.renderFrame(world);
    }
}