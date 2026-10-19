package com.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GradeReportFrame extends JFrame {

    private static final String[] FILTERS = {
        "All students", "Grade A (85 and above)", "Grade B (75 to 84)",
        "Grade C (60 to 74)", "Grade D (50 to 59)", "Grade F (below 50)"
    };
    private static final String[] GRADES = {null, "A", "B", "C", "D", "F"};   // same order as FILTERS

    private final StudentSummaryDao dao = new StudentSummaryDao();
    private JComboBox<String> filterBox;
    private JLabel lblTitle;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GradeReportFrame().setVisible(true));
    }

    public GradeReportFrame() {
        setTitle("Student Grade Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(root);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Show:"));
        filterBox = new JComboBox<>(FILTERS);
        JButton btnJ = new JButton("Names starting with J");
        JButton btnK = new JButton("Names starting with K");
        top.add(filterBox);
        top.add(btnJ);
        top.add(btnK);
        root.add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[] {"Reg No", "Name", "Average", "Grade"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;                                   // read-only table
            }
        };
        root.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);

        lblTitle = new JLabel(" ");
        root.add(lblTitle, BorderLayout.SOUTH);

        filterBox.addActionListener(e -> showFilter());         // choosing a grade updates the table
        btnJ.addActionListener(e -> showInitial('J'));
        btnK.addActionListener(e -> showInitial('K'));

        showFilter();                                           // all students when the window opens
    }

    private void showFilter() {
        int i = filterBox.getSelectedIndex();
        try {
            List<StudentSummary> all = dao.findAll();
            List<StudentSummary> rows = (GRADES[i] == null) ? all : GradeReport.withGrade(all, GRADES[i]);
            fill(FILTERS[i], rows);
        } catch (SQLException e) {
            dbError(e);
        }
    }

    private void showInitial(char letter) {
        try {
            fill("Names starting with " + letter, dao.findByInitial(letter));
        } catch (SQLException e) {
            dbError(e);
        }
    }

    private void fill(String title, List<StudentSummary> rows) {
        model.setRowCount(0);
        for (StudentSummary s : rows) {
            model.addRow(new Object[] {s.getRegNo(), s.getFullName(),
                    String.format(Locale.US, "%.2f", s.getAverage()), s.getGrade()});
        }
        lblTitle.setText(title + ": " + rows.size() + (rows.size() == 1 ? " student" : " students"));
    }

    private void dbError(SQLException e) {
        model.setRowCount(0);
        lblTitle.setText(" ");
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}