import java.io.*;

class Menu extends Utils {
  protected static void displayMainMenu() {
    // Menuliskan daftar main menu
    println("Main Menu");
    println("1. Sistem Persamaan Linear");
    println("2. Determinan");
    println("3. Inverse");
    println("4. Interpolasi Polinom");
    println("5. Regresi Linier Berganda");
    println("6. Keluar");
  }

  protected static void displayMenuSPL() {
    // Menuliskan daftar menu SPL
    println("Menu Sistem Persamaan Linear");
    println("1. Metode eliminasi Gauss");
    println("2. Metode eliminasi Gauss-Jordan");
    println("3. Metode matriks balikan");
    println("4. Kaidah Cramer");
  }

  protected static void displayMenuDet() {
    // Menuliskan daftar menu Determinan
    println("Menu Determinan");
    println("1. Metode eliminasi Gauss");
    println("2. Metode ekspansi kofaktor");
  }

  protected static void displayMenuInverse() {
    // Menuliskan daftar menu matriks balikan
    println("Menu Inverse");
    println("1. Metode eliminasi Gauss-Jordan");
    println("2. Metode matriks kofaktor");
  }

  protected static void displayMenuData() {
    // Menuliskan daftar menu masukan data
    println("Menu Input Data Matriks");
    println("1. Input Keyboard");
    println("2. Input File");
  }

  protected static void displayMenuOutput() {
    // Menuliskan daftar menu output
    println("Apakah Anda ingin menyimpan hasil ini ke dalam file?");
    println("1. Ya");
    println("2. Tidak");
  }

  protected static double[][] createMatrix(boolean mustSquare) {
    // Mengembalikan matriks input
    double[][] mat;

    displayMenuData();
    int choice = choose(1, 2);
    if (choice == 1) {
      mat = inputMatrixKeyboard(mustSquare);
    } else {
      mat = inputMatrixFile(mustSquare);
    }
    return mat;
  }

  private static double[][] inputMatrixKeyboard(boolean mustSquare) {
    // Mengembalikan matriks input dari keyboard
    int rows, cols;
    double[][] mat;

    while (true) {
      if (mustSquare) {
        println("Masukkan ukuran matriks");
        print("> ");
        rows = sc.nextInt();
        cols = rows;
      } else {
        println("Masukkan jumlah baris dan kolom matriks");
        println("(Pisahkan dengan spasi)");
        print("> ");
        rows = sc.nextInt();
        cols = sc.nextInt();
      }
      if (rows > 0 && cols > 0) {
        break;
      } else {
        println("Masukan invalid. Jumlah baris dan kolom harus > 0");
      }
    }
    mat = new double[rows][cols];

    println("Masukkan elemen matriks");
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        print("Elemen baris ke-" + (i + 1) + " kolom ke-" + (j + 1) + ": ");
        mat[i][j] = sc.nextDouble();
      }
    }

    return mat;
  }

  protected static double[][] inputMatrixFile(boolean mustSquare) {
    // Mengembalikan matriks input dari file
    String fileName;
    int[] rowsCols;
    double[][] mat;

    while (true) {
      fileName = "../test/" + inputFileName();
      rowsCols = FileReadWrite.calcRowsCols(fileName);
      if (mustSquare && (rowsCols[0] != rowsCols[1])) {
        println("Matriks dalam file tidak berbentuk persegi");
      } else {
        break;
      }
    }
    mat = FileReadWrite.readFile(fileName, rowsCols[0], rowsCols[1]);

    return mat;
  }

  protected static String inputFileName() {
    // Mengembalikan path file input yang telah divalidasi
    String fileName;
    // To check whether the file is exist or not
    FileReader fr = null;

    println("Masukkan nama file");
    print("> ");
    fileName = sc.next();
    try {
      fr = new FileReader("../test/" + fileName);
    } catch (FileNotFoundException fe) {
      println("File tidak ditemukan.");
      fileName = inputFileName();
    }

    return fileName;
  }
}
