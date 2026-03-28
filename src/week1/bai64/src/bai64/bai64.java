package bai64;

import java.util.Scanner;

public class bai64 {
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static int getDaysInMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else {
            return 0; 
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap thang: ");
        int month = sc.nextInt();

        System.out.print("Nhap nam: ");
        int year = sc.nextInt();

        int days = getDaysInMonth(month, year);

        if (days == 0) {
            System.out.println("Thang khong hop le!");
        } else {
            System.out.println("So ngay trong thang la: " + days);
        }

        sc.close();
    }
}