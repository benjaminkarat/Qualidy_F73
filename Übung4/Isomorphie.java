import java.util.ArrayList;
import java.util.List;

public class Isomorphie {
    public static void main(String[] args) {
        double[][] g1 = new double[][] {
            {1, 1, 1},
            {1, 0, 0},
            {1, 0, 0},
        };
        double[][] g2 = new double[][] {
            {0, 1, 0},
            {1, 1, 1},
            {0, 1, 0},
        };

        System.out.println(isIsomorph(g1, g2));
    }

    /**
     * Liefert ein Array der Länge n zurück mit den Elementen {0,1,...,n-1}
     * @param n Länge des Array
     * @return Array mit den Elementen von 0 bis n-1.
     */
    private static double[] getStandardArray(int n){
        double[] result = new double[n];
        for (int i = 0; i<n; i++){
            result[i] = i;
        }
        return result;
    }

    /**
     * Erstelle eine Permutationsmatrix basierend auf einem Array mit den Zahlen von 0 bis n-1.
     * Dabei wird aus {0,2,1} dann
     * 1 0 0
     * 0 0 1
     * 0 1 0
     * Das Array beschreibt also für jede Zeile den Spaltenindex der 1.
     * @param arr Array mit Spaltenindexen
     * @return Permutationsmatrix
     */
    private static double[][] createPermutationMatrix(double[] arr) {
        double[][] result = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                result[i][j] = arr[i] == j ? 1 : 0;
            }
        }
        return result;
    }

    /**
     * Liefert für eine Array alle Permutationen. Bei einer Liste der Länge n sind es n! viele Permutationen.
     * @param arr Array für das die Permutationen gesucht werden.
     * @return Array von Array. Innere Arrays sind die verschiedenen Permutationen.
     */
    private static double[][] permuteArray(double[] arr) {
        List<List<Double>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), arr);
        return to2dArray(list);
    }

    /**
     * Übersetzt eine verschachtelte Liste von Doubles in ein zweidimensionales Array.
     * @param list verschachtelte Liste
     * @return zweidimensionales Array mit primitiven double
     */
    private static double[][] to2dArray(List<List<Double>> list){
        double[][] array = new double[list.size()][list.get(0).size()];

        int i = 0;
        for (List<Double> nestedList : list) {
            int j = 0;
            for (Double d : nestedList) {
                array[i][j] = d;
                j++;
            }
            i++;
        }
        return array;
    }

    /**
     * Liefert für ein Array alle Permutationen ihrer Elemente. Diese werden in list gespeichert.
     * @param list Rückgabeliste, in der alle Permutationen gespeichert werden.
     * @param resultList Liste, der bereits zugordneten Elemente.
     * @param arr Array von Elementen,
     */
    private static void permuteHelper(List<List<Double>> list, List<Double> resultList, double [] arr){

        // Wenn alle Elemente zugeordnet worden, speichere sie ab.
        if (resultList.size() == arr.length){
            list.add(new ArrayList<>(resultList));
        }
        else {
            for(int i = 0; i < arr.length; i++){

                if(resultList.contains(arr[i]))
                {
                    // Wenn das Element bereits zugeordnet wurde, überspringe den Schritt.
                    continue;
                }
                // Füge das Element der resultList hinzu.
                resultList.add(arr[i]);
                // Ordne die übrigen Elemente zu
                permuteHelper(list, resultList, arr);
                // Entferne das Element aus der result list, um für den nächsten Schleifendurchlauf Platz zu schaffen.
                resultList.remove(resultList.size() - 1);
            }
        }
    }

    /**
     * Erstelle alle nxn Permutationsmatrizen
     * @param n Größe der Matrizen
     * @return Alle Permuationsmatrizen
     */
    private static double[][][] generatePermutations(int n) {
        double[][] permute = permuteArray(getStandardArray(n));
        double[][][] result = new double[permute.length][][];
        for (int i = 0; i < permute.length; i++) {
            result[i] = createPermutationMatrix(permute[i]);
        }
        return result;
    }

    private static boolean isIsomorph(double[][] g1, double[][] g2) {
        if (g1.length != g2.length || g1[0].length != g2[0].length || degreeSum(g1) != degreeSum(g2)) {
            return false;
        } else {
            double[][][] p = generatePermutations(g1.length);
            for (double[][] permutation : p) {
                double[][] lhs = Matrix.multiply(permutation, g1);
                double[][] rhs = Matrix.multiply(g2, permutation);
                if (isEqualMatrix(lhs, rhs)) return true;
            }
            return false;   
        }
    }

    private static boolean isEqualMatrix(double[][] m1, double[][] m2) {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                if (m1[i][j] != m2[i][j]) return false;
            }
        }
        return true;
    }
    private static int degreeSum(double[][] m) {
        int sum = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++)
                sum += m[i][j];
        }
        return sum;
    }
}