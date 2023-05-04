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
