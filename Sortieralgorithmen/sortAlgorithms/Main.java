package sortAlgorithms;

import javax.swing.SwingUtilities;
import sortAlgorithms.VisualiserWindow.ArrayToSort;

public class Main {

    /**
     * Funktion die mit dem Öffnen des Swing-Fensters ausgeführt wird
     * @throws InterruptedException wenn der Thread gestoppt wird
     */
    public void start() throws InterruptedException {

    // Absteigend sortiertes Array erzeugen
    int size = 20;
    int[] unsorted = createDescendingArray(size);

    // Hier die Klasse mit dem Sortieralgorithmus instanziieren, das führt automatisch die Visualisierung mit aus
    ArrayToSort values1 = new ExampleSort(unsorted);
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main().start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Erstellt ein absteigend sortiertes Array
     * @param size des Arrays
     * @return das generierte Array
     */
    public int[] createDescendingArray(int size) {
        int[] valueList = new int[size];
        for (int i = 0; i < valueList.length; i++) valueList[i] = size - i;
        return valueList;
    }
}