public class Matrix {

     // Multipliziert Matrix mit Vektor und gibt das Ergebnis zurück
    public static double[] multiply(double[][] a, double[] x) { // 3x3 * 3x1 -> 3x1
        if (x.length != a[0].length) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }

    // Multipliziert zwei Matrizen und gibt das Ergebnis zurück
    public static double[][] multiply(double[][] A, double[][] B) {
        if (A.length != B[0].length) throw new ArithmeticException("Matrix A braucht gleich viele Reihen wie Matrix B Spalten hat");
        double[][] result = new double[A.length][B[0].length];
        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < B[row].length; col++) {
                result[row][col] = 0; // Initialisieren

                // Multiplizieren
				for(int i = 0; i < B.length; i++ ) {
					result[row][col] += A[row][i] * B[i][col];
				}
            }
        }
        return result;
    }

    // Ergebnis ermitteln, falls vorhanden
    public static double[] solve(double[][] mat, double[] vector) throws IllegalArgumentException {
        if (mat.length < mat[0].length) throw new IllegalArgumentException("Wrong matrix dimensions"); // Mehr Variablen als Gleichungen
        System.out.println("Startmatrix: ");
        printMatrix(mat, vector);

        // Spalte, welche eine Zahl != 0 besitzt
        int tmpColumn = -1;

        // Treppenform erzeugen
        for (int line = 0; line < mat.length; line++) {
            tmpColumn = -1;

            // Schritt 1: Finden einer Spalte mit einem Wert != 0
            for (int col = 0; col < mat[line].length; col++) {
                for (int row = line; row < mat.length; row++) {
                    if (mat[row][col] != 0) {
                        tmpColumn = col;
                        break;
                    }
                }
                if (tmpColumn != -1) break; // Suche abbrechen wenn Wert gefunden wurde
            }

            // Nullzeile bei Linie 'line' entdeckt!
            if (tmpColumn == -1) { 
                for (int row = line; row < mat.length; row++) {
                    if (vector[line] != 0) throw new ArithmeticException("LGS unlösbar"); // Widerspruch?
                }
                break;
            }

            // Schritt 2: Die Zahl matrix[line][tmpColumn] soll != 0 sein, bei Bedarf tauschen
            if (mat[line][tmpColumn] == 0) {
                for (int row = line + 1; row < mat.length; row++) {
                    if (mat[row][tmpColumn] != 0) {
                        swapTwoLines(line, row, mat, vector);
                        System.out.println("Zeilentausch:");
                        printMatrix(mat, vector);
                        break;
                    }
                }
            }

            // Schritt 3: matrix[line][tmpColumn] soll gleich 1 sein.
            if (mat[line][tmpColumn] != 0) {
                divideLine(line, mat[line][tmpColumn], mat, vector); // Zeile normalisieren
            }
            System.out.println("Normalisieren von Zeile " + (line+1) + ":");
            printMatrix(mat, vector);

            // Schritt 4: Alle Zahlen unter der neuen 1 sollen 0 sein
            for (int row = line + 1; row < mat.length; row++) {
                // Zeile mit der 1er Zeile zu 0 verrechnen
                removeRowLeadingNumber(mat[row][tmpColumn], line, row, mat, vector);
            }
            System.out.println("Nullen unter Zeile " + (line + 1) + " erzeugen: ");
            printMatrix(mat, vector);
        }
        System.out.println("Treppenform:");
        printMatrix(mat, vector);

        // Schritt 6: Normalform - Alle Zahlen oberhalb der 1 sollen ebenfalls 0 sein
        for (int col = mat[0].length - 1; col > 0; col--) {
            for (int row = col; row > 0; row--) {			
                // Zeile mit der 1er Zeile zu 0 verrechnen  
                removeRowLeadingNumber(mat[row - 1][col], col, row - 1, mat, vector);
            }
        }
        System.out.println("Normalform:");
        printMatrix(mat, vector);
        return vector; // Ergebnis ausgeben
    }

    // Gibt Einheitsmatrix mit size x size Reihen und Spalten zurück
    public static double[][] generateIdentity(int size) {
        double[][] identity = new double[size][size];
        for (int row = 0; row < identity.length; row++) {
            for (int col = 0; col < identity.length; col++) {
                identity[row][col] = (row == col) ? 1 : 0;
            }
        }
        return identity;
    }

    // Gibt Inverse einer Matrix zurück, falls diese existiert
    public static double[][] invert(double[][] mat) {
        if (mat.length != mat[0].length) throw new IllegalArgumentException("Has to be quadratic"); // Mehr Variablen als Gleichungen
        double[][] inverse = generateIdentity(mat.length);
        // Spalte, welche eine Zahl != 0 besitzt
        int tmpColumn = -1;

        System.out.println("Ausgangsmatrix: ");
        printMatrix(mat);

        // Treppenform erzeugen
        for (int line = 0; line < mat.length; line++) {
            tmpColumn = -1;

            // Schritt 1: Finden einer Spalte mit einem Wert != 0
            for (int col = 0; col < mat[line].length; col++) {
                for (int row = line; row < mat.length; row++) {
                    if (mat[row][col] != 0) {
                        tmpColumn = col;
                        break;
                    }
                }
                if (tmpColumn != -1) break; // Suche abbrechen wenn Wert gefunden wurde
            }

            // Nullzeile bei Linie 'line' entdeckt!
            if (tmpColumn == -1) throw new ArithmeticException("det = 0");

            // Schritt 2: Die Zahl matrix[line][tmpColumn] soll != 0 sein, bei Bedarf tauschen
            if (mat[line][tmpColumn] == 0) {
                for (int row = line + 1; row < mat.length; row++) {
                    if (mat[row][tmpColumn] != 0) {
                        int rowOne = line;
                        int rowTwo = row;

                        double[] tmpLineMat = mat[rowOne];
                        double[] tmpLineInverse = inverse[rowOne];

                        mat[rowOne] = mat[rowTwo];
                        inverse[rowOne] = inverse[rowTwo];

                        mat[rowTwo] = tmpLineMat;
                        inverse[rowTwo] = tmpLineInverse;
                        
                        System.out.println("Reihentausch! ");
                        printMatrix(mat);
                        printMatrix(inverse);
                        break;
                    }
                }
            }

            // Schritt 3: matrix[line][tmpColumn] soll gleich 1 sein.
            if (mat[line][tmpColumn] != 0) {
                double divisor = mat[line][tmpColumn];
                for (int col = 0; col < mat[line].length; col++) {
                    inverse[line][col] /= divisor;
                    mat[line][col] /= divisor;
                }

                System.out.println("Normalisieren " + (line+1));
                printMatrix(mat);
                printMatrix(inverse);
            }

            // Schritt 4: Alle Zahlen unter der neuen 1 sollen 0 sein
            for (int row = line + 1; row < mat.length; row++) {
                // Zeile mit der 1er Zeile zu 0 verrechnen
                removeRowLeadingNumberInverse(mat[row][tmpColumn], line, row, mat, inverse);
                System.out.println("0 unter neuer 1 erzeugen: ");
                printMatrix(mat);
                printMatrix(inverse);
            }
        }

        // Schritt 5: Normalform - Alle Zahlen oberhalb der 1 sollen ebenfalls 0 sein
        for (int col = mat[0].length - 1; col > 0; col--) {
            for (int row = col; row > 0; row--) {			
                // Zeile mit der 1er Zeile zu 0 verrechnen  
                removeRowLeadingNumberInverse(mat[row - 1][col], col, row - 1, mat, inverse);
                System.out.println("0 oberhalb neuer 1 erzeugen: ");
                printMatrix(mat);
                printMatrix(inverse);
            }
        }

        return inverse; // Ergebnis ausgeben
    }

    // Zwei Zeilen tauschen
    public static void swapTwoLines(int rowOne, int rowTwo, double[][] mat, double[] vector) {
        double[] tmpLine;
        double tmpVar;

        tmpLine = mat[rowOne];
        tmpVar = vector[rowOne];

        mat[rowOne] = mat[rowTwo];
        vector[rowOne] = vector[rowTwo];

        mat[rowTwo] = tmpLine;
        vector[rowTwo] = tmpVar;
    }

    // Zeile durch 'div' dividieren
    private static void divideLine(int row, double div, double[][] matrix, double[] vector) {
        for (int col = 0; col < matrix[row].length; col++) {
            matrix[row][col] /= div;
        }
        vector[row] /= div;
    }

    // Nullen erzeugen
    private static void removeRowLeadingNumber(double factor, int rowRoot, int row, double[][] matrix, double[] vector) {
        for (int col = 0; col < matrix[row].length; col++) {
            matrix[row][col] -=  factor * matrix[rowRoot][col];
        }
        vector[row] -= factor * vector[rowRoot];
    }

    // Nullen erzeugen
    private static void removeRowLeadingNumberInverse(double factor, int rowRoot, int row, double[][] matrix, double[][] inverse) {
        for (int col = 0; col < matrix[row].length; col++) {
            matrix[row][col] -=  factor * matrix[rowRoot][col];
            inverse[row][col] -= factor * inverse[rowRoot][col];
        }
    }

    // Output
    public static void printMatrix(double[][] mat, double[] vec) {
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col <= mat[row].length - 1; col++) {
                System.out.printf("%7.2f", mat[row][col]);
            }
            System.out.printf("%7.2f", vec[row]);
            System.out.println();
        }
        System.out.println();
    }

    // Output
    public static void printMatrix(double[][] mat) {
        for (int row = 0; row < mat.length; row++) {
          for (int col = 0; col < mat[row].length; col++) {
            System.out.printf("%7.2f", mat[row][col]);
          }
          System.out.println();
        }
        System.out.println();
    }

    // Output
    public static void printMatrix(double[] mat) {
        for (int col = 0; col < mat.length; col++) {
            System.out.printf("%7.2f", mat[col]);
        }
        System.out.println();
    }
}