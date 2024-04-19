package Controller;

import DAO.User_DAO;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {
    int managercheck = 1;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField manaccount;
    @FXML
    private TextField manapassword;
    @FXML
    private ImageView klara;  // =  new ImageView(new Image("380263196_1029616164859701_6755985423599262347_n.jpg"));






    @FXML
    protected void testklaralefttoright(){
        managercheck = 2;
        //staffbutton.setDisable(true);
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.7), klara);
        trans.setByX(720);
        //trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.play();
    }
    @FXML
    protected void testklararighttoleft(){
        managercheck = 1;
        //staffbutton.setDisable(true);
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.7), klara);
        trans.setByX(-720);
        //trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.play();
    }


    @FXML

    protected void checklogin(){
        String account = manaccount.getText() ;
        String pass = manapassword.getText()  ;

        //ArrayList<User> a = User_DAO.getInstance().findByCondition("userName = " + account + "And Where password = " + pass);
//        String condition = "userName = ? AND password = ?";
//        ArrayList<User> a = User_DAO.getInstance().checklg(condition, account, pass);

        String condition = "userName = " + account + " AND password = " + pass;

        if(User_DAO.getInstance().findByCondition(condition) == null){
            System.out.println("null");
        }
        else{
            System.out.println("not null");
        }


//        ArrayList<User> b = User_DAO.getInstance().findAll();
//        for (User user : b) {
//            System.out.println("Account: " + user.getUserName() + ", Password: " + user.getPassword());
//        }
//        System.out.println("text    Account: " + account + ", Password: " + pass);

//        if(a != null) {
//            if (a.isEmpty()) {
//                System.out.println("Fail");
//            } else {
//                System.out.println("OK ngon vcl");
//            }
//        } else {
//            System.out.println("da co loi");
//        }
    }
}