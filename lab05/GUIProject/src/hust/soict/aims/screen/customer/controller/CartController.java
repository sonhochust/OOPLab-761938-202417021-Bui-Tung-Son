package hust.soict.aims.screen.customer.controller;

import hust.soict.aims.cart.Cart;
import hust.soict.aims.exception.PlayerException;
import hust.soict.aims.media.Media;
import hust.soict.aims.media.Playable;
import hust.soict.aims.store.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label costLabel;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;

    private Store store;
    private Cart cart;

    private FilteredList<Media> filteredList;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        // 6.3 — Setup columns
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // 6.3 — Bọc ObservableList trong FilteredList để hỗ trợ filter (6.6)
        if (cart.getItemsOrdered() != null) {
            filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
            tblMedia.setItems(filteredList);
        }

        // 6.4 — Ẩn buttons ban đầu
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // Cập nhật total cost
        costLabel.setText(cart.totalCost() + " $");

        // 6.4 — ChangeListener khi chọn item trong bảng
        tblMedia.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> updateButtonBar(newVal));

        // 6.6 — ChangeListener cho filter text field
        tfFilter.textProperty().addListener((obs, oldVal, newVal) ->
                showFilteredMedia(newVal));
    }

    // 6.4
    void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            btnPlay.setVisible(media instanceof Playable);
        }
    }

    // 6.5 — Xóa media khỏi cart
    @FXML
    void btnRemovePressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            costLabel.setText(cart.totalCost() + " $");
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        }
    }

    // Play media được chọn
    @FXML
    void btnPlayPressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Playback Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    // Place order
    @FXML
    void btnPlaceOrderPressed() {
        cart.placeOrder();
        costLabel.setText("0 $");
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
    }

    // 6.6 — Filter media theo ID hoặc Title
    private void showFilteredMedia(String keyword) {
        if (filteredList == null) return;

        if (keyword == null || keyword.isEmpty()) {
            filteredList.setPredicate(p -> true);
            return;
        }

        if (radioBtnFilterId.isSelected()) {
            // Filter by ID
            filteredList.setPredicate(media -> {
                try {
                    int id = Integer.parseInt(keyword.trim());
                    return media.getId() == id;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
        } else {
            // Filter by Title
            filteredList.setPredicate(media ->
                    media.getTitle().toLowerCase()
                            .contains(keyword.toLowerCase()));
        }
    }

    // 7 — Chuyển sang Store Screen
    @FXML
    void btnViewStorePressed() {
        try {
            final String STORE_FXML =
                    "/hust/soict/aims/screen/customer/view/Store.fxml";
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(STORE_FXML));
            ViewStoreController controller =
                    new ViewStoreController(store, cart);
            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = (Stage) tblMedia.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}