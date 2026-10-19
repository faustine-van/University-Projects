package com.transcriptsystem;

import com.jdbc.GradeCalculator;

public class TranscriptLine {

    private final String courseCode;
    private final String courseName;
    private final int creditHours;
    private final double marks;
    private final String semester;
    private final String academicYear;

    public TranscriptLine(String courseCode, String courseName, int creditHours,
                          double marks, String semester, String academicYear) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.marks = marks;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public String getCourseCode()  { return courseCode; }
    public String getCourseName()  { return courseName; }
    public int getCreditHours()    { return creditHours; }
    public double getMarks()       { return marks; }
    public String getSemester()    { return semester; }
    public String getAcademicYear() { return academicYear; }

    // the grade is worked out from the marks, never stored twice
    public String getGrade() {
        return GradeCalculator.grade(marks);
    }
}