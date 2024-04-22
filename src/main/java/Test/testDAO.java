package Test;

import DAO.User_DAO;
import Model.User;

import java.util.ArrayList;

public class testDAO {
    public static void main(String[] rags) {
        User user = new User();
        user.setUserName("luuviethoang1");
        user.setPassword("123456");
        user.setRole(1);
        if(User_DAO.getInstance().insert(user) == User_DAO.isDuplicate){
            System.out.println("Duplicate");
        }
        else {
            System.out.println("Success");
        }
    }
}
