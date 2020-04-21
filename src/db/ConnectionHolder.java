package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHolder {
    private  static  final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private  static  final String DB_URL="jdbc:mysql://localhost:3306/gym?serverTimezone=UTC";
    private  static  final String DB_USERNAME="root";
    private  static  final String DB_PASSWORD="root";
    public ConnectionHolder(){

    }
    public static Connection getConnection()  {
        Connection connection=null;
        try {
            Class.forName(DB_DRIVER);
            connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.print("Connection ok");
        } catch (ClassNotFoundException  | SQLException v) {
            v.printStackTrace();
            System.out.print("Error");
        }
        return connection;
    }
}
