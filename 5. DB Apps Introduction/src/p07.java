import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class p07 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        String getMinions = "SELECT * FROM minions;";

        PreparedStatement ps = connection.prepareStatement(getMinions);

        ResultSet rs = ps.executeQuery();

        ArrayList<String> minions = new ArrayList<>();

        while(rs.next()) {
            minions.add(rs.getString("name"));
        }

        for (int i = 0; i < minions.size()/2; i++){
                System.out.println(minions.get(i));
                System.out.println(minions.get(minions.size()-i-1));
        }

        if(minions.size()%2==1){
            System.out.println(minions.get(minions.size()/2));
        }

        rs.close();
        ps.closeOnCompletion();
        connection.close();
    }
}
