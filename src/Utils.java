import java.io.*;
import java.util.*;

class Utils {
  // Deklarasi scanner
  static Scanner sc = new Scanner(System.in);

  protected static void print(String msg) {
    // Menulis msg tanpa new line
    System.out.print(msg);
  }

  protected static void println() {
    // Menulis new line
    System.out.println();
  }

  protected static void println(String msg) {
    // Menulis msg dengan new line
    System.out.println(msg);
  }

  protected static void println(double msg) {
    // Menulis msg bertipe double denangan new line
    System.out.println(msg);
  }

  protected static void println(String format, Object... args) {
    // Menulis msg dengan beberapa format argumen
    System.out.printf(format, args);
  }

  // Menampilkan matriks
  protected static void displayMat(double[][] mat) {
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        print(mat[i][j] + " ");
      }
      println();
    }
  }

  // Menampilkan hasil berupa array of String
  protected static void displayResults(String[] result) {
    for (int i = 0; i < result.length; i++) {
      println(result[i]);
    }
  }

  // Fungsi untuk meminta pilihan masukan dari pengguna
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

  // Fungsi untuk melakukan transpose matriks
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

  // Fungsi untuk perkalian suatu matriks dengan konstanta
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

  // Fungsi untuk perkalian 2 matriks
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

  // Fungsi untuk mengecek nilai 0 dengan ketelitian hingga 10^-12
  protected static boolean isZero(double x) {
    double epsilon = 1.0e-12;
    return ((x < epsilon) && (x > -epsilon));
  }

  // Fungsi yang mengembalikan matriks persegi dari suatu matriks augmented
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

  // Fungsi untuk mengecek apakah semua elemen di dalam array bernilai nol
  protected static boolean isRowAllZero(double[] matRow) {
    for (int i = 0; i < matRow.length; i++) {
      if (!isZero(matRow[i]))
        return false;
    }
    return true;
  }

  // Fungsi untuk memotong matriks yang memiliki beberapa baris yang semua
  // elemennya bernilai nol
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

  // Fungsi untuk mengecek apakah semua elemen di dalam array bernilai nol kecuali
  // elemen terakhir
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

  // Fungsi untuk menentukan apakah suatu matriks tidak memiliki solusi
  protected static boolean isNoSolution(double[][] mat) {
    for (int i = 0; i < mat.length; i++) {
      if (isRowAllZeroExceptLastElmt(mat[i])) {
        return true;
      }
    }
    return false;
  }
}