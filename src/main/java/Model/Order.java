package Model;

public class Order {
    private int order_id;
    private int customer_id; // khóa ngoại của bảng customer
    private int employee_id; // khóa ngoại của bảng employee
    private String order_date;
    private int totalPrice;
    private int status; // 0: chưa xác nhận, 1: đã xác nhận


    public Order(int order_id, int customer_id, String order_date, int employee_id, int totalPrice, int status) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.employee_id = employee_id;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
