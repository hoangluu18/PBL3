package Controller;

import Model.Product;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class cardProductController {
    @FXML
    private ImageView Image;
    @FXML
    private Label Name;
    @FXML
    private Label Price;
    @FXML
    private AnchorPane cardPane;
    private Product productData;
    private int product_id;
    private String name;
    private int price;
    private String color;
    private String size;
    private int quantity;
    private String description;
    private int type_id;
    public void setData(Product productData){
        this.productData = productData;
        this.product_id = productData.getProduct_id();
        this.name = productData.getName();
        this.price = productData.getPrice();
        this.color = productData.getColor();
        this.size = productData.getSize();
        String path = "File:" + productData.getImage();
        this.quantity = productData.getQuantity();
        this.description = productData.getDescription();
        this.type_id = productData.getType_id();
    }

    public void add(){
        managerController managerController = new managerController();

    }

    public void initialize(){

    }
}
