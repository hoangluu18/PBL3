package Controller;

import Model.Product;
import com.gluonhq.charm.glisten.control.Avatar;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class managerController {

    Product test = new Product(1, "test", 100, "black", "big", 1, "nhu lol", "src/main/resources/Picture/Ava.jpg",1);
    @FXML Avatar ava;
    @FXML
    private AnchorPane anchorHome,  anchorStaff, anchorBill, getAnchorProduct;
    @FXML
    private Button staffBtn;
    @FXML
    private Button productBtn;
    @FXML
    private Button billBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Pane switch_pane;

        @FXML
        public void anchorProductappear() throws IOException {
            anchorStaff.setVisible(false);
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(productBtn.getLayoutX() - switch_pane.getLayoutX());
            tt.play();
            switch_pane.setPrefWidth(productBtn.getWidth());
            getAnchorProduct.setVisible(true);
            menuDisplayCard();
        }

        @FXML
        public void anchorHomeappear(){
            anchorStaff.setVisible(false);
            anchorBill.setVisible(false);
            anchorHome.setVisible(true);
            getAnchorProduct.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(0);
            tt.play();
            switch_pane.setPrefWidth(homeBtn.getWidth());
        }
        @FXML
        public void anchorStaffappear(){
            anchorStaff.setMinWidth(87.5);
            anchorStaff.setMaxWidth(87.5);
            anchorStaff.setMinHeight(53);
            anchorStaff.setMaxHeight(53);
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            anchorStaff.setVisible(true);
            getAnchorProduct.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(staffBtn.getLayoutX() - switch_pane.getLayoutX());
            tt.play();
            switch_pane.setPrefWidth(staffBtn.getWidth());
        }
        @FXML
        public void anchorBillappear(){
            anchorBill.setMinWidth(67);
            anchorBill.setMaxWidth(67);
            anchorBill.setMinHeight(53);
            anchorBill.setMaxHeight(53);
            anchorStaff.setVisible(false);
            anchorHome.setVisible(false);
            anchorBill.setVisible(true);
            getAnchorProduct.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(billBtn.getLayoutX() - switch_pane.getLayoutX());
            tt.play();
            switch_pane.setPrefWidth(billBtn.getWidth());
        }

        @FXML
        public void show() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose");
            File selected = fileChooser.showOpenDialog(null);

            if (selected != null) {
                // Tạo một Image từ đường dẫn của tệp đã chọn
                Image image = new Image(selected.toURI().toString());
                // Hiển thị hình ảnh trong ImageView
            }
        }
    public void menuDisplayCard() throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(this.getClass().getResource("cardProduct.fxml"));
        AnchorPane pane = (AnchorPane) load.load();
        cardProductController cardC = (cardProductController) load.getController();
        cardC.setData((test));
    }

}



