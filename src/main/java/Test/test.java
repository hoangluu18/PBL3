package Test;

import DAO.User_DAO;

public class test {
    public static void main(String[] rags) {
        String userName = "1";
        String password = "1";
        String condition = "userName = " + userName + " AND " + "password = " + password;
        if(User_DAO.getInstance().findByCondition(condition) == null){
            System.out.println("null");
        }
        else{
            System.out.println("not null");
        }
    }
}
