public class GaussTriangle extends Utils {
    public static void main(String[] args) {
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00,
        // 8.00, 9.00 } };
        // double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00,
        // 0.00, 1.00 } };

        double[][] matrix = { { 0.00, 1.00, 5.00 }, { 3.00, -6.00, 9.00 }, { 2.00, 6.00, 1.00 } };
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00,
        // -6.00, -12.00 }, {4.00, 3.00, 2.00} };
        printMatrix(matrix, 3, 3);
        // System.out.println("Matriks eselon barisnya adalah : ");
        // double[][] baru = GaussTriangle(matrix, 3, 3);
        // printMatrix(baru, 3, 3);
        double dtrmn = determinan(matrix, 3, 3);
        System.out.println("Nilai Determinannya; ");
        System.out.println(dtrmn);

    }

    public static void printMatrix(double[][] Matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                print(Matrix[i][j] + " ");
            }
            println();
        }
    }

    public static double[][] gaussTriangle(double[][] matrix, int row, int col) {
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
                // println("matrix setelah ditukar");
                // printMatrix(matrix, 3, 3);

                // melakukan eliminasi pada baris bawah agar bernilai = 0
                for (int i = k + 1; i < row; i++) {
                    if (isZero(matrix[i][k])) {
                        continue;
                    }
                    double factor = matrix[i][k] / matrix[k][k];

                    // System.out.println("ini faktornya "+factor);
                    for (int j = k; j < col; j++) {

                        matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        // System.out.println("faktor * atasnya = " + (factor * matrix[k][j]));
                        // System.out.println("matrix setelah dikurangi faktor "+matrix[i][j]);

                    }
                }
                // println("matrix setelah dieliminasi");
                // printMatrix(matrix, 3, 3);

            }
        } else {
            for (int k = 0; k < row - 1; k++) {
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
                // println("matrix setelah ditukar");
                // printMatrix(matrix, 3, 3);

                // melakukan eliminasi pada baris bawah agar bernilai = 0
                for (int i = k + 1; i < row; i++) {
                    if (isZero(matrix[i][k])) {
                        continue;
                    }
                    double factor = matrix[i][k] / matrix[k][k];
                    // System.out.println("ini faktornya "+factor);
                    for (int j = k; j < col; j++) {

                        matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        // System.out.println("faktor * atasnya = " + (factor * matrix[k][j]));
                        // System.out.println("matrix setelah dikurangi faktor "+matrix[i][j]);

                    }
                }
                // println("matrix setelah dieliminasi");
                // printMatrix(matrix, 3, 3);

            }
        }

        return matrix;

    }

    public static double determinan(double[][] matrix, int row, int col) {
        int numSwap = 0;

        // System.out.println("matrix awal: ");
        // printMatrix(matrix, row, col);
        if (col <= row) {
            for (int k = 0; k < col - 1; k++) {
                /* cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (isZero(matrix[k][k])) {
                    for (int i = k + 1; i < row; i++) {
                        // breakdown if matrix[i][k] is also near 0
                        if (!isZero(matrix[i][k])) {
                            for (int j = 0; j < col; j++) {
                                double temp = matrix[k][j];
                                matrix[k][j] = matrix[i][j];
                                matrix[i][j] = temp;
                            }
                            numSwap++;
                            break;
                        }
                    }
                }
                // System.out.println("matrix setelah ditukar");
                // printMatrix(matrix, 3, 3);

                // melakukan eliminasi pada baris bawah agar bernilai = 0
                for (int i = k + 1; i < row; i++) {
                    if (isZero(matrix[i][k])) {
                        continue;
                    }
                    double factor = matrix[i][k] / matrix[k][k];
                    // System.out.println("ini faktornya "+factor);
                    for (int j = k; j < col; j++) {

                        matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        // System.out.println("faktor * atasnya = " + (factor * matrix[k][j]));
                        // System.out.println("matrix setelah dikurangi faktor "+matrix[i][j]);

                    }
                }
                // System.out.println("matrix setelah dieliminasi");
                // printMatrix(matrix, 3, 3);

            }
        } else {
            for (int k = 0; k < row - 1; k++) {
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
                // System.out.println("matrix setelah ditukar");
                // printMatrix(matrix, 3, 3);

                // melakukan eliminasi pada baris bawah agar bernilai = 0
                for (int i = k + 1; i < row; i++) {
                    if (isZero(matrix[i][k])) {
                        continue;
                    }
                    double factor = matrix[i][k] / matrix[k][k];
                    // System.out.println("ini faktornya "+factor);
                    for (int j = k; j < col; j++) {

                        matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                        // System.out.println("faktor * atasnya = " + (factor * matrix[k][j]));
                        // System.out.println("matrix setelah dikurangi faktor "+matrix[i][j]);

                    }
                }
                // System.out.println("matrix setelah dieliminasi");
                // printMatrix(matrix, 3, 3);

            }

        }
        double result;
        double sumPivot = 1;
        // printMatrix(matrix, 3, 3);
        for (int i = 0; i < row; i++) {

            sumPivot *= matrix[i][i];

        }
        // System.out.println(numSwap);
        // System.out.println(sumPivot);
        // System.out.println("Diswap: "+numSwap);
        result = (Math.pow(-1, numSwap) * sumPivot);
        return result;
    }

}
