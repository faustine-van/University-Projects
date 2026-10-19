package com.jdbc;

public class StudentSummary {

    private final String regNo;
    private final String fullName;
    private final double average;

    public StudentSummary(String regNo, String fullName, double average) {
        this.regNo = regNo;
        this.fullName = fullName;
        this.average = average;
    }

    public String getRegNo()    { return regNo; }
    public String getFullName() { return fullName; }
    public double getAverage()  { return average; }

    // same rule as Q5, one source of truth
    public String getGrade() {
        return GradeCalculator.grade(average);
    }
}