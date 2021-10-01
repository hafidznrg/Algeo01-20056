public class GaussTriangle extends Utils {
    public static double determinan(double[][] matrix, int row, int col) {
        // Mengembalikan determinan dari matriks input menggunakan metode eliminasi
        // Gauss

        int numSwap = 0;

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
                            numSwap++;
                            break;
                        }
                    }
                }

                // melakukan eliminasi pada baris bawah agar bernilai = 0
                for (int i = k + 1; i < row; i++) {
                    if (isZero(matrix[i][k])) {
                        continue;
                    }
                    double factor = matrix[i][k] / matrix[k][k];
                    for (int j = k; j < col; j++) {

                        matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));

                    }
                }
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

                // melakukan eliminasi pada baris bawah agar bernilai = 0
                for (int i = k + 1; i < row; i++) {
                    if (isZero(matrix[i][k])) {
                        continue;
                    }
                    double factor = matrix[i][k] / matrix[k][k];
                    for (int j = k; j < col; j++) {

                        matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));

                    }
                }
            }

        }
        double result;
        double sumPivot = 1;
        for (int i = 0; i < row; i++) {

            sumPivot *= matrix[i][i];

        }
        result = (Math.pow(-1, numSwap) * sumPivot);
        return result;
    }

}
