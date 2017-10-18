import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyGraphTest {

    private MyGraph<String> g;
    @Test
    void addVertex() {
        for(int i = 0; i < 5; i++){
            assertTrue(g.addVertex(i, String.valueOf(i)));
        }
        for(int i = 0; i < 5; i++){
            assertTrue(g.containsVertex(i));
            assertEquals(String.valueOf(i), g.getVertexValue(i));
        }
    }

    @Test
    void removeVertex() {
        for(int i = 0; i < 5; i++){
            assertTrue(g.addVertex(i, String.valueOf(i)));
        }
        for(int i = 0; i < 5; i++){
            assertTrue(g.removeVertex(i));
        }
        for(int i = 0; i < 5; i++){
            assertFalse(g.containsVertex(i));
            assertNull(g.getVertexValue(i));
        }
    }

    @Test
    void changeVertexValue() {
        for(int i = 0; i < 5; i++){
            assertTrue(g.addVertex(i, String.valueOf(i)));
            assertTrue(g.changeVertexValue(i, String.valueOf(i+1)));
            assertEquals(String.valueOf(i+1), g.getVertexValue(i));
        }
    }

    @Test
    void addEdge() {
        assertTrue(g.addVertex(1, String.valueOf("Hello World!")));
        assertTrue(g.addVertex(2, String.valueOf("Goodbye Cruel World!")));
        assertTrue(g.addEdge(1,2, 20));
        assertEquals(new Integer(20), g.getEdgeValue(1, 2));
        assertFalse(g.addEdge(1,2, 10));
        assertFalse(g.addEdge(1,1, 20));
    }

    @Test
    void removeEdge() {
        assertTrue(g.addVertex(1, String.valueOf("Hello World!")));
        assertTrue(g.addVertex(2, String.valueOf("Goodbye Cruel World!")));
        assertTrue(g.addEdge(1,2, 20));
        assertFalse(g.addEdge(1,2, 10));
        assertFalse(g.addEdge(1,1, 20));
        assertTrue(g.isAdjacent(1,2));
        assertTrue(g.removeEdge(1,2));
        assertFalse(g.isAdjacent(1,2));
    }

    @Test
    void isAdjacent() {
        for(int i = 0; i < 5; i++){
            g.addVertex(i,"Hi");
        }
        for(int i = 0; i < 4; i++){
            g.addEdge(i, i + 1);
            assertTrue(g.isAdjacent(i, i+1));
        }
    }

    @Test
    void getNeighbors() {
        for(int i = 0; i < 4; i++){
            g.addVertex(i, "");
        }
        for(int i = 1; i < 4; i++){
            g.addEdge(0, i);
        }
        assertEquals(Arrays.asList(1, 2, 3), g.getNeighbors(0));
        g.removeEdge(0,2);
        assertEquals(Arrays.asList(1, 3), g.getNeighbors(0));
        g.removeVertex(3);
        assertEquals(Arrays.asList(1), g.getNeighbors(0));
    }

    @Test
    void isConnected() {
        for(int i = 1; i < 10; i++){
            g.addVertex(i,"");
        }
        assertFalse(g.isConnected());
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(3,4);
        g.addEdge(2,4);
        for(int i = 5; i < 10; i++){
            g.addEdge(4,i);
        }
        assertTrue(g.isConnected());
        g.removeEdge(2,4);
        g.removeEdge(3,4);
        assertTrue(g.isConnected());
        g.removeEdge(4,7);
        assertFalse(g.isConnected());
        g.removeVertex(4);
        assertFalse(g.isConnected());
    }

    @Test
    void createMST() {
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        g = new MyGraph<>();
    }

}