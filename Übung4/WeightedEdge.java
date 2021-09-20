public class WeightedEdge extends Edge {
    int weight;

    public WeightedEdge(Node a, Node b, int weight) {
        super(a, b);
        this.weight = weight;
    }
}