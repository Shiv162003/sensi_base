/*
Name: Shivangi Singh
prn: 21070126085*/
import java.sql.*;
public class MySQLConnection extends AbstractMySQLConnection {
    @Override
    public void  establishConnection(String st) throws FailedToConnectException {
        String url = "jdbc:mysql://localhost:3306/" + st;
        String user = "root";
        String password = "shiv162003";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database.");
        } catch (SQLException e) {
            throw new FailedToConnectException("Failed to connect to MySQL database please write the correct name.", e);
        }
    }
}

class FailedToConnectException extends Exception {
    public FailedToConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
