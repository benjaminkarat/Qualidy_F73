

public class Main {
    public static void main(String[] args) {
        double[][] mat = { { 3, 0, 1}, 
                           { 1, -4, 3},
                           { -6, 3, 2}
                         };

        double[] vector = {-2, 0, 5};

        double[] lsg = Matrix.solve(mat, vector); // Lösung mithilfe des Gauß-Jordan-Algorithmus
        //double[] lsg1 = Matrix.multiply(Matrix.invert(mat), vector); // Lösung mithilfe der Inversen bestimen
        //double[][] lsg2 = Matrix.multiply(mat, mat); // mat quadrieren
        //Matrix.printMatrix(lsg1);
        Matrix.printMatrix(lsg);
    }
}
