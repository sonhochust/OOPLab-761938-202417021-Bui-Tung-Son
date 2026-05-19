package hust.soict.aims.screen.manager;

import hust.soict.aims.media.Media;
import hust.soict.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show a dialog when Play is clicked
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Playing: " + media.getTitle());
                    dialog.setSize(400, 200);
                    dialog.setLocationRelativeTo(null);
                    dialog.setModal(true);

                    JPanel panel = new JPanel(new BorderLayout());
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);

                    // Capture play output
                    java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                    java.io.PrintStream ps = new java.io.PrintStream(baos);
                    java.io.PrintStream old = System.out;
                    System.setOut(ps);
                    ((Playable) media).play();
                    System.out.flush();
                    System.setOut(old);
                    textArea.setText(baos.toString());

                    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
                    JButton closeBtn = new JButton("Close");
                    closeBtn.addActionListener(ev -> dialog.dispose());
                    panel.add(closeBtn, BorderLayout.SOUTH);

                    dialog.add(panel);
                    dialog.setVisible(true);
                }
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
    }
}