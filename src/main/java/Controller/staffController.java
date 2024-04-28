package Controller;

import DAO.Order_DAO;
import Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class staffController implements Initializable {
    @FXML
    private TableView billList_table;
    @FXML
    public void addBillList(){
        ArrayList<Order> order = Order_DAO.getInstance().findAll();
        TableColumn<Order, Integer>idColumn = new TableColumn<Order, Integer>("ID");
        TableColumn<Order, Integer>customer_id = new TableColumn<Order, Integer>("Customer_id");
        TableColumn<Order, String>Date = new TableColumn<Order, String>("Date");
        TableColumn<Order, Integer>Employee_id = new TableColumn<Order, Integer>("Employee_id");
        TableColumn<Order, Integer>totalPrice = new TableColumn<Order, Integer>("Total Price");
        TableColumn<Order, Integer>status = new TableColumn<Order, Integer>("Status");

        idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("order_id"));
        customer_id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("customer_id"));
        Date.setCellValueFactory(new PropertyValueFactory<Order, String>("order_date"));
        Employee_id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("employee_id"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Order, Integer>("totalPrice"));
        status.setCellValueFactory(new PropertyValueFactory<Order, Integer>("status"));

        idColumn.setPrefWidth(200);
        customer_id.setPrefWidth(200);
        Date.setPrefWidth(430);
        Employee_id.setPrefWidth(250);
        totalPrice.setPrefWidth(250);
        status.setPrefWidth(100);
        ObservableList<Order> orderdata = FXCollections.observableArrayList(order);
        billList_table.getColumns().addAll(idColumn,customer_id,Date,Employee_id,totalPrice,status);
        billList_table.setItems(orderdata);
    }

    public void initialize(URL url, ResourceBundle rb) {
        addBillList();
    }
}
