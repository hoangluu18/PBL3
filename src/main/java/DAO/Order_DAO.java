package DAO;

import Database.JDBC_Util;
import Model.Order;

import java.sql.*;
import java.util.ArrayList;

public class Order_DAO implements DAO_Interface<Order, String>{
    @Override
    public int insert(Order entity) {

        String sql = "INSERT INTO orders (order_id, customer_id, employee_id, order_date, totalPrice,status) VALUES (?,?,?,?,?,?)";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getOrder_id());
            statement.setInt(2, entity.getCustomer_id());
            statement.setInt(3, entity.getEmployee_id());
            statement.setString(4, entity.getOrder_date());
            statement.setInt(5, entity.getTotalPrice());
            statement.setInt(6, entity.getStatus());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);


        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(Order entity) {
        String sql = "UPDATE orders SET customer_id = ?, employee_id = ?, order_date = ?, totalPrice = ?, status = ? WHERE order_id = ?";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getCustomer_id());
            statement.setInt(2, entity.getEmployee_id());
            statement.setString(3, entity.getOrder_date());
            statement.setInt(4, entity.getTotalPrice());
            statement.setInt(5, entity.getStatus());
            statement.setInt(6, entity.getOrder_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Order entity) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getOrder_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Order> findAll() {
        ArrayList<Order> listOrder = new ArrayList<Order>();
        String sql = "SELECT * FROM orders";

        try(Connection connection = JDBC_Util.getConnection();PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                order.setCustomer_id(resultSet.getInt("customer_id"));
                order.setEmployee_id(resultSet.getInt("employee_id"));
                order.setOrder_date(resultSet.getString("order_date"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setStatus(resultSet.getInt("status"));

                listOrder.add(order);
            }
            JDBC_Util.closeConnection(connection);
            return listOrder;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findById(String s) {
        Order order = new Order();
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                order.setOrder_id(resultSet.getInt("order_id"));
                order.setCustomer_id(resultSet.getInt("customer_id"));
                order.setEmployee_id(resultSet.getInt("employee_id"));
                order.setOrder_date(resultSet.getString("order_date"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setStatus(resultSet.getInt("status"));
            }
            JDBC_Util.closeConnection(connection);
            return order;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Order> findByCondition(String condition) {
        ArrayList<Order> listOrder = new ArrayList<Order>();
        String sql = "SELECT * FROM orders WHERE " + condition;

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                order.setCustomer_id(resultSet.getInt("customer_id"));
                order.setEmployee_id(resultSet.getInt("employee_id"));
                order.setOrder_date(resultSet.getString("order_date"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setStatus(resultSet.getInt("status"));

                listOrder.add(order);
            }
            JDBC_Util.closeConnection(connection);
            return listOrder;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Order_DAO getInstance(){
        return new Order_DAO();
    }
}
