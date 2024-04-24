package Controller;
import DAO.User_DAO;
import Database.JDBC_Util;
import Model.User;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hello_viewController {
    @FXML
    private Button sigin_btn;
    private Connection connect;
    private PreparedStatement prepare;
    private Alert alert;
    private ResultSet result;
    @FXML
    private TextField log_account;
    @FXML
    private PasswordField log_password;
    @FXML
    private Button signup_btn;
    @FXML
    private PasswordField sign_password;
    @FXML
    private TextField sign_account;
    @FXML
    private Button staffBtn;
    @FXML
    private Button manaBtn;
    private int role = 0;
    @FXML
    private Pane highlightPane;
    private int switch_status = 0;
    @FXML
    private AnchorPane switch_pane;
    @FXML
    private AnchorPane loginForm;
    @FXML
    private Button switchBtn;
    @FXML
    private Label notificationlABEL;


    @FXML
    public void checklogin(javafx.event.ActionEvent actionEvent) {
        String account = log_account.getText() ;
        String pass = log_password.getText()  ;
        User_DAO user_dao = new User_DAO();
        String condition = "userName" + " = " + "'" + account + "'" + "AND password = '" + pass + "'";
        try {
            if(user_dao.findByCondition(condition) != null){
                this.alert = new Alert(Alert.AlertType.INFORMATION);
                this.alert.setTitle("Information Message");
                this.alert.setHeaderText((String)null);
                this.alert.setContentText("Successfully Login!");
                this.alert.showAndWait();
                Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("manager.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Shop Management System");
                stage.setMinWidth(1512.0);
                stage.setMinHeight(982.0);
                stage.setScene(scene);
                stage.show();
                this.sigin_btn.getScene().getWindow().hide();
            } else {
                this.notificationlABEL.setText("Invalid account or password!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void staffStatus(ActionEvent actionEvent) {
        staffBtn.getStyleClass().add("selected_btn");
        manaBtn.getStyleClass().remove("selected_btn");
        manaBtn.getStyleClass().add("switch-btn");
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), highlightPane);
        tt.setToX(staffBtn.getLayoutX() - manaBtn.getLayoutX());
        tt.play();
        highlightPane.setPrefWidth(staffBtn.getWidth());
        this.role = 1;
    }

    @FXML
    public void managerStatus(ActionEvent actionEvent) {
        manaBtn.getStyleClass().add("selected_btn");
        staffBtn.getStyleClass().remove("selected_btn");
        staffBtn.getStyleClass().add("switch-btn");
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), highlightPane);
        tt.setToX(0.0);
        tt.play();
        highlightPane.setPrefWidth(manaBtn.getWidth());
        this.role = 0;
    }

    @FXML
    public void switchPane(ActionEvent actionEvent) {
        if(switch_status == 0){
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), switch_pane);
            tt.setToX(loginForm.getLayoutX());
            tt.play();
            tt.setOnFinished(e -> {
                switchBtn.setText("LOG IN");
                switch_status = 1;
            });
        }else{
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), switch_pane);
            tt.setToX(0);
            tt.play();
            tt.setOnFinished(e -> {
                switchBtn.setText("CREATE ACCOUNT");
                switch_status = 0;
            });
        }
    }

    @FXML
    public void registration(ActionEvent actionEvent) {// click button sign_up button
        User user = new User();
        user.setUserName(sign_account.getText());
        user.setPassword(sign_password.getText());
        user.setRole(User.ADMIN);
        if(User_DAO.getInstance().insert(user) == User_DAO.isDuplicate){
            this.alert = new Alert(Alert.AlertType.ERROR);
            this.alert.setTitle("Error Message");
            this.alert.setHeaderText((String)null);
            this.alert.setContentText("Duplicate account, try again");
            this.alert.showAndWait();
        }
        else {
            this.sign_account.setText("");
            this.sign_password.setText("");
            this.alert = new Alert(Alert.AlertType.INFORMATION);
            this.alert.setTitle("Information Message");
            this.alert.setHeaderText((String)null);
            this.alert.setContentText("Successfully Sign Up!");
            this.alert.showAndWait();
        }
    }
}
