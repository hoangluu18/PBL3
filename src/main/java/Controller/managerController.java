package Controller;

import DAO.Employee_DAO;
import DAO.OrderDetail_DAO;
import DAO.Order_DAO;
import DAO.User_DAO;
import Database.JDBC_Util;
import Model.*;
import com.gluonhq.charm.glisten.control.Avatar;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.Node;
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
import javafx.scene.image.Image;
import javafx.util.Duration;


import javax.xml.crypto.Data;

public class managerController implements Initializable {

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
    private ResultSet result;

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
    private TableView bill_table, productDetail_table;
    @FXML
    private PasswordField staffpasswordTextfield;
    @FXML
    private ObservableList<Product> cardListData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, Integer> productidColumn;
    @FXML
    private TableColumn<Order, Integer>idColumn;
    @FXML
    private TableColumn<Order, Integer>customer_id;
    @FXML
    private TableColumn<Order, String>Date;
    @FXML
    private TableColumn<Order, Integer>Employee_id;
    @FXML
    private TableColumn<Order, Integer>totalPrice;
    @FXML
    private TableColumn<Order, Integer>status;
    private ArrayList<Order> orderData;
    private ObservableList<Order> orderList;

    ArrayList<OrderDetail> detailList;
    @FXML
    TableColumn<OrderDetail, Integer>productorder_idcolumn;
    @FXML
    TableColumn<OrderDetail, Integer>order_detail_id;
    @FXML
    TableColumn<OrderDetail, Integer>detailproduct_id;
    @FXML
    TableColumn<OrderDetail, Integer>quantity;
    @FXML
    TableColumn<OrderDetail, Integer>unit_price;
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
    public void anchorProductappear() throws IOException, SQLException {
        anchorStaff.setVisible(false);
        anchorBill.setVisible(false);
        anchorHome.setVisible(false);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), switch_pane);
        tt.setToX(productBtn.getLayoutX() - switch_pane.getLayoutX());
        tt.play();
        switch_pane.setPrefWidth(productBtn.getWidth());
        getAnchorProduct.setVisible(true);
        menuDisplayCard();
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

    public ObservableList<Product> menuGetData() throws SQLException {

        String sql = "SELECT * FROM products";

        ObservableList<Product> listData = FXCollections.observableArrayList();
        connect = JDBC_Util.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Product prod;

            while (result.next()) {
                prod = new Product(
                        result.getInt("product_id"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getString("color"),
                        result.getString("size"),
                        result.getInt("quantity"),
                        result.getString("description"),
                        result.getString("image_path"),
                        result.getInt("type_id")
                );

                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    public void menuDisplayCard() throws IOException, SQLException {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        gridCardPane.getChildren().clear();
        gridCardPane.getRowConstraints().clear();
        gridCardPane.getColumnConstraints().clear();

        for (int i = 0; i < cardListData.size(); i++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/View/cardProduct.fxml"));
                AnchorPane pane = load.load();
                cardProductController cardC = load.getController();
                cardC.setData(cardListData.get(i));
                cardC.setProductInfo(cardListData.get(i));

                if (column == 6) {
                    column = 0;
                    row += 1;
                }

                gridCardPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String choosePictureFromDialog(ImageView imgView) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose");
        File selected = fileChooser.showOpenDialog(null);
        File url = selected;
        try {
            String path = url.toURI().toURL().toString();
            Image imageForFile = new Image(selected.toURI().toURL().toString());
            imgView.setImage(imageForFile);
            return path;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


//        table_billList.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, newSelection) -> {
//            if(newSelection != null) {
//                String orderID = newSelection.ge
//            }
//        } ));

    public int getOrderID(){
        Order selectedOrder = (Order) bill_table.getSelectionModel().getSelectedItem();
        int values = selectedOrder.getOrder_id();
        return values;
    }

    public void addProductDetail(){

        detailList = OrderDetail_DAO.getInstance().findAll();
        productorder_idcolumn.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("order_id"));
        order_detail_id.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("order_detail_id"));
        detailproduct_id.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("product_id"));
        quantity.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("quantity"));
        unit_price.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("unit_price"));
        int orderID = getOrderID();
        ArrayList<OrderDetail> selected = new ArrayList<>();

        for(int i = 0; i < detailList.size(); i++) {
            int index = detailList.get(i).getOrder_id();
            if(index == orderID) {
                selected.add(detailList.get(i));
            }
        }
        ObservableList<OrderDetail> orderDetailsdata = FXCollections.observableArrayList(selected);
        productDetail_table.setItems(orderDetailsdata);
    }


    @FXML
    public void addStaff(ActionEvent actionEvent) {
        // Check if the corresponding user record already exists
        User user = User_DAO.getInstance().findByUsername(staffemailTextfield.getText());
        int employee_id = 0;
        if (user == null) {
            // If the user record doesn't exist, insert a new user record
            User newUser = new User();
            newUser.setUserName(staffemailTextfield.getText());
            newUser.setPassword(staffpasswordTextfield.getText());
            newUser.setRole(User.EMPLOYEE);
            employee_id = User_DAO.getInstance().insert(newUser);

            // Insert the new employee record with the newly inserted user record's ID as the foreign key reference
            Employee newEmployee = new Employee();
            newEmployee.setEmployee_id(employee_id);
            newEmployee.setName(staffnameTextfield.getText());
            newEmployee.setPhone_number(staffphoneTextfield.getText());
            newEmployee.setAddress(staffaddressTextfield.getText());
            newEmployee.setEmail(staffemailTextfield.getText());
            Employee_DAO.getInstance().insert(newEmployee);
            employeeList.add(newEmployee);
            staff_table.refresh();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("User already exists");
            alert.showAndWait();
        }


    }

    @FXML
    public void saveStaff(ActionEvent actionEvent) {
        Employee employee = staff_table.getSelectionModel().getSelectedItem();
        User user = User_DAO.getInstance().findByUsername(employee.getEmail());

        employee.setName(staffnameTextfield.getText());
        employee.setPhone_number(staffphoneTextfield.getText());
        employee.setAddress(staffaddressTextfield.getText());
        employee.setEmail(staffemailTextfield.getText());
        Employee_DAO.getInstance().update(employee);

        // Kiểm tra xem tên người dùng có thay đổi không
        boolean isUsernameChanged = !user.getUserName().equals(employee.getEmail());

        // Update the user's information
        user.setUserName(employee.getEmail());
        user.setPassword(staffpasswordTextfield.getText());
        User_DAO.getInstance().update(user, isUsernameChanged);

        staff_table.refresh();
    }


    @FXML
    public void deleteStaff(ActionEvent actionEvent) {
        Employee employee = staff_table.getSelectionModel().getSelectedItem();
        User u1 = User_DAO.getInstance().findById(employee.getEmployee_id());
        Employee_DAO.getInstance().delete(employee);
        User_DAO.getInstance().delete(u1);
        employeeList.remove(employee);
        staff_table.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeData = Employee_DAO.getInstance().findAll();
        employeeList = FXCollections.observableList(employeeData);
        staffidColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        staffnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffphoneComlumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        staffaddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        staffemailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        staff_table.setItems(employeeList);

        orderData = Order_DAO.getInstance().findAll();
        orderList = FXCollections.observableArrayList(orderData);
        idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("order_id"));
        customer_id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("customer_id"));
        Date.setCellValueFactory(new PropertyValueFactory<Order, String>("order_date"));
        Employee_id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("employee_id"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Order, Integer>("totalPrice"));
        status.setCellValueFactory(new PropertyValueFactory<Order, Integer>("status"));
        bill_table.setItems(orderList);



        staff_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Employee employee = staff_table.getSelectionModel().getSelectedItem();
                staffidTextfield.setText(String.valueOf(employee.getEmployee_id()));
                staffnameTextfield.setText(employee.getName());
                staffphoneTextfield.setText(employee.getPhone_number());
                staffaddressTextfield.setText(employee.getAddress());
                staffemailTextfield.setText(employee.getEmail());
                User u1 = User_DAO.getInstance().findById(employee.getEmployee_id());
                staffpasswordTextfield.setText(u1.getPassword());
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Thêm EventFilter cho MouseEvent.MOUSE_CLICKED vào scene hoặc root pane của bạn.
                staff_table.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Node node = mouseEvent.getPickResult().getIntersectedNode();
                        Boolean clickedOn = false;
                        while (node != null) {
                            if (node == staff_table) {
                                clickedOn = true;
                                break;
                            }
                            if (node instanceof TextField) {
                                clickedOn = true;
                                break;
                            }
                            if (node instanceof Button) {
                                clickedOn = true;
                                break;
                            }
                            node = node.getParent();
                        }

                        if (!clickedOn) {
                            staff_table.getSelectionModel().clearSelection();
                            staffidTextfield.clear();
                            staffnameTextfield.clear();
                            staffphoneTextfield.clear();
                            staffaddressTextfield.clear();
                            staffemailTextfield.clear();
                            staffpasswordTextfield.clear();
                            staff_table.refresh();
                        }
                    }
                });
            }
        });

    }
}




