package com.bankingsystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BankingFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textName;
    private JTextField textBalance;
    private JTextField textLoan;
    private JTextArea textResult;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BankingFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BankingFrame() {
        setTitle("Banking System - Loan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 470, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Loan Calculator");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setBounds(20, 15, 420, 25);
        contentPane.add(lblTitle);

        
        JLabel lblRules = new JLabel("<html>Minimum balance: " + LoanCalculator.money(LoanCalculator.MIN_BALANCE)
                + " | Maximum loan: " + LoanCalculator.money(LoanCalculator.MAX_LOAN)
                + " | Interest: " + Math.round(LoanCalculator.RATE * 100) + "% for "
                + LoanCalculator.MONTHS + " months</html>");
        lblRules.setBounds(20, 45, 420, 40);
        contentPane.add(lblRules);

        JLabel lblName = new JLabel("Account holder:");
        lblName.setBounds(20, 100, 120, 20);
        contentPane.add(lblName);
        textName = new JTextField();
        textName.setBounds(150, 98, 280, 24);
        contentPane.add(textName);

        JLabel lblBalance = new JLabel("Account balance:");
        lblBalance.setBounds(20, 140, 120, 20);
        contentPane.add(lblBalance);
        textBalance = new JTextField();
        textBalance.setBounds(150, 138, 280, 24);
        contentPane.add(textBalance);

        JLabel lblLoan = new JLabel("Loan amount:");
        lblLoan.setBounds(20, 180, 120, 20);
        contentPane.add(lblLoan);
        textLoan = new JTextField();
        textLoan.setBounds(150, 178, 280, 24);
        contentPane.add(textLoan);

        JButton btnCheck = new JButton("Check Loan");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkLoan();
            }
        });
        btnCheck.setBounds(20, 225, 130, 30);
        contentPane.add(btnCheck);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        btnReset.setBounds(165, 225, 130, 30);
        contentPane.add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBounds(310, 225, 130, 30);
        contentPane.add(btnExit);

        textResult = new JTextArea();
        textResult.setEditable(false);
        textResult.setFont(new Font("Monospaced", Font.PLAIN, 13));
        textResult.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textResult.setBounds(20, 275, 420, 170);
        contentPane.add(textResult);
    }

    private void checkLoan() {
        try {
            double balance = Double.parseDouble(textBalance.getText().trim());
            double loan = Double.parseDouble(textLoan.getText().trim());

            LoanCalculator.checkEligibility(balance, loan);   // throws if refused

            String name = textName.getText().trim();
            if (name.isEmpty()) {
                name = "Not given";
            }
            long pct = Math.round(LoanCalculator.RATE * 100);

            textResult.setText("LOAN APPROVED\n\n"
                + line("Account holder", name)
                + line("Loan amount", LoanCalculator.money(loan))
                + line("Interest (" + pct + "%, " + LoanCalculator.MONTHS + " months)",
                       LoanCalculator.money(LoanCalculator.interest(loan)))
                + line("Total to repay", LoanCalculator.money(LoanCalculator.totalToRepay(loan)))
                + line("Monthly payment", LoanCalculator.money(LoanCalculator.monthlyInstallment(loan))));
        } catch (NumberFormatException ex) {          // must come BEFORE IllegalArgumentException
            JOptionPane.showMessageDialog(this, "Enter valid numbers for balance and loan");
        } catch (IllegalArgumentException ex) {       // a rule was broken: show the reason
            textResult.setText(ex.getMessage());
        }
    }

    private String line(String label, String value) {
        return String.format("%-24s: %s\n", label, value);
    }

    private void resetForm() {
        textName.setText("");
        textBalance.setText("");
        textLoan.setText("");
        textResult.setText("");
        textName.requestFocus();
    }
}