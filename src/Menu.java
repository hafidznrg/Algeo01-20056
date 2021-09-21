class Menu extends Utils {
  protected static void displayMainMenu() {
    println("Main Menu");
    println("1. Sistem Persamaan Linear");
    println("2. Determinan");
    println("3. Inverse");
    println("4. Interpolasi Polinom");
    println("5. Regresi Linier Berganda");
    println("6. Keluar");
  }
  protected static void displayMenuSPL() {
    println("Menu Sistem Persamaan Linear");
    println("1. Metode eliminasi Gauss");
    println("2. Metode eliminasi Gauss-Jordan");
    println("3. Metode matriks balikan");
    println("4. Kaidah Cramer");
  }
  protected static void displayMenuDet() {
    println("Menu Determinan");
    println("1. Metode eliminasi Gauss");
    println("2. Metode ekspansi kofaktor");
  }
  protected static void displayMenuInverse() {
    println("Menu Inverse");
    println("1. Metode eliminasi Gauss-Jordan");
    println("2. Metode matriks kofaktor");
  }
  protected static void displayMenuData() {
    println("Menu Input Data Matriks");
    println("1. Input Keyboard");
    println("2. Input File");
  }
  protected static void displayMenuOutput() {
    println("Menu Output");
    println("1. Output ke layar");
    println("2. Output ke file");
  }
  protected static double[][] createMatrix(boolean mustSquare) {
    double[][] mat;

    displayMenuData();
    int choice = choose(1,2);
    if (choice == 1) {
      mat = inputMatrixKeyboard(mustSquare);
    } else {
      mat = inputMatrixFile(mustSquare);
    }
    return mat;
  }
}
