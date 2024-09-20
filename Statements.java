import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Statements {
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

            // Step 2: Create a statement
            Statement stmt = con.createStatement();

            // Step 3: Create Department table
            String sqlDepartment = "Create table Department(did int primary key, dname varchar(20))";
            try {
                stmt.executeUpdate(sqlDepartment);
                System.out.println("Department table created.");
            } catch (SQLException e) {
                System.out.println("Department table creation skipped: Table already exists.");
            }

            // Step 4: Create Employee table with foreign key
            String sqlEmployee = "Create table Employee(eid int primary key, ename varchar(20), address varchar(20), did int, "
                    + "FOREIGN KEY (did) REFERENCES Department(did))";
            try {
                stmt.executeUpdate(sqlEmployee);
                System.out.println("Employee table created.");
            } catch (SQLException e) {
                System.out.println("Employee table creation skipped: Table already exists.");
            }

            // Step 5: Insert rows into Department table
            String insertDept1 = "INSERT INTO Department VALUES (2006, 'HR')";
            String insertDept2 = "INSERT INTO Department VALUES (2007, 'Finance')";
            String insertDept3 = "INSERT INTO Department VALUES (2008, 'IT')";
            String insertDept4 = "INSERT INTO Department VALUES (2009, 'Marketing')";
            String insertDept5 = "INSERT INTO Department VALUES (2010, 'Operations')";

            stmt.executeUpdate(insertDept1);
            stmt.executeUpdate(insertDept2);
            stmt.executeUpdate(insertDept3);
            stmt.executeUpdate(insertDept4);
            stmt.executeUpdate(insertDept5);
            System.out.println("Inserted rows into Department table.");

            // Step 6: Insert rows into Employee table
            String insertEmp1 = "INSERT INTO Employee VALUES (123, 'Halzz', 'Gadag', 2006)";
            String insertEmp2 = "INSERT INTO Employee VALUES (124, 'John', 'Bangalore', 2007)";
            String insertEmp3 = "INSERT INTO Employee VALUES (125, 'Mary', 'Delhi', 2008)";
            String insertEmp4 = "INSERT INTO Employee VALUES (126, 'Moris', 'Unkal', 2009)";
            String insertEmp5 = "INSERT INTO Employee VALUES (127, 'Manu', 'Hubli', 2010)";

            stmt.executeUpdate(insertEmp1);
            stmt.executeUpdate(insertEmp2);
            stmt.executeUpdate(insertEmp3);
            stmt.executeUpdate(insertEmp4);
            stmt.executeUpdate(insertEmp5);
            System.out.println("Inserted rows into Employee table.");

            // Step 7: Close the statement and connection
            stmt.close();
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
