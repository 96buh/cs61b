package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // TODO: Fill this in in tasks 2, 3, 4
        if (r == 0) { // 因為r=0已經是最上面了，不需要再往上移動，return 0(沒有合併)
            return 0;
        } else if (board[r-1][c] != 0) { // 上面的值不為零，用於之後判斷是否合併
            if ((board[r - 1][c] == board[r][c]) && r > minR) { // 當有連續兩個row有一樣的，並且r > minR才能合併
                board[r - 1][c] += board[r][c];
                board[r][c] = 0;
                return 1 + r - 1;
            }
            return 0; // 遇到不同的數字擋住無法往上移動
        } else if (minR == r) { // 如果minR和r一樣就代表指定的(r,c)不能往上移動
            return 0;
        }
        board[r-1][c] = board[r][c];
        board[r][c] = 0;
        return moveTileUpAsFarAsPossible(board, r-1, c, minR);
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: fill this in in task 5
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.EAST) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else if (side == Side.SOUTH) {
            return;
        } else {
            return;
        }
    }
}
