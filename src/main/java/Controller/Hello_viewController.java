package Controller;
import DAO.User_DAO;
import Database.JDBC_Util;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hello_viewController {
    @FXML
    private Button sigin_btn;
    @FXML
    private TextField mana_log_account;
    @FXML
    private TextField mana_log_password;
    private Connection connect;
    private PreparedStatement prepare;
    private Alert alert;
    private ResultSet result;



    @FXML
    public void checklogin(javafx.event.ActionEvent actionEvent) {
        String account = mana_log_account.getText() ;
        String pass = mana_log_password.getText()  ;
        User_DAO user_dao = new User_DAO();
        String condition = "userName" + " = " + "'" + account + "'" + "AND password = '" + pass + "'";
        if(user_dao.findByCondition(condition) != null){
            System.out.println("Login success");
        }
    }
}
