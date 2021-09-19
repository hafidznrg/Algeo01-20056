import java.io.*;
import java.util.*;

class FileReadWrite {
  int rows, cols, fileName;

  public static int[] calcRowsCols(String fileName) {
    FileReader fr=null;
    try {
      fr = new FileReader(fileName);
    }
    catch (FileNotFoundException fe) {
      System.out.println("File not found");
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
    // return realRead(rows,cols,fileName);
  }
  public static double[][] readFile(String fileName, int rows, int cols){
    System.out.println("file: " + fileName);

    FileReader fr=null;
    try {
      fr = new FileReader(fileName);
    }
    catch (FileNotFoundException fe) {
      System.out.println("File not found");
    }
    double[][] mat = new double[rows][cols]; 
    Scanner rowScanner2 = new Scanner(fr);

    for (int i=0;i<rows;i++) {
      for (int j=0;j<cols;j++) {
        try {
          double input = rowScanner2.nextDouble();
          mat[i][j] = input;
        } catch (NoSuchElementException e) {
          System.out.println("gagal, seharusnya memiliki elemen selanjutnya, tapi gagal terbaca");
        }
      }
    }
    rowScanner2.close();

    System.out.println(mat[0][0]);
    // displayMat(mat,rows,cols);
    return mat;
  }
  
  public static void writeFile(String path, double[][] mat) {
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
      System.out.println("Successfully wrote to " + path);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void displayMat(double[][] mat) {
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        System.out.print(mat[i][j]+" ");
      }
      System.out.println();
    }
  }

  // public static void main(String[] args){
  //   // this.mat = obj.readFile("test/" + inputName);
  //   double[][] mat = {{1,2},{3,4}};

  //   writeFile("test/output.txt",mat);
  //   System.out.println("test");
  // }
}
