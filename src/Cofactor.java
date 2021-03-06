public class Cofactor extends Utils{
    public static double[][] calculateOne(double[][] matrix, int row, int col) {
        // Mengembalikan matriks yang dimensinya lebih kecil satu kali, sesuai dengan aturan ekspansi kofaktor
        int n = matrix.length;
        double[][] newMatrix = new double[n-1][n-1];
        row -= 1;
        col -= 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < row) {
                    if (j < col) {
                        newMatrix[i][j] = matrix[i][j];
                    } else if (j > col) {
                        newMatrix[i][j - 1] = matrix[i][j];
                    }
                } else if (i > row) {
                    if (j < col) {
                        newMatrix[i - 1][j] = matrix[i][j];
                    } else if (j > col) {
                        newMatrix[i - 1][j - 1] = matrix[i][j];
                    }
                }
            }
        }

        return newMatrix;
    }

    public static double[][] cofactorMat(double[][] matrix) {
        // Mengembalikan matriks kofaktor dari matriks input
        int n = matrix.length;
        double[][] newMatrix = new double[n][n];
        double detOne;

        if (n == 1) {
            newMatrix[0][0] = matrix[0][0];
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int c = 1;
                    if ((i + j) % 2 == 1)
                        c = -1;

                    detOne = determinan(calculateOne(matrix, i + 1, j + 1));
                    newMatrix[i][j] = c * detOne;
                }
            }
        }

        return newMatrix;
    }

    public static double determinan(double[][] matrix) {
        // Menghitung determinan dari matriks input menggunakan metode ekspansi kofaktor
        int n = matrix.length;
        double det = 0;
        double detOne;

        if (n == 1) {
            det = matrix[0][0];
        } else {
            for (int i = 0; i < n; i++) {
                int c = 1;
                if (i % 2 == 1)
                    c = -1;

                detOne = determinan(calculateOne(matrix, 1, i + 1));

                det += c * matrix[0][i] * detOne;
            }
        }

        return det;
    }
}
