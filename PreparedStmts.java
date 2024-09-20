mport java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStmts {
    public static void main(String[] args) {

        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String USER = "system"; 
        String PASSWORD = "BCA5C";

        // Step 1: Register the driver class and connect to the database
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            if (con != null) {
                System.out.println("Connection successful to Oracle DB");
            } else {
                System.out.println("Connection Failed");
                return; // Exit the program if connection fails
            }

            // Step 2: Create the Department table
            String sqlDepartment = "CREATE TABLE Department(did int PRIMARY KEY, dname varchar(20))";
            try (PreparedStatement pstmtDept = con.prepareStatement(sqlDepartment)) {
                pstmtDept.executeUpdate();
                System.out.println("Department table created.");
            } catch (SQLException e) {
                System.out.println("Department table creation skipped: Table already exists.");
            }

            // Step 3: Create the Employee table with foreign key to Department
            String sqlEmployee = "CREATE TABLE Employee(eid int PRIMARY KEY, ename varchar(20), address varchar(20), did int, "
                    + "FOREIGN KEY (did) REFERENCES Department(did))";
            try (PreparedStatement pstmtEmp = con.prepareStatement(sqlEmployee)) {
                pstmtEmp.executeUpdate();
                System.out.println("Employee table created.");
            } catch (SQLException e) {
                System.out.println("Employee table creation skipped: Table already exists.");
            }

            // Step 4: Insert rows into Department table using PreparedStatement
            String insertDepartment = "INSERT INTO Department (did, dname) VALUES (?, ?)";
            try (PreparedStatement pstmtInsertDept = con.prepareStatement(insertDepartment)) {
                pstmtInsertDept.setInt(1, 2001);
                pstmtInsertDept.setString(2, "HR");
                pstmtInsertDept.executeUpdate();

                pstmtInsertDept.setInt(1, 2002);
                pstmtInsertDept.setString(2, "Finance");
                pstmtInsertDept.executeUpdate();

                pstmtInsertDept.setInt(1, 2003);
                pstmtInsertDept.setString(2, "IT");
                pstmtInsertDept.executeUpdate();

                pstmtInsertDept.setInt(1, 2004);
                pstmtInsertDept.setString(2, "Marketing");
                pstmtInsertDept.executeUpdate();

                pstmtInsertDept.setInt(1, 2005);
                pstmtInsertDept.setString(2, "Operations");
                pstmtInsertDept.executeUpdate();

                System.out.println("Inserted rows into Department table.");
            }

            // Step 5: Insert rows into Employee table using PreparedStatement
            String insertEmployee = "INSERT INTO Employee (eid, ename, address, did) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtInsertEmp = con.prepareStatement(insertEmployee)) {
                pstmtInsertEmp.setInt(1, 123);
                pstmtInsertEmp.setString(2, "Halzz");
                pstmtInsertEmp.setString(3, "Gadag");
                pstmtInsertEmp.setInt(4, 2001);
                pstmtInsertEmp.executeUpdate();

                pstmtInsertEmp.setInt(1, 124);
                pstmtInsertEmp.setString(2, "John");
                pstmtInsertEmp.setString(3, "Bangalore");
                pstmtInsertEmp.setInt(4, 2002);
                pstmtInsertEmp.executeUpdate();

                pstmtInsertEmp.setInt(1, 125);
                pstmtInsertEmp.setString(2, "Mary");
                pstmtInsertEmp.setString(3, "Delhi");
                pstmtInsertEmp.setInt(4, 2003);
                pstmtInsertEmp.executeUpdate();

                pstmtInsertEmp.setInt(1, 126);
                pstmtInsertEmp.setString(2, "Moris");
                pstmtInsertEmp.setString(3, "Unkal");
                pstmtInsertEmp.setInt(4, 2004);
                pstmtInsertEmp.executeUpdate();

                pstmtInsertEmp.setInt(1, 127);
                pstmtInsertEmp.setString(2, "Manu");
                pstmtInsertEmp.setString(3, "Hubli");
                pstmtInsertEmp.setInt(4, 2005);
                pstmtInsertEmp.executeUpdate();

                System.out.println("Inserted rows into Employee table.");
            }

            // Step 6: Close the connection
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
}
}
}
