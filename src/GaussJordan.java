import java.util.Scanner;

public class GaussJordan {
    public static void main(String[] args){
        //double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00, -6.00, -12.00 } };
        //double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00, 0.00, 1.00 } };
        //double[][] matrix = { { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 } };
        //double[][] matrix = { { 1.00, 3.00, 14.00 }, { -7.00, -1.00,10.00 } };
        double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00, -6.00, -12.00 }, {4.00, 3.00, 2.00} };
        //printMatrix(matrix, 4, 3);
//        Scanner sc = new Scanner(System.in);
//        double[][] matrix = new double[6][6];
//        for (int i = 0; i < 6; i++){
//            for (int j = 0; j < 6; j++) {
//                matrix[i][j] = sc.nextDouble();
//            }
//        }
//        sc.close();
        System.out.println("Matriks eselon barisnya adalah : ");
        double[][] baru = gaussJordan(matrix);
        printMatrix(baru);
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

    public static double[][] gaussJordan(double[][] matrix){
        int row = matrix.length;
        int col = matrix[0]. length;
        /*untuk colom <= baris*/
        if (col <= row){
            for (int k = 0; k < col - 1; k++){
                /*cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (Math.abs(matrix[k][k]) < 1.0e-12 ){
                    for (int i = k+1; i < row ; i++) {
                        if (Math.abs(matrix[i][k]) > Math.abs(matrix[k][k])){
                            for (int j = 0; j <col; j++) {
                                double temp = matrix[k][j];
                                matrix[k][j] = matrix[i][j];
                                matrix[i][j] = temp;
                            }
                            break;
                        }
                    }
                }
                //System.out.println("matrix setelah ditukar");
                //printMatrix(matrix, 3, 3);
                //melakukan pembagian pada baris pivot
                double pivot = matrix[k][k];
                if (Math.abs(pivot) < 1.0e-12){
                    continue;
                }
                else{
                    //System.out.println("ini pivot " + pivot);
                    for (int j = k; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }
//                    System.out.println("matrix setelah dibagi pivot");
//                    printMatrix(matrix, 4, 3);
//                    System.out.println();


                    //melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = 0; i < row; i++) {
                        if ((i == k) || matrix[i][k] == 0){
                            continue;
                        }
                        double factor = matrix[i][k];
                        //System.out.println("ini faktornya "+factor);
                        for (int j = k; j < col ; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                            //System.out.println("matix setelah dikurangi faktor "+matrix[i][j]);
                        }
                    }
                    //System.out.println("matrix setelah dieliminasi");
                    //printMatrix(matrix, 3, 3);
                }

            }

        }
        else{
            //untuk kolom > baris
            for (int k = 0; k < row; k++){
                /*cek apakah pivot = 0, jika 0 maka swap dengan yang tidak 0 */
                if (Math.abs(matrix[k][k]) < 1.0e-12 ){
                    for (int i = k+1; i < row ; i++) {
                        if (Math.abs(matrix[i][k]) > Math.abs(matrix[k][k])){
                            for (int j = 0; j <col; j++) {
                                double temp = matrix[k][j];
                                matrix[k][j] = matrix[i][j];
                                matrix[i][j] = temp;
                            }
                            break;
                        }
                    }
                }
                //System.out.println("matrix setelah ditukar");
                //printMatrix(matrix, 3, 3);
                //melakukan pembagian pada baris pivot
                double pivot = matrix[k][k];
                if (Math.abs(pivot) < 1.0e-12){
                    continue;
                }
                else{
                    //System.out.println("ini pivot " + pivot);
                    for (int j = k; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }
                    //System.out.println("matrix setelah dibagi pivot");
                    //printMatrix(matrix, 3, 3);
                    //System.out.println();


                    //melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = 0; i < row; i++) {
                        if ((i == k) || matrix[i][k] == 0){
                            continue;
                        }
                        double factor = matrix[i][k];
                        // System.out.println("ini faktornya "+factor);
                        for (int j = k; j < col ; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                            //System.out.println("matix setelah dikurangi faktor "+matrix[i][j]);
                        }
                    }
                    //System.out.println("matrix setelah dieliminasi");
                    //printMatrix(matrix, 3, 3);



                }

            }
        }

        return matrix;
    }

    /*public static void spl(double[][] matrix){
       double newMatrix[][] =  GaussJordan(matrix, 4, 3);
       int col = matrix[0].length;
       int row = matrix.length;
       int j = 0;
       boolean equalZero = true;
       while ( equalZero && (j < (col -1))){
           if (newMatrix[row-1][j] != 0){
               equalZero = false;
           }
       }
       boolean parametrik ==
       if (equalZero && newMatrix[row-1][col-1] == 0 || row < col-1){
           return parametrik
       }
       else if (equalZero && newMatrix[row-1][col-1] != 0){
           return tidakAdaSolusi
       }
       else{
           return; solusiUnik
       }
    }*/
}
