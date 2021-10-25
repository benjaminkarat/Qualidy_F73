package sortAlgorithms.VisualiserWindow;

import java.util.Arrays;
import java.util.Random;

public abstract class ArrayToSort {
    /** Dieses array nicht direkt beim Sortieren nutzen!!! >:( */
    public int[] values;
    private VisualiserWindow visualiser;
    public Counter counter;

    /** Hier Sortieralgorithmus schreiben */
    public abstract void sort(); // sort() muss implementiert werden! Nutze die Klassenmethoden zum modifizieren des Arrays

    /**
     * Erstellt eine ArrayToSort-Instanz mit n zufälligen Werten
     * @param n zufällige Werte
     */
    public ArrayToSort(int n) {
        this.values = new Random().ints(n, 0, n).toArray(); // Initialisieren auf n zufällige Zahlen aus [0, n]
        this.visualiser = new VisualiserWindow(this);
        this.counter = new Counter();
    }

    /**
     * Erstellt eine ArrayToSort-Instanz mit Werten des values-Array
     * @param values der Liste
     */
    public ArrayToSort(int[] values) {
        this.values = values;
        this.visualiser = new VisualiserWindow(this);
        this.counter = new Counter();
    }

    /**
     * Erstellt ArrayToSort-Instanz aus Ausschnitt einer bereits vorhandenen Instanz
     * @param parent ArrayToSort-Objekt aus welchem die Werte übernommen werden sollen
     * @param fromInclusive Index ab welchem Werte übernommen werden (inklusiv)
     * @param toExclusive Index bis welchem Werte übernommen werden (exklusiv)
     */
    public ArrayToSort(ArrayToSort parent, int fromInclusive, int toExclusive) {
        this.values = Arrays.copyOfRange(parent.values, fromInclusive, toExclusive);
        this.counter = parent.counter;
        this.visualiser = parent.visualiser;
    }

    /**
     * Abkürzung zur Ausgabe der Arraygröße
     * @return Größe des Arrays
     */
    public int size() {return this.values.length;}

    /////////////////////// Methoden zur Manipulation des Arrays //////////////////////////////
    
    /**
     * Gibt den Wert bei index zurück
     * @param index des Werts
     * @return Wert bei index
     */
    public int getValue(int index) {
        this.counter.add(1);
        return this.values[index];
    }

    /**
     * Tauscht die Werte bei den Indizes a und b
     * @param a erster Index
     * @param b zweiter Index
     */
    public void swapValue(int a, int b) {
        this.counter.add(4);

        int tmp = this.values[a];
        this.values[a] = this.values[b];
        this.values[b] = tmp;
        this.visualiser.update();
    }

    /**
     * Ersetzt den Wert bei index mit einem neuem Wert newValue.
     * @param index des zu ersetzenden Werts
     * @param newValue = neuerWert
     */
    public void setValue(int index, int newValue) {
        this.counter.add(1);
        this.values[index] = newValue;
        this.visualiser.update();
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gibt das Array formatiert in der Konsole aus
     */
    public void printArray() {
        System.out.println();
        System.out.print("[ ");
        for(int value : this.values) System.out.print(value + " ");
        System.out.print("]");
    }
}
