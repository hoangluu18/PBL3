package Controller;
import DAO.*;
import Database.JDBC_Util;
import Model.*;
import io.github.gleidson28.GNAvatarView;
import io.github.gleidson28.GNAvatarView;
import javafx.animation.ScaleTransition;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.scene.Node;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class managerController implements Initializable {

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
    // Add product
    public static int lastColumn;
    public static int lastRow;
    @FXML
    private Pane dimPane;
    @FXML
    private AnchorPane productInfoAnchorpane;
    @FXML
    private TextField productNameTxtField;
    @FXML
    private TextField productPriceTxtField;
    @FXML
    private TextField productSizeTxtField;
    @FXML
    private TextField productColorTxtField;
    @FXML
    private TextField productQuantityTxtField;
    @FXML
    private TextArea productDescriptionTxtArea;
    @FXML
    private ImageView productImageView;
    @FXML
    private Button addImageButton;
    @FXML
    private Button removeImageButton;
    @FXML
    private Button saveProductButton;
    @FXML
    private ComboBox<String> productTypeComboBox;
    @FXML
    private AnchorPane productInfoAnchorpane2;
    @FXML
    private TextField productNameTxtField2;
    @FXML
    private TextField productPriceTxtField2;
    @FXML
    private TextField productSizeTxtField2;
    @FXML
    private TextField productColorTxtField2;
    @FXML
    private TextField productQuantityTxtField2;
    @FXML
    private TextArea productDescriptionTxtArea2;
    @FXML
    private ImageView productImageView2;
    @FXML
    private Button addImageButton2;
    @FXML
    private Button deleteProductButton2;
    @FXML
    private Button updateProductButton2;
    @FXML
    private ComboBox<String> productTypeComboBox2;
    private ArrayList<AnchorPane> nodeList = new ArrayList<>();
    private int currentId;
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

    @FXML
    private GNAvatarView avatar;

//    ArrayList<OrderDetail> detailList;
//    @FXML
//    TableColumn<OrderDetail, Integer>productorder_idcolumn;
//    @FXML
//    TableColumn<OrderDetail, Integer>order_detail_id;
//    @FXML
//    TableColumn<OrderDetail, Integer>detailproduct_id;
//    @FXML
//    TableColumn<OrderDetail, Integer>quantity;
//    @FXML
//    TableColumn<OrderDetail, Integer>unit_price;
    @FXML
    private TextField testTextfield;
    @FXML
    private DatePicker datebegin;
    @FXML
    private DatePicker dateend;
    @FXML
    private Button deleteProductButton;

    @FXML
    private AnchorPane productInfoAnchorpane1;
    @FXML
    private Button updateProductButton;

    @FXML
    private Button addImageButton1;
    @FXML
    private ImageView productImageView1;

    @FXML
    private MenuButton menubutton;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    ArrayList<BillDetail> listBillDetail = new ArrayList<BillDetail>();
    @FXML
    TableColumn<BillDetail, Integer> order_id = new TableColumn<BillDetail, Integer>("Order ID");
    @FXML
    TableColumn<BillDetail, Integer> order_detail_id = new TableColumn<BillDetail, Integer>("Order Detail ID");
    @FXML
    TableColumn<BillDetail, String> product_name = new TableColumn<BillDetail, String>("Product");
    @FXML
    TableColumn<BillDetail, Integer> quantity = new TableColumn<BillDetail, Integer>("Quantity");
    @FXML
    TableColumn<BillDetail, Integer> unit_price = new TableColumn<BillDetail, Integer>("Unit Price");

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

    public ObservableList<Product> menuGetData() throws SQLException {
        ArrayList<Product> data = Product_DAO.getInstance().findActiveProduct();
        ObservableList<Product> listData = FXCollections.observableArrayList(data);

        return listData;
    }

    ArrayList<ProductType> data;

    public static boolean isValidEmailFormat(String email) {
        Pattern emailPattern = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }


    public void menuDisplayCard() throws IOException, SQLException {
        data = ProductType_DAO.getInstance().findAll();
        nodeList.clear();
        cardListData.clear();
        cardListData.addAll(menuGetData());
        productTypeComboBox.getItems().clear();
        productTypeComboBox2.getItems().clear();
        for (int i = 0; i < data.size(); i++) {
            productTypeComboBox.getItems().add(data.get(i).getCategory());
        }

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
                nodeList.add(pane);
                pane.setOnMouseClicked(event -> {
                    int position = nodeList.indexOf(pane);
                    dimPane.setVisible(true);
                    currentId = cardListData.get(position).getProduct_id();
                    productNameTxtField2.setText(cardListData.get(position).getName());
                    productColorTxtField2.setText(cardListData.get(position).getColor());
                    productSizeTxtField2.setText(cardListData.get(position).getSize());
                    productPriceTxtField2.setText(Integer.toString(cardListData.get(position).getPrice()));
                    productQuantityTxtField2.setText(Integer.toString(cardListData.get(position).getQuantity()));
                    productDescriptionTxtArea2.setText(cardListData.get(position).getDescription());
                    productTypeComboBox2.getSelectionModel().select(ProductType_DAO.getInstance().findById(cardListData.get(position).getType_id()).getCategory());
                    productInfoAnchorpane2.setVisible(true);

                    // Tạo một ScaleTransition cho productInfoAnchorpane2
                    ScaleTransition st = new ScaleTransition(Duration.millis(250), productInfoAnchorpane2);

                    // Thiết lập thuộc tính cho ScaleTransition
                    st.setFromX(0);  // Bắt đầu từ scale x = 0
                    st.setFromY(0);  // Bắt đầu từ scale y = 0
                    st.setToX(1);    // Kết thúc tại scale x = 1
                    st.setToY(1);    // Kết thúc tại scale y = 1
                    st.setCycleCount(1);  // Chỉ chạy 1 lần

                    st.play();

                    try {
                        setCheckImageButton(productImageView2, cardListData.get(position));
                        temp2 = cardListData.get(position).getImage();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                });

                if (column == 6) {
                    column = 0;
                    row += 1;
                }

                gridCardPane.add(pane, column++, row);
                lastColumn = column;
                lastRow = row;
                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try{
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/View/addProduct.fxml"));
            AnchorPane addPane = load.load();
            // Tạo một ScaleTransition
            ScaleTransition st = new ScaleTransition(Duration.millis(250), productInfoAnchorpane);

            // Thiết lập thuộc tính cho ScaleTransition
            st.setFromX(0);  // Bắt đầu từ scale x = 0
            st.setFromY(0);  // Bắt đầu từ scale y = 0
            st.setToX(1);    // Kết thúc tại scale x = 1
            st.setToY(1);    // Kết thúc tại scale y = 1
            st.setCycleCount(1);  // Chỉ chạy 1 lần
            addPane.setOnMouseClicked(event -> {
                dimPane.setVisible(true);
                productInfoAnchorpane.setVisible(true);
                st.play();
            });
            if (lastColumn == 6) {
                lastColumn = 0;
                lastRow += 1;
            }

            gridCardPane.add(addPane, lastColumn, lastRow);
            GridPane.setMargin(addPane, new Insets(10));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String temp = "";

    public void addProduct() throws IOException, SQLException {
        Product product = new Product();
        product = getProductInfo();
        Product_DAO.getInstance().insert(product);
        menuDisplayCard();
        clearAddInfo();
        dimPane.setVisible(false);
        productInfoAnchorpane.setVisible(false);
    }

    public void clearAddInfo() throws IOException, SQLException {
        productImageView.setImage(null);
        productColorTxtField.setText("");
        productNameTxtField.setText("");
        productPriceTxtField.setText("");
        productQuantityTxtField.setText("");
        productDescriptionTxtArea.setText("");
        productSizeTxtField.setText("");
        productTypeComboBox.getSelectionModel().select(-1);
    }

    public void clearCheckInfo() throws IOException, SQLException {
        productImageView2.setImage(null);
        productColorTxtField2.setText("");
        productNameTxtField2.setText("");
        productPriceTxtField2.setText("");
        productQuantityTxtField2.setText("");
        productDescriptionTxtArea2.setText("");
        productSizeTxtField2.setText("");
        productTypeComboBox2.getSelectionModel().select(-1);
    }

    public Product getProductInfo() throws SQLException, MalformedURLException {
        Product product = new Product();
        ArrayList<ProductType> data = ProductType_DAO.getInstance().findAll();
        product.setName(productNameTxtField.getText());
        product.setPrice(Integer.parseInt(productPriceTxtField.getText()));
        product.setColor(productColorTxtField.getText());
        product.setSize(productSizeTxtField.getText());
        product.setQuantity(Integer.parseInt(productQuantityTxtField.getText()));
        product.setImage(temp);
        product.setDescription(productDescriptionTxtArea.getText());
        int typeID = -1;
        for (int i = 0; i < data.size(); i++) {
            if (productTypeComboBox.getSelectionModel().getSelectedItem().equals(data.get(i).getCategory())) {
                typeID = data.get(i).getType_id();
            }
        }
        product.setType_id(typeID);
        return product;
    }

    public Product getCheckProductInfo() throws SQLException, MalformedURLException {
        Product product = new Product();
        ArrayList<ProductType> data = ProductType_DAO.getInstance().findAll();
        product.setProduct_id(currentId);
        product.setName(productNameTxtField2.getText());
        product.setPrice(Integer.parseInt(productPriceTxtField2.getText()));
        product.setColor(productColorTxtField2.getText());
        product.setSize(productSizeTxtField2.getText());
        product.setQuantity(Integer.parseInt(productQuantityTxtField2.getText()));
        product.setImage(temp2);
        product.setDescription(productDescriptionTxtArea2.getText());
        int typeID = -1;
        for (int i = 0; i < data.size(); i++) {
            if (productTypeComboBox2.getSelectionModel().getSelectedItem().equals(data.get(i).getCategory())) {
                typeID = data.get(i).getType_id();
            }
        }
        product.setType_id(typeID);
        return product;
    }

    public void setAddImageButton() throws MalformedURLException {
        productImageView.setImage(null);
        temp = choosePictureFromDialog(productImageView);
    }

    String temp2 = "";

    public void setAddImageButton2() throws MalformedURLException {
        productImageView2.setImage(null);
        temp2 = choosePictureFromDialog(productImageView2);
    }

    public void setCheckImageButton(ImageView imageView,  Product product) throws MalformedURLException {
        productImageView2.setImage(null);
        String imageUrl = product.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            productImageView2.setImage(image);
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
        Bill selectedOrder = (Bill) bill_table.getSelectionModel().getSelectedItem();
        int values = selectedOrder.getBill_Id();
        return values;
    }

    public void addProductDetail(){
        if(bill_table.getSelectionModel().getSelectedItem() != null) {
            int id = getOrderID();
            listBillDetail = BillDetail_DAO.getInstance().getBillDetail(id);

            order_id.setCellValueFactory(new PropertyValueFactory<BillDetail, Integer>("order_id"));
            order_detail_id.setCellValueFactory(new PropertyValueFactory<BillDetail, Integer>("orderdetail_id"));
            product_name.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("product_name"));
            quantity.setCellValueFactory(new PropertyValueFactory<BillDetail, Integer>("quantity"));
            unit_price.setCellValueFactory(new PropertyValueFactory<BillDetail, Integer>("unit_price"));

            ObservableList<BillDetail> billDetailsdata = FXCollections.observableArrayList(listBillDetail);
            productDetail_table.setItems(billDetailsdata);
        }

    }

    @FXML
    public void updateProduct() throws IOException, SQLException {
        Product product = new Product();
        product = getCheckProductInfo();
        Product_DAO.getInstance().update(product);
        menuDisplayCard();
        clearCheckInfo();
        dimPane.setVisible(false);
        productInfoAnchorpane2.setVisible(false);
    }
    @FXML
    public void deleteProduct() throws IOException, SQLException {
        Product product = new Product();
        product = getCheckProductInfo();
        Product_DAO.getInstance().unActive(product);
        menuDisplayCard();
        clearCheckInfo();
        dimPane.setVisible(false);
        productInfoAnchorpane2.setVisible(false);
    }



    @FXML
    public void addStaff(ActionEvent actionEvent) {
        String email = staffemailTextfield.getText();

        if (!isValidEmailFormat(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter the correct email format");
            alert.showAndWait();
            staffemailTextfield.setText("");
            return;
        }

        String phone = staffphoneTextfield.getText();
        //check phoneNumber (vietnam +84)
        String regex = "^0[0-9]{9}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phone);

        if(!matcher.matches()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter the correct phone number format");
            alert.showAndWait();
            staffphoneTextfield.setText("");
            return;
        }
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
        String email = staffemailTextfield.getText();

        if (!isValidEmailFormat(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter the correct email format");
            alert.showAndWait();
            staffemailTextfield.setText("");
            return;
        }

        String phone = staffphoneTextfield.getText();
        //check phoneNumber (vietnam +84)
        String regex = "^0[0-9]{9}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phone);

        if(!matcher.matches()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter the correct phone number format");
            alert.showAndWait();
            staffphoneTextfield.setText("");
            return;
        }
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
//        Employee_DAO.getInstance().delete(employee);
//        User_DAO.getInstance().delete(u1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Staff Confirmation");
        alert.setContentText("Are you sure to delete this staff?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK) {
            u1.setActive(User.NOT_ACTIVE);
            if(User_DAO.getInstance().update(u1) == User_DAO.isDuplicate){
                System.out.println("err");
            }
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Save successfully");
            alert1.showAndWait();
            employeeList.remove(employee);
            staff_table.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(Hello_viewController.IDManagerCurrent);
        String managerName = Manager_DAO.getInstance().getmanagerName(Hello_viewController.IDManagerCurrent);
        menubutton.setText(managerName);

        String avapath = Manager_DAO.getInstance().getavapath(Hello_viewController.IDManagerCurrent);
        System.out.println(avapath);
        if(avapath != null && !avapath.isEmpty()) {
            Image tempimage = new Image(avapath);
            avatar.setImage(tempimage);
        }


        testTextfield.textProperty().addListener((observable, oldvalue, newvalue )->  {
            ArrayList<Product> listProduct = new ArrayList<Product>();
            listProduct = Product_DAO.getInstance().findByname(newvalue);
            for(int i = 0; i < listProduct.size(); i++) {
                System.out.println(listProduct.get(i).getName());
            }
            data = ProductType_DAO.getInstance().findAll();
            for (int i = 0; i < data.size(); i++) {
                productTypeComboBox.getItems().add(data.get(i).getCategory());
            }
            nodeList.clear();
            cardListData.clear();
            cardListData.addAll(listProduct);
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
                    nodeList.add(pane);
                    pane.setOnMouseClicked(event -> {
                        int position = nodeList.indexOf(pane);
                        dimPane.setVisible(true);
                        currentId = cardListData.get(position).getProduct_id();
                        productNameTxtField2.setText(cardListData.get(position).getName());
                        productColorTxtField2.setText(cardListData.get(position).getColor());
                        productSizeTxtField2.setText(cardListData.get(position).getSize());
                        productPriceTxtField2.setText(Integer.toString(cardListData.get(position).getPrice()));
                        productQuantityTxtField2.setText(Integer.toString(cardListData.get(position).getQuantity()));
                        productDescriptionTxtArea2.setText(cardListData.get(position).getDescription());
                        productTypeComboBox2.getSelectionModel().select(ProductType_DAO.getInstance().findById(cardListData.get(position).getType_id()).getCategory());
                        productInfoAnchorpane2.setVisible(true);

                        // Tạo một ScaleTransition cho productInfoAnchorpane2
                        ScaleTransition st = new ScaleTransition(Duration.millis(250), productInfoAnchorpane2);

                        // Thiết lập thuộc tính cho ScaleTransition
                        st.setFromX(0);  // Bắt đầu từ scale x = 0
                        st.setFromY(0);  // Bắt đầu từ scale y = 0
                        st.setToX(1);    // Kết thúc tại scale x = 1
                        st.setToY(1);    // Kết thúc tại scale y = 1
                        st.setCycleCount(1);  // Chỉ chạy 1 lần

                        st.play();

                        try {
                            setCheckImageButton(productImageView2, cardListData.get(position));
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    if (column == 6) {
                        column = 0;
                        row += 1;
                    }

                    gridCardPane.add(pane, column++, row);
                    lastColumn = column;
                    lastRow = row;
                    GridPane.setMargin(pane, new Insets(10));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/View/addProduct.fxml"));
                AnchorPane addPane = load.load();
                // Tạo một ScaleTransition
                ScaleTransition st = new ScaleTransition(Duration.millis(250), productInfoAnchorpane);

                // Thiết lập thuộc tính cho ScaleTransition
                st.setFromX(0);  // Bắt đầu từ scale x = 0
                st.setFromY(0);  // Bắt đầu từ scale y = 0
                st.setToX(1);    // Kết thúc tại scale x = 1
                st.setToY(1);    // Kết thúc tại scale y = 1
                st.setCycleCount(1);  // Chỉ chạy 1 lần
                addPane.setOnMouseClicked(event -> {
                    dimPane.setVisible(true);
                    productInfoAnchorpane.setVisible(true);
                    st.play();
                });
                if (lastColumn == 6) {
                    lastColumn = 0;
                    lastRow += 1;
                }

                gridCardPane.add(addPane, lastColumn, lastRow);
                GridPane.setMargin(addPane, new Insets(10));
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<Employee> toRemove = new ArrayList<>();
        employeeData = Employee_DAO.getInstance().findAll();
        for(Employee e : employeeData){
            if(User_DAO.getInstance().findById(e.getEmployee_id()).isActive() == User.NOT_ACTIVE){
                toRemove.add(e);
            }
        }
        employeeData.removeAll(toRemove);

        employeeList = FXCollections.observableList(employeeData);
        staffidColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        staffnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffphoneComlumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        staffaddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        staffemailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        staff_table.setItems(employeeList);

        ArrayList<Bill> BillList = new ArrayList<Bill>();
        //query database
        String sql = "SELECT \n" +
                "    o.order_id AS id,\n" +
                "    c.name AS customer_name,\n" +
                "    o.order_date AS date,\n" +
                "    e.name AS employee_name,\n" +
                "    o.totalPrice AS total_price,\n" +
                "    o.status\n" +
                "FROM \n" +
                "    orders o\n" +
                "JOIN \n" +
                "    customers c ON o.customer_id = c.customer_id\n" +
                "JOIN \n" +
                "    employees e ON o.employee_id = e.employee_id;\n";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bill bill = new Bill();
                bill.setBill_Id(resultSet.getInt("id")); // Changed "o.order_id" to "id"
                bill.setCustomer_name(resultSet.getString("customer_name"));
                bill.setDate(resultSet.getString("date"));
                bill.setEmployee_name(resultSet.getString("employee_name"));
                bill.setTotal_price(resultSet.getInt("total_price"));
                bill.setStatus(resultSet.getInt("status") == 0 ? "unconfimred" : "confirmed");
                BillList.add(bill);
            }
            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }

        TableColumn<Bill, Integer>idColumn = new TableColumn<Bill, Integer>("ID");
        TableColumn<Bill, String>customer_name = new TableColumn<Bill,String>("Customer_Name");
        TableColumn<Bill, String>Date = new TableColumn<Bill, String>("Date");
        TableColumn<Bill, String>Employee_name = new TableColumn<Bill, String>("Employee_Name");
        TableColumn<Bill, Integer>totalPrice = new TableColumn<Bill, Integer>("Total Price");
        TableColumn<Bill, String>status = new TableColumn<Bill, String>("Status");

        idColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("Bill_Id"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Bill, String>("customer_name"));
        Date.setCellValueFactory(new PropertyValueFactory<Bill, String>("date"));
        Employee_name.setCellValueFactory(new PropertyValueFactory<Bill, String>("employee_name"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("total_price"));
        status.setCellValueFactory(new PropertyValueFactory<Bill, String>("status"));

        ObservableList<Bill> List = FXCollections.observableArrayList(BillList);
        bill_table.getColumns().addAll(idColumn,customer_name,Date,Employee_name,totalPrice,status);
        bill_table.setItems(List);
        //bill_table.setItems(orderList);

        //transition out
        dimPane.setOnMouseClicked(event -> {
            ScaleTransition stOut;
            if (productInfoAnchorpane.isVisible()) {
                stOut = new ScaleTransition(Duration.millis(250), productInfoAnchorpane);
            } else if (productInfoAnchorpane2.isVisible()) {
                stOut = new ScaleTransition(Duration.millis(250), productInfoAnchorpane2);
            } else {
                return;
            }

            stOut.setFromX(1);  // Bắt đầu từ scale x = 1
            stOut.setFromY(1);  // Bắt đầu từ scale y = 1
            stOut.setToX(0);    // Kết thúc tại scale x = 0
            stOut.setToY(0);    // Kết thúc tại scale y = 0
            stOut.setCycleCount(1);  // Chỉ chạy 1 lần

            stOut.setOnFinished(e -> {
                productInfoAnchorpane.setVisible(false);
                productInfoAnchorpane2.setVisible(false);
                try {
                    clearAddInfo();
                    clearCheckInfo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dimPane.setVisible(false);
            });

            stOut.play();  // Chạy hiệu ứng
            try {
                clearAddInfo();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

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

    public void logout() throws IOException {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/View/hello-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/hello-view.css").toExternalForm());
        stage.setTitle("Shop Management System");
        stage.setMinWidth(1512.0);
        stage.setMinHeight(982.0);
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
        Stage Current = (Stage) menubutton.getScene().getWindow();
        Current.close();
    }

    public static String getNextDay(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            LocalDate nextDay = date.plusDays(1);
            return nextDay.format(formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void setBarchart() {
        if(datebegin.getValue() != null && dateend.getValue() != null) {
            String nextday = getNextDay(dateend.getValue().toString());
            ArrayList<Order> orders = Order_DAO.getInstance().databarchart(datebegin.getValue().toString(), nextday);

//            ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
            XYChart.Series<String, Integer> data = new XYChart.Series();
            for(int i = 0; i < orders.size(); i++) {
                System.out.println(orders.get(i).getOrder_date());
                System.out.println(orders.get(i).getTotalPrice());

                data.getData().add(new XYChart.Data(orders.get(i).getOrder_date(), orders.get(i).getTotalPrice()));
            }
            CategoryAxis xAxis = (CategoryAxis) barchart.getXAxis();
            xAxis.setTickLabelRotation(0); // Đảm bảo nhãn trục x không bị xoay
            xAxis.setTickLabelGap(10); // Khoảng cách giữa các nhãn

            barchart.setCategoryGap(10); // Điều chỉnh khoảng cách giữa các cột
            barchart.getData().clear();
            barchart.getData().add(data);

        }
    }
}




