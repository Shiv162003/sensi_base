/*
Name: Shivangi Singh
prn: 21070126085*/

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;

public class MySQLtoCSV implements DataExporter {
    @Override
    public void exportToCSV() {
        String url = "jdbc:mysql://localhost:3306/device_one";
        String user = "root";
        String password = "shiv162003";

        String csvFilePath = "C:/Users/ASUS/Desktop/csv2.csv";

        String sql = "SELECT * FROM data";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();
             FileWriter writer = new FileWriter(csvFilePath)) {

            // Write headers to CSV file
            writer.append("timestamp,device,co,humidity,light,lpg,motion,smoke,temp\n");

            // Write rows to CSV file
            while (rs.next()) {
                writer.append(rs.getString("timestamp"))
                      .append(",")
                      .append(rs.getString("device"))
                      .append(",")
                      .append(String.valueOf(rs.getFloat("co")))
                      .append(",")
                      .append(String.valueOf(rs.getFloat("humidity")))
                      .append(",")
                      .append(String.valueOf(rs.getBoolean("light")))
                      .append(",")
                      .append(String.valueOf(rs.getFloat("lpg")))
                      .append(",")
                      .append(String.valueOf(rs.getBoolean("motion")))
                      .append(",")
                      .append(String.valueOf(rs.getFloat("smoke")))
                      .append(",")
                      .append(String.valueOf(rs.getFloat("temp")))
                      .append("\n");
            }

            System.out.println("Data exported to CSV file successfully!");

        } catch (SQLException e) {
            System.err.format("SQL Exception: %s\n", e.getMessage());
        } catch (IOException e) {
            System.err.format("IO Exception: %s\n", e.getMessage());
        }
    }
}
