public class Gauss extends Utils {
    public static void main(String[] args) {
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00,
        // -6.00, -12.00 } };
        // double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00,
        // 0.00, 1.00 } };
        // double[][] matrix = { { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 }, { 4.00,
        // 4.00, 4.00 } };
        // double[][] matrix = { { 1.00, 3.00, 14.00 }, { -7.00, -1.00,10.00 } };
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00,
        // 8.00, 9.00 } };
        // double[][] matrix = { { 1.00, 2.00, 3.00, 7 }, { 0, 1.00, 4.00, 10 }, { 0, 0,
        // 1.00, 5 } };
        double[][] matrix = { { 0, 1, 2, 3, }, { 5, 0, 3, 4 }, { 2, 0, 4, 5 }, { 3, 0, 5, 4 } };
        // x1 = 12, x2 = -10, x3 = 5

        displayMat(matrix);
        println("Matriks eselon barisnya adalah : ");
        double[][] baru = gauss(matrix);
        println("\nHasil: ");
        displayMat(baru);
        println("Solusi");
        String[] result = solveSPL(baru);
        displayResults(result);
    }

    public static double[][] gauss(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        /* jika ukuran colom <= baris */
        if (col <= row) {
            for (int k = 0; k < col - 1; k++) {
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (isZero(matrix[k][k])) {
                    for (int i = k + 1; i < row; i++) {
                        if (!isZero(matrix[i][k])) {
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
                double pivot = matrix[k][k];
                if (isZero(pivot)) {
                    continue;
                } else {
                    for (int j = k; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }

                    // melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = k + 1; i < row; i++) {
                        if (isZero(matrix[i][k])) {
                            continue;
                        }
                        double factor = matrix[i][k];
                        for (int j = k; j < col; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        }
                    }

                }

            }

        } else {
            for (int k = 0; k < row; k++) {
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (isZero(matrix[k][k])) {
                    for (int i = k + 1; i < row; i++) {
                        if (!isZero(matrix[i][k])) {
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
                double pivot = matrix[k][k];
                if (isZero(pivot)) {
                    continue;
                } else {
                    for (int j = k; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }

                    // melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = k + 1; i < row; i++) {
                        if ((i == k) || matrix[i][k] == 0) {
                            continue;
                        }
                        double factor = matrix[i][k];
                        for (int j = k; j < col; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        }
                    }

                }

            }
        }

        return matrix;
    }

    // Solver
    public static String[] solve(double[][] mat) {
        String[] result = new String[mat[0].length - 1];
        int rows = mat.length;
        int cols = mat[0].length;

        boolean unique = true;
        if (rows != (cols - 1))
            unique = false;
        else {
            int i = 0;
            while (unique && i < rows) {
                if (!isZero(mat[i][i] - 1))
                    unique = false;
                i++;
            }
        }

        double temp;
        double[] res = new double[cols];
        res[cols - 1] = mat[rows - 1][cols - 1];
        if (unique) {
            for (int i = rows - 1; i >= 0; i--) {
                temp = mat[i][cols - 1];
                for (int j = (cols - 2); j > i; j--) {
                    temp -= mat[i][j] * res[j];
                }
                res[i] = temp;
                result[i] = "x" + (i + 1) + " = " + temp;
            }
        } else {
            result = ParametricSolver.solve(mat, true);
        }

        // Check result
        // for (int i = 0; i < result.length; i++) {
        // println(result[i]);
        // }

        return result;
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
            cols = afterCut[0].length;
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