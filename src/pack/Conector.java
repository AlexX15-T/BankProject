package pack;
import java.sql.*;
public class Conector {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String DB_NAME = "banca";
    private final static String CONNECTION_LINK = "jdbc:mysql://localhost:3306/";
    public static void main(String[] args) throws ClassNotFoundException {

        Connection connection;

        try {
            connection = DriverManager.getConnection(CONNECTION_LINK + DB_NAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * from cont ");
            while(resultSet.next()){
                System.out.println(resultSet.getString("iban"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
