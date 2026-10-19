package com.bankingsystem;

import java.util.Locale;

public class LoanCalculator {

    public static final double MIN_BALANCE = 10000;
    public static final double MAX_LOAN = 60000;
    public static final double RATE = 0.05;     // 5% for the whole six months
    public static final int MONTHS = 6;

    // throws an exception with the reason when the loan is refused
    public static void checkEligibility(double balance, double loan) {
    	if (Double.isNaN(balance) || Double.isInfinite(balance)
                || Double.isNaN(loan) || Double.isInfinite(loan)) {
            throw new IllegalArgumentException("Enter valid amounts");
        }
        if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException(
                "Loan refused: the account must have at least " + money(MIN_BALANCE));
        }
        if (loan <= 0) {
            throw new IllegalArgumentException("Loan amount must be above 0");
        }
        if (loan > MAX_LOAN) {
            throw new IllegalArgumentException(
                "Loan refused: the maximum loan is " + money(MAX_LOAN));
        }
    }

    public static double interest(double loan) {
        return loan * RATE;
    }

    public static double totalToRepay(double loan) {
        return loan + interest(loan);
    }

    public static double monthlyInstallment(double loan) {
        return totalToRepay(loan) / MONTHS;
    }

    // 3000 -> "3,000.00 Rwf" (Locale.US keeps the same format on every computer)
    public static String money(double amount) {
        return String.format(Locale.US, "%,.2f Rwf", amount);
    }
}