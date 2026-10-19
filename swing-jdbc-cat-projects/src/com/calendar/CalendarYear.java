package com.calendar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarYear {

    public static boolean isLeap(int year) {
    	return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static String monthText(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        int days = ym.lengthOfMonth();
        int blanks = ym.atDay(1).getDayOfWeek().getValue() % 7; // Sunday = 0

        StringBuilder sb = new StringBuilder();
        String name = ym.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        sb.append(name).append(" ").append(year).append("\n");
        sb.append("Su Mo Tu We Th Fr Sa\n");

        for (int i = 0; i < blanks; i++) {
            sb.append("   ");
        }
        for (int d = 1; d <= days; d++) {
            sb.append(String.format("%2d ", d));
            if ((blanks + d) % 7 == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    // position of the day's number inside the month text, for highlighting
    public static int dayOffset(int year, int month, int day) {
        YearMonth ym = YearMonth.of(year, month);
        int blanks = ym.atDay(1).getDayOfWeek().getValue() % 7;
        String name = ym.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int titleLen = (name + " " + year + "\n").length();
        int headerLen = "Su Mo Tu We Th Fr Sa\n".length();   // 21
        int cell = blanks + day - 1;
        int row = cell / 7;
        int col = cell % 7;
        return titleLen + headerLen + row * 22 + col * 3;    // each row = 21 chars + newline
    }

    // for example: Tuesday, 13 October 2026
    public static String dayName(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return weekday + ", " + day + " " + monthName + " " + year;
    }
    
}