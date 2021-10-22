package sortAlgorithms.VisualiserWindow;

public class Counter {
    private int count = 0;

    public int add(int n) {
        this.count += n;
        return this.count;
    }

    public int getCount() {
        return this.count;
    }

    public Counter() {

    }

    public Counter(int start) {
        this.count = start;
    }
}
