import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by albert on 27.08.16.
 */
public class W5MySQLRequests {

    private static ArrayList<String> tournamentList;
    private  ArrayList<String> placeList;
    private  ArrayList<String> dateList;
    private  ArrayList<String> fightNumList;
    private  ArrayList<String> weightList;
    private  ArrayList<String> fightersList;
    //private static ArrayList<String> countryRedList;
    //private static ArrayList<String> countryBlueList;
    private  ArrayList<String> judge1List;
    private  ArrayList<String> refereeList;

    public static ArrayList<String> getFightersList() throws SQLException{
        tournamentList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fighters");
            while(rs.next()) {
                tournamentList.add(rs.getString("firstname") + " " + rs.getString("lastname"));
            }

        System.out.println(tournamentList.size());
        return tournamentList;
    }


}

class testDriveConnection {
    public static void main(String[] args) throws SQLException {
        W5MySQLRequests request = new W5MySQLRequests();
            System.out.println(request.getFightersList().get(0));

    }
}
