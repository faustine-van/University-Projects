/**
 * File: PostgresConnect.java
 * Author: [Your Name]
 * Description: This program connects to a PostgreSQL database and 
 *              displays student records where the course is 'IT'.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresConnect {

    /**
     * Main method that connects to the database, executes query, and prints results.
     */
    public static void main(String[] args) {

        /* ================================
           Database connection credentials
        ================================== */
        String url = "jdbc:postgresql://localhost:5432/university";
        String user = "postgres";
        String password = "12345";

        try {
            /* ================================
               Step 1: Establish database connection
            ================================== */
            Connection conn = DriverManager.getConnection(url, user, password);

            /* ================================
               Step 2: Create statement object
            ================================== */
            Statement stmt = conn.createStatement();

            /* ================================
               Step 3: Execute SQL query to retrieve 
                       students in 'IT' course
            ================================== */
            ResultSet res = stmt.executeQuery(
                "SELECT * FROM students WHERE course = 'IT'"
            );

            /* ================================
               Step 4: Process and display results
            ================================== */
            while (res.next()) {
                String id = res.getString("Id");
                String lname = res.getString("Lname");
                String course = res.getString("Course");
                String country = res.getString("Country");

                System.out.println(id + " | " + lname + " | " + course + " | " + country);
            }

            /* ================================
               Step 5: Close all connections
            ================================== */
            res.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            /* ================================
               Handle any exceptions or errors
            ================================== */
            e.printStackTrace();
        }
    }
}
