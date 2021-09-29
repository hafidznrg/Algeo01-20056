public class Interpolation extends Utils {
    public static void main(String[] args) {
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00,
        // 8.00, 9.00 } };
        // double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00,
        // 0.00, 1.00 } };
        // double[][] matrix = { { 8.00, 2.0794 }, { 9.00, 2.1972 }, { 9.50, 2.2513 } };
        double[][] matrix = { { 0.1, 0.003 }, { 0.3, 0.067 }, { 0.5, 0.148 }, { 0.7, 0.248 }, { 0.9, 0.370 },
                { 1.1, 0.518 }, { 1.3, 0.697 } };
        // double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00,
        // -6.00, -12.00 }, {4.00, 3.00, 2.00} };
        // printMatrix(matrix, 3, 3);
        // System.out.println("Matriks eselon barisnya adalah : ");
        // double[][] baru = GaussTriangle(matrix, 3, 3);
        // printMatrix(baru, 3, 3);
        // double dtrmn = determinan(matrix, 3, 3);
        // System.out.println("Nilai Determinannya; ");
        // System.out.println(dtrmn);
        // double result = polynomial(matrix, d2, 9.2);
        double[] koef = polynomial(matrix, matrix.length - 1);
        double result = estimate(koef, 0.2);
        println(result);
    }

    public static void printMatrix(double[][] Matrix) {
        int row = Matrix.length;
        int col = Matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                print(Matrix[i][j] + " ");
            }
            println();
        }
    }

    public static double estimate(double[] koef, double x) {
        int row = koef.length;
        double res = 0;

        for (int i = 0; i < row; i++) {
            res += (Math.pow(x, i) * koef[i]);
        }

        return res;
    }

    public static double[] polynomial(double[][] matrix, int n) {
        int row = n + 1;
        int col = n + 2;
        double[][] matrixPolynom = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == (col - 1)) {
                    matrixPolynom[i][j] = matrix[i][1];
                } else if (j == 0) {
                    matrixPolynom[i][0] = 1.00;
                } else {
                    matrixPolynom[i][j] = Math.pow(matrix[i][0], j);
                }
            }

        }
        // printMatrix(matrixPolynom);
        // spl
        double[][] newMatrix = GaussJordan.gaussJordan(matrixPolynom, matrixPolynom.length, matrixPolynom[0].length);
        // dalam bentuk ax = b
        // menyalin matrix b ke dalam array
        double[] b = new double[row];
        for (int i = 0; i < row; i++) {
            b[i] = newMatrix[i][col - 1];
        }
        print("y =");
        for (int i = b.length - 1; i >= 0; i--) {
            if (b[i] > 0 && i != b.length - 1) {
                print(" +");
            }
            System.out.printf(" %f", b[i]);
            if (i != 0) {
                System.out.printf(" x%d", i);
            }
        }
        println();

        return b;

    }

}
