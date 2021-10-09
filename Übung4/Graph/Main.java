package Graph;
public class Main {
    public static void main(String[] args) {

        // Test der Generierung eines Graphenper Adjazenzmatrix
        /*
        int[][] adj = new int[][] {
            {0, 0, 1},
            {0, 0, 1},
            {1, 1, 0}
        };
        Graph g = new Graph(adj);
        */
        Graph g = new Graph();
        Node n1 = new Node("1");
        Node n2 = new Node("2");

        g.addNode(n1);
        g.addNode(n2);
        g.addEdge(n1, n2);
    }
}
