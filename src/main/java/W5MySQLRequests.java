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
    private static  ArrayList<String> placeList;
    private static  ArrayList<String> dateList;
    private static  ArrayList<String> fightNumList;
    private static  ArrayList<String> weightList;
    private static ArrayList<String> fightersList;
    private static ArrayList<String> countryList;
    //private static ArrayList<String> countryBlueList;
    private static  ArrayList<String> judgeList;
    private static  ArrayList<String> refereeList;


    public static ArrayList<String> getTournamentsList() throws SQLException{
        tournamentList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Tournaments");
        while(rs.next()) {
            tournamentList.add(rs.getString("name"));
        }
        return tournamentList;
    }

    public static ArrayList<String> getPlaceList() throws SQLException{
        placeList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Tournaments");
        while(rs.next()) {
            placeList.add(rs.getString("place"));
        }
        return placeList;
    }

    public static ArrayList<String> getDateList() throws SQLException{
        dateList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Tournaments");
        while(rs.next()) {
            dateList.add(rs.getString("date"));
        }
        return dateList;
    }

    public static ArrayList<String> getFightNumList() throws SQLException{
        fightNumList = new ArrayList<String>();
        /*
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fights");

        while(rs.next()) {
            fightNumList.add(rs.getString("id"));
        }
        */
        for (int i = 1; i < 100; i++) {
            fightNumList.add(Integer.toString(i));
        }

        return fightNumList;
    }

    public static ArrayList<String> getWeightList() throws SQLException{
        weightList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fighters");
        while(rs.next()) {
            weightList.add(rs.getString("weight"));
        }
        return weightList;
    }

    public static ArrayList<String> getFightersList() throws SQLException{
        fightersList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fighters");
            while(rs.next()) {
                fightersList.add(rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        return fightersList;
    }

    public static ArrayList<String> getCountryList() throws SQLException{
        countryList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fighters");
        while(rs.next()) {
            countryList.add(rs.getString("country"));
        }
        return countryList;
    }

    public static ArrayList<String> getJudgeList() throws SQLException{
        judgeList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Judges");
        while(rs.next()) {
            judgeList.add(rs.getString("firstname")+" "+rs.getString("lastname"));
        }
        return judgeList;
    }

    public static ArrayList<String> getRefereeList() throws SQLException{
        refereeList = new ArrayList<String>();
        Connection connection = W5MySQLConnection.getConnection();
        assert connection != null;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Judges");
        while(rs.next()) {
            refereeList.add(rs.getString("firstname")+" "+rs.getString("lastname"));
        }
        return refereeList;
    }

}

class testDriveConnection {
    public static void main(String[] args) throws SQLException {
        W5MySQLRequests request = new W5MySQLRequests();
            System.out.println(request.getFightersList().get(0));

    }
}
