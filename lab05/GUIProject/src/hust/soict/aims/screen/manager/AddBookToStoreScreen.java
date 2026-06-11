package hust.soict.aims.screen.manager;

import hust.soict.aims.media.Book;
import hust.soict.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfCost, tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book");

        tfTitle    = addFormRow("Title:",    0);
        tfCategory = addFormRow("Category:", 1);
        tfCost     = addFormRow("Cost ($):", 2);
        tfAuthors  = addFormRow("Authors (comma-separated):", 3);
    }

    @Override
    protected boolean onSubmit() {
        try {
            String title    = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost      = Float.parseFloat(tfCost.getText().trim());
            String authorsRaw = tfAuthors.getText().trim();

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            Book book = new Book(title, category, cost);
            if (!authorsRaw.isEmpty()) {
                String[] authors = authorsRaw.split(",");
                for (String author : authors) {
                    book.addAuthor(author.trim());
                }
            }
            store.addMedia(book);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}