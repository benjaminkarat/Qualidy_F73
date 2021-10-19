package sortAlgorithms.VisualiserWindow;
import java.util.Random;
public abstract class ArrayToSort {
    public int[] values;
    public VisualiserWindow visualiser;
    public int ACCESS_COUNTER;

    private static final Random r = new Random();

    public abstract void sort(); // sort muss implementiert werden! Nutze getValue, swapValue und updateValue zum modifizieren des Arrays

    public ArrayToSort(int n, int TIMESTEP) {
        // Initialisieren auf n zufällige Zahlen aus [0, n]
        this.values = r.ints(n, 0, n).toArray();
        this.visualiser = new VisualiserWindow(this, TIMESTEP);
        this.ACCESS_COUNTER = 0;
    }

    public ArrayToSort(int[] values, int TIMESTEP) {
        this.values = values;
        this.visualiser = new VisualiserWindow(this, TIMESTEP);
        this.ACCESS_COUNTER = 0;
    }

    public int size() {return this.values.length;}
    public int getValue(int index) {
        this.ACCESS_COUNTER++;
        return this.values[index];
    }

    public void swapValue(int a, int b) {
        int tmp = this.values[a];
        this.values[a] = this.values[b];
        this.values[b] = tmp;
        this.visualiser.update();
    }

    public void updateValue(int index, int newValue) {
        this.ACCESS_COUNTER++;
        this.values[index] = newValue;
        visualiser.update();
    }

    public void randomize() {
        this.values = r.ints(this.values.length, 0, this.values.length).toArray();
    }

    public void printArray() {
        System.out.println();
        System.out.print("[ ");
        for(int value : this.values) System.out.print(value + " ");
        System.out.print("]");
    }

    public int getNumberOfOperations() {
        if (this.ACCESS_COUNTER != 0) {
            return this.ACCESS_COUNTER;
        } else return -1;
    }
}
