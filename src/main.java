class Main extends Menu{
  public static void main(String[] args) {
    boolean running = true;
    int choice;
    double[][] mat;
    // double[][] res;

    while (running) {
      displayMainMenu();
      choice = choose(1, 6);
      // choice = sc.nextInt();
      switch (choice) {
        case 1:
          displayMenuSPL();
          choice = choose(1,4);
          // System.out.println("pilihan 1:" + choice);
          mat = createMatrix(false);
          // displayMat(mat);
          
          // CALL SPL CLASS
          // res = SPL.calc(mat);
          displayMenuOutput();
          choice = choose(1,2);
          displayMat(mat);
          break;

        case 2:
          displayMenuDet();
          choice = choose(1,2);
          // System.out.println("pilihan 2:" + choice);
          mat = createMatrix(true);
          // CALL DETERMINANT CLASS
          
          displayMenuOutput();
          choice = choose(1,2);
          break;

        case 3:
          displayMenuInverse();
          choice = choose(1,2);
          mat = createMatrix(true);
          // CALL INVERSE
          displayMenuOutput();
          choice = choose(1,2);
          break;
        
        case 4:
          mat = createMatrix(false);
          // CALL INTERPOLASI POLINOM
          displayMenuOutput();
          choice = choose(1,2);
          break;
        
        case 5:
          mat = createMatrix(false);
          // CALL REGRESI LINIER BERGANDA 
          displayMenuOutput();
          choice = choose(1,2);
          break;

        default:
          System.out.println("Thank youu ^_^");
          running = false;
      }
      println();
    }
  }
}