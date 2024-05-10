package DAO;

import Database.JDBC_Util;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;

public class Product_DAO implements DAO_Interface<Product, String>{
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    int product_id;
    @Override
    public int insert(Product entity) {
        //product_id, name, price, color, size, quantity, description, type_id
        String sql = "INSERT INTO products (name, price, color, size, quantity, description, type_id, image_path) VALUES (?,?,?,?,?,?,?,?)";

        try{
            connection = JDBC_Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPrice());
            statement.setString(3, entity.getColor());
            statement.setString(4, entity.getSize());
            statement.setInt(5, entity.getQuantity());
            statement.setString(6, entity.getDescription());
            statement.setInt(7, entity.getType_id());
            statement.setString(8,entity.getImage());

            statement.executeUpdate();
            result = statement.getGeneratedKeys();
            if(result.next()){
                product_id = result.getInt(1);
            }
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
        return  0;
    }

    @Override
    public int update(Product entity) {
        String sql = "UPDATE products SET name = ?, price = ?, color = ?, size = ?, quantity = ?, description = ?, type_id = ?, image_path = ? WHERE product_id = ?";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPrice());
            statement.setString(3, entity.getColor());
            statement.setString(4, entity.getSize());
            statement.setInt(5, entity.getQuantity());
            statement.setString(6, entity.getDescription());
            statement.setInt(7, entity.getType_id());
            statement.setString(8,entity.getImage());
            statement.setInt(9, entity.getProduct_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Product entity) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getProduct_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        String sql = "SELECT * FROM products";

        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));
                product.setImage(resultSet.getString("image_path"));
                if(product.getQuantity() > 0)
                listProduct.add(product);
            }
            JDBC_Util.closeConnection(connection);
            return listProduct;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(String s) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, Integer.parseInt(s));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));
                product.setImage(resultSet.getString("image_path"));
            }
            JDBC_Util.closeConnection(connection);
            return product;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Product> findByCondition(String condition) {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        String sql = "SELECT * FROM products WHERE " + condition;

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));
                product.setImage(resultSet.getString("image_path"));
                if(product.getQuantity() > 0)
                listProduct.add(product);
            }
            JDBC_Util.closeConnection(connection);
            return listProduct;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Product> findByname(String name) {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        String sql = "SELECT * FROM products WHERE name LIKE ?" ;

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));
                product.setImage(resultSet.getString("image_path"));
                listProduct.add(product);
            }
            JDBC_Util.closeConnection(connection);
            return listProduct;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Product_DAO getInstance(){
        return new Product_DAO();
    }
}
