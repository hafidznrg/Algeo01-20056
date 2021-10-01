class Main extends Menu {
  public static void main(String[] args) {
    boolean running, mustSquare, success;
    int choice;
    double det;
    double[][] mat;

    running = true;
    while (running) {
      success = false;
      displayMainMenu();
      choice = choose(1, 6);
      switch (choice) {
        case 1: // Sistem Persamaan Linear
          mustSquare = false;
          mat = createMatrix(mustSquare);
          String[] result = new String[mat[0].length];
          double[][] resSPLMat;
          double detM = 0;
          if (mat.length == mat[0].length - 1) {
            detM = Cofactor.determinan(mat);
          }
          while (true) {
            displayMenuSPL();
            choice = choose(1, 4);
            if (choice == 1 || choice == 2 || !isZero(detM))
              break;
            else {
              println("Tidak dapat diselesaikan dengan metode " + ((choice == 3) ? "matriks balikan" : "cramer"));
              println("Silakan pilih metode lain\n");
            }
          }
          // CALL SPL CLASS
          switch (choice) {
            case 1: // eliminasi Gauss
              resSPLMat = Gauss.gauss(mat);
              displayMat(resSPLMat);
              result = Gauss.solveSPL(resSPLMat);
              break;

            case 2: // eliminasi Gauss-Jordan
              resSPLMat = GaussJordan.gaussJordan(mat);
              displayMat(resSPLMat);
              result = GaussJordan.solveSPL(resSPLMat);
              break;

            case 3: // Metode matriks balikan
              result = Inverse.solveSPL(mat);
              break;

            case 4: // Kaidah Cramer
              result = Cramer.cramerRule(mat);
              break;
          }
          // Check result
          displayResults(result);
          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            while (!success) {
              print("Masukkan path file yang dituju\n> ");
              String path = sc.next();
              success = FileReadWrite.writeFileSPL("../test/" + path, result);
            }
          }
          break;

        case 2:// Kalkulasi Determinan
          displayMenuDet();
          choice = choose(1, 2);
          mustSquare = true;
          mat = createMatrix(mustSquare);
          // CALL DETERMINANT CLASS
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
            while (!success) {
              print("Masukkan path file yang dituju\n> ");
              String path = sc.next();
              success = FileReadWrite.writeFileDeterminan("../test/" + path, mat, det);
            }
          }
          break;

        case 3: // Matriks Balikan
          displayMenuInverse();
          choice = choose(1, 2);
          mustSquare = true;
          mat = createMatrix(mustSquare);
          if (isZero(Cofactor.determinan(mat))) {
            println("Matriks: ");
            displayMat(mat);
            println("\nTidak memiliki inverse karena determinannya 0");
            displayMenuOutput();
            choice = choose(1, 2);
            if (choice == 1) {
              while (!success) {
                print("Masukkan path file yang dituju\n> ");
                String path = sc.next();
                success = FileReadWrite.writeFileNoInverse("../test/" + path, mat);
              }
            }
          } else {
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
              while (!success) {
                print("Masukkan path file yang dituju\n> ");
                String path = sc.next();
                success = FileReadWrite.writeFileInverse("../test/" + path, mat, inv);
              }
            }
          }
          break;

        case 4:// Interpolasi Polinomial
          mustSquare = false;
          mat = createMatrix(mustSquare);
          // CALL INTERPOLASI POLINOM
          double[] koef = Interpolation.polynomial(mat);
          print("Masukkan nilai yang akan ditaksir : ");
          double nilai = sc.nextDouble();
          double taksiran = Interpolation.estimate(koef, nilai);
          println("Nilai taksiran fungsi pada saat x = %f adalah ", nilai);
          println(taksiran);
          displayMenuOutput();
          choice = choose(1, 2);
          if (choice == 1) {
            while (!success) {
              print("Masukkan path file yang dituju\n> ");
              String path = sc.next();
              success = FileReadWrite.writeFileInterpolasi("../test/" + path, koef, nilai, taksiran);
            }
          }
          break;

        case 5: // Multi linear regresi
          Regresi.driverRegresi();
          break;

        default: // Keluar
          println("Thank youu ^_^");
          running = false;
          break;
      }
      println();
    }
  }
}