package hust.soict.test.screen.customer.store;

import hust.soict.aims.cart.Cart;
import hust.soict.aims.screen.customer.controller.ViewStoreController;
import hust.soict.aims.store.Store;
import hust.soict.aims.media.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {
    private static Store store;
    private static Cart cart;  // thêm cart

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH =
                "/hust/soict/aims/screen/customer/view/Store.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource(STORE_FXML_FILE_PATH)
        );

        ViewStoreController viewStoreController =
                new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();
        // initialize() được JavaFX tự gọi bên trong fxmlLoader.load() — không cần gọi lại

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();  // khởi tạo cart

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addMedia(dvd1);

        Book book1 = new Book("Java Core", "Technology", 25.50f);
        book1.addAuthor("Cay Horstmann");
        store.addMedia(book1);

        launch(args);
    }
}