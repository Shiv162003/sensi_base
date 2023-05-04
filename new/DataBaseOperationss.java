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

public abstract class DataBaseOperationss {
    public abstract void viewAllTables() throws SQLException;
    public abstract void viewTableContent(String tableName) throws SQLException;

    public void run() {
        MySQLConnection ob = new MySQLConnection();
        try {
            ob.establishConnection("device_one");
            viewAllTables();
            viewTableContent("data");
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

interface DataBaseOperationsInterface {
    void viewAllTables() throws SQLException;
    void viewTableContent(String tableName) throws SQLException;
}
