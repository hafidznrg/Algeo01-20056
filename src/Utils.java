import java.io.*;
import java.util.*;

class Utils {
  static Scanner sc = new Scanner(System.in);

  protected static void print(String msg) {
    System.out.print(msg);
  }

  protected static void println() {
    System.out.println();
  }

  protected static void println(String msg) {
    System.out.println(msg);
  }

  protected static void println(double msg) {
    System.out.println(msg);
  }

  protected static void println(String format, Object... args) {
    System.out.printf(format, args);
  }

  protected static void displayMat(double[][] mat) {
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        print(mat[i][j] + " ");
      }
      println();
    }
  }

  protected static void displayResults(String[] result) {
    for (int i = 0; i < result.length; i++) {
      println(result[i]);
    }
  }

  protected static int choose(int min, int max) {
    int input;

    println("Pilih antara " + min + " dan " + max);
    while (true) {
      print("> ");
      input = sc.nextInt();
      if (input >= min && input <= max) {
        break;
      } else {
        println("Pilihan invalid. Silakan input kembali!");
      }
    }
    return input;
  }

  protected static boolean isSquare(double[][] mat) {
    // Prekondisi mat terdefinisi / tidak kosong dan mat berbentuk kotak

    return (mat.length == mat[0].length);
  }

  protected static double[][] transpose(double[][] mat) {
    int i, j;
    double[][] transposed = new double[mat[0].length][mat.length];

    for (i = 0; i < transposed.length; i++) {
      for (j = 0; j < transposed[i].length; j++) {
        transposed[i][j] = mat[j][i];
      }
    }

    return transposed;
  }

  protected static double[][] multiplyConst(double[][] mat, double k) {
    int rows = mat.length;
    int cols = mat.length;
    double[][] newMat = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        newMat[i][j] = k * mat[i][j];
      }
    }

    return newMat;
  }

  protected static double[][] multiplyMatrix(double[][] mat1, double[][] mat2) {
    // Prekondisi kolom mat1 = baris mat2
    int rows1 = mat1.length;
    int cols1 = mat1[0].length;
    int rows2 = mat2.length;
    int cols2 = mat2[0].length;
    double[][] newMat = new double[rows1][cols2];

    for (int i = 0; i < newMat.length; i++) {
      for (int j = 0; j < newMat[0].length; j++) {
        newMat[i][j] = 0;
        for (int k = 0; k < cols1; k++) {
          newMat[i][j] += mat1[i][k] * mat2[k][j];
        }
      }
    }
    return newMat;
  }

  protected static boolean isZero(double x) {
    double epsilon = 1.0e-12;
    return ((x < epsilon) && (x > -epsilon));
  }

  protected static double[][] squareMatFromAugmented(double[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    double[][] newMat = new double[rows][rows];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < rows; j++) {
        newMat[i][j] = mat[i][j];
      }
    }
    return newMat;
  }

  protected static boolean isRowAllZero(double[] matRow) {
    for (int i = 0; i < matRow.length; i++) {
      if (!isZero(matRow[i]))
        return false;
    }
    return true;
  }

  protected static double[][] createMatEff(double[][] oriMat) {
    int countRowAllZero = 0;
    for (int i = 0; i < oriMat.length; i++) {
      if (isRowAllZero(oriMat[i])) {
        countRowAllZero++;
      }
    }
    if (countRowAllZero == 0) {
      return oriMat;
    } else {
      int rowEff = oriMat.length - countRowAllZero;
      int colEff = oriMat[0].length;
      double[][] newMat = new double[rowEff][colEff];

      for (int i = 0; i < rowEff; i++) {
        for (int j = 0; j < colEff; j++) {
          newMat[i][j] = oriMat[i][j];
        }
      }
      return newMat;
    }
  }

  protected static boolean isRowAllZeroExceptLastElmt(double[] matRow) {
    for (int i = 0; i < matRow.length - 1; i++) {
      if (!isZero(matRow[i])) {
        return false;
      }
    }
    if (!isZero(matRow[matRow.length - 1])) {
      return true;
    } else {
      return false;
    }
  }

  protected static boolean isNoSolution(double[][] mat) {
    for (int i = 0; i < mat.length; i++) {
      if (isRowAllZeroExceptLastElmt(mat[i])) {
        return true;
      }
    }
    return false;
  }
}