package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StartWithR {
	  public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3307/university";
	        String user = "root";
	        String password = "";
	        try {
	           
	            Connection conn = DriverManager.getConnection(
	                url, user, password
	            );
	            // Create statement and execute query
	            Statement stmt = conn.createStatement();
	            // Display all countries started by “R”
	            ResultSet res = stmt.executeQuery(
	               "SELECT Id, LName, Course, Country FROM students WHERE Country LIKE 'R%'"
	            );
	            while (res.next()) {
	                String id = res.getString("Id");
	                String lname = res.getString("LName");
	                String course = res.getString("Course");
	                String country = res.getString("Country");

	                System.out.println(id + " | " + lname + " | " + course + " | " + country);
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
