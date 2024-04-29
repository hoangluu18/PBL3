package Model;

//new class bill
public class  Bill{
    private int Bill_Id;
    private String customer_name;
    private String date;
    private String employee_name;
    private int total_price;
    private int status; // 0: chưa xác nhận, 1: đã xác nhận

    public Bill(int id, String customer_name, String date, String employee_name, int total_price, int status) {
        this.Bill_Id = id;
        this.customer_name = customer_name;
        this.date = date;
        this.employee_name = employee_name;
        this.total_price = total_price;
        this.status = status;
    }
    public Bill(){

    }

    public int getBill_Id() {
        return Bill_Id;
    }

    public void setBill_Id(int id) {
        this.Bill_Id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
