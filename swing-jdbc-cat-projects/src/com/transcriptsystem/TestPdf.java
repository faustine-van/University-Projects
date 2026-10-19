package com.transcriptsystem;

import java.io.File;

public class TestPdf {

    public static void main(String[] args) throws Exception {
        Transcript t = new Transcript("00000/2024", "Test Student", "Test Dept", "Test Faculty");
        t.addLine(new TranscriptLine("T1", "Course A", 4, 90, "Semester 1", "2025-2026"));
        t.addLine(new TranscriptLine("T2", "Course B", 3, 70, "Semester 1", "2025-2026"));
        t.addLine(new TranscriptLine("T3", "Course C", 3, 55, "Semester 1", "2025-2026"));

        File f = new File("transcript_test.pdf");
        PdfExporter.export(t, f);
        System.out.println("Created: " + f.getAbsolutePath() + " (" + f.length() + " bytes)");

        try {
            PdfExporter.export(new Transcript("00001/2024", "No Results", "Dept", "Faculty"),
                    new File("should_not_exist.pdf"));
        } catch (IllegalArgumentException e) {
            System.out.println("Empty transcript -> " + e.getMessage());
        }
    }
}