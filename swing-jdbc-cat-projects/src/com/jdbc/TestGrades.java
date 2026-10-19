package com.jdbc;

public class TestGrades {
    public static void main(String[] args) {
        double[] marks = {100, 85, 84.99, 75, 74.5, 60, 59.99, 50, 49.99, 0};
        for (double m : marks) {
            System.out.println(m + " -> " + GradeCalculator.grade(m));
        }
        System.out.println("average = " + GradeCalculator.average(new double[] {90, 88, 92, 86, 91}));
        System.out.println("gpa = " + GradeCalculator.gpa(new double[] {90, 70, 55}, new int[] {4, 3, 3}));
        try {
            GradeCalculator.grade(101);
        } catch (IllegalArgumentException e) {
            System.out.println("101 -> " + e.getMessage());
        }
    }
}