package com.bankingsystem;

public class TestLoan {

    private static void run(double balance, double loan) {
        String result;
        try {
            LoanCalculator.checkEligibility(balance, loan);
            result = "interest " + LoanCalculator.money(LoanCalculator.interest(loan))
                   + " | total " + LoanCalculator.money(LoanCalculator.totalToRepay(loan))
                   + " | monthly " + LoanCalculator.money(LoanCalculator.monthlyInstallment(loan));
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
        System.out.println("balance=" + (long) balance + ", loan=" + (long) loan + " -> " + result);
    }

    public static void main(String[] args) {
        run(9999, 20000);
        run(10000, 20000);
        run(50000, 60000);
        run(50000, 60001);
        run(50000, 0);
    }
}