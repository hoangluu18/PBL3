package Controller;

import DAO.*;
import Database.JDBC_Util;
import Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Controller.Hello_viewController.IdEmployeeCurrent;

public class staffController implements Initializable {


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

    private ObservableList<Product> CardListData;

    @FXML
    public void addBillList(){
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
        ArrayList<Product> data = Product_DAO.getInstance().findAll();
        ObservableList<Product> listData = FXCollections.observableArrayList(data);

        return listData;
    }

    public void initCardList() throws SQLException {
               // CardListData.clear();
        if (CardListData == null) {
            CardListData = FXCollections.observableArrayList();
        }
        CardListData.addAll(menuGetData());

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
    public void initialize(URL url, ResourceBundle rb) {
        String name = (Employee_DAO.getInstance().getemployeeName(IdEmployeeCurrent));
        menubutton.setText(name);
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

    }

}
