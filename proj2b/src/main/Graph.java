package main;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int V;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.adj = (List<Integer>[]) new ArrayList[this.V];
        for (int v = 0; v < adj.length; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    /* 把vertex v指向vertex w */
    public void addEdge(int v, int w) {
        if (w > adj.length - 1 || w < 0) {
            throw new IllegalArgumentException();
        }
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
