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
        double[][] matrix = { { 1.00, 2.00, 3.00, 7 }, { 0, 1.00, 4.00, 10 }, { 0, 0, 1.00, 5 } };

        displayMat(matrix);
        println("Matriks eselon barisnya adalah : ");
        double[][] baru = gauss(matrix);
        println("\nHasil: ");
        displayMat(baru);
        println("Solusi");
        solve(baru);
    }

    public static double[][] gauss(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        /* jika ukuran colom <= baris */
        if (col <= row) {
            for (int k = 0; k < col - 1; k++) {
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (Math.abs(matrix[k][k]) < 1.0e-12) {
                    for (int i = k + 1; i < row; i++) {
                        if (Math.abs(matrix[i][k]) > Math.abs(matrix[k][k])) {
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
                if (Math.abs(pivot) < 1.0e-12) {
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

        } else {
            for (int k = 0; k < row; k++) {
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (Math.abs(matrix[k][k]) < 1.0e-12) {
                    for (int i = k + 1; i < row; i++) {
                        if (Math.abs(matrix[i][k]) > Math.abs(matrix[k][k])) {
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
                if (Math.abs(pivot) < 1.0e-12) {
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
                if (mat[i][i] != 1)
                    unique = false;
                i++;
            }
        }

        double temp;
        if (unique) {
            for (int i = rows - 1; i >= 0; i--) {
                temp = mat[i][cols - 1];
                for (int j = rows - 1; j > i; j--) {
                    temp -= mat[i][j] * mat[j][cols - 1];
                }
                result[i] = "x" + (i + 1) + " = " + temp;
            }
        } else {
            ParametricSolver.solve(mat, true);
        }

        // Check result
        // for (int i = 0; i < result.length; i++) {
        // println(result[i]);
        // }

        return result;
    }
}