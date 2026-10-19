package com.transcriptsystem;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class TranscriptService {

    private final TranscriptDao dao = new TranscriptDao();

    // 5 digits, a slash, 4 digits: 26101/2024
    public static boolean isValidRegNo(String regNo) {
        return regNo != null && regNo.matches("\\d{5}/\\d{4}");
    }

    public Transcript getTranscript(String regNo) throws SQLException {
        String clean = (regNo == null) ? "" : regNo.trim();

        if (!isValidRegNo(clean)) {
            throw new IllegalArgumentException(
                "Invalid registration number. Use 5 digits, a slash and the year, for example 26101/2024");
        }
        Transcript t = dao.findByRegNo(clean);
        if (t == null) {
            throw new NoSuchElementException("No student found with registration number " + clean);
        }
        return t;
    }
}