import java.io.*;
import java.util.*;

public class Regresi extends Menu {
    public static double[] solveRegresi(double[][] matrix, double[] y) {
        // matrix adalah matriks augmented
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

        double[][] spl = new double[col + 1][col + 2];
        for (int i = 0; i <= col; i++) {
            for (int j = 0; j <= col + 1; j++) {
                spl[i][j] = 0;
                for (int k = 0; k < row; k++) {
                    spl[i][j] += temp[k][i] * temp[k][j];
                }
            }
        }
        // println("Matriks SPL");
        // displayMat(spl);

        double[][] res = GaussJordan.gaussJordan(spl, col + 1, col + 2);
        // println("Hasil gauss jordan");
        // displayMat(res);
        double[] b = new double[col + 1];
        for (int i = 0; i <= col; i++) {
            b[i] = res[i][col + 1];
        }

        // println("Matriks B");
        // for (int i = 0; i <= col; i++) {
        // println(b[i]);
        // }

        return b;
    }

    public static void driverRegresi() {
        Scanner input = new Scanner(System.in);
        println("Pilih Masukan\n1. Masukan dari keyboard\n2. Masukan dari file");
        int pilihan, n, m;
        String fileName;
        double[][] mat, raw;
        double[] y;
        pilihan = input.nextInt();
        while (pilihan < 1 || pilihan > 2) {
            println("Masukan salah, silakan ulangi lagi");
            pilihan = input.nextInt();
        }

        if (pilihan == 1) {
            print("Masukkan banyak peubah x: ");
            n = input.nextInt();
            print("Masukkan banyak persamaan: ");
            m = input.nextInt();
            System.out.printf("Masukkan %d persamaan\n", m);
            mat = new double[m][n];
            y = new double[m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = input.nextDouble();
                }
                y[i] = input.nextDouble();
            }
        } else {
            print("Masukkan banyak peubah x: ");
            n = input.nextInt();
            print("Masukkan banyak persamaan: ");
            m = input.nextInt();
            print("Masukkan nama file: ");
            fileName = input.next();

            // Still have a problem
            raw = FileReadWrite.readFile(fileName, m + 1, n + 2);
            mat = new double[m][n];
            y = new double[m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = raw[i][j];
                }
                y[m] = raw[m][raw.length - 1];
            }

        }

        double[] res = solveRegresi(mat, y);

        println("Persamaan regresi yang diperoleh");
        System.out.printf("y = %.2f", res[0]);
        for (int i = 1; i < res.length; i++) {
            if (res[i] > 0) {
                System.out.printf(" + %.2f x%d", res[i], i);
            } else {
                System.out.printf(" %.2f x%d", res[i], i);
            }
        }
        println("\nMenaksir nilai fungsi");
        System.out.printf("Masukkan %d peubah yang akan ditaksir nilai fungsinya\n", res.length - 1);
        double[] taksir = new double[res.length];
        for (int i = 0; i < res.length - 1; i++) {
            taksir[i] = input.nextDouble();
        }
        double result = res[0];
        for (int i = 0; i < res.length - 1; i++) {
            result += res[i + 1] * taksir[i];
        }
        System.out.printf("Nilai taksirannya adalah %.2f\n", result);

    }

    public static void main(String[] args) {
        driverRegresi();
    }
}