package Controller;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;



import java.awt.*;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class managerController {




        //    public static int  currentposition = 1;
        @FXML
        private Label labelproduct;
        @FXML Avatar ava;
        @FXML
        private AnchorPane anchorHome,  anchorStaff, anchorBill , anchorproduct;




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
            anchorproduct.setVisible(false);
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
        public void anchorStaffappear(){
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            anchorproduct.setVisible(false);
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
        public void anchorBillappear(){
            anchorStaff.setVisible(false);
            anchorproduct.setVisible(false);
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

    public void anchorproductappear() {
        anchorStaff.setVisible(false);
        anchorHome.setVisible(false);
        anchorBill.setVisible(false);
        anchorproduct.setVisible(true);
    }


    private ImageView testimage = new ImageView();
        public void show() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose");
            File selected = fileChooser.showOpenDialog(null);

            if (selected != null) {
                // Tạo một Image từ đường dẫn của tệp đã chọn
                String absolutePath = selected.getAbsolutePath();
//                System.out.println(absolutePath);
//                //String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
//                Image image = new Image( absolutePath);
//                // Hiển thị hình ảnh trong ImageView
//                testimage.setImage(image);
                File file = new File(absolutePath);
                Image image = new Image(getClass().getResource(absolutePath).toExternalForm());
                testimage.setImage(image);
            }
        }


    }



