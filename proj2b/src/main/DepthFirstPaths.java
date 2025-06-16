package main;

import java.util.*;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s; // 起始點
    private Set<Integer> set;

    public DepthFirstPaths(Graph G, int s) {
        this.set = new HashSet<>();
        this.s = s;
        this.marked = new boolean[G.size()];
        this.edgeTo = new int[G.size()];
        // data structure initialization
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        set.add(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(s);
        Collections.reverse(path);
        return path;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Set<Integer> verticesThatHasVisited() {
        return this.set;
    }
}
