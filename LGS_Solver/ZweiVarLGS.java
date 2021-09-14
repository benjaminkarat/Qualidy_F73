
public class ZweiVarLGS {
    public static double[] solve2VarMatrix(double[][] mat) {
    double x = -1;
    double y = -1;
    
    mat = divideRow(mat, 0, mat[0][0]); // 1. Schritt
    System.out.println("Normalisieren");
    printArray2D(mat);
    
    double multiplier = -mat[1][0];
    
    System.out.println("Stufenform");
    // Stufenform 0 unten links
    for (int i = 0; i < mat[1].length; i++) {
      mat[1][i] += mat[0][i] * multiplier;
    }
    printArray2D(mat);
    
    System.out.println("Y normalisieren");
    double divider = mat[1][1];
    // 2. Variable auf '1' bringen
    for (int i = 0; i < mat[1].length; i++) {
      mat[1][i] /= divider;
    }
    printArray2D(mat);
    
    
    // 0 auf Reihe 1 erzeugen
    System.out.println("0 auf Zeile 1 erzeugen");
    multiplier = mat[0][1];
    for (int i = 0; i < mat[0].length; i++) {
    	mat[0][i] -= (multiplier * mat[1][i]);
    }
    printArray2D(mat);
    
    // Y speichern
    if (mat[1][0] == 0 && mat[1][1] == 1) {
      y = mat[1][2];
    } else {
        throw new ArithmeticException("Y nicht definiert");
    }
    
    // X speichern
    if (mat[0][0] == 1 && mat[0][1] == 0) {
      x = mat[0][2];
    } else {
      throw new ArithmeticException("X nicht definiert");
    }
    
    // Ausgabe
    return new double[] {x, y};
  }
  
  // Hilfsfunktion Matrix ausgeben
  public static void printArray2D(double mat[][]) {
    for (int i = 0; i < mat.length; i++, System.out.println())
    for (int j = 0; j <= mat[i].length - 1; j++)
      System.out.print(mat[i][j] + " ");
    System.out.println();
  }
  
  private static double[][] divideRow(double[][] mat, int rowIndex, double divider) {
    for (int i = 0; i < mat[rowIndex].length; i++) {
      mat[rowIndex][i] /= divider;
    }
    return mat;
  }
}
