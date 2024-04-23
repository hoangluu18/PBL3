package DAO;

import Database.JDBC_Util;
import Model.Order;
import Model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetail_DAO implements DAO_Interface<OrderDetail,Integer> {

    @Override
    public int insert(OrderDetail entity) {
        String sql = "INSERT INTO order_details (order_detail_id,order_id, product_id, quantity, unit_price) VALUES (?,?,?,?,?)";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getOrder_detail_id());
            statement.setInt(2, entity.getOrder_id());
            statement.setInt(3, entity.getProduct_id());
            statement.setInt(4, entity.getQuantity());
            statement.setInt(5, entity.getUnit_price());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(OrderDetail entity) {
        String sql = "UPDATE order_details SET order_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE order_detail_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getOrder_id());
            statement.setInt(2, entity.getProduct_id());
            statement.setInt(3, entity.getQuantity());
            statement.setInt(4, entity.getUnit_price());
            statement.setInt(5, entity.getOrder_detail_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(OrderDetail entity) {
        String sql = "DELETE FROM order_details WHERE order_detail_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getOrder_detail_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<OrderDetail> findAll() {

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM order_details";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder_detail_id(resultSet.getInt("order_detail_id"));
                orderDetail.setOrder_id(resultSet.getInt("order_id"));
                orderDetail.setProduct_id(resultSet.getInt("product_id"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setUnit_price(resultSet.getInt("unit_price"));

                orderDetails.add(orderDetail);
            }
            JDBC_Util.closeConnection(connection);
            return !orderDetails.isEmpty() ? orderDetails : null;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderDetail findById(Integer integer) {
        OrderDetail orderDetail = new OrderDetail();
        String sql = "SELECT * FROM order_details WHERE order_detail_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                orderDetail.setOrder_detail_id(resultSet.getInt("order_detail_id"));
                orderDetail.setOrder_id(resultSet.getInt("order_id"));
                orderDetail.setProduct_id(resultSet.getInt("product_id"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setUnit_price(resultSet.getInt("unit_price"));
            }
            JDBC_Util.closeConnection(connection);
            return orderDetail.getOrder_detail_id() > 0 ? orderDetail : null;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetail> findByCondition(String condition) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM order_details WHERE " + condition;
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder_detail_id(resultSet.getInt("order_detail_id"));
                orderDetail.setOrder_id(resultSet.getInt("order_id"));
                orderDetail.setProduct_id(resultSet.getInt("product_id"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setUnit_price(resultSet.getInt("unit_price"));

                orderDetails.add(orderDetail);
            }
            JDBC_Util.closeConnection(connection);
            return !orderDetails.isEmpty() ? orderDetails : null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static OrderDetail_DAO getInstance(){
        return new OrderDetail_DAO();
    }
}
