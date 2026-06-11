package hust.soict.swing;

import javax.swing.*;
import java.awt.*;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    public NumberGrid() {
        // Khởi tạo text field và căn lề phải
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Tạo panel chứa các nút bấm với bố cục lưới 4 hàng x 3 cột
        JPanel panelButtons = new JPanel(new GridLayout(4, 3));
        addButtons(panelButtons);

        // Thiết lập container chính và thêm các thành phần vào vị trí
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(tfDisplay, BorderLayout.NORTH);
        cp.add(panelButtons, BorderLayout.CENTER);

        // Thiết lập các thuộc tính cho cửa sổ hiển thị
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(200, 200);
        setVisible(true);
    }

    // Hàm bổ trợ để khởi tạo và thêm các nút vào panel
    private void addButtons(JPanel panelButtons) {
        // 1. Tạo instance của ButtonListener và truyền cửa sổ hiện tại (this) vào
        ButtonListener listener = new ButtonListener(this);

        // 2. Khởi tạo các nút số từ 0 đến 9 và đăng ký listener
        for (int i = 0; i < 10; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            btnNumbers[i].addActionListener(listener); // Đăng ký sự kiện ở đây
        }

        // 3. Khởi tạo các nút chức năng và đăng ký listener
        btnDelete = new JButton("DEL");
        btnDelete.addActionListener(listener); // Đăng ký sự kiện cho nút DEL

        btnReset = new JButton("C");
        btnReset.addActionListener(listener);  // Đăng ký sự kiện cho nút C

        // 4. Thêm các nút vào panelButtons theo đúng thứ tự bố cục lưới (4x3)
        // Hàng 1: 1, 2, 3
        panelButtons.add(btnNumbers[1]);
        panelButtons.add(btnNumbers[2]);
        panelButtons.add(btnNumbers[3]);

        // Hàng 2: 4, 5, 6
        panelButtons.add(btnNumbers[4]);
        panelButtons.add(btnNumbers[5]);
        panelButtons.add(btnNumbers[6]);

        // Hàng 3: 7, 8, 9
        panelButtons.add(btnNumbers[7]);
        panelButtons.add(btnNumbers[8]);
        panelButtons.add(btnNumbers[9]);

        // Hàng 4: DEL, 0, C
        panelButtons.add(btnDelete);
        panelButtons.add(btnNumbers[0]);
        panelButtons.add(btnReset);
    }
    // Getter
    public JTextField getTfDisplay() {
        return tfDisplay;
    }

    public JButton[] getBtnNumbers() {
        return btnNumbers;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    // Hàm main để chạy thử giao diện
    public static void main(String[] args) {
        new NumberGrid();
    }
}
