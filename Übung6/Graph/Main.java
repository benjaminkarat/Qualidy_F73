package Graph;
public class Main {
    public static void main(String[] args) {

        // Test der Generierung eines Graphenper Adjazenzmatrix
        int[][] adj = new int[][] {
            {0, 0, 1},
            {0, 0, 1},
            {1, 1, 0}
        };
        Graph g1 = new Graph(adj); 

        // Generieren eines Graphen (objektorientiert)
        Graph g2 = new Graph();
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        g2.addNode(n1);
        g2.addNode(n2);
        g2.addNode(n3);
        g2.addEdge(n1, n3);
        g2.addEdge(n2, n3);
        //g2.addEdge(n1, n2);
 
        // Test ob alles connected ist
        System.out.println(g2.isConnected());
        System.out.println(g2.hasEulerTour());
        System.out.println(g2.hasEulerPath());
        //g2.addNode(new Node("4"));
        //System.out.println(g2.isConnected());
    }
}
