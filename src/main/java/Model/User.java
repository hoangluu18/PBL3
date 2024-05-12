package Model;

public class User {
    private int user_id; // khóa chính employee_id cx chinh la khóa chính cua bang user
    private String userName;
    private String password;
    private int role;// 0: admin, 1: employee

    private int isActive; //1: van dang lam viec, 0 : da bi xoa khoi cua hang
    public static final int ADMIN = 0;
    public static final int EMPLOYEE = 1;

    public static final int NOT_ACTIVE = 0;
    public static final int ACTIVE = 1;

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

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
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

    public User(String userName, String password, int role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isActive = ACTIVE;
    }

    public User() {
        this.isActive = ACTIVE;
    }


}
