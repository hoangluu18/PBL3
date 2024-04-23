package Test;

import DAO.OrderDetail_DAO;
import DAO.User_DAO;
import Model.OrderDetail;
import Model.User;

import java.util.ArrayList;

public class testDAO {
    public static void main(String[] rags) {
        //test OrderDetail_DAO
//        OrderDetail o1 = OrderDetail_DAO.getInstance().findById(1);
//        System.out.println(o1.getOrder_detail_id());
//        System.out.println(o1.getOrder_id());
//        System.out.println(o1.getProduct_id());
//        System.out.println(o1.getQuantity());
//        System.out.println(o1.getUnit_price());

         //test condition OrderDetail_DAO
       ArrayList<OrderDetail> list = OrderDetail_DAO.getInstance().findByCondition("order_id = 1");
        for (OrderDetail o : list) {
            System.out.println(o.getOrder_detail_id());
            System.out.println(o.getOrder_id());
            System.out.println(o.getProduct_id());
            System.out.println(o.getQuantity());
            System.out.println(o.getUnit_price());
        }

        //test User_DAO




    }
}
