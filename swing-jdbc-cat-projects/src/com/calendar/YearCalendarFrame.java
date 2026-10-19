package com.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class YearCalendarFrame extends JFrame {

	private JPanel contentPane;
    private JTextField textYear;
    private JTextField textDay;
    private JComboBox<String> monthBox;
    private JLabel lblLeap;
    private JLabel lblSelected;
    private JTextArea[] months = new JTextArea[12];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YearCalendarFrame frame = new YearCalendarFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
    public YearCalendarFrame() {
        setTitle("Year Calendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 650);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(5, 5));
        setContentPane(contentPane);

        LocalDate today = LocalDate.now();

        // top bar
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 8));
        top.add(new JLabel("Year:"));
        textYear = new JTextField(String.valueOf(today.getYear()), 5);
        top.add(textYear);

        top.add(new JLabel("Month:"));
        String[] names = new String[12];
        for (int i = 0; i < 12; i++) {
            names[i] = Month.of(i + 1).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }
        monthBox = new JComboBox<>(names);
        monthBox.setSelectedIndex(today.getMonthValue() - 1);
        top.add(monthBox);

        top.add(new JLabel("Day:"));
        textDay = new JTextField(String.valueOf(today.getDayOfMonth()), 3);
        top.add(textDay);

        JButton btnShow = new JButton("Show");
        JButton btnToday = new JButton("Today");
        top.add(btnShow);
        top.add(btnToday);
        lblLeap = new JLabel(" ");
        top.add(lblLeap);
        contentPane.add(top, BorderLayout.NORTH);

        // center: 12 months
        JPanel grid = new JPanel(new GridLayout(3, 4, 5, 5));
        for (int i = 0; i < 12; i++) {
            months[i] = new JTextArea();
            months[i].setEditable(false);
            months[i].setFont(new Font("Monospaced", Font.PLAIN, 13));
            // grid.add(months[i]);
            
            JPanel cell = new JPanel(new GridBagLayout());   // a GridBagLayout centers its only child
            cell.setBackground(Color.WHITE);
            cell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            cell.add(months[i]);
            grid.add(cell);
            
        }
        contentPane.add(grid, BorderLayout.CENTER);

        // bottom: selected date
        lblSelected = new JLabel(" ", SwingConstants.CENTER);
        lblSelected.setFont(new Font("SansSerif", Font.BOLD, 14));
        contentPane.add(lblSelected, BorderLayout.SOUTH);

        btnShow.addActionListener(e -> showYear());
        btnToday.addActionListener(e -> resetToToday());

        showYear();   // show the current year as soon as the window opens
    }
	
    private void showYear() {
        try {
            int year = Integer.parseInt(textYear.getText().trim());
            int month = monthBox.getSelectedIndex() + 1;
            int day = Integer.parseInt(textDay.getText().trim());

            if (year < 1 || year > 9999) {
                JOptionPane.showMessageDialog(this, "Enter a year between 1 and 9999");
                return;
            }
            if (!YearMonth.of(year, month).isValidDay(day)) {
                JOptionPane.showMessageDialog(this, "That month does not have day " + day);
                return;
            }

            for (int m = 1; m <= 12; m++) {
                months[m - 1].setText(CalendarYear.monthText(year, m));
                months[m - 1].getHighlighter().removeAllHighlights();
            }

            // highlight the chosen day
            int start = CalendarYear.dayOffset(year, month, day);
            Highlighter h = months[month - 1].getHighlighter();
            try {
                h.addHighlight(start, start + 2,
                        new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE));
            } catch (BadLocationException ex) {
                // position is always valid, nothing to do
            }

            lblLeap.setText(CalendarYear.isLeap(year) ? "Leap year" : "Not a leap year");
            lblSelected.setText(CalendarYear.dayName(year, month, day));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid year and day");
        }
    }

    private void resetToToday() {
        LocalDate today = LocalDate.now();
        textYear.setText(String.valueOf(today.getYear()));
        monthBox.setSelectedIndex(today.getMonthValue() - 1);
        textDay.setText(String.valueOf(today.getDayOfMonth()));
        showYear();
    }


}
