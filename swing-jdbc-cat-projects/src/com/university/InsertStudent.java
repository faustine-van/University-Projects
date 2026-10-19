package com.university;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertStudent {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3307/university";
        String user = "root";
        String password = "your_password";

        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection(
                url, user, password
            );

            // Create statement
            Statement stmt = conn.createStatement();

            // Insert a new row into the students table
            String sql = "INSERT INTO students (Id, LName, Course, Country) "
                       + "VALUES (2027, 'Mutoni', 'Java', 'Rwanda')";

            int rows = stmt.executeUpdate(sql);

            // Display confirmation
            if (rows > 0) {
                System.out.println("Student inserted successfully!");
            }

            // Close resources
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

