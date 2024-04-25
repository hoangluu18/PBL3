package Controller;

import DAO.Employee_DAO;
import DAO.User_DAO;
import Database.JDBC_Util;
import Model.Employee;
import Model.Product;
import Model.User;
import com.gluonhq.charm.glisten.control.Avatar;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;

import javax.xml.crypto.Data;

public class managerController implements Initializable {

    Product test = new Product(1, "test", 100, "black", "big", 1, "nhu lol", "src/main/resources/Picture/Ava.jpg",1);
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
    private Button homeBtn;
    @FXML
    private Pane switch_pane;
    // Data connect
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    //manager
    @FXML
    private ObservableList<Product> cardListData = FXCollections.observableArrayList();

    //employee
    @FXML
    private GridPane gridCardPane;
    private ObservableList<Employee> employeeList;
    @FXML
    private TableColumn<Employee, Integer>  staffidColumn;
    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<Employee, String> staffphoneComlumn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<Employee, String> staffaddressColumn;
    @FXML
    private TableColumn<Employee, String> staffnameColumn;
    @FXML
    private TableColumn<Employee, String> staffemailColumn;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField staffpasswordTextfield;
    @FXML
    private TextField staffaddressTextfield;
    @FXML
    private TextField staffidTextfield;
    @FXML
    private TextField staffphoneTextfield;
    @FXML
    private TextField staffemailTextfield;
    @FXML
    private TextField staffnameTextfield;
    private ArrayList<Employee> employeeData;
    @FXML
    private TableView<Employee> staff_table;

    @FXML
        public void anchorProductappear() throws IOException, SQLException {
            anchorStaff.setVisible(false);
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(productBtn.getLayoutX() - switch_pane.getLayoutX());
            tt.play();
            switch_pane.setPrefWidth(productBtn.getWidth());
            getAnchorProduct.setVisible(true);
//            menuDisplayCard();
        }

        @FXML
        public void anchorHomeappear(){
            anchorStaff.setVisible(false);
            anchorBill.setVisible(false);
            anchorHome.setVisible(true);
            getAnchorProduct.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(0);
            tt.play();
            switch_pane.setPrefWidth(homeBtn.getWidth());
        }
        @FXML
        public void anchorStaffappear(){
            anchorStaff.setMinWidth(87.5);
            anchorStaff.setMaxWidth(87.5);
            anchorStaff.setMinHeight(53);
            anchorStaff.setMaxHeight(53);
            anchorBill.setVisible(false);
            anchorHome.setVisible(false);
            anchorStaff.setVisible(true);
            getAnchorProduct.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(staffBtn.getLayoutX() - switch_pane.getLayoutX());
            tt.play();
            switch_pane.setPrefWidth(staffBtn.getWidth());
        }
        @FXML
        public void anchorBillappear(){
            anchorBill.setMinWidth(67);
            anchorBill.setMaxWidth(67);
            anchorBill.setMinHeight(53);
            anchorBill.setMaxHeight(53);
            anchorStaff.setVisible(false);
            anchorHome.setVisible(false);
            anchorBill.setVisible(true);
            getAnchorProduct.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
            tt.setToX(billBtn.getLayoutX() - switch_pane.getLayoutX());
            tt.play();
            switch_pane.setPrefWidth(billBtn.getWidth());
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
        }
    }

//    public ObservableList<Product> menuGetData() throws SQLException {
//
//        String sql = "SELECT * FROM product";
//
//        ObservableList<Product> listData = FXCollections.observableArrayList();
//        connect = JDBC_Util.getConnection();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            Product prod;
//
//            while (result.next()) {
//                prod = new Product(
//                        result.getInt("product_id"),
//                        result.getString("name"),
//                        result.getInt("price"),
//                        result.getString("color"),
//                        result.getString("size"),
//                        result.getInt("quantity"),
//                        result.getString("description"),
//                        result.getString("image"),
//                        result.getInt("type_id")
//                );
//
//                listData.add(prod);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return listData;
//    }
//
//    public void menuDisplayCard() throws IOException, SQLException {
//        cardListData.clear();
//        cardListData.addAll(menuGetData());
//
//        int row = 0;
//        int column = 0;
//
//        gridCardPane.getChildren().clear();
//        gridCardPane.getRowConstraints().clear();
//        gridCardPane.getColumnConstraints().clear();
//
//        for (int q = 0; q < cardListData.size(); q++) {
//
//            try {
//                FXMLLoader load = new FXMLLoader();
//                load.setLocation(getClass().getResource("cardProduct.fxml"));
//                AnchorPane pane = load.load();
//                cardProductController cardC = load.getController();
//                cardC.setData(cardListData.get(q));
//
//                if (column == 6) {
//                    column = 0;
//                    row += 1;
//                }
//
//                gridCardPane.add(pane, column++, row);
//
//                GridPane.setMargin(pane, new Insets(10));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public String choosePictureFromDialog(ImageView imgView) throws MalformedURLException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Choose");
//        File selected = fileChooser.showOpenDialog(null);
//        File url = selected;
//        try {
//            String path = url.toURI().toURL().toString();
//            Image imageForFile = new Image(selected.toURI().toURL().toString());
//            imgView.setImage(imageForFile);
//            return path;
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @FXML
    public void addStaff(ActionEvent actionEvent) {
        Employee employee = new Employee();
        if(Employee_DAO.getInstance().findAll() == null){
            employee.setEmployee_id(1);
        }else {
            employee.setEmployee_id(Employee_DAO.getInstance().findAll().size()+1);
        }
        employee.setName(staffnameTextfield.getText());
        employee.setPhone_number(staffphoneTextfield.getText());
        employee.setAddress(staffaddressTextfield.getText());
        employee.setEmail(staffemailTextfield.getText());
        Employee_DAO.getInstance().insert(employee);
    }

    @FXML
    public void saveStaff(ActionEvent actionEvent) {
        Employee employee = staff_table.getSelectionModel().getSelectedItem();
        staffidTextfield.setText(String.valueOf(employee.getEmployee_id()));
        staffnameTextfield.setText(employee.getName());
        staffphoneTextfield.setText(employee.getPhone_number());
        staffaddressTextfield.setText(employee.getAddress());
        staffemailTextfield.setText(employee.getEmail());
        User u1 = User_DAO.getInstance().findById(employee.getEmployee_id());
        staffpasswordTextfield.setText(u1.getPassword());
        employee.setName(staffnameTextfield.getText());
        employee.setPhone_number(staffphoneTextfield.getText());
        employee.setAddress(staffaddressTextfield.getText());
        employee.setEmail(staffemailTextfield.getText());
        Employee_DAO.getInstance().update(employee);
    }

    @FXML
    public void deleteStaff(ActionEvent actionEvent) {
        Employee employee = staff_table.getSelectionModel().getSelectedItem();
        employeeList.remove(employee);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeData = Employee_DAO.getInstance().findAll();
        employeeList = FXCollections.observableList(employeeData);
        staffidColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employee_id"));
        staffnameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        staffphoneComlumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phonenumber"));
        staffaddressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("ddress"));
        staffemailColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        staff_table.setItems(employeeList);
    }
}




