public class GaussTriangle {
    public static void main(String[] args){
        double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00, 8.00, 9.00 } };
        //double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00, 0.00, 1.00 } };
        //double[][] matrix = { { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 } };
        printMatrix(matrix, 3, 3);
        System.out.println("Matriks eselon barisnya adalah : ");
        double[][] baru = GaussTriangle(matrix, 3, 3);
        printMatrix(baru, 3, 3);
    }

    public static void printMatrix(double[][] Matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] GaussTriangle(double[][] matrix,  int row, int col){
        for (int k = 0; k < row-1; k++){
            /*cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
            if (Math.abs(matrix[k][k]) < 1.0e-12 ){
                for (int i = k+1; i < row ; i++) {
                    if (Math.abs(matrix[i][k]) > Math.abs(matrix[k][k])){
                        double temp = matrix[k][i];
                        matrix[k][i] = matrix[i][k];
                        matrix[i][k] = temp;
                        break;
                    }
                }
            }
            System.out.println("matrix setelah ditukar");
            printMatrix(matrix, 3, 3);


            //melakukan eliminasi pada baris bawah agar bernilai = 0
            for (int i = k+1; i < row; i++) {
                if ( matrix[i][k] == 0){
                    continue;
                }
                double factor = matrix[i][k]/matrix[k][k];
                System.out.println("ini faktornya "+factor);
                for (int j = k; j < col ; j++) {

                    matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                    System.out.println("faktor * atasnya = " + (factor * matrix[k][j]));
                    System.out.println("matrix setelah dikurangi faktor "+matrix[i][j]);

                }
            }
            System.out.println("matrix setelah dieliminasi");
            printMatrix(matrix, 3, 3);



        }


        return matrix;

    }
}
