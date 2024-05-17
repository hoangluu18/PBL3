package DAO;

import Database.JDBC_Util;
import Model.Manager;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager_DAO {
    public String getmanagerName(int id) {
        String sql = "select name from manager\n" +
                "where manager_id = ?;";
        String name = "";
        try (Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
            return name;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getavapath(int id) {
        String sql = "select image_path from manager\n" +
                "where manager_id = ?;";
        String avapath = "";
        try (Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                avapath = resultSet.getString("image_path");
                System.out.println(avapath);
            }
            return avapath;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertManager(Manager manager) {
        String sql = "insert into manager (manager_id, name, image_path) values (?,?,?) ";
        try (Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, manager.getManager_id());
            statement.setString(2, manager.getName());
            statement.setString(3, manager.getImage_path());
            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);
            JDBC_Util.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Manager_DAO getInstance(){
        return new Manager_DAO();
    }
}
