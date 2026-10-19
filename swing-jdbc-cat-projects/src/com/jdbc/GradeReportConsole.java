package com.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class GradeReportConsole {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        StudentSummaryDao dao = new StudentSummaryDao();
        try {
            List<StudentSummary> all = dao.findAll();
            printSection("GRADE A (85 and above)", GradeReport.withGrade(all, "A"));
            printSection("GRADE B (75 to 84)", GradeReport.withGrade(all, "B"));
            printSection("GRADE C (60 to 74)", GradeReport.withGrade(all, "C"));
            printSection("GRADE D (50 to 59)", GradeReport.withGrade(all, "D"));
            printSection("GRADE F (below 50)", GradeReport.withGrade(all, "F"));
            printSection("STUDENTS STARTING WITH J", dao.findByInitial('J'));
            printSection("STUDENTS STARTING WITH K", dao.findByInitial('K'));
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void printSection(String title, List<StudentSummary> list) {
        System.out.println(title);
        if (list.isEmpty()) {
            System.out.println("  (none)");
        }
        for (StudentSummary s : list) {
            System.out.printf("  %-12s %-24s %6.2f  %s%n",
                    s.getRegNo(), s.getFullName(), s.getAverage(), s.getGrade());
        }
        System.out.println();
    }
}