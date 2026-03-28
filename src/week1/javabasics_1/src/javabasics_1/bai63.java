package javabasics_1;

import java.util.Scanner;

public class bai63{

	public static void main(String[] args) {
	
		System.out.println("Nhap so di friend");
		Scanner sc = new Scanner(System.in);
		int h;// chieu cao cua tam giac
		while(true) {
			h=sc.nextInt();
			if(h <= 10 && h >= 2) break;
		}
		for(int i = 1; i <= h; i ++) {
			for(int j = 0; j <= h-i; j ++) System.out.print(' ');
			for(int j=1; j<= (2 * (i - 1) + 1); j ++) System.out.print('*');
			System.out.print('\n');
		}
		sc.close();		
	}

}
