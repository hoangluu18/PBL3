package Controller;

import Database.JDBC_Util;
import Model.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class staffController implements Initializable {
    @FXML
    private TableView billList_table;


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

//        private int Bill_Id;
//        private String customer_name;
//        private String date;
//        private String employee_name;
//        private int total_price;
//        private int status; // 0: chưa xác nhận, 1: đã xác nhận

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

    public void initialize(URL url, ResourceBundle rb) {
        addBillList();
    }
}
