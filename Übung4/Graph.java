import java.util.HashSet;

public class Graph {
    HashSet<Node> nodes = new HashSet<>();
    HashSet<Edge> edges = new HashSet<>();

    // Create Graph with adjacency matrix
    public Graph (int[][] adj) {

        // Firstly create all Nodes
        for(int i = 0; i < adj.length; i++) {
            addNode(new Node(Integer.toString(i)));
        }
        
        // Scan for 1 in adj and add corresponding edge
        for(int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                if (adj[i][j] == 1) {
                    Node a = new Node(Integer.toString(i));
                    Node b = new Node(Integer.toString(j));
                    addEdge(a, b);
                }
            }
        }
    }

    public void addNode(Node n) {
        this.nodes.add(n);
    }

    public void addEdge(Node a, Node b) {
        this.edges.add(new Edge(a, b));
    }
}