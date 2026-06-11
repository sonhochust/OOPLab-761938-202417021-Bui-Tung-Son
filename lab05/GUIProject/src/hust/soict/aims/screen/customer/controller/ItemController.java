package hust.soict.aims.screen.customer.controller;

import hust.soict.aims.cart.Cart;
import hust.soict.aims.exception.PlayerException;
import hust.soict.aims.media.Media;
import hust.soict.aims.media.Playable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ItemController {
    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    private Media media;
    private Cart cart;

    public void setData(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        lblTitle.setText(media.getTitle());
        lblCost.setText("$" + media.getCost());

        if(media instanceof Playable){
            btnPlay.setVisible(true);
        }else{
            btnPlay.setVisible(false);
            HBox.setMargin(btnPlay, new Insets(0,0,0,60));
        }
    }

    @FXML
    void btnAddToCartClicked() {
        if (cart != null && media != null) {
            cart.addMedia(media);
        }
    }

    @FXML
    void btnPlayClicked() {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.ERROR);
                alert.setTitle("Playback Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
