package com.transcriptsystem;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

public class PdfExporter {

    private static final Font TITLE = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
    private static final Font BOLD = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
    private static final Font NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 10);

    public static void export(Transcript t, File file) throws DocumentException, IOException {
        if (t.getLines().isEmpty()) {
            throw new IllegalArgumentException("No results to export");
        }
        Document doc = new Document(PageSize.A4, 40, 40, 40, 40);
        try (FileOutputStream out = new FileOutputStream(file)) {
            PdfWriter.getInstance(doc, out);
            doc.open();
            addHeader(doc, t);
            doc.add(courseTable(t));
            addSummary(doc, t);
            doc.close();
        }
    }

    private static void addHeader(Document doc, Transcript t) throws DocumentException {
        Paragraph title = new Paragraph("STUDENT TRANSCRIPT", TITLE);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(15);
        doc.add(title);

        doc.add(infoLine("Registration number", t.getRegNo()));
        doc.add(infoLine("Name", t.getFullName()));
        doc.add(infoLine("Department", t.getDepartment()));
        Paragraph faculty = infoLine("Faculty", t.getFaculty());
        faculty.setSpacingAfter(15);
        doc.add(faculty);
    }

    private static Paragraph infoLine(String label, String value) {
        Paragraph p = new Paragraph();
        p.add(new Phrase(label + ": ", BOLD));
        p.add(new Phrase(value, NORMAL));
        return p;
    }

    private static PdfPTable courseTable(Transcript t) throws DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setWidths(new float[] {1.2f, 3.2f, 1.1f, 1.1f, 1.1f, 1.8f, 1.8f});
        table.setHeaderRows(1);                       // the header repeats on every page

        String[] heads = {"Code", "Course", "Credits", "Marks", "Grade", "Semester", "Year"};
        for (String h : heads) {
            PdfPCell cell = new PdfPCell(new Phrase(h, BOLD));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPadding(5);
            table.addCell(cell);
        }
        for (TranscriptLine l : t.getLines()) {
            table.addCell(cell(l.getCourseCode(), Element.ALIGN_LEFT));
            table.addCell(cell(l.getCourseName(), Element.ALIGN_LEFT));
            table.addCell(cell(String.valueOf(l.getCreditHours()), Element.ALIGN_CENTER));
            table.addCell(cell(String.format(Locale.US, "%.1f", l.getMarks()), Element.ALIGN_CENTER));
            table.addCell(cell(l.getGrade(), Element.ALIGN_CENTER));
            table.addCell(cell(l.getSemester(), Element.ALIGN_LEFT));
            table.addCell(cell(l.getAcademicYear(), Element.ALIGN_LEFT));
        }
        return table;
    }

    private static PdfPCell cell(String text, int align) {
        PdfPCell c = new PdfPCell(new Phrase(text, NORMAL));
        c.setPadding(5);
        c.setHorizontalAlignment(align);
        return c;
    }

    private static void addSummary(Document doc, Transcript t) throws DocumentException {
        Paragraph credits = new Paragraph("Total credits: " + t.getTotalCredits(), BOLD);
        credits.setSpacingBefore(15);
        doc.add(credits);
        doc.add(new Paragraph(String.format(Locale.US, "Average: %.2f (%s)",
                t.getAverage(), t.getOverallGrade()), BOLD));
        doc.add(new Paragraph(String.format(Locale.US, "GPA: %.2f", t.getGpa()), BOLD));

        Paragraph date = new Paragraph("Generated on " + LocalDate.now(), NORMAL);
        date.setSpacingBefore(25);
        doc.add(date);
    }
}