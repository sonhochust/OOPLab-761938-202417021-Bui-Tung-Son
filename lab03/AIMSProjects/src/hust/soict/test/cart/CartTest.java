/*package hust.soict.test.cart;

import hust.soict.aims.cart.Cart;
import hust.soict.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        // 1. Khởi tạo giỏ hàng mới
        Cart cart = new Cart();

        // 2. Tạo các đối tượng DVD mới
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);

        // 3. Thêm DVD vào giỏ hàng
        cart.addDigitalVideoDisc(dvd1);
        cart.addDigitalVideoDisc(dvd2);
        cart.addDigitalVideoDisc(dvd3);

        // 4. Kiểm tra phương thức in danh sách giỏ hàng (Bài 4)
        cart.print();

        // 5. Kiểm tra các phương thức tìm kiếm
        System.out.println("\n--- Testing Search ---");

        // Tìm kiếm theo ID (Giả sử ID của Lion King là 1)
        System.out.println("Search by ID (1):");
        cart.searchById(1);

        // Tìm kiếm theo Title
        System.out.println("\nSearch by Title ('Star Wars'):");
        cart.searchByTitle("Star Wars");

        System.out.println("\nSearch by Title ('Harry Potter'):");
        cart.searchByTitle("Harry Potter");

    }
}
*/
