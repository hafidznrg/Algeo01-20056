public class Cramer extends Utils {
    public static String[] cramerRule(double[][] matrix) {
        // Mengembalikan array of string berisi solusi dari SPL matriks
        // PREKONDISI: DETERMINAN MATRIKS != 0

        int row = matrix.length;
        int col = matrix[0].length;
        double[][] squareMat = squareMatFromAugmented(matrix);
        String[] result = new String[col-1];

        //model Ax = b
        //membuat matrix b dari matrix awal
        double[][] b = new double[row][1];
        for (int i = 0; i < row; i++){
            b[i][0] = matrix[i][col-1];
        }
        //menghitung nilai x[1 ... col-1]
        for (int k=0;k<(col-1);k++) {
            //membuat matrix a sementara untuk setiap colom
            double[][] a = new double[row][col-1];
            for (int i=0;i<row;i++) {
                for (int j=0;j<=(col-2); j++){
                    if (k == j){
                        a[i][j] = b[i][0];
                    }
                    else {
                        a[i][j] = matrix[i][j];
                    }
                }
            }
            //nilai x ke berapa
            int xNow = k+1;
            //menghitung nilai x[xNow] = determinan(A[k])/determinan(matrix origin)
            double x = Cofactor.determinan(a)/Cofactor.determinan(squareMat);
            result[k] = "Nilai X"+xNow+": "+x;
        }
        return result;
    }
}
