import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class p04 {
    private static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] minionInfo = reader.readLine().split(" ");
        String[] villainInfo = reader.readLine().split(" ");

        String minion_name = minionInfo[1];
        String minion_age = minionInfo[2];
        String town = minionInfo[3];
        String villain_name = villainInfo[1];

        String getTownId = "SELECT id\n" +
                "FROM towns\n" +
                "WHERE name = \"" + town + "\";";

        PreparedStatement getIdStatement = connection.prepareStatement(getTownId);

        ResultSet rs = getIdStatement.executeQuery();

        if (!rs.next()) {

            String addTown = "INSERT INTO towns(name)\n" +
                    "VALUES(\"" + town + "\");";
            PreparedStatement addStatement = connection.prepareStatement(addTown);
            int townAdded = addStatement.executeUpdate();
            if (townAdded == 1) {
                System.out.printf("Town %s was added to the database.%n", town);
                rs = getIdStatement.executeQuery();
                rs.next();
            }
            addStatement.closeOnCompletion();
        }
        int town_id = Integer.parseInt(rs.getString("id"));

        String getVillainId = "SELECT id \n" +
                "FROM villains\n" +
                "WHERE name = \"" + villain_name + "\";";
        getIdStatement = connection.prepareStatement(getVillainId);
        rs = getIdStatement.executeQuery();


        if (!rs.next()) {

            String addVillain = "INSERT INTO villains(name, evilness_factor)\n" +
                    "VALUES(\"" + villain_name + "\", \"evil\");";
            PreparedStatement addStatement = connection.prepareStatement(addVillain);
            int villainAdded = addStatement.executeUpdate();
            if (villainAdded == 1) {
                System.out.printf("Villain %s was added to the database.%n", villain_name);
                rs = getIdStatement.executeQuery();
                rs.next();
            }
            addStatement.closeOnCompletion();
        }

        int villain_id = Integer.parseInt(rs.getString("id"));

        String getMinionId = "SELECT id \n" +
                "FROM minions\n" +
                "WHERE name = \"" + minion_name + "\";";
        getIdStatement = connection.prepareStatement(getMinionId);
        rs = getIdStatement.executeQuery();

        if(!rs.next()) {

            String addMinion = "INSERT INTO minions(name, age, town_id)\n" +
                    "VALUES(\"" + minion_name +"\", " + Integer.parseInt(minion_age) + ", " + town_id + ");";
            PreparedStatement addStatement = connection.prepareStatement(addMinion);
            addStatement.executeUpdate();
            rs = getIdStatement.executeQuery();
            rs.next();
            addStatement.closeOnCompletion();
        }

        int minion_id = Integer.parseInt(rs.getString("id"));

        String addRelationship = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
                "VALUES(" + minion_id + ", " + villain_id + ");";
        PreparedStatement addStatement = connection.prepareStatement(addRelationship);

        int relationship = addStatement.executeUpdate();

        if (relationship == 1) {
            System.out.printf("Successfully added %s to be minion of %s.", minion_name, villain_name);
        }

        addStatement.closeOnCompletion();
        getIdStatement.closeOnCompletion();
        connection.close();
        rs.close();

    }

}


