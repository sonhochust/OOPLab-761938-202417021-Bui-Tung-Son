package hust.soict.aims.screen.manager;

import hust.soict.aims.media.CompactDisc;
import hust.soict.aims.media.Track;
import hust.soict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfArtist, tfDirector, tfCost;
    private JPanel tracksPanel;
    private ArrayList<JTextField[]> trackFields = new ArrayList<>();

    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add Compact Disc");

        tfTitle    = addFormRow("Title:",    0);
        tfCategory = addFormRow("Category:", 1);
        tfArtist   = addFormRow("Artist:",   2);
        tfDirector = addFormRow("Director:", 3);
        tfCost     = addFormRow("Cost ($):", 4);

        // Tracks section
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.WEST;

        JLabel tracksLabel = new JLabel("Tracks:");
        tracksLabel.setFont(new Font(tracksLabel.getFont().getName(), Font.BOLD, 13));
        formPanel.add(tracksLabel, gbc);

        gbc.gridy = 6; gbc.fill = GridBagConstraints.HORIZONTAL;
        tracksPanel = new JPanel();
        tracksPanel.setLayout(new BoxLayout(tracksPanel, BoxLayout.Y_AXIS));
        formPanel.add(tracksPanel, gbc);

        gbc.gridy = 7; gbc.fill = GridBagConstraints.NONE;
        JButton addTrackBtn = new JButton("+ Add Track");
        addTrackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrackRow();
            }
        });
        formPanel.add(addTrackBtn, gbc);

        // Start with one track row
        addTrackRow();
    }

    private void addTrackRow() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField trackTitle = new JTextField(12);
        JTextField trackLength = new JTextField(5);
        row.add(new JLabel("Title:"));
        row.add(trackTitle);
        row.add(new JLabel("Length (sec):"));
        row.add(trackLength);
        tracksPanel.add(row);
        trackFields.add(new JTextField[]{trackTitle, trackLength});
        tracksPanel.revalidate();
        tracksPanel.repaint();
    }

    @Override
    protected boolean onSubmit() {
        try {
            String title    = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String artist   = tfArtist.getText().trim();
            String director = tfDirector.getText().trim();
            float cost      = Float.parseFloat(tfCost.getText().trim());

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            CompactDisc cd = new CompactDisc(0, title, category, cost, director, artist);

            for (JTextField[] tf : trackFields) {
                String tTitle = tf[0].getText().trim();
                String tLenStr = tf[1].getText().trim();
                if (!tTitle.isEmpty() && !tLenStr.isEmpty()) {
                    int tLen = Integer.parseInt(tLenStr);
                    cd.addTrack(new Track(tTitle, tLen));
                }
            }

            store.addMedia(cd);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost and Track Length must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}