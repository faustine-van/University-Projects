package com.transcriptsystem;

import java.util.Locale;

public class TestTranscript {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);   // always print 71.67, never 71,67

        Transcript t = new Transcript("00000/2024", "Test Student", "Test Dept", "Test Faculty");
        t.addLine(new TranscriptLine("T1", "Course A", 4, 90, "Semester 1", "2025-2026")); // A
        t.addLine(new TranscriptLine("T2", "Course B", 3, 70, "Semester 1", "2025-2026")); // C
        t.addLine(new TranscriptLine("T3", "Course C", 3, 55, "Semester 1", "2025-2026")); // D

        System.out.println("total credits = " + t.getTotalCredits());
        System.out.printf("average = %.2f%n", t.getAverage());
        System.out.println("overall grade = " + t.getOverallGrade());
        System.out.printf("gpa = %.2f%n", t.getGpa());

        Transcript empty = new Transcript("00001/2024", "No Results", "Dept", "Faculty");
        System.out.printf("empty: average = %.2f, grade = %s, gpa = %.2f%n",
                empty.getAverage(), empty.getOverallGrade(), empty.getGpa())
        ;
        String[] tests = {"26101/2024", "2691/2024", "26101-2024", "26101/24", "abcde/2024", ""};
        for (String s : tests) {
            System.out.println("\"" + s + "\" -> " + TranscriptService.isValidRegNo(s));
        }
    }
}