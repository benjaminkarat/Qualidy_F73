public class Isomorphie {
    public static void main(String[] args) {               
        double[][] g1 = new double[][] {
            {1, 1, 1},
            {1, 0, 0},
            {1, 0, 0},
        };
        double[][] g2 = new double[][] {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0},
        };

        System.out.println(isIsomorph(g1, g2));
    }
    private static double[][][] swapGeneratePermutations(int n) {
        // Generate Identity
        double[][] E = Matrix.generateIdentity(n);
        int n_cycles = factorial(n);
        double[][][] permutations = new double[n_cycles][][];
        
        for (int i = 0; i < n_cycles; i++) {
            permutations[i] = E.clone();

            // Cyclic indeces to swap
            int r1_index = i % n;
            int r2_index = (i + 1) % n;
            
            // Swap rows
            double[] tmpRow = E[r1_index];
            E[r1_index] = E[r2_index];
            E[r2_index] = tmpRow;
        }
        return permutations;
    }
    private static boolean isIsomorph(double[][] g1, double[][] g2) {
        if (g1.length != g2.length || g1[0].length != g2[0].length || degreeSum(g1) != degreeSum(g2)) {
            return false;
        } else {
            double[][][] p = swapGeneratePermutations(g1.length);
            for (double[][] permutation : p) {
                double[][] lhs = Matrix.multiply(permutation, g1);
                double[][] rhs = Matrix.multiply(g2, permutation);
                if (isEqualMatrix(lhs, rhs)) return true;
            }
            return false;   
        }
    }
    private static int factorial(int n) {
        int sum = 1;
        for (int i = n; i > 1; i--) sum*=i;
        return sum;
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