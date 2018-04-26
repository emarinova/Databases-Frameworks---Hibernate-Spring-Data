import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class p01 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected!");

        connection.close();

    }
}
