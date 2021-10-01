public class Interpolation extends Utils {
    public static double estimate(double[] koef, double x) {
        int row = koef.length;
        double res = 0;

        for (int i = 0; i < row; i++) {
            res += (Math.pow(x, i) * koef[i]);
        }

        return res;
    }

    public static double[] polynomial(double[][] matrix) {
        int row = matrix.length;
        int col = matrix.length + 1;
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
        double[][] newMatrix = GaussJordan.gaussJordan(matrixPolynom);
        // dalam bentuk ax = b
        // menyalin matrix b ke dalam array
        double[] b = new double[row];
        for (int i = 0; i < row; i++) {
            b[i] = newMatrix[i][col - 1];
        }

        // Mengeluarkan persamaan polinomial
        boolean first = true;
        print("Persamaan polinomial yang diperoleh\ny =");
        for (int i = b.length - 1; i >= 0; i--) {
            if (!isZero(b[i])) {
                if (first)
                    first = false;
                else if (b[i] > 0 && !first)
                    print(" +");
                println(" %f", b[i]);
                if (i != 0) {
                    println(" x^%d", i);
                }
            }
        }
        println();

        return b;

    }

}
