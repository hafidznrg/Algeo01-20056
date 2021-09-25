class Main extends Menu{
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
          choice = choose(1,4);
          // System.out.println("pilihan 1:" + choice);
          mustSquare = false;
          mat = createMatrix(mustSquare);
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
          mustSquare = true;
          mat = createMatrix(mustSquare);
          // CALL DETERMINANT CLASS
          
          displayMenuOutput();
          choice = choose(1,2);
          break;

        case 3:
          displayMenuInverse();
          choice = choose(1,2);
          mustSquare = true;
          mat = createMatrix(mustSquare);
          // CALL INVERSE
          displayMenuOutput();
          choice = choose(1,2);
          break;
        
        case 4:
          mustSquare = false;
          mat = createMatrix(mustSquare);
          // CALL INTERPOLASI POLINOM
          displayMenuOutput();
          choice = choose(1,2);
          break;
        
        case 5:
          mustSquare = false;
          mat = createMatrix(mustSquare);
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