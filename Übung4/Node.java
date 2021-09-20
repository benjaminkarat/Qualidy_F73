public class Node {
    private String val;
    
    public Node(String val) {
        this.val = val;
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
}