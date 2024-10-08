package Controller;

import javafx.fxml.FXML;

import Model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Controller.staffController.listProductPick;

public class CardProductStaffController {
    @FXML
    private ImageView ImageViewStaffCard;
    @FXML
    private Label Price;
    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private Label Name;

    @FXML
    private  Label LabelQuantity;

    private static boolean isCheck = true;

    @FXML
    void mouseClicked(){
        Product data = new Product();
        data.setProduct_id(product_id);
        data.setName(name);
        data.setPrice(price);
        data.setColor(color);
        data.setSize(size);
        data.setDescription(description);
        data.setType_id(type_id);
        data.setImage(productData.getImage());
        data.setQuantity(spinner.getValue());


        // Kiểm tra xem sản phẩm đã tồn tại trong danh sách hay chưa
        Product existingProduct = findProductInList(data.getProduct_id());
        if (existingProduct != null) {
            // Nếu sản phẩm đã tồn tại nhưng có số lượng khác, xóa sản phẩm cũ và thêm sản phẩm mới
            if (existingProduct.getQuantity() != data.getQuantity()) {
                listProductPick.remove(existingProduct);
                listProductPick.add(data);
            }
        } else {
            // Nếu sản phẩm chưa tồn tại trong danh sách, thêm sản phẩm mới
            listProductPick.add(data);
        }
        System.out.println("hee");
    }

    @FXML
    void clickImage(){
        System.out.println("click image");
        System.out.println(product_id);
        System.out.println(name);
        System.out.println(price);
        System.out.println(color);
        System.out.println(size);
        System.out.println(quantity);
        System.out.println(description);
        System.out.println(type_id);
        System.out.println(productData.getImage());
        Product product = new Product();
        product.setProduct_id(product_id);
        product.setName(name);
        product.setPrice(price);
        product.setColor(color);
        product.setSize(size);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setType_id(type_id);
        product.setImage(productData.getImage());


        staffController.setImageAnchorPane(product);
    }


    private Product findProductInList(int productId) {
        for (Product product : listProductPick) {
            if (product.getProduct_id() == productId) {
                return product;
            }
        }
        return null;
    }



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
        this.LabelQuantity.setText("" +productData.getQuantity());

    }

    public void setImageByPath(Product product, ImageView imageView) {
        String imagePath = product.getImage();
        if (!imagePath.startsWith("file:")) {
            imagePath = "file:" + imagePath; // Prepend "file:" if missing
        }
        Image image = new Image(imagePath);
        imageView.setImage(image);
    }

    public void setProductInfo(Product product){
        Price.setText((Integer.toString(product.getPrice())));
        setImageByPath(product, this.ImageViewStaffCard);
        Name.setText(product.getName());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, product.getQuantity(), 0);
        spinner.setValueFactory(valueFactory);
        int product_id = product.getProduct_id();
        for(Product p : listProductPick){
            if(p.getProduct_id() == product_id){
                spinner.getValueFactory().setValue(p.getQuantity());

            }
        }
    }
}

