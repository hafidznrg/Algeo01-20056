public class Gauss extends Utils {
    public static double[][] gauss(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        /* jika ukuran colom <= baris */
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
                // melakukan pembagian pada baris pivot
                pivot = matrix[k][pivotCol];
                if (isZero(pivot)) {
                    continue;
                } else {
                    for (int j = pivotCol; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }

                    // melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = k + 1; i < row; i++) {
                        if (isZero(matrix[i][pivotCol])) {
                            continue;
                        }
                        double factor = matrix[i][pivotCol];
                        for (int j = pivotCol; j < col; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        }
                    }

                }
                pivotCol++;

            }

        } else {
            int pivotCol = 0;
            for (int k = 0; k < row; k++) {
                //jika sudag sampai kolom terakhir maka hentikan proses
                if (pivotCol == col-1){
                    break;
                }
                //cek apakah kolom bernilai 0
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

                // melakukan pembagian pada baris pivot
                pivot = matrix[k][pivotCol];
                if (isZero(pivot)) {
                    continue;
                } else {
                    for (int j = pivotCol; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }

                    // melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = k + 1; i < row; i++) {
                        if ((i == k) || matrix[i][pivotCol] == 0) {
                            continue;
                        }
                        double factor = matrix[i][pivotCol];
                        for (int j = pivotCol; j < col; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        }
                    }

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
        boolean solvable = true;

        int i = rows - 1;
        // Cek apakah ada baris yang matrixnya nol dan hasilnya tidak nol
        if (isNoSolution(matrix)) {
            String[] ret = { "Tidak ada solusi" };
            return ret;
        } else {
            double[][] afterCut = createMatEff(matrix);
            rows = afterCut.length;
            try {
                cols = afterCut[0].length;
            } catch (ArrayIndexOutOfBoundsException err) {
                cols = 0;
            }
            if (rows == (cols - 1)) {
                // CASE 1 : baris == kolom -1
                return solveSPLCase1(afterCut);
            } else if (rows < (cols - 1)) {
                // CASE 2 : baris < kolom - 1
                return ParametricSolver.solve(afterCut, true);
                // return solveSPLCase2(afterCut);
            } else {
                // CASE 3 : baris > kolom - 1
                String[] ret = { "Tidak ada solusi" };
                return ret;
            }
        }
    }

    // rows = cols - 1
    public static String[] solveSPLCase1(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        String[] result = new String[cols - 1];
        double[] res = new double[cols - 1];
        for (int i = rows - 1; i >= 0; i--) {
            res[i] = matrix[i][cols - 1];
            for (int j = (cols - 2); j > i; j--) {
                res[i] -= matrix[i][j] * res[j];
            }
            result[i] = "x" + (i + 1) + " = " + res[i];
        }

        return result;
    }
}