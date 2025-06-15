import main.Graph;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGraph {
    @Test
    public void createNodesTest() {
        Graph g = new Graph(10);
        for (int i = 0; i < 10; i++) {
            assertThat(g.adj(i)).isEmpty();
        }
    }

    @Test
    public void addingEdgeTest() {
        Graph g = new Graph(10);
        g.addEdge(0, 2);
        g.addEdge(0, 4);
        g.addEdge(1, 9);
        g.addEdge(6, 7);
        g.addEdge(2, 5);
        assertThat(g.adj(0)).containsExactly(2, 4).inOrder();
        assertThat(g.adj(1)).containsExactly(9).inOrder();
        assertThat(g.adj(6)).containsExactly(7).inOrder();
        assertThat(g.adj(2)).containsExactly(5).inOrder();
    }

    @Test
    public void addInvalidEdgeTest() {
        Graph g = new Graph(10);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> g.addEdge(-1, 5));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> g.addEdge(10, 5));
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(-1, -5));
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(3, 50));
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(30, 50));
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(3, 10));
    }
}
