package DAO;

import Database.JDBC_Util;
import Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Customer_DAO implements DAO_Interface<Customer, String>{
    @Override
    public int insert(Customer entity) {

        String sql = "INSERT INTO customers (customer_id, Name, dateOfBirth, phone_number) VALUES (?,?,?,?)";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getCustomer_id());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getDate_of_birth());
            statement.setString(4, entity.getPhone_number());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Customer entity) {

        String sql = "UPDATE customers SET Name = ?, dateOfBirth = ?, phone_number = ? WHERE customer_id = ?";
        try (Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDate_of_birth());
            statement.setString(3, entity.getPhone_number());
            statement.setInt(4, entity.getCustomer_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

        @Override
    public int delete(Customer entity) {

        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getCustomer_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Customer> findAll() {
        ArrayList<Customer> listCustomer = new ArrayList<Customer>();
        String sql = "SELECT * FROM customers";

        try (Connection connection = JDBC_Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("Name"));
                customer.setDate_of_birth(resultSet.getString("dateOfBirth"));
                customer.setPhone_number(resultSet.getString("phone_number"));

                listCustomer.add(customer);

            }
            JDBC_Util.closeConnection(connection);
            return listCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findById(String id){
        Customer customer = new Customer();

        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("Name"));
                customer.setDate_of_birth(resultSet.getString("dateOfBirth"));
                customer.setPhone_number(resultSet.getString("phone_number"));
            }
            JDBC_Util.closeConnection(connection);
            return customer;
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public ArrayList<Customer> findByCondition(String condition) {
        ArrayList<Customer> listCustomer = new ArrayList<Customer>();
        String sql = "SELECT * FROM customers WHERE " + condition;

        try (Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("Name"));
                customer.setDate_of_birth(resultSet.getString("dateOfBirth"));
                customer.setPhone_number(resultSet.getString("phone_number"));

                listCustomer.add(customer);
            }
            JDBC_Util.closeConnection(connection);
            return listCustomer;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Customer_DAO getInstance(){
        return new Customer_DAO();
    }
}
