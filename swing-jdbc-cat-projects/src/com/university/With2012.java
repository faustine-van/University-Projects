package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class With2012 {

	 public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3307/university";
	        String user = "root";
	        String password = "your_password";


	        try {
	            
	            Connection conn = DriverManager.getConnection(
	                url, user, password
	            );
	            // Create statement and execute query
	            Statement stmt = conn.createStatement();
	            // Display all information of 2012 Id
	            ResultSet res = stmt.executeQuery(
	                "SELECT * from students WHERE id = '2012'"
	            );
	            while(res.next()){
	                String id = res.getString("Id");
	                String lname = res.getString("Lname");
	                String course = res.getString("Course");
	                String country = res.getString("Country");
	                System.out.println(id + " | " +  lname + " | " + course + " | " + country );
	            }
	            // Close resources
	            res.close(); stmt.close(); conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

