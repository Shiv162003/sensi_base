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

public class DatabaseOperations
{

    // Method to create a new table
    public static void createTable(String tableName, String[] columnNames) {
        Connection conn = MySQLConnection.getConnection();
        try {
            Statement stmt = conn.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS ");
            sb.append(tableName);
            sb.append(" (");
            for (int i = 0; i < columnNames.length; i++) {
                sb.append(columnNames[i]);
                sb.append(" VARCHAR(255), ");
            }
            sb.setLength(sb.length() - 2);  // Remove trailing comma and space
            sb.append(")");
            String sql = sb.toString();
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create table.");
            e.printStackTrace();
        }
    }

    // Method to drop a table
    public static void dropTable(String tableName) {
        Connection conn = MySQLConnection.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS " + tableName;
            stmt.executeUpdate(sql);
            System.out.println("Table dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to drop table.");
            e.printStackTrace();
        }
    }

    // Method to add a new row to a table
    public static void addRow(String tableName, String[] columnNames, String[] values) {
        Connection conn = MySQLConnection.getConnection();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append(tableName);
            sb.append(" (");
            for (int i = 0; i < columnNames.length; i++) {
                sb.append(columnNames[i]);
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);  // Remove trailing comma and space
            sb.append(") VALUES (");
            for (int i = 0; i < values.length; i++) {
                sb.append("'");
                sb.append(values[i]);
                sb.append("', ");
            }
            sb.setLength(sb.length() - 2);  // Remove trailing comma and space
            sb.append(")");
            String sql = sb.toString();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Row added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add row.");
            e.printStackTrace();
        }
    }

    // Method to view all tables in the database
    public static void viewTables() {
        Connection conn = MySQLConnection.getConnection();
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
            System.out.println("Tables in the database:");
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to view tables.");
            e.printStackTrace();
        }
    }

    // Method to delete a row from a table
    public static void deleteRow(String tableName, String condition) {
        Connection conn = MySQLConnection.getConnection();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ");
            sb.append(tableName);
            if (condition != null && !condition.isEmpty()) {
                sb.append(" WHERE ");
                sb.append(condition);
            }
            String sql = sb.toString();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Row deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to view tables.");
            e.printStackTrace();
        }
    }
    public static void viewTableContent(String tableName) {
        try {
            Connection conn = MySQLConnection.getConnection();
            String query = "SELECT * FROM " + tableName;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData meta = resultSet.getMetaData();
            int columnCount = meta.getColumnCount();

            System.out.println("Table: " + tableName);
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(meta.getColumnName(i) + ": " + resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error viewing table content: " + e.getMessage());
        }
    }
}
