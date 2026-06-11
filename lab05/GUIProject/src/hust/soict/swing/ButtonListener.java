package hust.soict.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private NumberGrid grid;

    public ButtonListener(NumberGrid grid) {
        this.grid = grid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        // Nếu chuỗi rỗng (đề phòng) thì không làm gì cả
        if (buttonText.isEmpty()) return;

        char c = buttonText.charAt(0);

        // 1. Nếu là số từ 0-9
        if (Character.isDigit(c)) {
            grid.getTfDisplay().setText(grid.getTfDisplay().getText() + buttonText);
        }
        // 2. Nếu là các nút chức năng (DEL hoặc C)
        else {
            switch (buttonText) {
                case "DEL":
                    String currentText = grid.getTfDisplay().getText();
                    if (currentText.length() > 0) {
                        // Cắt bỏ 1 ký tự cuối cùng bên phải
                        grid.getTfDisplay().setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;

                case "C":
                    // Xóa trắng toàn bộ màn hình hiển thị
                    grid.getTfDisplay().setText("");
                    break;
            }
        }
    }
}