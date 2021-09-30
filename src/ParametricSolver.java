class ParametricSolver extends Utils {
  public static void main(String[] args) {
    // double[][] mat = {{1, 2, 0, 0, 5},{0, 0, 1, 0, 9},{0, 0, 0, 1, 3}};
    double[][] mat = {{1, 0, 5, 0, 9, 1},{0, 1, 7, 0, 4, -9},{0, 0, 0, 1, 1, 3}};
    displayResults(solve(mat,false));
  }
  
  private static int idxEselon1(double[][] mat, int colMat) {
    int i, idx;
    boolean onlyOnce=true;

    idx = -1;
    for (i=0;i<mat.length;i++) {
      if (mat[i][colMat] != 0 && mat[i][colMat] != 1)
        return -1;
      if (mat[i][colMat] == 1){
        if (onlyOnce) {
          idx = i;
          onlyOnce = false;
        } else {
          return -1;
        }
      }
    }
    return idx;
  }

  private static char makeVar(int idx, int[] arrIdx, int count) {
    char var = 'a';
    for (int i = 0; i < count; i++) {
      if (arrIdx[i] == idx) {
        var = (char) (((int) var) + i);
        return var;
      }
    }
    return 'X';
  }

  public static String[] solve(double[][] mat, boolean fromGauss) {
    String[] result = new String[mat[0].length-1];
    int rows = mat.length;
    int cols = mat[0].length;

    if (fromGauss) {
      mat = GaussJordan.gaussJordan(mat);
    }
    
    int i,count=0;
    int[] idxFreeVar = new int[cols-1]; 
    for (i=0;i<cols-1;i++) {
      if (idxEselon1(mat,i) == -1) {
        idxFreeVar[count] = i;
        count++;
      }
    }

    for (i=0;i<cols-1;i++) {
      String res = "";
      int idxRowEselon = idxEselon1(mat,i);
      if (idxRowEselon != -1) {
        res += ( "x" + (i+1) + " = " + mat[idxRowEselon][cols-1] );
        for (int j=(i+1);j<cols-1;j++){
          double xVal = mat[idxRowEselon][j];
          if (!isZero(xVal)) {
            res += ( " + (" + (-xVal) + ")" + makeVar(j,idxFreeVar,count) );
          }
        }
      } else {
        res = "x" + (i+1) + " = " + makeVar(i,idxFreeVar,count);
      }
      result[i] = res;
    } 
    return result;
  }
}
