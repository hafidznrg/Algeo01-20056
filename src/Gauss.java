public class Gauss extends Utils {
    public static void main(String[] args){
        //double[][] matrix = { { 1.00, 2.00, 3.00 }, { 0.00, -3.00, -6.00 }, { 0.00, -6.00, -12.00 } };
        //double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00, 0.00, 1.00 } };
        //double[][] matrix = { { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 } };
        //double[][] matrix = { { 1.00, 3.00, 14.00 }, { -7.00, -1.00,10.00 } };
        double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00, 8.00, 9.00 } };

        printMatrix(matrix, 3, 3);
        println("Matriks eselon barisnya adalah : ");
        double[][] baru = gauss(matrix, 3, 3);
        println("\nHasil: ");
        printMatrix(baru, 3, 3);
    }

    public static void printMatrix(double[][] Matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                print(Matrix[i][j] + " ");
            }
            println();
        }
    }

    public static double[][] gauss(double[][] matrix,  int row, int col){
        /*jika ukuran colom <= baris*/
        if (col <= row){
            for (int k = 0; k < col-1; k++){
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
                println("matrix setelah ditukar");
                printMatrix(matrix, 3, 3);
                //melakukan pembagian pada baris pivot
                double pivot = matrix[k][k];
                if (Math.abs(pivot) < 1.0e-12){
                    continue;
                }
                else{
                    println("ini pivot " + pivot);
                    for (int j = k; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }
                    println("matrix setelah dibagi pivot");
                    printMatrix(matrix, 3, 3);
                    println();


                    //melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = k+1; i < row; i++) {
                        if ((i == k) || matrix[i][k] == 0){
                            continue;
                        }
                        double factor = matrix[i][k];
                        println("ini faktornya "+factor);
                        for (int j = k; j < col ; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                            //println("matix setelah dikurangi faktor "+matrix[i][j]);
                        }
                    }
                    println("matrix setelah dieliminasi");
                    printMatrix(matrix, 3, 3);



                }

            }

        }
        else{
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
                println("matrix setelah ditukar");
                printMatrix(matrix, 3, 3);
                //melakukan pembagian pada baris pivot
                double pivot = matrix[k][k];
                if (Math.abs(pivot) < 1.0e-12){
                    continue;
                }
                else{
                    println("ini pivot " + pivot);
                    for (int j = k; j < col; j++) {
                        matrix[k][j] = matrix[k][j] / pivot;
                    }
                    println("matrix setelah dibagi pivot");
                    printMatrix(matrix, 3, 3);
                    println();


                    //melakukan eliminasi pada baris bawah dan atasnya agar bernilai = 0
                    for (int i = k+1; i < row; i++) {
                        if ((i == k) || matrix[i][k] == 0){
                            continue;
                        }
                        double factor = matrix[i][k];
                        println("ini faktornya "+factor);
                        for (int j = k; j < col ; j++) {
                            matrix[i][j] = (matrix[i][j] - (factor * matrix[k][j]));
                            //println("matix setelah dikurangi faktor "+matrix[i][j]);
                        }
                    }
                    println("matrix setelah dieliminasi");
                    printMatrix(matrix, 3, 3);



                }

            }
        }

        return matrix;
    }
}