
package botask;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    private Connection con;
    private Statement sta;
    private ResultSet res;

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + "BotaskDB", "root", "");
            sta = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String> look4Continent() {

        ArrayList<String> templist = new ArrayList();
        String query = "SELECT Name FROM ContinentesDB "
                + "ORDER BY `ContinentesDB`.`NumberFounds` DESC";

        String resultQuery = "";
        try {
            res = sta.executeQuery(query);
            while(res.next()){
                templist.add(res.getString(1));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return templist;

    }


    public boolean found(String response, String continente) {
        boolean flag = false;
        if (response.equals("y") || response.equals("Y")) {
            String query = "UPDATE ContinentesDB "
                    + "SET NumberFounds = NumberFounds + 1 "
                    + "WHERE Name=" + "'" + continente + "'";
            try {
                sta.executeUpdate(query);
                flag = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return flag;
    }

}