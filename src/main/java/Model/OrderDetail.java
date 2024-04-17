package Model;

public class OrderDetail {

    private int order_id; // khóa ngoại của bảng order
    private int product_id; // khóa ngoại của bảng product
    private int quantity;
    private int unit_price;

    public OrderDetail(int order_id, int product_id, int Quantity, int unit_price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = Quantity;
        this.unit_price = unit_price;
    }
    public OrderDetail() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }
}
