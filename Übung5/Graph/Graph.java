package Graph;
import java.util.HashSet;

public class Graph {
    HashSet<Node> nodes;
    HashSet<Edge> edges;

    // Create Graph with adjacency matrix
    public Graph (int[][] adj) {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        // Implement Me!
    }

    public Graph () {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    };

    public boolean isConnected() {
        // Implement Me!
        return true;
    }

    public boolean hasEulerTour() {
        // Impelement Me!
        return true;
    }

    public boolean hasEulerPath() {
        // Impelment Me!
        return true;
    }

    public void addNode(Node n) {
        this.nodes.add(n);
    }

    public void addEdge(Node a, Node b) {
        this.edges.add(new Edge(a, b));
    }
}