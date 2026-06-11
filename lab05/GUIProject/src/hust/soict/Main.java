package hust.soict;

import hust.soict.aims.cart.Cart;
import hust.soict.aims.media.Book;
import hust.soict.aims.media.DigitalVideoDisc;
import hust.soict.aims.screen.manager.StoreManagerScreen;
import hust.soict.aims.store.Store;

import javax.swing.SwingUtilities;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo core data của ứng dụng
        Store store = new Store();
        Cart cart = new Cart();
        
        // 2. Thêm một số dữ liệu mẫu (Sample data) để test cho dễ
        initSampleData(store);

        // 3. Cho người dùng chọn chế độ chạy
        Scanner sc = new Scanner(System.in);
        System.out.println("===============================");
        System.out.println("    WELCOME TO AIMS PROJECT    ");
        System.out.println("===============================");
        System.out.println("1. Run Console Interface (Text)");
        System.out.println("2. Run GUI Interface (Window)");
        System.out.print("Please choose your UI (1 or 2): ");
        
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            // Chạy Console
            AIMS aimsConsole = new AIMS(store, cart);
            aimsConsole.start(sc);
        } else if (choice == 2) {
            // Chạy GUI (Window)
            System.out.println("Opening GUI Window...");
            SwingUtilities.invokeLater(() -> new StoreManagerScreen(store));
        } else {
            System.out.println("Invalid choice. System exited.");
        }
    }

    // Hàm phụ trợ để thêm dữ liệu mẫu
    private static void initSampleData(Store store) {
        store.addMedia(new DigitalVideoDisc("Harry Potter and the Philosopher's Stone (2001)", 
                "Fantasy", "Chris Columbus", 152, 3.0f));
        store.addMedia(new DigitalVideoDisc("Harry Potter and the Chamber of Secrets (2002)", 
                "Fantasy", "Chris Columbus", 161, 3.5f));
        
        Book book1 = new Book("The Hunger Games", "Fiction", 5.5f);
        book1.addAuthor("Suzanne Collins");
        store.addMedia(book1);
    }
}