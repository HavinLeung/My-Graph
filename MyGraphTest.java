import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyGraphTest {
    MyGraph<String> g;
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
        assertTrue(g.removeEdge(1,2));
        assertTrue(g.isAdjacent(1,2));
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
    }

    @Test
    void isConnected() {
    }

    @Test
    void createMST() {
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        g = new MyGraph<>();
    }

}