package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSummaryDao {

    private static final String SELECT =
          "SELECT s.reg_no, s.fullname, ROUND(AVG(r.marks), 2) AS avg_marks "
        + "FROM students s JOIN results r ON r.student_id = s.student_id ";
    private static final String GROUP_ORDER =
          "GROUP BY s.student_id, s.reg_no, s.fullname "
        + "ORDER BY avg_marks DESC, s.fullname";

    public List<StudentSummary> findAll() throws SQLException {
        return query(SELECT + GROUP_ORDER, null);
    }

    // names starting with one letter: LIKE 'J%'
    public List<StudentSummary> findByInitial(char letter) throws SQLException {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("Enter a letter");
        }
        return query(SELECT + "WHERE s.fullname LIKE ? " + GROUP_ORDER, letter + "%");
    }

    private List<StudentSummary> query(String sql, String param) throws SQLException {
        List<StudentSummary> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (param != null) {
                ps.setString(1, param);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new StudentSummary(rs.getString("reg_no"),
                            rs.getString("fullname"), rs.getDouble("avg_marks")));
                }
            }
        }
        return list;
    }
}