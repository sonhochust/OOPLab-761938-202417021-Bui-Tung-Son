package hust.soict.aims.cart;

import java.util.Collections;
import java.util.ArrayList;
import hust.soict.aims.media.Media;

public class Cart {

    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("The media has been added: " + media.getTitle());
        } else {
            System.out.println("The media is already in the cart: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("The media has been removed: " + media.getTitle());
        } else {
            System.out.println("The media is not in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    // Tìm kiếm theo Title
    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found match: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
    }

    public void searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found match: " + media.toString());
                return;
            }
        }
        System.out.println("No media found with ID: " + id);
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("The cart has been cleared.");
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart has been sorted by title.");
        this.print();
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart has been sorted by cost.");
        this.print();
    }

    public void placeOrder() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("***********************ORDER***********************");
            System.out.println("Order Success!");
            System.out.println("Total products: " + itemsOrdered.size());
            // Thêm dòng này để hiện tổng hóa đơn
            // System.out.println("Total cost: " + totalCost() + " $");

            itemsOrdered.clear();
            System.out.println("Cart has been cleared. Thanks for ordering!");
            System.out.println("***************************************************");
        }
    }
}