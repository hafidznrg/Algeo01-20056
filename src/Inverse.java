class Inverse extends Utils {
  public static void main(String[] args) {
    double[][] mat = {{1,2,5},{4,2,5},{3,9,8}};
    // println("Inverse Cofactor: ");
    // displayMat(cofactorMethods(mat));

    println("Inverse GJ: ");
    displayMat(gaussJordanMethods(mat));
    // displayMat(mat);
  }
  public static double[][] cofactorMethods(double[][] mat) {
    double det = Cofactor.determinan(mat);
    double[][] cofactorMat = Cofactor.cofactorMat(mat);
    double[][] transposed = transpose(cofactorMat);
    double[][] multiplied = multiplyConst(transposed,1/det);

    return multiplied;
  }
  public static double[][] gaussJordanMethods(double[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    double[][] tempMat = new double[rows][2*cols];
    double[][] gaussJordan = new double[rows][2*cols];
    double[][] result = new double[rows][cols];

    for (int i=0;i<tempMat.length;i++) {
      for (int j=0;j<tempMat[0].length;j++) {
        if (j < cols) {
          tempMat[i][j] = mat[i][j];
        } else {
          if ((j-cols == i)) {
            tempMat[i][j] = 1;
          } else {
            tempMat[i][j] = 0;
          }
        }
      }
    }
    // displayMat(tempMat);
    // println();
    gaussJordan = GaussJordan.gaussJordan(tempMat,tempMat.length, tempMat[0].length);
    // displayMat(gaussJordan);
    // println();

    for (int i=0;i<result.length;i++) {
      for (int j=0;j<result[i].length;j++) {
        result[i][j] = gaussJordan[i][j+cols];
      }
    }
    return result;
  }
}