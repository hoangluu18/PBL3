package Controller;
import DAO.User_DAO;
import Database.JDBC_Util;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

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
                this.alert = new Alert(Alert.AlertType.ERROR);
                this.alert.setTitle("Error Message");
                this.alert.setHeaderText((String)null);
                this.alert.setContentText("Incorrect Username/Password");
                this.alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void staffStatus(ActionEvent actionEvent) {
    }

    @FXML
    public void managerStatus(ActionEvent actionEvent) {
    }

    @FXML
    public void switchPane(ActionEvent actionEvent) {
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
            this.alert = new Alert(Alert.AlertType.INFORMATION);
            this.alert.setTitle("Information Message");
            this.alert.setHeaderText((String)null);
            this.alert.setContentText("Successfully Sign Up!");
            this.alert.showAndWait();
        }
    }
}
