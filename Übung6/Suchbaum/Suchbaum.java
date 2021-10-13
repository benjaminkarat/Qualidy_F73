import java.util.Collection;

public class Suchbaum<E extends Comparable> {
    private E value;
    private Suchbaum<E> left;
    private Suchbaum<E> right;

    // Constructor leerer Baum
    public Suchbaum(E value) {
        this.value = value;
    }

    // Erstellen eines Baumes mit Werten aus einem Array
    public Suchbaum(E[] arr) {

    }

    // Erstellen eines Baumes mit Werten aus einer Collection wie einer ArrayList, LinkedList etc.
    public Suchbaum(Collection<E> c) {
        
    }

    public Suchbaum<E> add(E e) {
        // Implement me!
        return null;
    }

    public E getMin() {
        // Implement me!
        return null;
    }

    public E getMax() {
        // Implement me!
        return null;
    }

    public boolean contains(E val) {
        // Implement me!
        return false;
    }
}
