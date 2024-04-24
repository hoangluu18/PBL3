package Controller;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private ImageView testImage;
    @FXML
    private Button staffBtn;
    @FXML
    private Button productBtn;
    @FXML
    private Button billBtn;
    @FXML
    private Button homeBtn;


    @FXML
//    public void anchorProductappear() {
//        anchorHome.setVisible(false);
//        anchorStaff.setVisible(false);
//        anchorBill.setVisible(false);
//        anchorproduct.setVisible(true);
//
//    }

        public void anchorHomeappear(){
            anchorStaff.setVisible(false);
            anchorBill.setVisible(false);
            anchorHome.setVisible(true);
//        if(1 - currentposition < 0) {
//            TranslateTransition trans = new TranslateTransition(Duration.seconds(0.1), anchorHome);
//            trans.setFromX(anchorHome.getWidth());
//            trans.setToX(0);
//            trans.play();
//        }
//        currentposition = 1;
        }
        @FXML
        public void anchorStaffappear(){
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            anchorStaff.setVisible(true);

//        if(3 - currentposition < 0) {
//            TranslateTransition trans = new TranslateTransition(Duration.seconds(0.1), anchorHome);
//            trans.setFromX(anchorHome.getWidth());
//            trans.setToX(0);
//            trans.play();
//
//        } else if (3 - currentposition > 0) {
//            TranslateTransition trans = new TranslateTransition(Duration.seconds(0.1), anchorHome);
//            trans.setFromX(-anchorHome.getWidth());
//            trans.setToX(0);
//            trans.play();
//
//        }
//        currentposition = 3;
        }
        @FXML
        public void anchorBillappear(){
            anchorStaff.setVisible(false);
            anchorHome.setVisible(false);
            anchorBill.setVisible(true);
//        if(4 - currentposition < 0) {
//            TranslateTransition trans = new TranslateTransition(Duration.seconds(0.1), anchorHome);
//            trans.setFromX(anchorHome.getWidth());
//            trans.setToX(0);
//            trans.play();
//
//        } else if (4 - currentposition > 0) {
//            TranslateTransition trans = new TranslateTransition(Duration.seconds(0.1), anchorHome);
//            trans.setFromX(-anchorHome.getWidth());
//            trans.setToX(0);
//            trans.play();
//
//        }
//        currentposition = 4;
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



