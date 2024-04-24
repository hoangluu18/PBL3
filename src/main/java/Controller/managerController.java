package Controller;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
public class managerController {


    @FXML Avatar ava;
    @FXML
    private AnchorPane anchorHome,  anchorStaff, anchorBill;
    @FXML
    private Button staffBtn;
    @FXML
    private Button productBtn;
    @FXML
    private Button billBtn;
    @FXML
    private ImageView testImage;
    @FXML
    private Button homeBtn;

        @FXML
        public void anchorHomeappear(){
            anchorStaff.setVisible(false);
            anchorBill.setVisible(false);
            anchorHome.setVisible(true);
        }
        @FXML
        public void anchorStaffappear(){
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            anchorStaff.setVisible(true);

        }
        @FXML
        public void anchorBillappear(){
            anchorStaff.setVisible(false);
            anchorHome.setVisible(false);
            anchorBill.setVisible(true);

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


    }



