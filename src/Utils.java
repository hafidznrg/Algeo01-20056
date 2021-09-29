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
    double epsilon = 0.00000001;
    return ((x < epsilon) && (x > - epsilon));
  }

  protected static double[][] squareMatFromAugmented(double[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    double[][] newMat = new double[rows][rows];

    for (int i=0;i<rows;i++) {
      for (int j=0;j<rows;j++) {
        newMat[i][j] = mat[i][j];
      }
    }
    return newMat;
  }

  // TESTER
  public static void main(String[] args) {
    println("Hello " + 5 + " mabar");
    println("Test %d", 5);
    double[][] mat = new double[2][3];

    mat[0][0] = 1;
    mat[0][1] = 2;
    mat[0][2] = 3;
    mat[1][0] = 4;
    mat[1][1] = 5;
    mat[1][2] = 6;

    double[][] transposed = transpose(mat);

    println("Ori Mat");
    displayMat(mat);
    println("transposed mat");
    displayMat(transposed);

    if (isSquare(mat)) {
      println("Mat is square");
    }
    int input = choose(2, 5);

    println("pilihan: " + input);
  }
}

// print (output without newline)
// printLn (output with newline)
// displayMat (display matrix)
// choose (ask input integer from user in bound of min and max)
// isSquare (check whether matrix is nxn)
// scan (ask for input user, make scanner object only in utils class)
// So on (later)