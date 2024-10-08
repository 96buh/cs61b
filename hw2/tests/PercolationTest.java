import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     *              (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    @Test
    public void xyTo1DTest() {
        Percolation p1 = new Percolation(5);
        assertThat(p1.xyTo1D(3,0)).isEqualTo(15);
        assertThat(p1.xyTo1D(3,4)).isEqualTo(19);
        Percolation p2 = new Percolation(4);
        assertThat(p2.xyTo1D(3,0)).isEqualTo(12);
        assertThat(p2.xyTo1D(2,2)).isEqualTo(10);
    }

    @Test
    public void isOpenTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        assertThat(p.isOpen(2,2)).isFalse();
        p.open(2,2);
        assertThat(p.isOpen(2,2)).isTrue();
    }

    @Test
    public void isFullTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        p.open(3,4);
        p.open(2,4);
        p.open(2,2);
        p.open(2,3);
        p.open(0,2);
        assertThat(p.isFull(2,2)).isFalse();
        p.open(1,2);
        assertThat(p.isFull(2,2)).isTrue();
    }

    @Test
    public void numbersOfOpenSiteTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        assertThat(p.numberOfOpenSites()).isEqualTo(0);
        p.open(3,4);
        p.open(2,4);
        p.open(2,2);
        p.open(2,3);
        p.open(0,2);
        assertThat(p.numberOfOpenSites()).isEqualTo(5);
    }

    @Test
    public void backwashTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        p.open(0,1);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(4,1);
        assertThat(p.isFull(0, 1)).isTrue();
        p.open(3,3);
        assertThat(p.isFull(3,3)).isFalse();
        p.open(4, 3);
        assertThat(p.isFull(4, 3)).isFalse();
    }

    @Test
    public void errorTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        Exception exception = assertThrows(Exception.class, () -> p.open(5,5));
        assertEquals("超過sites大小", exception.getMessage());
        exception = assertThrows(Exception.class, () -> p.isOpen(5,5));
        assertEquals("超過sites大小", exception.getMessage());
        exception = assertThrows(Exception.class, () -> p.isFull(5,5));
        assertEquals("超過sites大小", exception.getMessage());
        exception = assertThrows(Exception.class, () -> new Percolation(-10));
        assertEquals("N must be greater than 0.", exception.getMessage());
    }
}
