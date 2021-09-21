import java.io.*;
import java.util.*;

class FileReadWrite extends Utils {
  public static int[] calcRowsCols(String fileName) {
    FileReader fr=null;
    try {
      fr = new FileReader(fileName);
    }
    catch (FileNotFoundException fe) {
      println("File tidak ditemukan");
    }
    
    int rows=0, cols=0;
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
  public static double[][] readFile(String fileName, int rows, int cols){
    double[][] mat = new double[rows][cols]; 
    
    println("Mencoba membaca file: " + fileName);

    FileReader fr=null;
    try {
      fr = new FileReader(fileName);
    }
    catch (FileNotFoundException fe) {
      println("File tidak ditemukan");
    }
    Scanner rowScanner2 = new Scanner(fr);

    for (int i=0;i<rows;i++) {
      for (int j=0;j<cols;j++) {
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
      for (int i=0;i<mat.length;i++) {
        for (int j=0;j<mat[i].length; j++) {
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

  // TESTER
  public static void main(String[] args){
    int[] rowsCols = calcRowsCols("test/test.txt");
    double[][] mat = readFile("test/test.txt",rowsCols[0],rowsCols[1]);

    displayMat(mat);

    boolean success = false;
    while (!success) {
      success = writeFile("new/output.txt",mat);
      if (success)
        break;
      else {
        success = writeFile("test/lala.txt",mat);
      }
    }
    println("test");
  }
}
