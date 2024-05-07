package DAO;

import Database.JDBC_Util;
import Model.BillDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillDetail_DAO {
    public static BillDetail_DAO getInstance() {
        return new BillDetail_DAO();
    }

    public ArrayList<BillDetail> getBillDetail(int order_id) {
        ArrayList<BillDetail> billDetails = new ArrayList<BillDetail>();
        String sql = "select orders.order_id, order_detail_id, products.name, products.price, order_details.quantity\n" +
                "from orders\n" +
                "Join order_details on orders.order_id = order_details.order_id\n" +
                "Join products on order_details.product_id = products.product_id\n" +
                "Where orders.order_id = ?" ;
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order_id);
            ResultSet res = statement.executeQuery();
            while(res.next()) {
                BillDetail bill = new BillDetail();
                bill.setOrder_id(res.getInt("order_id"));
                bill.setOrderdetail_id(res.getInt("order_detail_id"));
                bill.setProduct_name(res.getString("name"));
                bill.setUnit_price(res.getInt("price"));
                bill.setQuantity(res.getInt("quantity"));
                billDetails.add(bill);
            }
            for(int i = 0; i < billDetails.size(); i++) {
                System.out.println(billDetails.get(i).getOrder_id());
                System.out.println(billDetails.get(i).getProduct_name());
                System.out.println(billDetails.get(i).getOrderdetail_id());
            }
            JDBC_Util.closeConnection(connection);
            return billDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
