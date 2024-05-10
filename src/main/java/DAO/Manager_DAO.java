package DAO;

import Database.JDBC_Util;
import Model.Manager;

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


    public static Manager_DAO getInstance(){
        return new Manager_DAO();
    }
}
