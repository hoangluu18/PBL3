package Test;

import DAO.OrderDetail_DAO;
import DAO.User_DAO;
import Model.OrderDetail;
import Model.User;

import java.util.ArrayList;

public class testDAO {
    public static void main(String[] rags) {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("0000");
        user.setRole(User.ADMIN);

        if(User_DAO.getInstance().insert(user) == User_DAO.isDuplicate) {// check duplicate account
            System.out.println("Duplicate account, try again");
        }



    }
}
