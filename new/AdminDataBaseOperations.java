/*
Name: Shreyas Rai
PRN: 21070126088
Batch: AIML B1
OS: MacOS 12 Monterey version 12.6.2
java Version: 19.0.1 2022-10-18
Java(TM) SE Runtime Environment (build 19.0.1+10-21)
Java HotSpot(TM) 64-Bit Server VM (build 19.0.1+10-21, mixed mode, sharing)
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDataBaseOperations {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/device_one";
        String user = "root";
        String password = "shiv162003";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            readCSVAndInsertData(conn, "deviceone.csv");
            System.out.println("CSV file imported to database.");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e.getMessage());
        }
    }
        public static void readCSVAndInsertData(Connection conn, String filename) throws SQLException {
        String sql = "INSERT INTO data (timestamp, device, co, humidity, light, lpg, motion, smoke, temp) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new FileReader(filename));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String line;
            // Skip the first line (header)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                double timestamp = Double.parseDouble(fields[0].trim());
                String device = fields[1].trim();
                float co = Float.parseFloat(fields[2].trim());
                float humidity = Float.parseFloat(fields[3].trim());
                boolean light = Boolean.parseBoolean(fields[4].trim());
                float lpg = Float.parseFloat(fields[5].trim());
                boolean motion = Boolean.parseBoolean(fields[6].trim());
                float smoke = Float.parseFloat(fields[7].trim());
                float temp = Float.parseFloat(fields[8].trim());
                pstmt.setLong(1, (long) timestamp);
                pstmt.setString(2, device);
                pstmt.setFloat(3, co);
                pstmt.setFloat(4, humidity);
                pstmt.setBoolean(5, light);
                pstmt.setFloat(6, lpg);
                pstmt.setBoolean(7, motion);
                pstmt.setFloat(8, smoke);
                pstmt.setFloat(9, temp);
                pstmt.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file " + filename + ": " + e.getMessage());
        }
    }
    public static void deleteRow(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM data WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public static void addRow(Connection conn, long timestamp, String device, float co, float humidity, boolean light, float lpg, boolean motion, float smoke, float temp) throws SQLException {
        String sql = "INSERT INTO data (timestamp, device, co, humidity, light, lpg, motion, smoke, temp) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, timestamp);
            pstmt.setString(2, device);
            pstmt.setFloat(3, co);
            pstmt.setFloat(4, humidity);
            pstmt.setBoolean(5, light);
            pstmt.setFloat(6, lpg);
            pstmt.setBoolean(7, motion);
            pstmt.setFloat(8, smoke);
            pstmt.setFloat(9, temp);
            pstmt.executeUpdate();
        }
    }
}
