import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class p03 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String villain_id = reader.readLine();

        PreparedStatement ps = connection.prepareStatement(
                "SELECT v.name, m.name, m.age\n" +
                "FROM villains AS v\n" +
                "LEFT JOIN minions_villains AS m_v ON v.id = m_v.villain_id\n" +
                "LEFT JOIN minions AS m ON m.id = m_v.minion_id\n" +
                "WHERE v.id = " + villain_id +
                ";");
        ResultSet rs = ps.executeQuery();

        int counter = 1;
        try {
                rs.next();
                System.out.printf("Villain: %s%n", rs.getString("v.name"));

                do {
                        if (rs.getString("m.name") == null) {
                            System.out.println("<no minions>");
                            break;

                        }

                        System.out.printf("%d. %s %s%n", counter, rs.getString("m.name"), rs.getString("m.age"));
                        counter++;


                } while (rs.next());
                String empty = "";
            } catch (Exception e) {
                System.out.printf("No villain with ID %s exists in the database.%n", villain_id);
        } finally {
                rs.close();
                ps.closeOnCompletion();
                connection.close();

        }
    }
}
