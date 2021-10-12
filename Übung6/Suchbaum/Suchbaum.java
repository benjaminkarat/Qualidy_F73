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
    public Suchbaum(E[] arr, E val) {
        this.value = val;
        for (E e : arr) {
            this.add(e);
        }
    }

    // Erstellen eines Baumes mit Werten aus einer Collection wie einer ArrayList, LinkedList etc.
    public Suchbaum(Collection<E> c, E val) {
        this.value = val;
        for (E e : c) {
            this.add(e);
        }
    }

    public Suchbaum<E> add(E e) {
        if (this.value.compareTo(e) > 0) { // Wert geringerwertig als der Wert des aktuellen Knotens
            if (this.left == null) {
                this.left = new Suchbaum<E>(e);
            } else {
                this.left.add(e); // Falls bereits links ein Kind vorhanden ist, rufe die selbe Funktion in diesem Kindknoten auf
            }
        } else if (this.value.compareTo(e) <= 0) { // Wir erlauben hier durch das '<=' auch doppelte Werte
            if (this.right == null) {
                this.right = new Suchbaum<E>(e);
            } else {
                this.right.add(e);
            }
        }
        return this;
    }

    public E getMin() {
        return (this.left == null) ? this.value : this.left.getMin();
    }

    public E getMax() {
        return (this.right == null) ? this.value : this.right.getMax();
    }

    public boolean contains(E val) {
        if (this.value == val) {
            return true;
        } else {
            if (this.value.compareTo(val) > 0 && this.left != null) { // val ist kleiner und links existiert ein Kindknoten
                return this.left.contains(val); // Funktion links aufrufen
            } else if (this.value.compareTo(val) <= 0 && this.right != null) { // val ist größer und rechts existiert ein Kindknoten
                return this.right.contains(val);
            } else { // val ist kleiner und links ist kein Kindknoten mehr || val ist größer und rechts ist kein Kindknoten mehr
                return false;
            }
        }
    }
}
