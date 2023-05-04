// #Name - Shivangi Singh
// #PRN 21070126085

import java.sql.*;
public class MySQLConnection {
 private static Connection conn = null;

 public static void establishConnection() {
 String url = "jdbc:mysql://localhost:3306/sensi_base";
 String user = "root";
 String password = "shiv162003";
 
 try {
 conn = DriverManager.getConnection(url, user, password);
 System.out.println("Connected to MySQL database.");
 } catch (SQLException e) {
 System.out.println("Failed to connect to MySQL database.");
 e.printStackTrace();
 }
 }
 
 public static Connection getConnection()
 {
 return conn;
 }
 public static void closeConnection() {
 
 try {
 if (conn != null) {
 conn.close();
 System.out.println("Database connection closed.");
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 

}
