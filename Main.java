public class Main {
    public static void main(String[] args) {
        MyGraph<String> g = new MyGraph<>();
        for (int i = 1; i < 10; i++) {
            g.addVertex(i, String.valueOf(i));
        }
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 1);
        g.addEdge(1, 4, 4);
        g.addEdge(4, 2, 7);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 5, 5);
        g.addEdge(4, 6, 2);
        g.addEdge(4, 7, 1);
        g.addEdge(4, 8, 2);
        g.addEdge(4, 9, 5);
        System.out.println(g.toString());
        /*
        BEFORE... Ugly non-tree

         [1]--3--[2]  [5]
          | \     |   /
          1   4   7  5
          |     \ | /
         [3]--3--[4]--2--[6]
                / | \
               5  2  1
              /   |   \
             [9] [8]  [7]
        */
        /*
        AFTER... Beautiful Minimum Spanning Tree

         [1]--3--[2]  [5]
          |           /
          1          5
          |         /
         [3]--3--[4]--2--[6]
                / | \
               5  2  1
              /   |   \
             [9] [8]  [7]
        */

        // Good practice:
        if(g.isConnected()){
            System.out.println(g.createMST().toString());
        }
        // Bad practice:
//        try {
//            System.out.println(g.createMST().toString());
//        } catch (IllegalStateException e) {
//            System.out.println("Failed lmao");
//        }

    }
}
