import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDuplicates{
    public static void x() {
    // Database connection parameters
    String url = "jdbc:mysql://localhost/device_one";
    String user = "root";
    String password = "shiv162003";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         Statement stmt = conn.createStatement();
         Statement deleteStmt = conn.createStatement()) {
        // Select duplicate rows
        String sql = "SELECT timestamp, device, co, humidity, light, lpg, motion, smoke, temp, COUNT(*) AS count " +
                     "FROM data " +
                     "GROUP BY timestamp, device, co, humidity, light, lpg, motion, smoke, temp " +
                     "HAVING COUNT(*) > 1";
        ResultSet rs = stmt.executeQuery(sql);
        int rowsDeleted = 0;
        // Iterate over duplicate rows and delete all but the first occurrence
        while (rs.next()) {
            long timestamp = rs.getLong("timestamp");
            String device = rs.getString("device");
            float co = rs.getFloat("co");
            float humidity = rs.getFloat("humidity");
            boolean light = rs.getBoolean("light");
            float lpg = rs.getFloat("lpg");
            boolean motion = rs.getBoolean("motion");
            float smoke = rs.getFloat("smoke");
            float temp = rs.getFloat("temp");

            // Delete duplicate rows
            sql = "DELETE FROM data WHERE timestamp = " + timestamp + " AND " +
                                            "device = '" + device + "' AND " +
                                            "co = " + co + " AND " +
                                            "humidity = " + humidity + " AND " +
                                            "light = " + light + " AND " +
                                            "lpg = " + lpg + " AND " +
                                            "motion = " + motion + " AND " +
                                            "smoke = " + smoke + " AND " +
                                            "temp = " + temp;
            rowsDeleted += deleteStmt.executeUpdate(sql);
        }
        System.out.println("Deleted " + rowsDeleted + " rows with duplicate values.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
