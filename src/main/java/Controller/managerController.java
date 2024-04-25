package Controller;

import Model.Order;
import Model.OrderDetail;
import Model.Product;
import com.gluonhq.charm.glisten.control.Avatar;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class managerController {

    Product test = new Product(1, "test", 100, "black", "big", 1, "nhu lol", "/resources/Picture/Ava.jpg",1);
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
    private ImageView testImage;
    @FXML
    private Button buttonTest;
    @FXML
    private Button homeBtn;
    @FXML
    private GridPane gridCardPane;
    @FXML
    private TableView table_billList ,table_productDetail;
    @FXML
    public void anchorProductappear() throws IOException {
        anchorStaff.setVisible(false);
        anchorBill.setVisible(false);
        anchorHome.setVisible(false);
        getAnchorProduct.setVisible(true);
        menuDisplayCard();
    }

    @FXML
    public void anchorHomeappear(){
        anchorStaff.setVisible(false);
        anchorBill.setVisible(false);
        anchorHome.setVisible(true);
        getAnchorProduct.setVisible(false);
    }
    @FXML
    public void anchorStaffappear(){
        anchorBill.setVisible(false);
        anchorHome.setVisible(false);
        anchorStaff.setVisible(true);
        getAnchorProduct.setVisible(false);

    }
    @FXML
    public void anchorBillappear(){
        if(getAnchorProduct.isVisible() == false) {
            anchorStaff.setVisible(false);
            anchorHome.setVisible(false);
            anchorBill.setVisible(true);
            getAnchorProduct.setVisible(false);
            addTable_bill();
        }
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
            testImage.setImage(image);
        }
    }
    public void menuDisplayCard() throws IOException {
        int r = 0;
        int c = 0;
        try{
            FXMLLoader load = new FXMLLoader();
            load.setLocation(this.getClass().getResource("cardProduct.fxml"));
            AnchorPane pane = (AnchorPane) load.load();
            cardProductController cardC = (cardProductController) load.getController();
            cardC.setData((test));

//            if (c == 5){
//                c = 0;
//                r++;
//            }
            this.gridCardPane.add(pane, ++c, r);
            GridPane.setMargin(pane, new Insets(10.0));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String choosePictureFromDialog() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose");
        File selected = fileChooser.showOpenDialog(null);
        File url = selected;
        try {
            String path = url.toURI().toURL().toString();
            Image imageForFile = new Image(selected.toURI().toURL().toString());
            testImage.setImage(imageForFile);
            return path;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTable_bill(){
        Order order1 = new Order(1,1,"22-12-2024", 1, 10000,1);
        Order order2 = new Order(2,2,"1-12-2024", 2, 20000,2);
        ArrayList<Order> order = new ArrayList<>();
        order.add(order1);
        order.add(order2);

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
        table_billList.getColumns().addAll(idColumn,customer_id,Date,Employee_id,totalPrice,status);
        table_billList.setItems(orderdata);

    }


//        table_billList.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, newSelection) -> {
//            if(newSelection != null) {
//                String orderID = newSelection.ge
//            }
//        } ));

    public int getOrderID(){
        Order selectedOrder = (Order) table_billList.getSelectionModel().getSelectedItem();
        int values = selectedOrder.getOrder_id();
        return values;
    }

    public void addProductDetail(){
        OrderDetail orderDetail1 = new OrderDetail(1,1,1,1,1000);
        OrderDetail orderDetail2 = new OrderDetail(1,1,1,1,1000);
        OrderDetail orderDetail3 = new OrderDetail(1,1,1,1,1000);

        OrderDetail orderDetail4 = new OrderDetail(2,2,2,2,2);
        ArrayList<OrderDetail> detailList = new ArrayList<>();
        detailList.add(orderDetail1);
        detailList.add(orderDetail2);
        detailList.add(orderDetail3);
        detailList.add(orderDetail4);
        TableColumn<OrderDetail, Integer>idcolumn = new TableColumn<OrderDetail, Integer>("Order ID");
        TableColumn<OrderDetail, Integer>order_detail_id = new TableColumn<OrderDetail, Integer>("Order detail ID");
        TableColumn<OrderDetail, Integer>product_id = new TableColumn<OrderDetail, Integer>("Product id");
        TableColumn<OrderDetail, Integer>quantity = new TableColumn<OrderDetail, Integer>("Quantity");
        TableColumn<OrderDetail, Integer>unit_price = new TableColumn<OrderDetail, Integer>("Unit Price");

        idcolumn.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("order_id"));
        order_detail_id.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("order_detail_id"));
        product_id.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("product_id"));
        quantity.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("quantity"));
        unit_price.setCellValueFactory(new PropertyValueFactory<OrderDetail, Integer>("unit_price"));

        idcolumn.setPrefWidth(1430 / 5);
        order_detail_id.setPrefWidth(1430 / 5);
        product_id.setPrefWidth(1430 / 5);
        quantity.setPrefWidth(1430 / 5);
        unit_price.setPrefWidth(1430 / 5);
        int orderID = getOrderID();
        ArrayList<OrderDetail> selected = new ArrayList<>();

        for(int i = 0; i < detailList.size(); i++) {
            int index = detailList.get(i).getOrder_detail_id();
            if(index == orderID) {
                selected.add(detailList.get(i));
            }
        }
        ObservableList<OrderDetail> orderDetailsdata = FXCollections.observableArrayList(selected);
        table_productDetail.getColumns().addAll(idcolumn,order_detail_id, product_id, quantity, unit_price);
        table_productDetail.setItems(orderDetailsdata);
    }

}



