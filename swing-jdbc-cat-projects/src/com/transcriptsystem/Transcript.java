package com.transcriptsystem;

import com.jdbc.GradeCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transcript {

    private final String regNo;
    private final String fullName;
    private final String department;
    private final String faculty;
    private final List<TranscriptLine> lines = new ArrayList<>();

    public Transcript(String regNo, String fullName, String department, String faculty) {
        this.regNo = regNo;
        this.fullName = fullName;
        this.department = department;
        this.faculty = faculty;
    }

    public void addLine(TranscriptLine line) {
        lines.add(line);
    }

    public String getRegNo()      { return regNo; }
    public String getFullName()   { return fullName; }
    public String getDepartment() { return department; }
    public String getFaculty()    { return faculty; }

    public List<TranscriptLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public int getTotalCredits() {
        int total = 0;
        for (TranscriptLine l : lines) {
            total += l.getCreditHours();
        }
        return total;
    }

    public double getAverage() {
        return GradeCalculator.average(marks());
    }

    public double getGpa() {
        return GradeCalculator.gpa(marks(), credits());
    }

    // overall grade comes from the average, the same rule Q7 uses
    public String getOverallGrade() {
        return lines.isEmpty() ? "-" : GradeCalculator.grade(getAverage());
    }

    private double[] marks() {
        double[] m = new double[lines.size()];
        for (int i = 0; i < m.length; i++) {
            m[i] = lines.get(i).getMarks();
        }
        return m;
    }

    private int[] credits() {
        int[] c = new int[lines.size()];
        for (int i = 0; i < c.length; i++) {
            c[i] = lines.get(i).getCreditHours();
        }
        return c;
    }
}