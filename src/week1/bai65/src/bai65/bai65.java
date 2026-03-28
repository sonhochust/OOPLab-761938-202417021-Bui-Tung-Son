package bai65;
import java.util.Scanner;

public class bai65 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Moi nhap so so hang: ");
		int n = sc.nextInt();
		int[] a = new int[n];
		long sum=0;
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
			sum+=a[i];
		}
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(a[i]>a[j]) {
					int t=a[i];
					a[i]=a[j];
					a[j]=t;
				}
			}
		}
		System.out.print("Day so sau sap xep: ");
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.print("\n");
		System.out.println("Tong = " + sum);
		System.out.println("Trung binh = " + (sum/n));
	}
}
