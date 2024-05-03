package Controller;

import DAO.Customer_DAO;
import DAO.Product_DAO;
import Database.JDBC_Util;
import Model.Bill;
import Model.Customer;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class staffController implements Initializable {


    @FXML
    private AnchorPane AnchorPaneBillList;

    //AnchorPaneStaffInformation
    @FXML
    private AnchorPane AnchorPaneStaffInformation;


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

    public static List<Product> listProductPick = new ArrayList<Product>();

    //grid pane
    @FXML
    private GridPane GridPane;
    public static int lastColumn;
    public static int lastRow;

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
                bill.setStatus(resultSet.getInt("status"));
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
        TableColumn<Bill, Integer>status = new TableColumn<Bill, Integer>("Status");

        System.out.println("Add bill list");
        idColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("Bill_Id"));
        customer_name.setCellValueFactory(new PropertyValueFactory<Bill, String>("customer_name"));
        Date.setCellValueFactory(new PropertyValueFactory<Bill, String>("date"));
        Employee_name.setCellValueFactory(new PropertyValueFactory<Bill, String>("employee_name"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("total_price"));
        status.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("status"));

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
    public void clickBtnAdd(){
        //display AnchorPaneCustomer
        AnchorPaneBillList.setVisible(false);
        AnchorPaneCustomer.setVisible(true);

    }

    @FXML public void ClickButtonNext() throws SQLException {
        //get data from scene builder
        String name = TextFieldCustomerName.getText();
        String date = DatePickerDateOfBirth.getValue().toString();
        String phone = TextFieldPhoneNumber.getText();
        int gender = ComboBoxGender.getSelectionModel().getSelectedIndex();
        System.out.println(name + " " + date + " " + phone + gender + "");
        System.out.println("Click button next");

        //create new customer
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setDate_of_birth(date);
        newCustomer.setPhone_number(phone);
        newCustomer.setGender(gender);
        //add to database
        Customer_DAO.getInstance().insert(newCustomer);
        this.displayAnchorPaneBillList();
    }

    public ObservableList<Product> menuGetData() throws SQLException {
        ArrayList<Product> data = Product_DAO.getInstance().findAll();
        ObservableList<Product> listData = FXCollections.observableArrayList(data);

        return listData;
    }

    public void displayAnchorPaneBillList() throws SQLException {
        AnchorPaneCustomer.setVisible(false);
        AnchorPaneProductList.setVisible(true);

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





    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxGender.getItems().addAll("Male", "Female", "Other");
        addBillList();
    }
}
