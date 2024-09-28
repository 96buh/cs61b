import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnionFind {
    // TODO: Instance variables
    private int[] parent;


    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -1 * parent[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        int root = v;
        if (v > parent.length) {
            throw new IllegalArgumentException("Index out of range");
        }
        if (v < 0) {
            throw new IllegalArgumentException("Negative input");
        }
        while (parent[root] > 0) {
            root = parent[root];
        }
        int temp;
        while (parent[v] > 0) {
            temp = parent[v];
            parent[v] = root;
            v = temp;
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 == v2) {
            return;
        }
        if (connected(v1,v2)) {
            throw new IllegalArgumentException("sets are connected");
        }else if (sizeOf(v1) <= sizeOf(v2)) {
            parent[find(v2)] -= sizeOf(v1);
            parent[find(v1)] = find(v2);
        } else {
            parent[find(v1)] -= sizeOf(v2);
            parent[find(v2)] = find(v1);
        }
    }

}
