package hust.soict.aims.screen.customer.controller;

import hust.soict.aims.cart.Cart;
import hust.soict.aims.store.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.IOException;

public class ViewStoreController {

    @FXML
    private GridPane gridPane;

    private Store store;
    private Cart cart;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        final String ITEM_FXML =
                "/hust/soict/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(ITEM_FXML));
                ItemController itemController = new ItemController();
                loader.setController(itemController);
                AnchorPane anchorPane = loader.load();
                itemController.setData(store.getItemsInStore().get(i), cart);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 7 — Chuyển sang Cart Screen
    @FXML
    void btnViewCartPressed() {
        try {
            final String CART_FXML =
                    "/hust/soict/aims/screen/customer/view/Cart.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(CART_FXML));
            CartController cartController = new CartController(store, cart);
            loader.setController(cartController);
            Parent root = loader.load();

            Stage stage = (Stage) gridPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}