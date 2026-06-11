package hust.soict.aims.screen.manager;

import hust.soict.aims.media.DigitalVideoDisc;
import hust.soict.aims.store.Store;

import javax.swing.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfDirector, tfLength, tfCost;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add Digital Video Disc");

        tfTitle    = addFormRow("Title:",    0);
        tfCategory = addFormRow("Category:", 1);
        tfDirector = addFormRow("Director:", 2);
        tfLength   = addFormRow("Length (min):", 3);
        tfCost     = addFormRow("Cost ($):", 4);
    }

    @Override
    protected boolean onSubmit() {
        try {
            String title    = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String director = tfDirector.getText().trim();
            int length      = Integer.parseInt(tfLength.getText().trim());
            float cost      = Float.parseFloat(tfCost.getText().trim());

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Length and Cost must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}