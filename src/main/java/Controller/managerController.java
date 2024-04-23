package Controller;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class managerController {




        //    public static int  currentposition = 1;
        @FXML
        private Label labelproduct;
        @FXML Avatar ava;
        @FXML
        private AnchorPane anchorHome,  anchorStaff, anchorBill;



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
    }



