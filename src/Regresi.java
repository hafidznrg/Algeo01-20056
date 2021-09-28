import java.io.*;
import java.util.*;

public class Regresi extends Menu {
    public static double[] solveRegresi(double[][] matrix, double[] y) {
        // buat matriks augmented [1|matrix|y] : temp
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] temp = new double[row][col + 2];
        for (int i = 0; i < row; i++) {
            temp[i][0] = 1;
        }
        for (int j = 1; j <= col; j++) {
            for (int i = 0; i < row; i++) {
                temp[i][j] = matrix[i][j - 1];
            }
        }
        for (int i = 0; i < row; i++) {
            temp[i][col + 1] = y[i];
        }
        // println("Matriks Augmented");
        // displayMat(temp);
        // println();

        // Membuat matrix SPL dengan menggunakan Normal Estimation Equation
        double[][] spl = new double[col + 1][col + 2];
        for (int i = 0; i <= col; i++) {
            for (int j = 0; j <= col + 1; j++) {
                spl[i][j] = 0;
                for (int k = 0; k < row; k++) {
                    spl[i][j] += temp[k][i] * temp[k][j];
                }
            }
        }
        println("\nDengan Normal Estimation Equation, diperoleh matrix SPL sebagai berikut");
        displayMat(spl);

        // Menyelesaikan matrix SPL dengan metode gauss jordan
        double[][] res = GaussJordan.gaussJordan(spl, col + 1, col + 2);
        // println("Hasil gauss jordan");
        // displayMat(res);

        // Mengambil nilai y saja
        double[] b = new double[col + 1];
        for (int i = 0; i <= col; i++) {
            b[i] = res[i][col + 1];
        }

        return b;
    }

    public static void driverRegresi() {
        println("Pilih Masukan\n1. Masukan dari keyboard\n2. Masukan dari file");
        int pilihan, n, m;
        double[][] mat, raw;
        double[] y;
        pilihan = sc.nextInt();
        while (pilihan < 1 || pilihan > 2) {
            println("Masukan salah, silakan ulangi lagi");
            pilihan = sc.nextInt();
        }

        if (pilihan == 1) {
            print("Masukkan banyak peubah x: ");
            n = sc.nextInt();
            print("Masukkan banyak persamaan: ");
            m = sc.nextInt();
            System.out.printf("Masukkan %d persamaan\n", m);
            mat = new double[m][n];
            y = new double[m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = sc.nextDouble();
                }
                y[i] = sc.nextDouble();
            }
        } else {
            print("Masukkan banyak peubah x: ");
            n = sc.nextInt();
            print("Masukkan banyak persamaan: ");
            m = sc.nextInt();
            raw = inputMatrixFile();
            mat = new double[m][n];
            y = new double[m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = raw[i][j];
                }
                y[i] = raw[i][raw[0].length - 1];
            }

        }

        // Menyelesaikan regresi linear berganda
        double[] res = solveRegresi(mat, y);

        // Mengeluarkan persamaan regresi linear berganda
        println("\nPersamaan regresi linear berganda yang diperoleh");
        System.out.printf("y = %f", res[0]);
        for (int i = 1; i < res.length; i++) {
            if (res[i] > 0) {
                System.out.printf(" + %f x%d", res[i], i);
            } else {
                System.out.printf(" %f x%d", res[i], i);
            }
        }
        println("\n\nMenaksir nilai fungsi");
        System.out.printf("Masukkan %d peubah yang akan ditaksir nilai fungsinya\n", res.length - 1);
        double[] taksir = new double[res.length];
        for (int i = 0; i < res.length - 1; i++) {
            taksir[i] = sc.nextDouble();
        }
        double result = res[0];
        for (int i = 0; i < res.length - 1; i++) {
            result += res[i + 1] * taksir[i];
        }
        System.out.printf("Nilai taksirannya adalah %f\n\n", result);

        // Memilih menyimpan di file atau tidak
        displayMenuOutput();
        int choice = choose(1, 2);
        if (choice == 1) {
            try {
                print("Masukkan path file yang dituju\n> ");
                String path = sc.next();
                FileWriter myWriter = new FileWriter(path);
                myWriter.write("Persamaan regresi linear berganda yang diperoleh\ny = ");
                myWriter.write(Double.toString(res[0]));
                for (int i = 1; i < res.length; i++) {
                    if (res[i] > 0) {
                        myWriter.write(" + ");
                    }
                    myWriter.write(Double.toString(res[i]));
                    myWriter.write("x");
                    myWriter.write(Integer.toString(i));
                }
                // myWriter.write("\n");
                myWriter.write("\nNilai taksirannya adalah ");
                myWriter.write(Double.toString(result));
                myWriter.close();
                println("Berhasil menuliskan pada " + path);
            } catch (IOException e) {
                println("Terjadi error.");
            }
        }
    }

    public static double[][] inputMatrixFile() {
        String fileName;
        int[] rowsCols;
        double[][] mat;

        fileName = inputFileName();
        rowsCols = FileReadWrite.calcRowsCols(fileName);
        mat = FileReadWrite.readFile(fileName, rowsCols[0], rowsCols[1]);

        return mat;
    }

    public static String inputFileName() {
        String fileName;
        // To check whether the file is exist or not
        FileReader fr = null;

        println("Masukkan nama file");
        print("> ");
        fileName = sc.next();
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException fe) {
            println("File tidak ditemukan.");
            fileName = inputFileName();
        }

        return fileName;
    }

    public static void main(String[] args) {
        driverRegresi();
    }
}
