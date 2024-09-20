import java.sql.*;
import java.util.Scanner;

public class ScrollResultset {

    public static void main(String[] args) {

        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String USER = "system"; 
        String PASSWORD = "BCA5C";

        try {
            // Step 1: Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Establish a connection
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Connected to Oracle DB");
            }

            // Step 3: Create a scrollable ResultSet
            String query = "SELECT * FROM Employee"; // Simple query to get all employees
            PreparedStatement pstmt = con.prepareStatement(query, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Step 4: Execute query and get scrollable ResultSet
            ResultSet rs = pstmt.executeQuery();

            // Step 5: Display the result set using Scanner for user input to navigate
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Enter command: (next/prev/first/last/exit)");
                String command = scanner.nextLine();

                switch (command.toLowerCase()) {
                    case "next":
                        if (rs.next()) {
                            displayRow(rs);
                        } else {
                            System.out.println("You are at the last row.");
                        }
                        break;

                    case "prev":
                        if (rs.previous()) {
                            displayRow(rs);
                        } else {
                            System.out.println("You are at the first row.");
                        }
                        break;

                    case "first":
                        if (rs.first()) {
                            displayRow(rs);
                        } else {
                            System.out.println("No data found.");
                        }
                        break;

                    case "last":
                        if (rs.last()) {
                            displayRow(rs);
                        } else {
                            System.out.println("No data found.");
                        }
                        break;

                    case "exit":
                        exit = true;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid command. Use next/prev/first/last/exit.");
                        break;
                }
            }

            // Close resources
            rs.close();
            pstmt.close();
            con.close();
            scanner.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
        }
    }

    // Helper method to display the current row from ResultSet
    public static void displayRow(ResultSet rs) throws SQLException {
        int eid = rs.getInt("eid");
        String ename = rs.getString("ename");
        String address = rs.getString("address");
        int did = rs.getInt("did");

        System.out.println("Employee ID: " + eid);
        System.out.println("Employee Name: " + ename);
        System.out.println("Employee Address: " + address);
        System.out.println("Department ID: " + did);
        System.out.println("------------------------------");
}
}