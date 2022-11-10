package jukebox1;
import java.sql.*;

public class Database {
    Connection con = null;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "root");
        return con;
    }
}
