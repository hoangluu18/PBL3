package Controller;
import DAO.Manager_DAO;
import DAO.User_DAO;
import Model.Manager;
import Model.User;
import io.github.gleidson28.GNAvatarView;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    @FXML
    private GNAvatarView avatar;
    private int role = User.ADMIN;

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
    private TextField sign_name;
    public static int IdEmployeeCurrent;
    public static int IDManagerCurrent;
    public String newUserAvaPath;

    @FXML
    public void checklogin(javafx.event.ActionEvent actionEvent) {
        String account = log_account.getText() ;
        String pass = log_password.getText()  ;
        User_DAO user_dao = new User_DAO();
        String condition = "userName = '" + account + "' AND password = '" + User_DAO.encode(pass) + "' AND role = " + role + " AND is_active = 1";
        try {
            if(user_dao.findByCondition(condition) != null && this.role == User.ADMIN){
                String condition1 = "userName = '" + account + "' AND password = '" + User_DAO.encode(pass) + "' AND role = " + User.ADMIN;
                IDManagerCurrent = user_dao.findByCondition(condition1).get(0).getUser_id();
                Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/View/manager.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/manager.css").toExternalForm());
                stage.setTitle("Shop Management System");
                stage.setMinWidth(1512.0);
                stage.setMinHeight(982.0);
                stage.setScene(scene);
                stage.show();
                stage.setMaximized(true);
                this.sigin_btn.getScene().getWindow().hide();
            } else if (user_dao.findByCondition(condition) != null && this.role == User.EMPLOYEE){
                //save Employee ID current
                IdEmployeeCurrent = user_dao.findByCondition(condition).get(0).getUser_id();
                System.out.println(IdEmployeeCurrent);
                Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/View/Staff.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/staff.css").toExternalForm());
                stage.setTitle("Shop Management System");
                stage.setMinWidth(1512.0);
                stage.setMinHeight(982.0);
                stage.setScene(scene);
                stage.show();
                stage.setMaximized(true);
                this.sigin_btn.getScene().getWindow().hide();
            } else {
                this.notificationlABEL.setText("Invalid account or password!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println("Root cause:");
            cause.printStackTrace();
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
        this.role = User.EMPLOYEE;
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
        this.role = User.ADMIN;
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
    public void choosePictureFromDialog() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose");
        File selected = fileChooser.showOpenDialog(null);
        File url = selected;
        try {
            String path = url.toURI().toURL().toString();

            newUserAvaPath = path;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void registration(ActionEvent actionEvent) throws MalformedURLException {// click button sign_up button
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
            String condition = "userName = '" + sign_account.getText() + "' AND password = '" + User_DAO.encode( sign_password.getText()) + "' AND role = " + User.ADMIN;
            int newManagerID =  User_DAO.getInstance().findByCondition(condition).get(0).getUser_id();
//            System.out.println(newManagerID);

            Manager manager = new Manager();
            manager.setName(sign_name.getText());
            manager.setManager_id(newManagerID);
            manager.setImage_path(newUserAvaPath);
            System.out.println(manager.getImage_path());


            Manager_DAO.getInstance().insertManager(manager);
            this.sign_account.setText("");
            this.sign_name.setText("");
            this.sign_password.setText("");
            this.alert = new Alert(Alert.AlertType.INFORMATION);
            this.alert.setTitle("Information Message");
            this.alert.setHeaderText((String)null);
            this.alert.setContentText("Successfully Sign Up!");
            this.alert.showAndWait();
        }
    }
}
