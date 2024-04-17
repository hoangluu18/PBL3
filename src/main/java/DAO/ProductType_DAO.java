package DAO;

import Database.JDBC_Util;
import Model.ProductType;

import java.sql.*;
import java.util.ArrayList;

public class ProductType_DAO implements DAO_Interface<ProductType, Integer>{
    @Override
    public int insert(ProductType entity) {
        String sql = "INSERT INTO product_type (type_id, category) VALUES (?,?)";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getType_id());
            statement.setString(2, entity.getCategory());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ProductType entity) {
        String sql = "UPDATE product_type SET category = ? WHERE type_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getCategory());
            statement.setInt(2, entity.getType_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(ProductType entity) {
        String sql = "DELETE FROM product_type WHERE type_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getType_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<ProductType> findAll() {
        String sql = "SELECT * FROM product_type";
        ArrayList<ProductType> productTypes = new ArrayList<>();

        try( Connection connection = JDBC_Util.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ProductType productType = new ProductType();
                productType.setType_id(resultSet.getInt("type_id"));
                productType.setCategory(resultSet.getString("category"));
                productTypes.add(productType);
            }
            JDBC_Util.closeConnection(connection);
            return productTypes;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductType findById(Integer integer) {
        ProductType productType = new ProductType();
        String sql = "SELECT * FROM product_type WHERE type_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                productType.setType_id(resultSet.getInt("type_id"));
                productType.setCategory(resultSet.getString("category"));
            }
            JDBC_Util.closeConnection(connection);
            return productType;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ProductType> findByCondition(String condition) {

        String sql = "SELECT * FROM product_type WHERE " + condition;
        ArrayList<ProductType> productTypes = new ArrayList<>();

        try(Connection connection = JDBC_Util.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ProductType productType = new ProductType();
                productType.setType_id(resultSet.getInt("type_id"));
                productType.setCategory(resultSet.getString("category"));
                productTypes.add(productType);
            }
            JDBC_Util.closeConnection(connection);
            return productTypes;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ProductType_DAO getInstance(){
        return new ProductType_DAO();
    }
}
