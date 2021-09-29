class Main extends Menu {
  public static void main(String[] args) {
    boolean running;
    boolean mustSquare;
    int choice;
    double[][] mat;
    // double[][] res;

    running = true;
    while (running) {
      displayMainMenu();
      choice = choose(1, 6);
      switch (choice) {
        case 1:
          displayMenuSPL();
          choice = choose(1, 4);
          // println("pilihan 1:" + choice);
          mustSquare = false;
          mat = createMatrix(mustSquare);
          // displayMat(mat);

          // CALL SPL CLASS
          String[] result = new String[mat[0].length];
          switch (choice) {
            case 1: // eliminasi Gauss

              break;

            case 2: // eliminasi Gauss-Jordan

              break;

            case 3: // Metode matriks balikan

              break;

            case 4: // Kaidah Cramer

              break;
          }
          // res = SPL.calc(mat);
          displayMenuOutput();
          choice = choose(1, 2);
          displayMat(mat);
          break;

        case 2:
          displayMenuDet();
          choice = choose(1, 2);
          // println("pilihan 2:" + choice);
          mustSquare = true;
          mat = createMatrix(mustSquare);
          // CALL DETERMINANT CLASS
          double det;
          if (choice == 1) {
            det = GaussTriangle.determinan(mat, mat.length, mat[0].length);
          } else {
            det = Cofactor.determinan(mat);
          }
          println("Determinan dari matriks");
          displayMat(mat);
          println("adalah %f\n", det);

          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            println("Masukkan path file yang dituju");
            String path = sc.next();
            FileReadWrite.writeFileDeterminan(path, mat, det);
          }
          break;

        case 3:
          displayMenuInverse();
          choice = choose(1, 2);
          mustSquare = true;
          mat = createMatrix(mustSquare);
          // CALL INVERSE
          double[][] inv = new double[mat.length][mat[0].length];
          if (choice == 1) { // Eliminasi Gauss-Jordan
            inv = Inverse.gaussJordanMethods(mat);
          } else { // Metode cofactor
            inv = Inverse.cofactorMethods(mat);
          }
          println("Inverse dari matriks");
          displayMat(mat);
          println("adalah");
          displayMat(inv);
          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            println("Masukkan path file yang dituju");
            String path = sc.next();
            FileReadWrite.writeFileInverse(path, mat, inv);
          }
          break;

        case 4:
          mustSquare = false;
          mat = createMatrix(mustSquare);
          // CALL INTERPOLASI POLINOM
          double[] koef = Interpolation.polynomial(mat, mat.length - 1);
          print("Masukkan nilai yang akan ditaksir : ");
          double nilai = sc.nextDouble();
          double taksiran = Interpolation.estimate(koef, nilai);
          println("Nilai taksiran dari %f adalah ", nilai);
          println(taksiran);
          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            println("Masukkan path file yang dituju");
            String path = sc.next();
            FileReadWrite.writeFileInterpolasi(path, koef, nilai, taksiran);
          }
          break;

        case 5:
          Regresi.driverRegresi();
          break;

        default:
          println("Thank youu ^_^");
          running = false;
          break;
      }
      println();
    }
  }
}