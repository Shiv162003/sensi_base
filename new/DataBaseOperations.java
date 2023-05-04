import java.sql.*;
public class DataBaseOperations extends DataBaseOperationss {
    static MySQLConnection ob =new MySQLConnection();
    @Override 
    public  void viewAllTables() throws SQLException 
    {
        Connection conn = MySQLConnection.getConnection();
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables("device_one", null, null, new String[] {"TABLE"});
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
        
    }   
    public  void viewTableContent(String tableName) throws SQLException {
        Statement stmt =ob.getConnection().createStatement();
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

}
