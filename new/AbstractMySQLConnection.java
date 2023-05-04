import java.sql.*;

abstract class AbstractMySQLConnection {
     static Connection conn = null;

    public abstract void establishConnection(String st) throws FailedToConnectException;

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
            System.out.println("Database connection closed.");
        }
    }
}