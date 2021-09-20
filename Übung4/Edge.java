public class Edge {
    private Node a,b;

    public Edge(Node a, Node b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        return this.a.hashCode() + this.b.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }
}