package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM students")) {
            rs.next();
            System.out.println("Connected to " + con.getCatalog() + ", students: " + rs.getInt(1));
        } catch (SQLException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}