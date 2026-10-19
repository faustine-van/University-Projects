package com.transcriptsystem;

import com.jdbc.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TranscriptDao {

    private static final String SQL =
          "SELECT s.reg_no, s.fullname, d.department_name, f.faculty_name, "
        + "c.courses_code, c.courses_name, c.credit_hours, "
        + "r.marks, r.semester, r.academic_year "
        + "FROM students s "
        + "JOIN departments d ON s.department_id = d.department_id "
        + "JOIN faculties f ON d.faculty_id = f.faculty_id "
        + "LEFT JOIN results r ON r.student_id = s.student_id "
        + "LEFT JOIN courses c ON r.course_id = c.course_id "
        + "WHERE s.reg_no = ? "
        + "ORDER BY r.academic_year, r.semester, c.courses_code";

    // returns null when no student has this registration number
    public Transcript findByRegNo(String regNo) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {

            ps.setString(1, regNo);

            try (ResultSet rs = ps.executeQuery()) {
                Transcript t = null;
                while (rs.next()) {
                    if (t == null) {    // the student's details repeat on every row, read them once
                        t = new Transcript(rs.getString("reg_no"), rs.getString("fullname"),
                                rs.getString("department_name"), rs.getString("faculty_name"));
                    }
                    String code = rs.getString("courses_code");
                    if (code != null) {  // a student with no results gives one row of NULLs
                        t.addLine(new TranscriptLine(code, rs.getString("courses_name"),
                                rs.getInt("credit_hours"), rs.getDouble("marks"),
                                rs.getString("semester"), rs.getString("academic_year")));
                    }
                }
                return t;
            }
        }
    }
}