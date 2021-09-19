import java.io.*;
import java.util.*;

class Main {
  static Scanner sc = new Scanner(System.in);
  static String path;

  public static void main(String[] args) {
    boolean running = true;
    int choice;
    double[][] mat;
    double[][] res;

    while (running) {
      displayMainMenu();
      choice = take(1, 6);
      // choice = sc.nextInt();
      switch (choice) {
        case 1:
          displayMenuSPL();
          choice = take(1,4);
          // System.out.println("pilihan 1:" + choice);
          mat = createMatrix(false);
          FileReadWrite.displayMat(mat);
          
          // CALL SPL CLASS
          // res = SPL.calc(mat);
          displayMenuOutput();
          choice = take(1,2);
          break;
        case 2:
          displayMenuDet();
          choice = take(1,2);
          // System.out.println("pilihan 2:" + choice);
          mat = createMatrix(true);
          // CALL DETERMINANT CLASS
          
          displayMenuOutput();
          choice = take(1,2);
          break;

        case 3:
          displayMenuInverse();
          choice = take(1,2);
          mat = createMatrix(true);
          // CALL INVERSE
          displayMenuOutput();
          choice = take(1,2);
          break;
        
        case 4:
          mat = createMatrix(false);
          // CALL INTERPOLASI POLINOM
          displayMenuOutput();
          choice = take(1,2);
          break;
        
        case 5:
          mat = createMatrix(false);
          // CALL REGRESI LINIER BERGANDA 
          displayMenuOutput();
          choice = take(1,2);
          break;

        default:
          System.out.println("Thank youu ^_^");
          running = false;
      }
    }
    sc.close();
  }
  public static void displayMainMenu() {
    System.out.println("Main Menu");
    System.out.println("1. Sistem Persamaan Linear");
    System.out.println("2. Determinan");
    System.out.println("3. Inverse");
    System.out.println("4. Interpolasi Polinom");
    System.out.println("5. Regresi Linier Berganda");
    System.out.println("6. Keluar");
  }
  public static void displayMenuSPL() {
    System.out.println("Menu Sistem Persamaan Linear");
    System.out.println("1. Metode eliminasi Gauss");
    System.out.println("2. Metode eliminasi Gauss-Jordan");
    System.out.println("3. Metode matriks balikan");
    System.out.println("4. Kaidah Cramer");
  }
  public static void displayMenuDet() {
    System.out.println("Menu Determinan");
    System.out.println("1. Metode eliminasi Gauss");
    System.out.println("2. Metode ekspansi kofaktor");
  }
  public static void displayMenuInverse() {
    System.out.println("Menu Inverse");
    System.out.println("1. Metode eliminasi Gauss-Jordan");
    System.out.println("2. Metode matriks kofaktor");
  }
  public static void displayMenuOutput() {
    System.out.println("Menu Output");
    System.out.println("1. Output ke layar");
    System.out.println("2. Output ke file");
  }
  public static int take(int low, int high) {
    System.out.print("> ");
    int res = Main.sc.nextInt();
    while (res < low || res > high){
      System.out.println("Pilihan tidak ada. Silakan input ulang!");
      System.out.print("> ");
      res = Main.sc.nextInt();
    }
    return res;
  }
  public static void displayMenuData() {
    System.out.println("Menu Data Matriks");
    System.out.println("1. Input Keyboard");
    System.out.println("2. Input File");
  }
  public static double[][] createMatrix(boolean mustSquare) {
    int res, rows, cols;
    double[][] mat;

    while (true) {
      displayMenuData();
      System.out.print("> ");
      res = Main.sc.nextInt();
      if (res >= 1 && res <= 2){
        break;
      } else {
        System.out.println("Masukan invalid");
      }
    }

    if (res == 1) {
      while (true) {
        if (mustSquare) {
          System.out.println("Masukkan ukuran matriks");
          System.out.print("> ");
          rows = Main.sc.nextInt();
          cols = rows;
        } else {
          System.out.println("Masukkan jumlah baris dan kolom matriks");
          System.out.println("(pisahkan dengan spasi)");
          System.out.print("> ");
          rows = Main.sc.nextInt();
          cols = Main.sc.nextInt();
        }
        if (rows > 0 && cols > 0) {
          break;
        } else {
          System.out.println("Masukkan invalid");
        }
      }
      mat = new double[rows][cols];
      System.out.println("Masukkan elemen matriks");
      for (int i = 0; i < rows; i++) {
        for (int j =0;j<cols; j++) {
          System.out.print("Elemen baris"+rows + " kolom" +cols+": ");
          mat[i][j] = Main.sc.nextInt();
        }
      }
    } else {
      System.out.println("Masukkan nama file (nama saja)");
      System.out.print("> ");
      String filename = Main.sc.next();

      System.out.println("Masukkan integer");
      int tes = Main.sc.nextInt();
      System.out.print("Input: "+ tes);

      int[] rowsCols = new int[2];
      rowsCols = FileReadWrite.calcRowsCols("test/"+filename);
      mat = new double[rowsCols[0]][rowsCols[1]];

      mat = FileReadWrite.readFile("test/"+filename, rowsCols[0],rowsCols[1]);
    }
    return mat;
  } 
}