package DAO;

import Database.JDBC_Util;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class User_DAO implements DAO_Interface<User, Integer>{
    public static final int isDuplicate = -1;
    ResultSet result = null;
    Connection connection = null;
    PreparedStatement statement = null;

    public static String encode(String password){
        return  Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decode(String password){
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        return new String(decodedBytes);
    }
    @Override

    public int insert(User entity) {
        //check duplicate
        if(checkDuplicateAccounts(entity.getUserName(), entity.getPassword(), entity.getRole())){
            return isDuplicate;
        }
        //user_id, userName, password, role
        String sql = "INSERT INTO users (userName, password, role) VALUES (?,?,?)";
        try{
            int employee_id = 0;
            connection = JDBC_Util.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getUserName());
            statement.setString(2, encode(entity.getPassword()));
            statement.setInt(3, entity.getRole());

            statement.executeUpdate();
            result = statement.getGeneratedKeys();
            if(result.next()){
                employee_id = result.getInt(1);
            }
            return employee_id;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    @Override
    public int update(User entity) {
        //check duplicate
        if(checkDuplicateAccounts(entity.getUserName(), entity.getPassword(), entity.getRole())){
            return isDuplicate;
        }
        String sql = "UPDATE users SET userName = ?, password = ? WHERE user_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getUserName());
            statement.setString(2, encode(entity.getPassword()));
            statement.setInt(3, entity.getUser_id());

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
                user.setPassword(decode(resultSet.getString("password")));
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
                user.setPassword(decode(resultSet.getString("password")));
                user.setRole(resultSet.getInt("role"));
            }
            JDBC_Util.closeConnection(connection);
            return user;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByUsername(String username){
        String condition = "userName = '" + username + "'";
        ArrayList<User> listUser = findByCondition(condition);
        if(listUser != null){
            return listUser.get(0);
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
                user.setPassword(decode(resultSet.getString("password")));
                user.setRole(resultSet.getInt("role"));
                listUser.add(user);
            }
            JDBC_Util.closeConnection(connection);
            if(listUser.size() < 1) return null;
            return listUser;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkDuplicateAccounts(String username, String password, int role){
        String condition = "userName = '" + username  + "' AND role = '" + role + "'";
        if(findByCondition(condition) != null){
            return true;
        }
        return false;
    }
    public boolean checkUserID(User entity){
        String condition = "user_id = " + entity.getUser_id();
        if(findByCondition(condition) != null){
            return true;
        }
        return false;
    }

    public static User_DAO getInstance(){
        return new User_DAO();
    }
    public int update(User entity, boolean isUsernameChanged) {
        // Chỉ kiểm tra trùng lặp khi tên người dùng thay đổi
        if (isUsernameChanged && checkDuplicateAccounts(entity.getUserName(), entity.getPassword(), entity.getRole())){
            return isDuplicate;
        }
        String sql = "UPDATE users SET userName = ?, password = ? WHERE user_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getUserName());
            statement.setString(2, encode(entity.getPassword()));
            statement.setInt(3, entity.getUser_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}


