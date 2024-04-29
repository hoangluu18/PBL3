package Model;

import java.util.Date;

public class Customer {
    private int customer_id;
    private String name;
    private String date_of_birth;
    private String phone_number;
    private int gender;// 0 male, 1 female, 2 other

    public static final int MALE = 0;
    public static final int FEMALE = 1;
    public static final int OTHER = 2;

    public Customer() {
    }

    public Customer(int customer_id, String name, String date_of_birth, String phone_number, int gender) {
        this.customer_id = customer_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.gender = gender;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
