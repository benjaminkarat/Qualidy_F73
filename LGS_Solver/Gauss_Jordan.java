/**
 * Benjamin Karatschinez, Qualidy GmbH
 * 
 * Vereinfachter Lösungsalgorithmus für Lineare Gleichungssysteme variablen Grades
 * mithilfe des Gauß-Jordan Algorithmus
 * 
 * Vereinfachte Version basierend auf dem Code von Sebastian Peuser:
 * https://wiki.freitagsrunde.org/Javakurs/%C3%9Cbungsaufgaben/Gau%C3%9F-Algorithmus/Musterloesung
 */

public class Gauss_Jordan {
	    public static void main(String[] args) {
		// Ax = b
		double[][] mat = { { 2, 2, 3, 2 }, 
                           { 0, 2, 0, 1 },
						   { 4, -3, 0, 1 },
						   { 6, 1, -6, -5 },};

		double[] vector = {-2, 0, -7, 6};
		double[] result = solve(mat, vector);
	}

	private static double[] solve(double[][] mat, double[] vector) throws IllegalArgumentException {

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

	// Zwei Zeilen tauschen
	private static void swapTwoLines(int rowOne, int rowTwo, double[][] mat, double[] vector) {
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

	// Output
	public static void printMatrix(double[][] mat, double[] vec) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j <= mat[i].length - 1; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.print("| " + vec[i]);
			System.out.println();
		}
		System.out.println();
	}
}