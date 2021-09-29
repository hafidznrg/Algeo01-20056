import java.io.*;
import java.util.*;

class FileReadWrite extends Utils {
  public static int[] calcRowsCols(String fileName) {
    FileReader fr = null;
    try {
      fr = new FileReader(fileName);
    } catch (FileNotFoundException fe) {
      println("File tidak ditemukan");
    }

    int rows = 0, cols = 0;
    String row = "";
    Scanner rowScanner = new Scanner(fr);
    while (rowScanner.hasNextLine()) {
      rows++;
      row = rowScanner.nextLine();
    }
    Scanner colScanner = new Scanner(row);
    while (colScanner.hasNextDouble()) {
      cols++;
      colScanner.nextDouble();
    }
    rowScanner.close();
    colScanner.close();

    int[] rowsCols = new int[2];
    rowsCols[0] = rows;
    rowsCols[1] = cols;

    return rowsCols;
  }

  public static double[][] readFile(String fileName, int rows, int cols) {
    double[][] mat = new double[rows][cols];

    println("Mencoba membaca file: " + fileName);

    FileReader fr = null;
    try {
      fr = new FileReader(fileName);
    } catch (FileNotFoundException fe) {
      println("File tidak ditemukan");
    }
    Scanner rowScanner2 = new Scanner(fr);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        double input = rowScanner2.nextDouble();
        mat[i][j] = input;
      }
    }
    rowScanner2.close();
    return mat;
  }

  public static boolean writeFile(String path, double[][] mat) {
    try {
      FileWriter myWriter = new FileWriter(path);
      for (int i = 0; i < mat.length; i++) {
        for (int j = 0; j < mat[i].length; j++) {
          myWriter.write(Double.toString(mat[i][j]) + " ");
        }
        myWriter.write("\n");
      }
      // Add End Of File (new line)
      myWriter.write("\n");
      myWriter.close();
      println("Berhasil menuliskan pada " + path);
      return true;
    } catch (IOException e) {
      println("Terjadi error.");
      return false;
    }
  }

  public static boolean writeFileSPL(String path, String[] res) {
    try {
      FileWriter myWriter = new FileWriter(path);
      for (int i = 0; i < res.length; i++) {
        myWriter.write(res[i] + "\n");
      }
      // Add End Of File (new line)
      myWriter.close();
      println("Berhasil menuliskan pada " + path);
      return true;
    } catch (IOException e) {
      println("Terjadi error.");
      return false;
    }
  }

  public static boolean writeFileDeterminan(String path, double[][] mat, double det) {
    try {
      FileWriter myWriter = new FileWriter(path);
      myWriter.write("Determinan dari matriks :\n");
      for (int i = 0; i < mat.length; i++) {
        for (int j = 0; j < mat[i].length; j++) {
          myWriter.write(Double.toString(mat[i][j]) + " ");
        }
        myWriter.write("\n");
      }
      // Add End Of File (new line)
      myWriter.write("\nadalah ");
      myWriter.write(Double.toString(det));
      myWriter.close();
      println("Berhasil menuliskan pada " + path);
      return true;
    } catch (IOException e) {
      println("Terjadi error.");
      return false;
    }
  }

  public static boolean writeFileInverse(String path, double[][] mat, double[][] inv) {
    try {
      FileWriter myWriter = new FileWriter(path);
      myWriter.write("Inverse dari matriks :\n");
      for (int i = 0; i < mat.length; i++) {
        for (int j = 0; j < mat[i].length; j++) {
          myWriter.write(Double.toString(mat[i][j]) + " ");
        }
        myWriter.write("\n");
      }
      // Add End Of File (new line)
      myWriter.write("adalah ");
      for (int i = 0; i < inv.length; i++) {
        for (int j = 0; j < inv[i].length; j++) {
          myWriter.write(Double.toString(inv[i][j]) + " ");
        }
        myWriter.write("\n");
      }
      myWriter.close();
      println("Berhasil menuliskan pada " + path);
      return true;
    } catch (IOException e) {
      println("Terjadi error.");
      return false;
    }
  }

  public static boolean writeFileInterpolasi(String path, double[] koef, double nilai, double taksiran) {
    try {
      FileWriter myWriter = new FileWriter(path);
      myWriter.write("Persamaan polinom\ny =");
      boolean first = true;
      for (int i = koef.length - 1; i >= 0; i--) {
        if (!isZero(koef[i])) {
          if (koef[i] > 0 && first)
            first = false;
          else if (koef[i] > 0 && !first) {
            myWriter.write(" +");
          }
          myWriter.write(" ");
          myWriter.write(Double.toString(koef[i]));
          if (i != 0) {
            myWriter.write(" x^");
            myWriter.write(Integer.toString(i));
          }
        }
      }
      myWriter.write("\n");
      myWriter.write("Taksiran nilai fungsi dari ");
      myWriter.write(Double.toString(nilai));
      myWriter.write(" adalah ");
      myWriter.write(Double.toString(taksiran));
      // Add End Of File (new line)
      myWriter.write("\n");
      myWriter.close();
      println("Berhasil menuliskan pada " + path);
      return true;
    } catch (IOException e) {
      println("Terjadi error.");
      return false;
    }
  }

  // TESTER
  public static void main(String[] args) {
    int[] rowsCols = calcRowsCols("test/test.txt");
    double[][] mat = readFile("test/test.txt", rowsCols[0], rowsCols[1]);

    displayMat(mat);

    boolean success = false;
    while (!success) {
      success = writeFile("new/output.txt", mat);
      if (success)
        break;
      else {
        success = writeFile("test/lala.txt", mat);
      }
    }
    println("test");
  }
}
