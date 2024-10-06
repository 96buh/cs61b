import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] sites;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF wqu2;
    private int virtualTop;
    private int virtualBottom;
    private int openSite;


    public Percolation(int N) {
        // blocked = false
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        openSite = 0;

        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0.");
        }
        sites = new boolean[N][N];
        // virtual top and virtual bottom
        wqu = new WeightedQuickUnionUF(N * N + 2);
        // only virtual top site
        wqu2 = new WeightedQuickUnionUF(N * N + 1);

        // virtual top site
        for (int i = 0; i < N; i++) {
            wqu.union(xyTo1D(0, i), virtualTop);
            wqu2.union(xyTo1D(0, i), virtualTop);
        }
        // virtual bottom site
        for (int i = 0; i < N; i++) {
            wqu.union(virtualBottom, xyTo1D(N - 1, i));
        }
    }

    public void open(int row, int col) {
        if (row > sites.length - 1 || col > sites.length - 1) {
            throw new IndexOutOfBoundsException("超過sites大小");
        }
        if (!isOpen(row, col)) {
            sites[row][col] = true;
            openSite += 1;
        }
        if (row + 1 < sites.length && isOpen(row + 1, col)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            wqu2.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            wqu2.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (col + 1 < sites.length && isOpen(row, col + 1)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            wqu2.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            wqu2.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

    }

    public boolean isOpen(int row, int col) {
        if (row > sites.length - 1 || col > sites.length - 1) {
            throw new IndexOutOfBoundsException("超過sites大小");
        }
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row > sites.length - 1 || col > sites.length - 1) {
            throw new IndexOutOfBoundsException("超過sites大小");
        }
        // 連接到virtual top以及該site是open才能是Full.
        return wqu2.connected(virtualTop, xyTo1D(row, col)) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        return wqu.connected(virtualTop, virtualBottom);
    }

    public int xyTo1D(int row, int col) {
        return (sites.length * row) + col;
    }

}
