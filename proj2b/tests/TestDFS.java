import main.DepthFirstPaths;
import main.Graph;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class TestDFS {
    @Test
    public void dfsHasPathTest() {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 3);
        g.addEdge(5, 6);
        g.addEdge(5, 8);
        g.addEdge(6, 7);
        DepthFirstPaths dfs = new DepthFirstPaths(g, 0);
        assertThat(dfs.hasPathTo(8)).isTrue();

        DepthFirstPaths dfs2 = new DepthFirstPaths(g, 5);
        assertThat(dfs2.hasPathTo(7)).isTrue();
        assertThat(dfs2.hasPathTo(8)).isTrue();
        assertThat(dfs2.hasPathTo(0)).isFalse();
        assertThat(dfs2.hasPathTo(4)).isFalse();
        assertThat(dfs2.hasPathTo(3)).isFalse();
    }

    @Test
    public void dfsPathToTest() {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 3);
        g.addEdge(5, 6);
        g.addEdge(5, 8);
        g.addEdge(6, 7);
        DepthFirstPaths dfs1 = new DepthFirstPaths(g, 0);
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 6, 7));
        assertThat(dfs1.pathTo(7)).containsExactlyElementsIn(expected1).inOrder();

        DepthFirstPaths dfs2 = new DepthFirstPaths(g, 2);
        assertThat(dfs2.pathTo(0)).isNull();
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(2, 5, 8));
        assertThat(dfs2.pathTo(8)).containsExactlyElementsIn(expected2).inOrder();
    }

    /* 測試返回的set是不是做DFS所拜訪過的snyset ID */
    @Test
    public void dfsVisitedVerticesTest() {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 3);
        g.addEdge(5, 6);
        g.addEdge(5, 8);
        g.addEdge(6, 7);
        DepthFirstPaths dfs1 = new DepthFirstPaths(g, 2);
        Set<Integer> expected1 = new HashSet<>();
        expected1.add(2);
        expected1.add(5);
        expected1.add(6);
        expected1.add(7);
        expected1.add(8);
        assertThat(dfs1.verticesThatHasVisited()).containsExactlyElementsIn(expected1).inOrder();
    }

}
