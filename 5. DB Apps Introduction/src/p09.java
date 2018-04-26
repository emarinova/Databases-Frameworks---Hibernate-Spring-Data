import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class p09 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int id = Integer.parseInt(reader.readLine());

        String callProcedure = "CALL usp_get_older(" + id + ");";

        PreparedStatement ps = connection.prepareStatement(callProcedure);

        if (ps.executeUpdate() != 0) {

            String getMinion = "SELECT * FROM minions WHERE id = " + id + ";";
            ps = connection.prepareStatement(getMinion);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.printf("%s %s", rs.getString("name"), rs.getString("age"));
            rs.close();
        }

        ps.closeOnCompletion();
        connection.close();
    }
}
