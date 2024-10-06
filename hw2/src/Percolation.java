import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] sites;
    private WeightedQuickUnionUF wqu;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        // blocked = false
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0.");
        }
        sites = new boolean[N][N];
        wqu = new WeightedQuickUnionUF(N * N + 2);
        // virtual top site
        for (int i = 0; i < N; i++) {
            wqu.union(xyTo1D(0, i), N * N); // N*N is the virtual top site
        }
        // virtual bottom site
        for (int i = 0; i < N; i++) {
            wqu.union(N * N + 1, xyTo1D(N - 1, i));
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row > sites.length - 1 || col > sites.length - 1) {
            throw new IndexOutOfBoundsException("超過sites大小");
        }
        if (!isOpen(row, col)) {
            sites[row][col] = true;
        }
        if (row + 1 < sites.length && isOpen(row + 1, col)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (col + 1 < sites.length && isOpen(row, col + 1)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            wqu.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row > sites.length - 1 || col > sites.length - 1) {
            throw new IndexOutOfBoundsException("超過sites大小");
        }
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (row > sites.length - 1 || col > sites.length - 1) {
            throw new IndexOutOfBoundsException("超過sites大小");
        }
        // 連接到virtual top以及該site是open才能是Full.
        return wqu.connected(sites.length * sites.length, xyTo1D(row, col)) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        int counter = 0;
        for (int i = 0; i < sites.length; i++) {
            for (int j = 0; j < sites.length; j++) {
                if (sites[i][j]) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        int t = sites.length * sites.length;
        return wqu.connected(t, t + 1);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    public int xyTo1D(int row, int col) {
        return (sites.length * row) + col;
    }

}
