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
        if (r == 0) { // 因為r=0已經是最上面了，不需要再往上移動，return 0(沒有合併)
            return 0;
        } else if (board[r - 1][c] != 0) { // 上面的值不為零，用於之後判斷是否合併
            if ((board[r - 1][c] == board[r][c]) && r > minR) { // 當有連續兩個row有一樣的，並且r > minR才能合併
                board[r - 1][c] += board[r][c];
                board[r][c] = 0;
                return 1 + r - 1;
            }
            return 0; // 遇到不同的數字擋住無法往上移動
        } else if (minR == r) { // 如果minR和r一樣就代表指定的(r,c)不能往上移動
            return 0;
        }
        board[r - 1][c] = board[r][c];
        board[r][c] = 0;
        return moveTileUpAsFarAsPossible(board, r - 1, c, minR);
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int top = 0;
        int out;
        for (int i = 0; i < board.length; i++) {
            // 看往上移動有沒有合併
            out = moveTileUpAsFarAsPossible(board, i, c, top);
            // 合併完後上面還有一個空格可以移動，把最高可以移動到的row限制為merge發生的row
            if (out != 0 && board[i - 1][c] == 0) {
                top = out - 1;
            } else if (out != 0) { // 發生合併但上面不為0(上面已經有數字擋住了)
                top = i;
            }
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            tiltColumn(board, i);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // 把所有棋盤都變成往上移的形式
        if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else if (side == Side.SOUTH) {
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateRight(board);
            rotateRight(board);
        } else {
            tiltUp(board);
        }
    }
}
