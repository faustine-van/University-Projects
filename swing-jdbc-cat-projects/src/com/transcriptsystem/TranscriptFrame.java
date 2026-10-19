package com.transcriptsystem;

import java.awt.*;
import java.sql.SQLException;
import java.util.Locale;
import java.util.NoSuchElementException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;

public class TranscriptFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final TranscriptService service = new TranscriptService();
    private Transcript current;          // the transcript on screen, used by the PDF button later

    private JTextField textRegNo;
    private JLabel lblReg, lblName, lblDept, lblFaculty;
    private JLabel lblCredits, lblAverage, lblGpa;
    private JButton btnPdf;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new TranscriptFrame().setVisible(true));
    }

    public TranscriptFrame() {
        setTitle("Student Transcript");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 520);

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(root);
        root.add(buildTop(), BorderLayout.NORTH);
        root.add(buildTable(), BorderLayout.CENTER);
        root.add(buildBottom(), BorderLayout.SOUTH);
        clearResult();
    }

    private JPanel buildTop() {
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
        search.add(new JLabel("Registration number:"));
        textRegNo = new JTextField(12);
        JButton btnSearch = new JButton("Search");
        JButton btnClear = new JButton("Clear");
        search.add(textRegNo);
        search.add(btnSearch);
        search.add(btnClear);
        btnSearch.addActionListener(e -> search());
        textRegNo.addActionListener(e -> search());          // the Enter key also searches
        btnClear.addActionListener(e -> clearAll());

        lblReg = new JLabel();
        lblName = new JLabel();
        lblDept = new JLabel();
        lblFaculty = new JLabel();
        JPanel info = new JPanel(new GridLayout(4, 1));
        info.add(lblReg);
        info.add(lblName);
        info.add(lblDept);
        info.add(lblFaculty);

        JPanel top = new JPanel(new BorderLayout());
        top.add(search, BorderLayout.NORTH);
        top.add(info, BorderLayout.CENTER);
        return top;
    }

    private JScrollPane buildTable() {
        String[] columns = {"Code", "Course", "Credits", "Marks", "Grade", "Semester", "Year"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;                                 // read-only table
            }
        };
        return new JScrollPane(new JTable(model));
    }

    private JPanel buildBottom() {
        lblCredits = new JLabel();
        lblAverage = new JLabel();
        lblGpa = new JLabel();
        JPanel summary = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));
        summary.add(lblCredits);
        summary.add(lblAverage);
        summary.add(lblGpa);

        btnPdf = new JButton("Generate PDF");
        btnPdf.addActionListener(e -> exportPdf());
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(summary, BorderLayout.CENTER);
        bottom.add(btnPdf, BorderLayout.EAST);
        return bottom;
    }

    private void search() {
        try {
            show(service.getTranscript(textRegNo.getText()));
        } catch (IllegalArgumentException | NoSuchElementException e) {
            clearResult();
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (SQLException e) {
            clearResult();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void show(Transcript t) {
        current = t;
        lblReg.setText("Reg No     : " + t.getRegNo());
        lblName.setText("Name       : " + t.getFullName());
        lblDept.setText("Department : " + t.getDepartment());
        lblFaculty.setText("Faculty    : " + t.getFaculty());

        model.setRowCount(0);
        for (TranscriptLine l : t.getLines()) {
            model.addRow(new Object[] {l.getCourseCode(), l.getCourseName(), l.getCreditHours(),
                    String.format(Locale.US, "%.1f", l.getMarks()), l.getGrade(),
                    l.getSemester(), l.getAcademicYear()});
        }
        lblCredits.setText("Total credits: " + t.getTotalCredits());
        lblAverage.setText(String.format(Locale.US, "Average: %.2f (%s)",
                t.getAverage(), t.getOverallGrade()));
        lblGpa.setText(String.format(Locale.US, "GPA: %.2f", t.getGpa()));

        btnPdf.setEnabled(!t.getLines().isEmpty());          // nothing to print without results
        if (t.getLines().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No results recorded for this student.");
        }
    }

    private void clearResult() {
        current = null;
        lblReg.setText("Reg No     : -");
        lblName.setText("Name       : -");
        lblDept.setText("Department : -");
        lblFaculty.setText("Faculty    : -");
        model.setRowCount(0);
        lblCredits.setText("Total credits: -");
        lblAverage.setText("Average: -");
        lblGpa.setText("GPA: -");
        btnPdf.setEnabled(false);
    }

    private void clearAll() {
        textRegNo.setText("");
        clearResult();
        textRegNo.requestFocus();
    }
    
    private void exportPdf() {
        if (current == null) return;

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("transcript_" + current.getRegNo().replace('/', '_') + ".pdf"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new File(file.getParentFile(), file.getName() + ".pdf");
        }
        if (file.exists() && JOptionPane.showConfirmDialog(this,
                file.getName() + " already exists. Replace it?", "Confirm",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            PdfExporter.export(current, file);
            JOptionPane.showMessageDialog(this, "PDF saved:\n" + file.getAbsolutePath());
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not create the PDF: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}