package bai66;
import java.util.Scanner;

public class bai66
 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so dong: ");
        int r = sc.nextInt();
        System.out.print("Nhap so cot: ");
        int c = sc.nextInt();
        int[][] matrix1 = new int[r][c];
        int[][] matrix2 = new int[r][c];
        int[][] sumMatrix = new int[r][c];
        System.out.println("Ma tran 1:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }
        System.out.println("Ma tran 2:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        System.out.println("Ma tran ket qua:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(sumMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        sc.close();
    }

}
