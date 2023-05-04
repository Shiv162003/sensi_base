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
