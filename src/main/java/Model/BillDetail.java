package Model;

public class BillDetail {
    private int order_id;
    private int orderdetail_id;
    private String product_name;
    private int quantity;
    private int unit_price;

    public BillDetail(){

    }

    public BillDetail(int order_id, int orderdetail_id, String product_name, int quantity, int unit_price) {
        this.order_id = order_id;
        this.orderdetail_id = orderdetail_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public void setOrderdetail_id (int orderdetail_id) {
        this.orderdetail_id = orderdetail_id;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getOrder_id(){
        return this.order_id;
    }
    public int getOrderdetail_id(){
        return this.orderdetail_id;
    }
    public String getProduct_name(){
        return this.product_name;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public int getUnit_price() {
        return this.unit_price;
    }
}
