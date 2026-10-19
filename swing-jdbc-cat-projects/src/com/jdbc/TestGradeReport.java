package com.jdbc;

import java.util.ArrayList;
import java.util.List;

public class TestGradeReport {

    public static void main(String[] args) {
        List<StudentSummary> list = new ArrayList<>();
        list.add(new StudentSummary("1", "Ann", 90));
        list.add(new StudentSummary("2", "Ben", 85));      // boundary: A
        list.add(new StudentSummary("3", "Cat", 84.99));   // B
        list.add(new StudentSummary("4", "Dan", 75));      // boundary: B
        list.add(new StudentSummary("5", "Eve", 74.99));   // C
        list.add(new StudentSummary("6", "Fay", 60));      // boundary: C
        list.add(new StudentSummary("7", "Gus", 59.99));   // D
        list.add(new StudentSummary("8", "Hal", 50));      // boundary: D
        list.add(new StudentSummary("9", "Ian", 49.99));   // F

        for (String g : new String[] {"A", "B", "C", "D", "F"}) {
            StringBuilder sb = new StringBuilder(g + ":");
            for (StudentSummary s : GradeReport.withGrade(list, g)) {
                sb.append(" ").append(s.getFullName());
            }
            System.out.println(sb);
        }
    }
}