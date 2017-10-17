import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGraph<V> {
    /*
    * Should support:
    * - adding/removing vertices
    * - adding/removing edges
    * - checking connectivity
    * - create MST
    * - TSP Algorithms (Prim's and Christofides') -- NOTE: graph must follow triangle inequality... may be difficult to check
    * - Print current
    * */

    private Map<Integer, MyVertex> vertices;
    private int numEdges;

    public MyGraph(){
        vertices = new HashMap<>();
        numEdges = 0;
    }
    /**
     * Adds a vertex if and only if the vertex ID does not already exist.
     * @param id unique ID of the vertex to be added.
     * @return true if vertex was added, false if not.
     * */
    public boolean addVertex(int id, V val){
        if(vertices.containsKey(id)){
            return false;
        }
        vertices.put(id, new MyVertex(id, val));
        return true;
    }
    /**
     * Removes the vertex with the specified ID, along with all edges that are connected to it.
     * @param id unique ID of the vertex to be removed.
     * @return true if vertex was removed, false if not.
     * */
    public boolean removeVertex(int id){
        if(!vertices.containsKey(id)){
            return false;
        }
        List<Integer> otherVertices= new ArrayList<>();
        MyVertex temp = vertices.get(id);
        //for each edge, get the other vertex that it is connected to
        temp.weightedEdges.forEach((k,v) -> {
            otherVertices.add(k);
        });
        //remove these edges from other vertices
        for(int i: otherVertices){
            if(vertices.containsKey(i)){
                vertices.get(i).removeEdge(id);
            }
        }
        //remove vertex
        vertices.remove(id);
        return true;
    }
    /**
     * Returns the value store by the vertex of the given ID
     * @param id ID of the vertex
     * @return Value of the vertex. Null if vertex does not exist
     * */
    public V getVertexValue(int id){
        if(containsVertex(id)) return vertices.get(id).value;
        return null;
    }
    /**
     * Changes the value of the vertex
     * @param id ID of the vertex
     * @param val new value of the vertex
     * @return true if operation was successful, false otherwise
     * */
    public boolean changeVertexValue(int id, V val){
        if(containsVertex(id)){
            vertices.get(id).value = val;
            return true;
        }
        return false;
    }
    /**
     * Adds an edge of a specified weight between the vertices with ID1 and ID2
     * @param id1 ID of one of the vertices
     * @param id2 ID of the other vertex
     * @return true if operation was successful, false otherwise
     * */
    public boolean addEdge(int id1, int id2, int weight){
        if(id1 == id2 || isAdjacent(id1, id2)) return false;
        if(vertices.containsKey(id1) && vertices.containsKey(id2)){
            vertices.get(id1).addEdge(id2, weight);
            vertices.get(id2).addEdge(id1, weight);
            return true;
        }
        return false;
    }
    /**
     * Default {@link #addEdge(int, int, int) addEdge} method with weight set at 1
     * */
    public boolean addEdge(int id1, int id2){
        return addEdge(id1, id2, 1);
    }
    /**
     * Removes edge between vertices with ID1 and ID2
     * @param id1 ID of one of the vertices
     * @param id2 ID of the other vertex
     * @return true if the edge was removed, false otherwise
     * */
    public boolean removeEdge(int id1, int id2){
        if(!(vertices.containsKey(id1) && vertices.containsKey(id2))){
            return false;
        }
        MyVertex vert1 = vertices.get(id1);
        MyVertex vert2 = vertices.get(id2);
        return vert1.removeEdge(id2) && vert2.removeEdge(id1);
    }
    /**
     * Checks whether or not the vertex with the given ID exists in the graph
     * @param id ID of the vertex
     * @return true if vertex is in graph, false otherwise
     * */
    public boolean containsVertex(int id){
        return vertices.containsKey(id);
    }
    /**
     * Checks whether or not an edge between vertices denoted by ID1 and ID2 exists
     * @param id1 ID of the first vertex
     * @param id2 ID of the second vertex
     * @return true if edge exists, false otherwise
     * */
    public boolean isAdjacent(int id1, int id2){
        if(id1 == id2) return true; //vacuously true?
        if(!containsVertex(id1) || !containsVertex(id2)) return false;
        return vertices.get(id1).containsEdge(id2) && vertices.get(id2).containsEdge(id1);
    }
    /**
     * Lists all vertices that are connected a vertex specified by the user
     * @param id
     * @return List of the ID's of all the vertices adjacent to the specified vertex. Returns null if vertex does not exist
     * */
    public List<Integer> getNeighbors(int id){
        if(!containsVertex(id)) return null;
        List<Integer> neighbors = new ArrayList<>();
        MyVertex vertex = vertices.get(id);
        vertex.weightedEdges.forEach((k,v) -> {
            neighbors.add(k);
        });
        return neighbors;
    }
    /**
     * A graph is connected if and only if you can get from any one vertex in the graph
     * to any other vertex. In this method, we will choose a vertex at random, and then
     * check connectivity to all other vertices.
     * @return true if the graph is connected, false otherwise
     * */
    public boolean isConnected(){
        //TODO: Logic
        return false;
    }
    /**
     * A MST (Minimum Spanning Tree) is a tree in which the branches are all of minimum weight.
     * This method will use Prim's algorithm. It is a greedy algorithm that starts at an
     * arbitrary vertex (in this case, the one specified by the user) and at each step, choosing
     * the cheapest possible connection from the tree to another vertex not in the tree.
     * @return returns a MyGraph instance containing one of the possible MST
     * */
    public MyGraph createMST() throws IllegalStateException{
        //TODO: Logic
        //Check if graph is empty or disconnected
        return null;
    }
    //    public MyGraph solveTSP(int id){
//        return null;
//    }
    @Override
    public String toString() {
        final String[] s = {String.format("This graph has %d vertices\n\n", vertices.size())};
        vertices.forEach((k,v) -> { //WOW MUCH FUNCTIONAL
            s[0] = s[0].concat(v.toString()+"\n\n");
        });
        return s[0];
    }
    /* Helper functions and classes */
    /**
     * Private class that denotes a vertex. Each vertex should have a unique integer ID.
     * The vertex will also store all edges associated to it, along with their weights.
     * */
    private class MyVertex{
        private int id;
        private V value;
        private Map<Integer, Integer> weightedEdges;

        private MyVertex(int id, V val){
            this.id = id;
            this.value = val;
            this.weightedEdges = new HashMap<>();
        }
        /**
         * Add a weighted edge to the vertex. Note that it will overwrite an existing edge
         * @param id the integer ID of the other vertex of this edge.
         * @param weight the weight of the edge
         * @return true if edge was added, false if not
         * */
        private boolean addEdge(int id, int weight){
            weightedEdges.put(id, weight);
            return false;
        }
        /**
         * default {@link #addEdge(int, int) addEdge} method with weight as 1
         * */
        private boolean addEdge(int id){
            return addEdge(id, 1);
        }
        /**
         * Removes the edge that connects this vertex to the vertex specified by the ID
         * @param id the ID of the other vertex that the edge is associated to
         * @return true if the edge was removed, false if not
         * */
        private boolean removeEdge(int id){
            if(weightedEdges.containsKey(id)){
                return false;
            }
            weightedEdges.remove(id);
            return true;
        }
        /**
         * Returns the truth value of whether or not this vertex is connected to
         * the vertex specified by the user
         * @param id the ID of the specified vertex
         * @return true if the edge exists, false otherwise
         * */
        private boolean containsEdge(int id){
            return weightedEdges.containsKey(id);
        }

        @Override
        public String toString() {
            String s = "Vertex (ID, value): " +String.format("(%s, %s)", String.valueOf(this.id), String.valueOf(this.value)) + "\nEdges(vertex, weight): ";
            if(weightedEdges.isEmpty()){
                return s + "n/a";
            }
            for(Map.Entry<Integer, Integer> entry: weightedEdges.entrySet()){
                s = s.concat("("+String.valueOf(entry.getKey())+", "+String.valueOf(entry.getValue())+"), ");
            }
            return s;
        }
    }
}
