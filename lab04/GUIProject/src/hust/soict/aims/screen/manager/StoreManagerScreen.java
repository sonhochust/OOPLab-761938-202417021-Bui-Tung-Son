package hust.soict.aims.screen.manager;

import hust.soict.aims.media.*;
import hust.soict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreManagerScreen extends JFrame {
    private Store store;

    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // -------- NORTH --------
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Refresh the store view
                dispose();
                new StoreManagerScreen(store);
            }
        });
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            dispose();
            new AddBookToStoreScreen(store);
        });

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store);
        });

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });

        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        return header;
    }

    // -------- CENTER --------
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        JScrollPane scrollPane = new JScrollPane(center);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);
        return wrapper;
    }

    // -------- MAIN --------
    public static void main(String[] args) {
        Store store = new Store();

        // Add some sample data
        store.addMedia(new DigitalVideoDisc("Harry Potter and the Philosopher's Stone (2001)",
                "Fantasy", "Chris Columbus", 152, 3.0f));
        store.addMedia(new DigitalVideoDisc("Harry Potter and the Chamber of Secrets (2002)",
                "Fantasy", "Chris Columbus", 161, 3.5f));
        store.addMedia(new DigitalVideoDisc("Harry Potter and the Prisoner of Azkaban (2004)",
                "Fantasy", "Alfonso Cuarón", 142, 5.0f));
        store.addMedia(new DigitalVideoDisc("Harry Potter and the Goblet of Fire (2005)",
                "Fantasy", "Mike Newell", 157, 4.5f));
        store.addMedia(new DigitalVideoDisc("Fetch the Bolt Cutters",
                "Music", "Fiona Apple", 60, 10.39f));
        store.addMedia(new DigitalVideoDisc("Future Nostalgia",
                "Music", "Dua Lipa", 37, 9.6f));

        Book book1 = new Book("The Hunger Games", "Fiction", 5.5f);
        store.addMedia(book1);
        Book book2 = new Book("Catching Fire", "Fiction", 4.9f);
        store.addMedia(book2);
        Book book3 = new Book("Mockingjay", "Fiction", 5.1f);
        store.addMedia(book3);

        SwingUtilities.invokeLater(() -> new StoreManagerScreen(store));
    }
}