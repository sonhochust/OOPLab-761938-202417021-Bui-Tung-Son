package hust.soict;

import java.util.Scanner;
import hust.soict.aims.cart.Cart;
import hust.soict.aims.store.Store;
import hust.soict.aims.media.*;

public class AIMS {
    private static Store store = new Store();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewStore(sc);
                    break;
                case 2:
                    updateStore(sc);
                    break;
                case 3:
                    viewCart(sc);
                    break;
                case 0:
                    System.out.println("Exiting AIMS. Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose an option: ");
    }

    public static void viewStore(Scanner sc) {
        store.view();
        int choice;
        do {
            storeMenu();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the media: ");
                    String title = sc.nextLine();
                    Media found = store.searchByTitle(title);
                    if (found != null) {
                        System.out.println(found.toString());
                        mediaDetailsMenu(sc, found);
                    } else {
                        System.out.println("Media not found!");
                    }
                    break;
                case 2: // Add to cart
                    System.out.print("Enter the title of the media: ");
                    String t = sc.nextLine();
                    Media m = store.searchByTitle(t);
                    if (m != null) cart.addMedia(m);
                    else System.out.println("Media not found!");
                    break;
                case 3: // Play
                    System.out.print("Enter the title of the media: ");
                    String tp = sc.nextLine();
                    Media mp = store.searchByTitle(tp);
                    if (mp instanceof Playable) ((Playable) mp).play();
                    else System.out.println("This media cannot be played!");
                    break;
                case 4:
                    cart.print();
                    break;
            }
        } while (choice != 0);
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose an option: ");
    }

    public static void mediaDetailsMenu(Scanner sc, Media media) {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (media instanceof Playable) System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) cart.addMedia(media);
        else if (choice == 2 && media instanceof Playable) ((Playable) media).play();
    }

    public static void viewCart(Scanner sc) {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: // Filter
                    System.out.println("1. By ID / 2. By Title");
                    int f = sc.nextInt();
                    sc.nextLine();
                    if (f == 1) { /* Gọi cart.searchById */ }
                    else { /* Gọi cart.searchByTitle */ }
                    break;
                case 2:
                    System.out.println("1. By Title / 2. By Cost");
                    int s = sc.nextInt();
                    if (s == 1) cart.sortByTitle();
                    else cart.sortByCost();
                    break;
                case 3:
                    System.out.print("Enter title to remove: ");
                    String tr = sc.nextLine();
                    Media mr = store.searchByTitle(tr);
                    if (mr != null) cart.removeMedia(mr);
                    break;
                case 4:
                    System.out.print("Enter title to play: ");
                    String tp = sc.nextLine();
                    break;
                case 5:
                    cart.placeOrder();
                    break;
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose an option: ");
    }

    public static void updateStore(Scanner sc) {
        System.out.println("1. Add Media / 2. Remove Media");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Clear buffer

        if (choice == 1) {
            System.out.println("Choose type to add: 1. DVD / 2. Book / 3. CD");
            int type = sc.nextInt();
            sc.nextLine();

            // Những thông tin chung mà Media nào cũng có
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            System.out.print("Enter category: ");
            String category = sc.nextLine();
            System.out.print("Enter cost: ");
            float cost = sc.nextFloat();
            sc.nextLine();

            Media media = null;

            switch (type) {
                case 1: // DigitalVideoDisc
                    System.out.print("Enter director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter length: ");
                    int length = sc.nextInt();
                    sc.nextLine();
                    media = new DigitalVideoDisc(id, title, category, director, length, cost);
                    break;

                case 2: // Book
                    media = new Book(id, title, category, cost);
                    System.out.print("How many authors? ");
                    int numAuthors = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < numAuthors; i++) {
                        System.out.print("Enter author name " + (i+1) + ": ");
                        ((Book)media).addAuthor(sc.nextLine());
                    }
                    break;

                case 3: // CompactDisc
                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter director: ");
                    String cdDirector = sc.nextLine();
                    media = new CompactDisc(id, title, category, cost, cdDirector, artist);

                    System.out.print("How many tracks? ");
                    int numTracks = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < numTracks; i++) {
                        System.out.print("Track title: ");
                        String tTitle = sc.nextLine();
                        System.out.print("Track length: ");
                        int tLength = sc.nextInt();
                        sc.nextLine();
                        ((CompactDisc)media).addTrack(new Track(tTitle, tLength));
                    }
                    break;

                default:
                    System.out.println("Invalid type!");
                    return;
            }

            store.addMedia(media);

        } else if (choice == 2) {
            System.out.print("Enter the title to remove: ");
            String titleToRemove = sc.nextLine();
            Media found = store.searchByTitle(titleToRemove);
            if (found != null) {
                store.removeMedia(found);
            } else {
                System.out.println("Media not found!");
            }
        }
    }
}