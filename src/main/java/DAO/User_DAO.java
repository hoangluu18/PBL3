package DAO;

import Database.JDBC_Util;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.sql.*;
import java.util.ArrayList;

public class User_DAO implements DAO_Interface<User, Integer>{
    @Override
    public int insert(User entity) {

        //user_id, userName, password, role
        String sql = "INSERT INTO users (user_id, userName, password, role) VALUES (?,?,?,?)";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getUser_id());
            statement.setString(2, entity.getUserName());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getRole());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User entity) {

        String sql = "UPDATE users SET userName = ?, password = ?, role = ? WHERE user_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole());
            statement.setInt(4, entity.getUser_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(User entity) {

        String sql = "DELETE FROM users WHERE user_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getUser_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> listUser = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
                listUser.add(user);
            }
            JDBC_Util.closeConnection(connection);
            return listUser;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(Integer s) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, s);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
            }
            JDBC_Util.closeConnection(connection);
            return user;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> findByCondition(String condition) {

        String sql = "SELECT * FROM users WHERE " + condition;
        ArrayList<User> listUser = new ArrayList<User>();
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
                listUser.add(user);
            }
            JDBC_Util.closeConnection(connection);
            return listUser;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static User_DAO getInstance(){
        return new User_DAO();
    }

}
