package hust.soict.aims.screen.manager;

import hust.soict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JPanel formPanel;

    public AddItemToStoreScreen(Store store, String title) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createMenuBar(), BorderLayout.NORTH);

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel wrapper = new JPanel(new BorderLayout());
        JLabel heading = new JLabel(title);
        heading.setFont(new Font(heading.getFont().getName(), Font.BOLD, 22));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        wrapper.add(heading, BorderLayout.NORTH);
        wrapper.add(formPanel, BorderLayout.CENTER);
        wrapper.add(createSubmitButton(), BorderLayout.SOUTH);

        cp.add(wrapper, BorderLayout.CENTER);

        setTitle("Store - " + title);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            dispose();
            new StoreManagerScreen(store);
        });
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> { dispose(); new AddBookToStoreScreen(store); });
        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> { dispose(); new AddCompactDiscToStoreScreen(store); });
        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> { dispose(); new AddDigitalVideoDiscToStoreScreen(store); });

        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    protected JTextField addFormRow(String labelText, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = row; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        JTextField field = new JTextField(20);
        formPanel.add(field, gbc);
        return field;
    }

    private JPanel createSubmitButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btn = new JButton("Add to Store");
        btn.setFont(new Font(btn.getFont().getName(), Font.BOLD, 14));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onSubmit()) {
                    JOptionPane.showMessageDialog(AddItemToStoreScreen.this,
                            "Item added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new StoreManagerScreen(store);
                }
            }
        });
        panel.add(btn);
        return panel;
    }

    protected abstract boolean onSubmit();
}