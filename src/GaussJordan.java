import java.util.Scanner;

public class GaussJordan extends Utils {
    public static void main(String[] args) {
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00,
        // -6.00, -12.00 } };
        // double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00,
        // 0.00, 1.00 } };
        // double[][] matrix = { { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 }, { 4.00,
        // 4.00, 4.00 } };
        // double[][] matrix = { { 1.00, 3.00, 14.00 }, { -7.00, -1.00,10.00 } };
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00,
        // -6.00, -12.00 },
        // { 4.00, 3.00, 2.00 } };
        //double[][] matrix = { { 1.00, 2.00, 3.00, 7 }, { 0, 1.00, 4.00, 10 }, { 0, 0, 1.00, 5 } };
        // printMatrix(matrix, 4, 3);
         Scanner sc = new Scanner(System.in);
         double[][] matrix = new double[3][5];
        int rowMat = matrix.length;
        int colMat = matrix[0].length;
         for (int i = 0; i < rowMat; i++){
            for (int j = 0; j < colMat; j++) {
                matrix[i][j] = sc.nextDouble();
            }
         }
         sc.close();
         System.out.println("Matriks eselon barisnya adalah : ");
         double[][] baru = gaussJordan(matrix);
         displayMat(baru);
         displayResults(solveSPL(baru));
    }

    public static double[][] gaussJordan(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        /* untuk colom <= baris */
        if (col <= row) {
            int pivotCol = 0;
            for (int k = 0; k < col - 1; k++) {
                //jika sudah sampai kolom terakhir maka hentikan proses
                if (pivotCol == col-1){
                    break;
                }
                //cek apakah kolo bernilai 0, apabila 0 maka geser pivot ke kolom kanannya
                while (isAllZero(matrix, pivotCol) && (pivotCol < col)){
                    pivotCol++;
                }
                double pivot = matrix[k][pivotCol];
                //System.out.println("sekarang pivot baris "+k+" kolom "+ pivotCol);
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (isZero(pivot)) {
                    for (int i = k + 1; i < row; i++) {
                        if (!isZero(matrix[i][pivotCol])) {
                            for (int j = 0; j < col; j++) {
                                double temp = matrix[k][j];
                                matrix[k][j] = matrix[i][j];
                                matrix[i][j] = temp;
                            }
                            break;
                        }
                    }
                }
                // System.out.println("matrix setelah ditukar");
                // printMatrix(matrix, 3, 3);
                // melakukan pembagian pada baris pivot
                pivot = matrix[k][pivotCol];
                if (isZero(pivot)) {
                    continue;
                } else {
                    // System.out.println("ini pivot " + pivot);
                    for (int j = pivotCol; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }
                    // System.out.println("matrix setelah dibagi pivot");
                    // printMatrix(matrix, 4, 3);
                    // System.out.println();

                    // melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = 0; i < row; i++) {
                        if ((i == k) || matrix[i][pivotCol] == 0) {
                            continue;
                        }
                        double factor = matrix[i][pivotCol];
                        // System.out.println("ini faktornya "+factor);
                        for (int j = pivotCol; j < col; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                            // System.out.println("matix setelah dikurangi faktor "+matrix[i][j]);
                        }
                    }
                    // System.out.println("matrix setelah dieliminasi");
                    // printMatrix(matrix, 3, 3);
                }
                pivotCol++;

            }

        } else {
            // untuk kolom > baris
            int pivotCol = 0;
            for (int k = 0; k < row; k++) {
                //jika sudah sampai kolom terakhir maka hentikan proses
                if (pivotCol == col-1){
                    break;
                }
                //cek apakah kolom bernilai 0
                //jika 0 maka pivot bergeser ke kolom sebelah kanan-nya
                while (isAllZero(matrix, pivotCol) && (pivotCol < col)){
                    pivotCol++;
                }
                double pivot = matrix[k][pivotCol];
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (isZero(pivot)) {
                    for (int i = k + 1; i < row; i++) {
                        if (!isZero(matrix[i][pivotCol])) {
                            for (int j = 0; j < col; j++) {
                                double temp = matrix[k][j];
                                matrix[k][j] = matrix[i][j];
                                matrix[i][j] = temp;
                            }
                            break;
                        }
                    }
                }
                // System.out.println("matrix setelah ditukar");
                // printMatrix(matrix, 3, 3);
                // melakukan pembagian pada baris pivot
                pivot = matrix[k][pivotCol];
                if (isZero(pivot)) {
                    continue;
                } else {
                    // System.out.println("ini pivot " + pivot);
                    for (int j = pivotCol; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }
                    // System.out.println("matrix setelah dibagi pivot");
                    // printMatrix(matrix, 3, 3);
                    // System.out.println();

                    // melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = 0; i < row; i++) {
                        if ((i == k) || matrix[i][pivotCol] == 0) {
                            continue;
                        }
                        double factor = matrix[i][pivotCol];
                        // System.out.println("ini faktornya "+factor);
                        for (int j = pivotCol; j < col; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                            // System.out.println("matix setelah dikurangi faktor "+matrix[i][j]);
                        }
                    }
                    // System.out.println("matrix setelah dieliminasi");
                    // printMatrix(matrix, 3, 3);

                }
                pivotCol++;

            }
        }

        return matrix;
    }

    public static boolean isAllZero(double[][] matrix, int pivotCol){
        boolean allZero = true;
        for (int i = 0; i < matrix.length; i++){
            if (!isZero(matrix[i][pivotCol])){
                allZero = false;
            }
        }
        return  allZero;
    }

    public static String[] solveSPL(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Cek apakah ada baris yang matrixnya nol dan hasilnya tidak nol
        if (isNoSolution(matrix)) {
            String[] ret = { "Tidak ada solusi" };
            return ret;
        } else {
            double[][] afterCut = createMatEff(matrix);
            rows = afterCut.length;
            cols = afterCut[0].length;
            if (rows == (cols - 1)) {
                // CASE 1 : baris == kolom -1
                return solveSPLCase1(afterCut);
            } else if (rows < (cols - 1)) {
                // CASE 2 : baris < kolom - 1
                return ParametricSolver.solve(afterCut, false);
            } else {
                // CASE 3 : baris > kolom - 1
                String[] ret = { "Tidak ada solusi" };
                return ret;
            }
        }
    }

    public static String[] solveSPLCase1(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        String[] result = new String[rows];
        for (int i = 0; i < rows; i++) {
            result[i] = "x" + (i + 1) + " = " + matrix[i][cols - 1];
        }

        return result;
    }
}
