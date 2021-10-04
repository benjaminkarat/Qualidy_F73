package Graph;

import java.util.ArrayList;

public class Node {
    private String val;
    private ArrayList<Node> neighbors;

    public Node(String val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node n) {
        this.neighbors.add(n);
    }

    @Override
    public boolean equals(Object obj) {
        Node o = (Node) obj;
        return this.val.equals(o.val);
    }

    @Override
    public int hashCode() {
        return this.val.hashCode();
    }

    public String getVal() {
        return val;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }
}