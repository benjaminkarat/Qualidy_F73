package Graph;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    HashSet<Node> nodes;
    HashSet<Edge> edges;

    // Create Graph with adjacency matrix
    public Graph (int[][] adj) {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        
        // Firstly create all Nodes
        for(int i = 0; i < adj.length; i++) {
            this.addNode(new Node(Integer.toString(i)));
        }
        
        // Scan for 1 in adj and add corresponding edge
        for(int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                if (adj[i][j] == 1) {
                    Node a = new Node(Integer.toString(i));
                    Node b = new Node(Integer.toString(j));
                    this.addEdge(a, b);
                }
            }
        }
    }

    public Graph () {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    };

    public boolean isConnected() {
        Node startingNode = (Node) this.nodes.toArray()[0]; // Da der Startknoten keine Rolle spielt, wird der erste Knoten der Liste gewählt
        // 1. Map mit Knoten -> true/false (besucht/nicht besucht) deklarieren und initialisieren
        HashMap<Node, Boolean> visitedNodes = new HashMap<>();
        for (Node n : nodes) {
            visitedNodes.put(n, false);
        }

        // 2. Rekursiv Graph durchforsten
        searchGraph(visitedNodes, startingNode);
        
        // 3. Prüfen ob alle Knoten besucht wurden
        return !visitedNodes.containsValue(false); 
    }

    public void searchGraph(HashMap<Node, Boolean> visitedNodes, Node startingNode) {
        visitedNodes.put(startingNode, true);
        for (Node neighbor : startingNode.getNeighbors()) { // Call function on all unvisited neighbors
            if (!visitedNodes.get(neighbor)) searchGraph(visitedNodes, neighbor);
        }
    }

    public boolean hasEulerTour() {
        if (isConnected()) {
            for (Node n : this.nodes) { // Prüfe für jeden Knoten ob Anzahl Nachbarn gerade ist
                if (n.getNeighbors().size() % 2 != 0) return false;
            }
            return true; // Alle Knoten sind gerade -> Eulertour vorhanden
        } else {
            return false;
        }
    }

    public boolean hasEulerPath() {
        if (isConnected()) {
            if (hasEulerTour()) return true; // Ein Graph mit Eulertour hat auch einen Eulerweg
            
            int nodesWithOddDegree = 0;
            for (Node node : this.nodes) {
                if (node.getNeighbors().size() % 2 != 0) nodesWithOddDegree++;
            }
            return nodesWithOddDegree == 2;
        } else {
            return false;
        }
    }

    public void addNode(Node n) {
        this.nodes.add(n);
    }

    public void addEdge(Node a, Node b) {
        a.addNeighbor(b);
        b.addNeighbor(a);
        this.edges.add(new Edge(a, b));
    }
}