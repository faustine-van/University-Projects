package com.jdbc;

import java.util.ArrayList;
import java.util.List;

public class GradeReport {

    // keeps the order of the list it receives
    public static List<StudentSummary> withGrade(List<StudentSummary> all, String grade) {
        List<StudentSummary> result = new ArrayList<>();
        for (StudentSummary s : all) {
            if (s.getGrade().equals(grade)) {
                result.add(s);
            }
        }
        return result;
    }
}