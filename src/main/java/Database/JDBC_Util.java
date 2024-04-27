package Database;

import com.sun.source.tree.BreakTree;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Util {
    public static Connection getConnection() throws SQLException {
        Connection c = null;

        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // thong so ket noi
            String url = "jdbc:mysql://localhost:3306/pbl3";
            String username = "root"; //sua theo may cua ban
            String password = ""; //sua theo may cua ban

            c = DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return c;
    }

    public static void closeConnection(Connection c){
        try{
            if(c != null){
                c.close();
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
}
