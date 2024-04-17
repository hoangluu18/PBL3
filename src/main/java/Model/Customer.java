package Model;

import java.util.Date;

public class Customer {
    private int customer_id;
    private String name;
    private String date_of_birth;
    private String phone_number;
    public Customer(String name, int customer_id, String date_of_birth, String phone_number) {
        this.name = name;
        this.customer_id = customer_id;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
    }

    public Customer() {
    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
