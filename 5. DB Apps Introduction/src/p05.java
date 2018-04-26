import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class p05 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String country = reader.readLine();

        String changeToUpcase = "UPDATE towns\n" +
                "SET name = UPPER(name)\n" +
                "WHERE country = \"" + country + "\"" +
                "AND name != upper(name);";

        PreparedStatement ps = connection.prepareStatement(changeToUpcase);


        if (ps.executeUpdate() != 0) {

            String getTowns = "SELECT * \n" +
                    "FROM towns\n" +
                    "WHERE country = \"" + country + "\";";

            ps = connection.prepareStatement(getTowns);
            ResultSet towns = ps.executeQuery();
            ArrayList<String> townNames = new ArrayList<>();
            while (towns.next()) {
                townNames.add(towns.getString("name"));
            }
            if (townNames.size() == 1) {
                System.out.println("1 town name was affected.");
            } else {
                System.out.println(townNames.size() + " town names were affected.");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(String town : townNames)
            {
                sb.append(town).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
            sb.append("]");
            System.out.println(sb.toString());

            towns.close();
        } else {
            System.out.println("No town names were affected.");
        }

        ps.closeOnCompletion();
        connection.close();
    }
}
