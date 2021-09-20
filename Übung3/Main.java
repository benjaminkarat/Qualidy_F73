import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        double[][] mat = { { 3, 0, 1}, 
                           { 1, -4, 3},
                           { -6, 3, 2}
                         };

        double[] vector = {-2, 0, 5};

        double[] lsg = Matrix.solve(mat, vector); // Lösung mithilfe des Gauß-Jordan-Algorithmus
        double[] lsg1 = Matrix.multiply(Matrix.invert(mat), vector); // Lösung mithilfe der Inversen bestimen
        double[][] lsg2 = Matrix.multiply(mat, mat); // mat quadrieren
        //Matrix.printMatrix(lsg);

        ArrayList<String[]> P = new ArrayList<>();
        int i = 512; // 9 digit binary number
        while (i >= 1) {
            String[] b = Integer.toBinaryString(i).split("");
            P.add(b);
            i /= 2;
        }
    }
}
