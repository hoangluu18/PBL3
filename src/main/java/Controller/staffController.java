package Controller;

import DAO.*;
import Database.JDBC_Util;
import Model.*;
import io.github.gleidson28.GNAvatarView;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static Controller.Hello_viewController.IdEmployeeCurrent;

public class staffController implements Initializable {

    private static staffController instance;
    public staffController(){
        instance = this;
    }



    @FXML
    private AnchorPane AnchorPaneBillList;

    @FXML
    private AnchorPane AnchorPaneBillInfor;

    //AnchorPaneStaffInformation
    @FXML
    private AnchorPane AnchorPaneStaffInformation;

    @FXML
    private MenuButton menubutton;
    @FXML
    private MenuItem logout;
    @FXML
    private TextField setStaffavapath;
    //VARIABLE ANCHORPANE CUSTOMER
    @FXML
    private AnchorPane AnchorPaneCustomer;

    @FXML
    private ComboBox ComboBoxGender;

    @FXML
    private TextField TextFieldCustomerName;

    @FXML
    private DatePicker DatePickerDateOfBirth;

    @FXML
    private TextField TextFieldPhoneNumber;

    @FXML
    private  Button ButtonNext;
    @FXML
    private GNAvatarView avatar;
    //AnchorPaneProductList
    @FXML
    private AnchorPane AnchorPaneProductList;

    //AnchorPaneBillList
    @FXML
    private TableView billList_table;
    @FXML
    private Button ButtonRemove;
    @FXML
    private Button ButtonAdd;

    public static List<Product> listProductPick = new ArrayList<Product>();

    public static  Customer newCustomer;
    public static  boolean isSaved = false;
    //grid pane
    @FXML
    private GridPane GridPane;
    public static int lastColumn;
    public static int lastRow;
    public static int idCustomerIfPickStatusUnconfirmed = -1;
    @FXML
    private Button ButtonNextProduct;


    //bill list
    @FXML
    TextField textFieldBillId;
    @FXML
    TextField textFieldCustomer;
    @FXML
    TextField textFieldStaffName;
    @FXML
    TextField textFieldOrderDate;

    @FXML
    TableView tableViewBillInformation;
    @FXML
    Label LabelTotalPrice;
    @FXML
    Button buttonSave;
    @FXML
    Button buttonPurchase;
    @FXML
    TextField setStaffID, setStaffName, setStaffPhoneNumber, setStaffAddress, setStaffEmail;
    @FXML
    PasswordField setStaffPassword;
    @FXML
    PasswordField currentPass_txt, newPass_txt, confirmPass_txt;
    @FXML
    Button setProfile_btn, setStaffPass_btn;
    @FXML
    Button saveNewPass_btn, BackStaffInfor_btn;
    @FXML
    AnchorPane AnchorPaneSetPassword;
    @FXML
    Label wrongPass_label, wrongConfirm_label;
    @FXML
    Button backBilllist_btn;
    public static int  IdOrderCurrentIfPickStatusUnconfirmed = -1;

    private ObservableList<Product> CardListData;

    @FXML
    private TextField textFieldSearch;
    boolean isChanged = false;
    ObservableList<Product> listDataByKey;
    List<Product> listProductCache;

    @FXML
    private TextField productNameTxtField;
    @FXML
    private TextField productTypeTxtField;
    @FXML
    private TextField productPriceTxtField;
    @FXML
    private TextField productColorTxtField;
    @FXML
    private TextField productSizeTxtField;
    @FXML
    private TextField productQuantityTxtField;
    @FXML
    private TextArea productDescriptionTxtArea;
    @FXML
    private ImageView productImageView;
    @FXML
    private Pane dimPane;
    @FXML
    private AnchorPane productInfoAnchorpane;

    private int currentId;
    private ArrayList<AnchorPane> nodeList = new ArrayList<>();
    @FXML
    public void addBillList(){
        //clear data
        billList_table.getColumns().clear();
        billList_table.getItems().clear();
        //init
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
        for(Bill bill: BillList){
            System.out.println(bill.getBill_Id() + " " + bill.getCustomer_name() + " " + bill.getDate() + " " + bill.getEmployee_name() + " " + bill.getTotal_price() + " " + bill.getStatus());
        }

        TableColumn<Bill, Integer>idColumn = new TableColumn<Bill, Integer>("ID");
        TableColumn<Bill, String>customer_name = new TableColumn<Bill,String>("Customer_Name");
        TableColumn<Bill, String>Date = new TableColumn<Bill, String>("Date");
        TableColumn<Bill, String>Employee_name = new TableColumn<Bill, String>("Employee_Name");
        TableColumn<Bill, Integer>totalPrice = new TableColumn<Bill, Integer>("Total Price");
        TableColumn<Bill, String>status = new TableColumn<Bill, String>("Status");

        System.out.println("Add bill list");
        idColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("Bill_Id"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Bill, String>("customer_name"));
        Date.setCellValueFactory(new PropertyValueFactory<Bill, String>("date"));
        Employee_name.setCellValueFactory(new PropertyValueFactory<Bill, String>("employee_name"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("total_price"));
        status.setCellValueFactory(new PropertyValueFactory<Bill, String>("status"));

        System.out.println("Add bill list");

        idColumn.setPrefWidth(200);
        idColumn.setResizable(false);

        customer_name.setPrefWidth(200);
        customer_name.setResizable(false);

        Date.setPrefWidth(430);
        Date.setResizable(false);

        Employee_name.setPrefWidth(250);
        Employee_name.setResizable(false);

        totalPrice.setPrefWidth(250);
        totalPrice.setResizable(false);

        status.setPrefWidth(100);
        status.setResizable(false);

        ObservableList<Bill> List = FXCollections.observableArrayList(BillList);
        System.out.println("Add bill list");
        billList_table.getColumns().addAll(idColumn,customer_name,Date,Employee_name,totalPrice,status);
        System.out.println("Add bill list");
        billList_table.setItems(List);
        System.out.println("Add bill list");
    }


    //EVENT ANCHORPANE CUSTOMER
    @FXML
    public void clickBtnAdd() throws SQLException {
        //display AnchorPaneCustomer

        //if click element in table view display AnchorPaneProductList
        if(billList_table.getSelectionModel().getSelectedItem() != null){
            Bill bill = (Bill) billList_table.getSelectionModel().getSelectedItem();
            Order order = Order_DAO.getInstance().findById(bill.getBill_Id()+"");
            Customer customer = Customer_DAO.getInstance().findById(order.getCustomer_id()+"");
            String nameCustomer = bill.getCustomer_name();
            String dateOfBirth = customer.getDate_of_birth();
            String phoneNumber = customer.getPhone_number();
            int gender = customer.getGender();

            if(bill.getStatus() == "confirmed"){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can't edit confirmed bill");
                alert.showAndWait();
                idCustomerIfPickStatusUnconfirmed = -1; //reset
                return;
            }
            else {
                //get order current if pick status unconfirmed
                IdOrderCurrentIfPickStatusUnconfirmed = bill.getBill_Id();
                //reset list product
                CardListData.clear();
                listProductPick.clear();
//                GridPane.getChildren().clear();
//                GridPane.getRowConstraints().clear();
//                GridPane.getColumnConstraints().clear();
                idCustomerIfPickStatusUnconfirmed = customer.getCustomer_id();
                //set data
                TextFieldCustomerName.setText(nameCustomer);
                TextFieldPhoneNumber.setText(phoneNumber);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDate date = LocalDate.parse(dateOfBirth, formatter);
                DatePickerDateOfBirth.setValue(date);
                ComboBoxGender.getSelectionModel().select(gender);
                //set data to listProductPick
                int order_id = bill.getBill_Id();
                ArrayList<OrderDetail> orderDetails = OrderDetail_DAO.getInstance().findByCondition("order_id = " + order_id);
                for(OrderDetail orderDetail: orderDetails){
                    Product product = Product_DAO.getInstance().findById(orderDetail.getProduct_id()+"");
                    product.setQuantity(orderDetail.getQuantity());
                    if (!listProductPick.contains(product)) {
                        listProductPick.add(product);
                    }
                }

                initCardList();

            }

        }
        else {
            idCustomerIfPickStatusUnconfirmed = -1; //reset
            TextFieldCustomerName.setText("");
            TextFieldPhoneNumber.setText("");
            DatePickerDateOfBirth.setValue(null);
            ComboBoxGender.getSelectionModel().clearSelection();
            CardListData.clear();
            listProductPick.clear();
            initCardList();
        }
        AnchorPaneBillList.setVisible(false);
        AnchorPaneCustomer.setVisible(true);
    }

    @FXML
    public void clickButtonBack(){
        addBillList();
        AnchorPaneBillList.setVisible(true);
        AnchorPaneCustomer.setVisible(false);
    }

    @FXML
    public void clickButtonBack2(){
        AnchorPaneCustomer.setVisible(true);
        AnchorPaneProductList.setVisible(false);
    }


    @FXML
    public void clickButtonBack3(){

        AnchorPaneProductList.setVisible(true);
        AnchorPaneBillInfor.setVisible(false);
    }


    @FXML public void ClickButtonNext() throws SQLException {


        //get data from scene builder
        String name = TextFieldCustomerName.getText();
        String date = (DatePickerDateOfBirth.getValue() != null) ? DatePickerDateOfBirth.getValue().toString() : null;
        String phone = TextFieldPhoneNumber.getText();
        int gender = ComboBoxGender.getSelectionModel().getSelectedIndex();
        System.out.println(name + " " + date + " " + phone + gender + "");
        System.out.println("Click button next");
        //if click element in table view display AnchorPaneProductList , status = unconfirmed
        if(idCustomerIfPickStatusUnconfirmed != -1){
            //update customer
            Customer customer =  new Customer();
            customer.setCustomer_id(idCustomerIfPickStatusUnconfirmed);
            customer.setName(name);
            customer.setDate_of_birth(date);
            customer.setPhone_number(phone);
            customer.setGender(gender);
            Customer_DAO.getInstance().update(customer);
            newCustomer = customer;
            displayAnchorPaneBillList();
            return;
        }
        //check input
        if(name.equals("") || date == null || phone.equals("") || gender == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill all the information");
            alert.showAndWait();
            return;

        }
        //create new customer

        if(!isSaved ){
            newCustomer = new Customer();
            newCustomer.setName(name);
            newCustomer.setDate_of_birth(date);
            newCustomer.setPhone_number(phone);
            newCustomer.setGender(gender);
            Customer_DAO.getInstance().insert(newCustomer);
            isSaved = true;
        }
        else{
            String condition = "name = '" + newCustomer.getName() + "' AND dateOfBirth = '" + newCustomer.getDate_of_birth() + "' AND phone_number = '" + newCustomer.getPhone_number()+ "' AND gender = '"+newCustomer.getGender()+ "'";
            int customer_id = Customer_DAO.getInstance().findByCondition(condition).get(0).getCustomer_id();
            newCustomer.setCustomer_id(customer_id);
            newCustomer.setName(name);
            newCustomer.setDate_of_birth(date);
            newCustomer.setPhone_number(phone);
            newCustomer.setGender(gender);
            Customer_DAO.getInstance().update(newCustomer);
        }

        displayAnchorPaneBillList();
    }

    public ObservableList<Product> menuGetData() throws SQLException {
        ArrayList<Product> data = Product_DAO.getInstance().findActiveProduct();

        ObservableList<Product> listData = FXCollections.observableArrayList(data);

        return listData;
    }

    @FXML
    public void enterText() throws SQLException {
        String key = textFieldSearch.getText();
        System.out.println(key);
        if(key.equals("") || key == null){
           isChanged = false;
        }
        else{
            isChanged = true;
        }
        ArrayList<Product> data = Product_DAO.getInstance().findByCondition("name LIKE '%" + key + "%'");
        listDataByKey = FXCollections.observableArrayList(data);
        initCardList();


    }

    public void initCardList() throws SQLException {
        //listenertextfield


               // CardListData.clear();
        if(isChanged){
            CardListData = listDataByKey;
        }
        else{
            CardListData = menuGetData();
        }
//        if (CardListData == null) {
//            CardListData = FXCollections.observableArrayList();
//        }
//        CardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        GridPane.getChildren().clear();
        GridPane.getRowConstraints().clear();
        GridPane.getColumnConstraints().clear();

        for(int i = 0; i < CardListData.size(); i++){
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/View/staffcardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductStaffController cardProductStaffController = load.getController();
                cardProductStaffController.setData(CardListData.get(i));
                cardProductStaffController.setProductInfo(CardListData.get(i));


//                // Tạo một ScaleTransition
//                ScaleTransition st = new ScaleTransition(Duration.millis(250), productInfoAnchorpane);
//
//                // Thiết lập thuộc tính cho ScaleTransition
//                st.setFromX(0);  // Bắt đầu từ scale x = 0
//                st.setFromY(0);  // Bắt đầu từ scale y = 0
//                st.setToX(1);    // Kết thúc tại scale x = 1
//                st.setToY(1);    // Kết thúc tại scale y = 1
//                st.setCycleCount(1);  // Chỉ chạy 1 lần
//                st.setFromX(0);  // Bắt đầu từ scale x = 0
//                st.setFromY(0);  // Bắt đầu từ scale y = 0
//                st.setToX(1);    // Kết thúc tại scale x = 1
//                st.setToY(1);    // Kết thúc tại scale y = 1
//                st.setCycleCount(1);  // Chỉ chạy 1 lần
//
//                if (column == 6) {
//                    column = 0;
//                    row += 1;
//                }
//                nodeList.add(pane);
//                pane.setOnMouseClicked(event -> {
//                    int position = nodeList.indexOf(pane);
//                    dimPane.setVisible(true);
//                    currentId = CardListData.get(position).getProduct_id();
//                    productNameTxtField.setText(CardListData.get(position).getName());
//                    productColorTxtField.setText(CardListData.get(position).getColor());
//                    productSizeTxtField.setText(CardListData.get(position).getSize());
//                    productPriceTxtField.setText(Integer.toString(CardListData.get(position).getPrice()));
//                    productQuantityTxtField.setText(Integer.toString(CardListData.get(position).getQuantity()));
//                    productDescriptionTxtArea.setText(CardListData.get(position).getDescription());
//                    String productType = ProductType_DAO.getInstance().findById(CardListData.get(position).getType_id()).getCategory();
//                    productTypeTxtField.setText(productType);
//                    productInfoAnchorpane.setVisible(true);
//
////                    try {
////                        setCheckImageButton(productImageView2, cardListData.get(position));
////                    } catch (MalformedURLException e) {
////                        throw new RuntimeException(e);
////                    }
//                    st.play();
//                });

                if(column == 6){
                    column = 0;
                    row += 1;
                }

                GridPane.add(pane, column++, row);
                lastColumn = column;
                lastRow = row;
                GridPane.setMargin(pane, new Insets(10));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public void displayAnchorPaneBillList() {
        AnchorPaneCustomer.setVisible(false);
        AnchorPaneProductList.setVisible(true);
    }



    @FXML
    void clickButtonNextProduct() {
        System.out.println("Click button next product");
        System.out.println(listProductPick.size());
        for(Product product: listProductPick){
            System.out.println(product.getName());
            System.out.println(product.getQuantity());
            System.out.println("\n");
        }

        System.out.println("id employee current: " +  IdEmployeeCurrent);
        String condition = "name = '" + newCustomer.getName() + "' AND dateOfBirth = '" + newCustomer.getDate_of_birth() + "' AND phone_number = '" + newCustomer.getPhone_number() + "'";
        int customer_id = Customer_DAO.getInstance().findByCondition(condition).get(0).getCustomer_id();
        System.out.println("customer id: " + customer_id);

        AnchorPaneProductList.setVisible(false);
        AnchorPaneBillInfor.setVisible(true);

        //

        //cal total price
        int totalPrice = 0;
        for(Product product: listProductPick){
            totalPrice += product.getPrice() * product.getQuantity();
        }
        //display
        tableViewBillInformation.getColumns().clear();
        tableViewBillInformation.getItems().clear();

        Order order = new Order();
        textFieldBillId.setText(order.getOrder_id()+"");
        textFieldCustomer.setText(newCustomer.getName());
        Employee employeeCurrent = Employee_DAO.getInstance().findById(IdEmployeeCurrent+"");
        textFieldStaffName.setText(employeeCurrent.getName());
        textFieldOrderDate.setText(order.getCurrentTime());
        LabelTotalPrice.setText(totalPrice + "VND");

        TableColumn<Product,String> nameProduct = new TableColumn<Product,String>("Product Name");
        TableColumn<Product,Integer> unitPrice = new TableColumn<Product,Integer>("Unit Price");
        TableColumn<Product,Integer> quantity = new TableColumn<Product,Integer>("Quantity");

        nameProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));

        nameProduct.setPrefWidth(1000);
        nameProduct.setResizable(false);

        unitPrice.setPrefWidth(200);
        unitPrice.setResizable(false);

        quantity.setPrefWidth(200);
        quantity.setResizable(false);

        for(Product product: listProductPick){
           if(product.getQuantity() == 0){
               listProductPick.remove(product);
           }
        }
        ObservableList<Product> List = FXCollections.observableArrayList(listProductPick);
        tableViewBillInformation.getColumns().addAll(nameProduct,unitPrice,quantity);
        tableViewBillInformation.setItems(List);

    }

    @FXML
    void clickButtonPurchase(){
        if(IdOrderCurrentIfPickStatusUnconfirmed != -1){
            //delete data in order detail
            ArrayList<OrderDetail> orderDetails = OrderDetail_DAO.getInstance().findByCondition("order_id = " + IdOrderCurrentIfPickStatusUnconfirmed);
            for(OrderDetail orderDetail: orderDetails){
                OrderDetail_DAO.getInstance().delete(orderDetail);
            }

            //delete data in order
            Order order = Order_DAO.getInstance().findById(IdOrderCurrentIfPickStatusUnconfirmed+"");
            Order_DAO.getInstance().delete(order);
            IdOrderCurrentIfPickStatusUnconfirmed = -1;//reset
        }
        //get text
        int order_id = Integer.parseInt(textFieldBillId.getText());
        String condition = "name = '" + newCustomer.getName() + "' AND dateOfBirth = '" + newCustomer.getDate_of_birth() + "' AND phone_number = '" + newCustomer.getPhone_number() + "'";
        int customer_id = Customer_DAO.getInstance().findByCondition(condition).get(0).getCustomer_id();
        String order_date = textFieldOrderDate.getText();
        int employee_id = IdEmployeeCurrent;
        int total_price = Integer.parseInt(LabelTotalPrice.getText().replace("VND",""));
        int status = 1; //confirmed
        //save order
        Order newOrder = new Order();
        newOrder.setOrder_id(order_id);
        newOrder.setCustomer_id(customer_id);
        newOrder.setOrder_date(order_date);
        newOrder.setEmployee_id(employee_id);
        newOrder.setTotalPrice(total_price);
        newOrder.setStatus(status);
        //save order detail
        List<OrderDetail> OrderDetails = new ArrayList<>();
        for(Product product: listProductPick){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_id(order_id);
            orderDetail.setProduct_id(product.getProduct_id());
            orderDetail.setQuantity(product.getQuantity());
            orderDetail.setUnit_price(product.getPrice());
            OrderDetails.add(orderDetail);
        }
        //update quantity
        for(OrderDetail orderDetail: OrderDetails){
            Product product = Product_DAO.getInstance().findById(orderDetail.getProduct_id()+"");
            product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
            Product_DAO.getInstance().update(product);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Purchase Confirmation");
        alert.setContentText("Do you want to save the purchase to the database?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // User chose OK, save to database
            Order_DAO.getInstance().insert(newOrder);
            for(OrderDetail orderDetail: OrderDetails){
                OrderDetail_DAO.getInstance().insert(orderDetail);
            }
            //alert
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Purchase successfully");
            alert1.showAndWait();

            //reload bill list
            addBillList();
            //show bill list
            AnchorPaneBillList.setVisible(true);
            AnchorPaneBillInfor.setVisible(false);

        } else {
            // User chose Cancel, do nothing
            alert.close();
        }

    }

    @FXML
    void clickButtonSave(){
        if(IdOrderCurrentIfPickStatusUnconfirmed != -1){
            //delete data in order detail
            ArrayList<OrderDetail> orderDetails = OrderDetail_DAO.getInstance().findByCondition("order_id = " + IdOrderCurrentIfPickStatusUnconfirmed);
            for(OrderDetail orderDetail: orderDetails){
                OrderDetail_DAO.getInstance().delete(orderDetail);
            }

            //delete data in order
            Order order = Order_DAO.getInstance().findById(IdOrderCurrentIfPickStatusUnconfirmed+"");
            Order_DAO.getInstance().delete(order);
            IdOrderCurrentIfPickStatusUnconfirmed = -1;//reset
        }
        //get text
        int order_id = Integer.parseInt(textFieldBillId.getText());
        String condition = "name = '" + newCustomer.getName() + "' AND dateOfBirth = '" + newCustomer.getDate_of_birth() + "' AND phone_number = '" + newCustomer.getPhone_number() + "'";
        int customer_id = Customer_DAO.getInstance().findByCondition(condition).get(0).getCustomer_id();
        String order_date = textFieldOrderDate.getText();
        int employee_id = IdEmployeeCurrent;
        int total_price = Integer.parseInt(LabelTotalPrice.getText().replace("VND",""));
        int status = 0; //unconfirmed
        //save order
        Order newOrder = new Order();
        newOrder.setOrder_id(order_id);
        newOrder.setCustomer_id(customer_id);
        newOrder.setOrder_date(order_date);
        newOrder.setEmployee_id(employee_id);
        newOrder.setTotalPrice(total_price);
        newOrder.setStatus(status);
        //save order detail
        List<OrderDetail> OrderDetails = new ArrayList<>();
        for(Product product: listProductPick){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_id(order_id);
            orderDetail.setProduct_id(product.getProduct_id());
            orderDetail.setQuantity(product.getQuantity());
            orderDetail.setUnit_price(product.getPrice());
            OrderDetails.add(orderDetail);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Save Confirmation");
        alert.setContentText("Do you want to save the save to the database?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // User chose OK, save to database
            Order_DAO.getInstance().insert(newOrder);
            for(OrderDetail orderDetail: OrderDetails){
                OrderDetail_DAO.getInstance().insert(orderDetail);
            }
            //alert
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Save successfully");
            alert1.showAndWait();

            //reload bill list
            addBillList();
            //show bill list
            AnchorPaneBillList.setVisible(true);
            AnchorPaneBillInfor.setVisible(false);
            
        } else {
            // User chose Cancel, do nothing
            alert.close();
        }


    }



    @FXML
    void clickButtonDelete(){
        //chi xoa khi chua xac nhan

        Bill bill = (Bill) billList_table.getSelectionModel().getSelectedItem();
        if(bill.getStatus() == "unconfimred"){
            ArrayList<OrderDetail> orderDetail = OrderDetail_DAO.getInstance().findByCondition("order_id = " + bill.getBill_Id());
            for(OrderDetail o : orderDetail){
                OrderDetail_DAO.getInstance().delete(o);
            }

            Order order = Order_DAO.getInstance().findById(bill.getBill_Id()+"");
            Order_DAO.getInstance().delete(order);
            //alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Delete successfully");
            alert.showAndWait();
            //refresh
            addBillList();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can't delete confirmed bill");
            alert.showAndWait();
        }

    }

    public void pressOutOfRange(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Thêm EventFilter cho MouseEvent.MOUSE_CLICKED vào scene hoặc root pane của bạn.
                billList_table.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Node node = mouseEvent.getPickResult().getIntersectedNode();
                        Boolean clickedOn = false;
                        while (node != null) {
                            if (node == billList_table || node == ButtonRemove || node == ButtonAdd) {
                                clickedOn = true;
                                break;
                            }
                            node = node.getParent();
                        }

                        if (!clickedOn) {
                            billList_table.getSelectionModel().clearSelection();

                        }
                    }
                });
            }
        });
    }

    @FXML
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
    @FXML
    public void setProfile_btn(){
        AnchorPaneBillList.setVisible(false);
        AnchorPaneStaffInformation.setVisible(true);
        Employee employee = Employee_DAO.getInstance().findById(IdEmployeeCurrent+"");
        if(employee != null){
            setStaffID.setText(employee.getEmployee_id()+"");
            setStaffName.setText(employee.getName());
            setStaffPhoneNumber.setText(employee.getPhone_number());
            setStaffAddress.setText(employee.getAddress());
            setStaffEmail.setText(employee.getEmail());
            User user = User_DAO.getInstance().findByUsername(employee.getEmail());
            setStaffPassword.setText(user.getPassword());
            String avapath = Employee_DAO.getInstance().getavapath(IdEmployeeCurrent);
            setStaffavapath.setText(avapath);
        }
    }

    @FXML
    public void saveInforamtion(){
        Employee employee = Employee_DAO.getInstance().findById(IdEmployeeCurrent+"");
        employee.setName(setStaffName.getText());
        employee.setPhone_number(setStaffPhoneNumber.getText());
        employee.setAddress(setStaffAddress.getText());
        employee.setEmail(setStaffEmail.getText());
        employee.setImage_path(setStaffavapath.getText());
        if(setStaffavapath.getText() != null && !setStaffavapath.getText().isEmpty()) {
            Image tempimage = new Image(setStaffavapath.getText());
//            double radius = Math.min(tempimage.getWidth(), tempimage.getHeight()) / 2;
//            Circle clip = new Circle(radius);
            avatar.setImage(tempimage);
        }

        Employee_DAO.getInstance().update(employee);
    }

    @FXML
    public void setStaffPass_btn(){
        AnchorPaneStaffInformation.setVisible(false);
        AnchorPaneSetPassword.setVisible(true);
    }
    @FXML
    public void saveNewPass(){
        AnchorPaneStaffInformation.setVisible(false);
        AnchorPaneSetPassword.setVisible(true);
        User user = User_DAO.getInstance().findById(IdEmployeeCurrent);
        String currentPass = currentPass_txt.getText();
        String newPass = newPass_txt.getText();
        String confirmPass = confirmPass_txt.getText();
        if(currentPass.equals(user.getPassword()) && newPass.equals(confirmPass)){
            user.setPassword(newPass);
            User_DAO.getInstance().update(user,false);
            AnchorPaneSetPassword.setVisible(false);
            AnchorPaneBillList.setVisible(true);
        }
        else if(!currentPass.equals(user.getPassword())){
            wrongPass_label.setText("Wrong password");
        } else if (!newPass.equals(confirmPass)){
            wrongConfirm_label.setText("Password does not match");
        }
    }

    @FXML
    public void BackStaffInfor(){
        AnchorPaneSetPassword.setVisible(false);
        AnchorPaneStaffInformation.setVisible(true);
    }

    @FXML
    public void backBilllist(){
        AnchorPaneStaffInformation.setVisible(false);
        AnchorPaneBillList.setVisible(true);
    }
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println(IdEmployeeCurrent);
        String name = (Employee_DAO.getInstance().getemployeeName(IdEmployeeCurrent));
        menubutton.setText(name);
        String avapath = Employee_DAO.getInstance().getavapath(IdEmployeeCurrent);
        if(avapath != null && !avapath.isEmpty()) {
            Image tempimage = new Image(avapath);
//            double radius = Math.min(tempimage.getWidth(), tempimage.getHeight()) / 2;
//            Circle clip = new Circle(radius);
            avatar.setImage(tempimage);
//          avatar.setClip(clip);
        }
        listProductCache = Product_DAO.getInstance().findActiveProduct();
        //set up combobox
        ComboBoxGender.getItems().addAll("Male", "Female", "Other");
        //set up bill list
        addBillList();
        //set up product list
        try {
            initCardList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pressOutOfRange();
        pressOutOfRangeProductDetailRange();


    }
    public String choosePictureFromDialog() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose");
        File selected = fileChooser.showOpenDialog(null);
        File url = selected;
        try {
            String path = url.toURI().toURL().toString();
            setStaffavapath.setText(path);
            return path;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pressOutOfRangeProductDetailRange(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Thêm EventFilter cho MouseEvent.MOUSE_CLICKED vào scene hoặc root pane của bạn.
                productInfoAnchorpane.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Node node = mouseEvent.getPickResult().getIntersectedNode();
                        Boolean clickedOn = false;
                        while (node != null) {
                            if (node == productInfoAnchorpane ) {
                                clickedOn = true;
                                break;
                            }
                            node = node.getParent();
                        }

                        if (!clickedOn) {
                            productInfoAnchorpane.setVisible(false);
                            dimPane.setVisible(false);

                        }
                    }
                });
            }
        });
        // Trong phương thức initialize() hoặc một phương thức khởi tạo tương tự

    }

    public static void setImageAnchorPane(Product product) {
        instance.displayProduct(product);
    }

    public void displayProduct(Product product) {
        // Cập nhật AnchorPane với thông tin từ product
        // Ví dụ:
        productNameTxtField.setText(product.getName());
        productTypeTxtField.setText(ProductType_DAO.getInstance().findById(product.getType_id()).getCategory());
        productPriceTxtField.setText(String.valueOf(product.getPrice()));
        productColorTxtField.setText(product.getColor());
        productSizeTxtField.setText(product.getSize());
        productQuantityTxtField.setText(String.valueOf(product.getQuantity()));
        productDescriptionTxtArea.setText(product.getDescription());
        productImageView.setImage(new Image(product.getImage()));
        dimPane.setVisible(true);
        productInfoAnchorpane.setVisible(true);
        // ...
    }

}
