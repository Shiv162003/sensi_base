import java.util.*;
import java.io.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class InvalidInputException extends Exception  {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class ui extends MySQLConnection  
{    
    private static int validateInput(String inputStr) throws InvalidInputException 
    {
        MySQLConnection ob =new MySQLConnection();
        try {
            int input = Integer.parseInt(inputStr);
            if (input < 0 || input > 1) {
                throw new InvalidInputException("Input should be between 0 and 1.");
            }
            return input;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input should be an integer.");
        }
    }
    public static void main(String args[]) 
    {
        AdminDataBaseOperations ob2 =new AdminDataBaseOperations();
        DataBaseOperations ob=new DataBaseOperations();
        Scanner so = new Scanner(System.in);
        System.out.println("Enter the database you want to use");
        String DB_NAME = so.nextLine();
        try {
            ob.establishConnection(DB_NAME);
        } catch (FailedToConnectException ftce) {
            ftce.printStackTrace();
        }
        int x = -1;
        while (true) {
            System.out.println("Enter 1 if you want to enter as admin or 0 if you want to access as user");
            String inputStr = so.nextLine();
            try {
                x = validateInput(inputStr);
                break;
            } catch (InvalidInputException iie) {
                System.out.println(iie.getMessage());
            }
        }
        if (x==0)
        {
            while (true) {
                System.out.println("Press enter 1 to View all the tables and their content, 2 to exit as an user");
                int qwe = so.nextInt();
                if (qwe==1)
                {
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
                } else if (qwe==2) {
                    break;
                } else {
                    System.out.println("Invalid input, please try again");
                }
            }
        }
        if (x == 1) {
            int qwe = 2454;
            while (qwe != 0) {
                System.out.println("Press enter 1 to View all the tables and their content, 2 to add content from a file, 3 to delete a row, 4 to delete duplicate entries, or 5 to get the data out as CSV enter 0 to exit");
                qwe = so.nextInt();
                if (qwe==1)
                        {
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
                if (qwe == 2) {
                    String url = "jdbc:mysql://localhost:3306/device_one";
                    String user = "root";
                    String password = "shiv162003";
        
                    try (Connection conn = DriverManager.getConnection(url, user, password)) {
                        ob2.readCSVAndInsertData(conn, "deviceone.csv");
                        System.out.println("CSV file imported to database.");
                    } catch (SQLException e) {
                        System.out.println("Error executing SQL statement: " + e.getMessage());
                    }
                }
                if (qwe == 3) {
                    String filename = "data.csv";
                    String url = "jdbc:mysql://localhost:3306/device_one";
                    String username = "root";
                    String password = "shiv162003";
                    Connection conn = null;
                    try {
                        conn = DriverManager.getConnection(url, username, password);
                        Scanner input = new Scanner(System.in);
                        System.out.println("Enter the timestamp of the row to delete:");
                        int timestampToDelete = input.nextInt();
                        ob2.deleteRow(conn, timestampToDelete);
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                    } finally {
                        try {
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                            System.out.println("SQLException: " + e.getMessage());
                        }
                    }
                }
                if (qwe == 4) {
                    DeleteDuplicates ob3 = new DeleteDuplicates();
                    ob3.x();
                }
                if (qwe == 5) {
                    MySQLtoCSV obx = new MySQLtoCSV();
                    obx.x();
                }
            }
        }
    }
}