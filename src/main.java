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
          double detM = 0;
          if (mat.length == mat[0].length - 1) {
            detM = Cofactor.determinan(mat);
          }
          while (true) {
            if (choice == 1 || choice == 2 || !isZero(detM)) {
              break;
            } else {
              println("Tidak bisa menyelesaikan SPL dengan cara ini.\nSilakan menggunakan cara lain");
              displayMenuSPL();
              choice = choose(1, 4);
            }
          }

          // CALL SPL CLASS
          String[] result = new String[mat[0].length];
          switch (choice) {
            case 1: // eliminasi Gauss
              double[][] matRes = new double[mat.length][mat[0].length];
              matRes = Gauss.gauss(mat);
              result = Gauss.solve(matRes);
              break;

            case 2: // eliminasi Gauss-Jordan

              break;

            case 3: // Metode matriks balikan

              break;

            case 4: // Kaidah Cramer
              result = Cramer.cramerRule(mat);
              break;
          }
          // res = SPL.calc(mat);
          // Check result
          for (int i = 0; i < result.length; i++) {
            println(result[i]);
          }
          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            print("Masukkan path file yang dituju\n> ");
            String path = sc.next();
            FileReadWrite.writeFileSPL(path, result);
          }
          // displayMat(mat);
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
            print("Masukkan path file yang dituju\n> ");
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
            print("Masukkan path file yang dituju\n> ");
            String path = sc.next();
            FileReadWrite.writeFileInverse(path, mat, inv);
          }
          break;

        case 4:
          mustSquare = false;
          mat = createMatrix(mustSquare);
          // CALL INTERPOLASI POLINOM
          double[] koef = Interpolation.polynomial(mat);
          print("Masukkan nilai yang akan ditaksir : ");
          double nilai = sc.nextDouble();
          double taksiran = Interpolation.estimate(koef, nilai);
          println("Nilai taksiran dari %f adalah ", nilai);
          println(taksiran);
          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            print("Masukkan path file yang dituju\n> ");
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