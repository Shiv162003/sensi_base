/*
Name: Shreyas Rai
PRN: 21070126088
Batch: AIML B1
OS: MacOS 12 Monterey version 12.6.2
java Version: 19.0.1 2022-10-18
Java(TM) SE Runtime Environment (build 19.0.1+10-21)
Java HotSpot(TM) 64-Bit Server VM (build 19.0.1+10-21, mixed mode, sharing)
*/

import java.sql.*;
public class DataBaseOperations extends MySQLConnection {
    static MySQLConnection ob =new MySQLConnection();
    public static void viewAllTables() throws SQLException 
    {
        Connection conn = MySQLConnection.getConnection();
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables("device_one", null, null, new String[] {"TABLE"});
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
        
    }   
    public static void viewTableContent(String tableName) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        try {
            ob.establishConnection("device_one");
            DataBaseOperations.viewAllTables();
            DataBaseOperations.viewTableContent("data");
        } catch (FailedToConnectException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                MySQLConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }      

}
