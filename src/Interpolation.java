public class Interpolation {
    public static void main(String[] args){
        //double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00, 8.00, 9.00 } };
        //double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00, 0.00, 1.00 } };
        double[][] matrix = { { 8.00, 2.0794 }, { 9.00, 2.1972 }, { 9.50, 2.2513 } };
        //double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00, -6.00, -12.00 }, {4.00, 3.00, 2.00} };
        //printMatrix(matrix, 3, 3);
        //System.out.println("Matriks eselon barisnya adalah : ");
        //double[][] baru = GaussTriangle(matrix, 3, 3);
        //printMatrix(baru, 3, 3);
        //double dtrmn = determinan(matrix, 3, 3);
        //System.out.println("Nilai Determinannya; ");
        //System.out.println(dtrmn);
        double result = polynomial(matrix, 2, 9.2);
        System.out.println(result);
    }



    public static void printMatrix(double[][] Matrix) {
        int row = Matrix.length;
        int col = Matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double polynomial(double[][] matrix, int n, double x){
        int row = n+1;
        int col = n+2;
        double[][] matrixPolynom = new double[row][col];
        for (int i = 0; i < row; i++ ){
            for (int j = 0; j < col; j++) {
                if ( j == (col -1)){
                    matrixPolynom[i][j] = matrix[i][1];
                }
                else if ( j == 0){
                    matrixPolynom[i][0] = 1.00;
                }
                else{
                    matrixPolynom[i][j] = Math.pow(matrix[i][0], j);
                }
            }

        }
        printMatrix(matrixPolynom);
        //spl
        double[][] newMatrix = GaussJordan.gaussJordan(matrixPolynom, matrixPolynom.length, matrixPolynom[0].length);
        //dalam bentuk ax = b
        //menyalin matrix b ke dalam array
        double[] b = new double[row];
        for (int i = 0; i < row; i++){
            b[i] = matrixPolynom[i][col-1];
        }
        double result = 0;
        //menghitung hasil prediksi
        for (int i = 0; i < row; i++){
            result = (result + (Math.pow(x, i) * b[i]));
        }
        //System.out.println(result);


        return result;

    }

}
