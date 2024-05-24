package Model;

public class Employee {
    private  int employee_id;
    private String name;
    private String phone_number;
    private String address;
    private String email;
    private String image_path;

    public Employee(String name, int employee_id, String phone_number, String address, String email) {
        this.name = name;
        this.employee_id = employee_id;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }
    public Employee() {
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
