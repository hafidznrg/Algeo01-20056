public class Cofactor {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        printMatrix(matrix, 3, 3);
        int det = Determinan(matrix, 3);
        System.out.println("Determinan : " + det);
        System.out.println("Matriks kofaktornya adalah : ");
        int[][] baru = Calculate(matrix, 3);
        printMatrix(baru, 3, 3);
    }

    public static void printMatrix(int[][] Matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] CalculateOne(int[][] matrix, int n, int row, int col) {
        int[][] newMatrix = new int[n][n];
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

    public static int[][] Calculate(int[][] matrix, int n) {
        int[][] newMatrix = new int[n + 1][n + 1];
        int detOne;

        if (n == 1) {
            newMatrix[0][0] = matrix[0][0];
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int c = 1;
                    if ((i + j) % 2 == 1)
                        c = -1;

                    detOne = Determinan(CalculateOne(matrix, n, i + 1, j + 1), n - 1);
                    newMatrix[i][j] = c * detOne;
                }
            }
        }

        return newMatrix;
    }

    public static int Determinan(int[][] matrix, int n) {
        int det = 0;
        int detOne;

        if (n == 1) {
            det = matrix[0][0];
        } else {
            for (int i = 0; i < n; i++) {
                int c = 1;
                if (i % 2 == 1)
                    c = -1;

                detOne = Determinan(CalculateOne(matrix, n, 1, i + 1), n - 1);

                det += c * matrix[0][i] * detOne;
            }
        }

        return det;
    }
}
