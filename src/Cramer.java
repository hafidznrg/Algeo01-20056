public class Cramer extends Utils {
    public static void main(String[] args){
        //double[][] matrix = { { 1.00, 2.00, 3.00 }, { 4.00, 5.00, 6.00 }, { 7.00, 8.00, 9.00 } };
        //double[][] matrix = { { 1.00, 0.00, 0.00 }, { 0.00, 1.00, 0.00 }, { 0.00, 0.00, 1.00 } };
        //double[][] matrix = { { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 }, { 4.00, 4.00, 4.00 } };
        double[][] matrix = { { -1.00, 2.00, -3.00, 1.00 }, { 2.00, 0.00, 1.00, 0.00 }, { 3.00, -4.00, 4.00, 2.00 } };

        System.out.println("Matriks awalnya adalah : ");
        printMatrix(matrix);

        CramerRule(matrix);
    }

    public static void printRes(String[] args) {
        for (int i = 0; i < args.length; i++) {
            println(args[i]);
        }
    }

    public static void CramerRule(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        double determinan = Cofactor.determinan(matrix);

        if (determinan == 0){
            System.out.println("Determinan matrix ini = 0, silakan menggunakan cara lain");
        }
        else{
            //model Ax = b
            //membuat matrix b dari matrix awal
            double[][] b = new double[row][1];
            for (int i = 0; i < row; i++){
                b[i][0] = matrix[i][col-1];
            }
            //System.out.println("ini matriks b ");
            //printMatrix(b);
            //menghitung nilai x[1 ... col-1]
            for (int k = 0; k < col - 1; k++) {
                //membuat matrix a sementara untuk setiap colom
                double[][] a = new double[row][col-1];
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j <= col-2; j++){
                        if (k == j){
                            a[i][j] = b[i][0];
                        }
                        else {
                            a[i][j] = matrix[i][j];
                        }
                    }
                }
                //printMatrix(a);
                //nilai x ke berapa
                int xNow = k+1;
                //menghitung nilai x[xNow] = determinan(A[k])/determinan(matrix origin)
                // Not yet solve
                double x = Cofactor.determinan(a)/Cofactor.determinan(matrix);
                //System.out.println(x);
                System.out.println("Nilai X"+xNow+": "+x);
            }

        }


    }
}
