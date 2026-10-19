package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class StartWithJ {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/university";
        String user = "root";
        String password = "your_password";
        try {
            
            Connection conn = DriverManager.getConnection(
                url, user, password
            );
            
            Statement stmt = conn.createStatement();


           
            ResultSet res = stmt.executeQuery(
            "SELECT lname from students WHERE lname LIKE 'J%'"
            );


            while(res.next()){
                String lname = res.getString("LName");


                System.out.println(lname);
            }
            // Close resources
            res.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
