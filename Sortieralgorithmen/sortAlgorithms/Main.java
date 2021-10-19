package sortAlgorithms;

import javax.swing.SwingUtilities;

public class Main {

    public void start() throws InterruptedException {

        // Maximal unsortiertes Array erzeugen
        int size = 50;
        int[] valueList = new int[size];
        for (int i = 0; i < valueList.length; i++) valueList[i] = size - i;

        int TIMESTEP = 5; // Millisekunden zwischen jedem Frame-Update

        /* NUTZBEISPIEL */
        // Erstellt Array Instanz, sortiert das Array und öffnet die Visualisierung
        //ArrayToSortWithBubbleSort values = new ArrayToSortWithBubbleSort(valueList, TIMESTEP);
        //ArrayToSortWithMergeSort values = new ArrayToSortWithMergeSort(valueList, TIMESTEP);
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
}