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
  protected static void println(String format, Object... args){
    System.out.printf(format, args);
  }
  protected static void displayMat(double[][] mat) {
    for (int i = 0; i < mat.length; i++){
      for (int j = 0; j < mat[i].length; j++){
        print(mat[i][j] + " ");
      }
      println();
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
    int i,j;
    double[][] transposed = new double[mat[0].length][mat.length];

    for (i=0;i<transposed.length;i++) {
      for (j=0;j<transposed[i].length;j++) {
        transposed[i][j] = mat[j][i];
      }
    }

    return transposed;
  }

  // TESTER
  public static void main(String[] args){
    println("Hello " + 5 + " mabar");
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
    int input = choose(2,5);

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