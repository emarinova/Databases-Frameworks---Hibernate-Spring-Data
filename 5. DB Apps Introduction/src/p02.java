
import java.sql.*;

public class p02 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {


        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = connection.prepareStatement(
                "SELECT v.name AS name, COUNT(m_v.minion_id) AS count_minions\n" +
                        "FROM villains AS v\n" +
                        "JOIN minions_villains AS m_v ON v.id = m_v.villain_id\n" +
                        "GROUP BY m_v.villain_id\n" +
                        "HAVING count_minions > 3\n" +
                        "ORDER BY count_minions DESC;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            String name = rs.getString("name");
            String number = rs.getString("count_minions");
            System.out.println(name + " " + number);
        }

        rs.close();
        ps.closeOnCompletion();
        connection.close();
    }
}
