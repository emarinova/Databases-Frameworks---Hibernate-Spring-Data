import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;

public class p08 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] ids = Arrays.stream(reader.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        String update = "";

        for (int i = 0; i < ids.length; i++) {
            update = "UPDATE minions\n" +
                    "SET age = age + 1,\n" +
                    "name = CONCAT(UPPER(LEFT(name, 1)), SUBSTRING(name, 2))\n" +
                    "WHERE id = " + ids[i] + ";";
            PreparedStatement ps = connection.prepareStatement(update);
            ps.executeUpdate();
            ps.closeOnCompletion();
        }

        String getMinions = "SELECT * FROM minions;";

        PreparedStatement ps = connection.prepareStatement(getMinions);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.printf("%s %s%n", rs.getString("name"), rs.getString("age"));
        }

        rs.close();
        ps.closeOnCompletion();
        connection.close();
    }
}
