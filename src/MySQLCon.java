package p1;
import java.sql.*;

public class MySQLCon {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "qwer1234";
    private final static String DB_NAME = "banca";
    private final static String CONNECTION_LINK = "jdbc:mysql://localhost:3306/banca";

    public Connection connectDB (){
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONNECTION_LINK,USERNAME,PASSWORD);
        }


        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}