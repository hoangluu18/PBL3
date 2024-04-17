package Model;

public class User {
    private int user_id; // khóa chính employee_id cx chinh la khóa chính cua bang user
    private String userName;
    private String password;
    private int role;// 0: admin, 1: employee

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User(int user_id, String userName, String password, int role) {
        this.user_id = user_id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User() {
    }


}
