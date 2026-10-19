package com.transcriptsystem;

import java.sql.SQLException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TranscriptConsole {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(System.in);
        TranscriptService service = new TranscriptService();

        while (true) {
            System.out.print("Registration number (q to quit): ");
            String regNo = in.nextLine().trim();
            if (regNo.equalsIgnoreCase("q")) {
                break;
            }
            try {
                print(service.getTranscript(regNo));
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void print(Transcript t) {
        System.out.println();
        System.out.println("STUDENT TRANSCRIPT");
        System.out.println("Reg No     : " + t.getRegNo());
        System.out.println("Name       : " + t.getFullName());
        System.out.println("Department : " + t.getDepartment());
        System.out.println("Faculty    : " + t.getFaculty());
        System.out.println();

        if (t.getLines().isEmpty()) {
            System.out.println("No results recorded for this student.");
            return;
        }
        System.out.printf("%-8s %-22s %-8s %-6s %-6s %-11s %s%n",
                "Code", "Course", "Credits", "Marks", "Grade", "Semester", "Year");
        for (TranscriptLine l : t.getLines()) {
            System.out.printf("%-8s %-22s %-8d %-6.1f %-6s %-11s %s%n",
                    l.getCourseCode(), l.getCourseName(), l.getCreditHours(),
                    l.getMarks(), l.getGrade(), l.getSemester(), l.getAcademicYear());
        }
        System.out.println();
        System.out.printf("Total credits : %d%n", t.getTotalCredits());
        System.out.printf("Average       : %.2f (%s)%n", t.getAverage(), t.getOverallGrade());
        System.out.printf("GPA           : %.2f%n", t.getGpa());
        System.out.println();
    }
}