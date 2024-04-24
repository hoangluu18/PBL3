package Controller;

import Model.Product;
import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
public class managerController {

    Product test = new Product(1, "test", 100, "black", "big", 1, "nhu lol", "/resources/Picture/Ava.jpg",1);
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
    private ImageView testImage;
    @FXML
    private Button buttonTest;
    @FXML
    private Button homeBtn;
    @FXML
    private GridPane gridCardPane;

    @FXML
    public void anchorProductappear() throws IOException {
        anchorStaff.setVisible(false);
        anchorBill.setVisible(false);
        anchorHome.setVisible(false);
        getAnchorProduct.setVisible(true);
        menuDisplayCard();
    }

    @FXML
    public void anchorHomeappear(){
        anchorStaff.setVisible(false);
        anchorBill.setVisible(false);
        anchorHome.setVisible(true);
        getAnchorProduct.setVisible(false);
    }
    @FXML
    public void anchorStaffappear(){
        anchorBill.setVisible(false);
        anchorHome.setVisible(false);
        anchorStaff.setVisible(true);
        getAnchorProduct.setVisible(false);

    }
    @FXML
    public void anchorBillappear(){
        anchorStaff.setVisible(false);
        anchorHome.setVisible(false);
        anchorBill.setVisible(true);
        getAnchorProduct.setVisible(false);
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
            testImage.setImage(image);
        }
    }
    public void menuDisplayCard() throws IOException {
        int r = 0;
        int c = 0;
        try{
            FXMLLoader load = new FXMLLoader();
            load.setLocation(this.getClass().getResource("cardProduct.fxml"));
            AnchorPane pane = (AnchorPane) load.load();
            cardProductController cardC = (cardProductController) load.getController();
            cardC.setData((test));

//            if (c == 5){
//                c = 0;
//                r++;
//            }
            this.gridCardPane.add(pane, ++c, r);
            GridPane.setMargin(pane, new Insets(10.0));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String choosePictureFromDialog() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose");
        File selected = fileChooser.showOpenDialog(null);
        File url = selected;
        try {
            String path = url.toURI().toURL().toString();
            Image imageForFile = new Image(selected.toURI().toURL().toString());
            testImage.setImage(imageForFile);
            return path;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}



