package hust.soict.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton radioPen;

    @FXML
    private RadioButton radioEraser;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Chỉ vẽ khi tọa độ x > 0 (trong vùng Pane)
        // event.getX() và event.getY() là tọa độ TRONG Pane
        // nên luôn >= 0 nếu chuột trong Pane
        if (event.getX() >= 0 && event.getY() >= 0
                && event.getX() <= drawingAreaPane.getWidth()
                && event.getY() <= drawingAreaPane.getHeight()) {
            Color color = radioEraser.isSelected()?Color.WHITE:Color.BLACK; // nut eraser duoc chon thi ve mau trang, pen thi  mau den
            Double radius =  radioEraser.isSelected()? 10.0 : 4.0; // ban kinh eraser to hon cho de xoa
            Circle newCircle = new Circle(
                    event.getX(), event.getY(), radius, color
            );
            drawingAreaPane.getChildren().add(newCircle);
        }
    }

    
}