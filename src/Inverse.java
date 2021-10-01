class Inverse extends Utils {
  public static double[][] cofactorMethods(double[][] mat) {
    // Mengembalikan matriks balikan dengan menggunakan metode ekspansi kofaktor
    double det = Cofactor.determinan(mat);
    double[][] cofactorMat = Cofactor.cofactorMat(mat);
    double[][] transposed = transpose(cofactorMat);
    double[][] multiplied = multiplyConst(transposed, 1 / det);

    return multiplied;
  }

  public static double[][] gaussJordanMethods(double[][] mat) {
    // Mengembalikan matriks balikan dengan menggunakan metode GaussJordan
    int rows = mat.length;
    int cols = mat[0].length;
    double[][] tempMat = new double[rows][2 * cols];
    double[][] gaussJordan = new double[rows][2 * cols];
    double[][] result = new double[rows][cols];

    for (int i = 0; i < tempMat.length; i++) {
      for (int j = 0; j < tempMat[0].length; j++) {
        if (j < cols) {
          tempMat[i][j] = mat[i][j];
        } else {
          if ((j - cols == i)) {
            tempMat[i][j] = 1;
          } else {
            tempMat[i][j] = 0;
          }
        }
      }
    }
    gaussJordan = GaussJordan.gaussJordan(tempMat);

    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = gaussJordan[i][j + cols];
      }
    }
    return result;
  }

  public static String[] solveSPL(double[][] mat) {
    // Mengembalikan array of string berisi solusi SPL
    int rows = mat.length;
    int cols = mat[0].length;
    double[][] bMat = new double[rows][1];
    String[] result = new String[rows + 1];

    for (int i = 0; i < rows; i++) {
      bMat[i][0] = mat[i][cols - 1];
    }
    double[][] inverse = cofactorMethods(squareMatFromAugmented(mat));
    double[][] multiplied = multiplyMatrix(inverse, bMat);

    result[0] = "Solusi dari SPL tersebut menggunakan metode matriks balikan ialah: ";
    for (int i = 0; i < rows; i++) {
      result[i + 1] = "x" + (i + 1) + " = " + multiplied[i][0];
    }

    return result;
  }
}