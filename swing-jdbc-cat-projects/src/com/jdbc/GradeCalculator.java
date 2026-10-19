package com.jdbc;

public class GradeCalculator {

    // A: 85 and above, B: 75-84, C: 60-74, D: 50-59, F: below 50
    public static String grade(double marks) {
        if (!(marks >= 0 && marks <= 100)) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        if (marks >= 85) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    public static double gradePoints(String grade) {
        switch (grade) {
            case "A": return 4;
            case "B": return 3;
            case "C": return 2;
            case "D": return 1;
            default:  return 0;
        }
    }

    public static double average(double[] marks) {
        if (marks.length == 0) return 0;
        double sum = 0;
        for (double m : marks) {
            sum += m;
        }
        return sum / marks.length;
    }

    // GPA weighted by credit hours: sum(points x credits) / sum(credits)
    public static double gpa(double[] marks, int[] credits) {
        double points = 0;
        int totalCredits = 0;
        for (int i = 0; i < marks.length; i++) {
            points += gradePoints(grade(marks[i])) * credits[i];
            totalCredits += credits[i];
        }
        return totalCredits == 0 ? 0 : points / totalCredits;
    }
}