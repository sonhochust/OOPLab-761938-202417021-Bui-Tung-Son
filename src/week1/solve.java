import java.util.Scanner;
public class solve {
    public void solve(double a, double b){
        if(a==0){
            if(b==0){
                System.out.println("Vo so nghiem");
            }else{
                System.out.println("Vo nghiem");
            }
        }else{
            double x=-b/a;
                System.out.println("x = "+x);
        }
    }
    public void solve(double a11, double a12, double b1, double a21, double a22, double b2){
        double d = a11 * a22- a12 * a21,d1 = b1 * a22 - b2 * a12,d2 = a11 * b2- a21 * b1;
        if(d!=0){
            System.out.println("x1 = "+(d1/d)+" x2 = "+(d2/d));
        }
        else{
            if(d1==0&&d2==0){
                System.out.println("Vo so nghiem");
            }else{
                System.out.println("Vo nghiem");
            }
        }
    }
    public void solve(double a, double b, double c){
        double delta=b*b-4*a*c;
        if(delta<0){
            System.out.println("Vo gnhiem");
        }else if(delta==0){
            System.out.println("x = "+ ((-b) / (2 * a)));
        }else{
            System.out.println("x1 = "+ ((-b + Math.sqrt(delta)) / (2 * a)) +" x2 = "+ ((-b - Math.sqrt(delta)) /(2 * a)));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve s = new solve();
        
        System.out.println("----- MENU -----");
        System.out.println("1. Giai phuong trinh bac nhat (ax + b = 0)");
        System.out.println("2. Giai he phuong trinh 2 an");
        System.out.println("3. Giai phuong trinh bac hai (ax^2 + bx + c = 0)");
        System.out.print("Chon loai phuong trinh (1-3): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Nhap a, b: ");
                s.solve(sc.nextDouble(), sc.nextDouble());
                break;
            case 2:
                System.out.print("Nhap a11, a12, b1, a21, a22, b2: ");
                s.solve(sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), 
                        sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
                break;
            case 3:
                System.out.print("Nhap a, b, c: ");
                s.solve(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
        sc.close();
    }
}