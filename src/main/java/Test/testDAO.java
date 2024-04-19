package Test;

import DAO.User_DAO;
import Model.User;

import java.util.ArrayList;

public class testDAO {
    public static void main(String[] rags) {
        ArrayList<User> listUser = User_DAO.getInstance().findAll();
        for(User user : listUser) {
            if(user.getRole() == User.ADMIN){
                System.out.println("Admin: " + user.getUserName());
            } else {
                System.out.println("Employee: " + user.getUserName());
            }
        }
    }
}
