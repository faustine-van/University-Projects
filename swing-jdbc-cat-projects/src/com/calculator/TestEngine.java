package com.calculator;

public class TestEngine {

    private static void print(String label, double value) {
        System.out.println(label + " = " + CalculatorEngine.format(value));
    }

    public static void main(String[] args) {
        print("0.1 + 0.2", CalculatorEngine.calculate(0.1, "+", 0.2));
        print("12 / 4", CalculatorEngine.calculate(12, "\u00F7", 4));
        print("2 ^ 10", CalculatorEngine.calculate(2, "^", 10));
        print("sin 30", CalculatorEngine.apply("sin", 30));
        print("sin 180", CalculatorEngine.apply("sin", 180));
        print("cos 60", CalculatorEngine.apply("cos", 60));
        print("tan 45", CalculatorEngine.apply("tan", 45));
        print("ln e", CalculatorEngine.apply("ln", Math.E));
        print("log 1000", CalculatorEngine.apply("log", 1000));
        print("5 !", CalculatorEngine.apply("!", 5));
        print("sqrt 144", CalculatorEngine.apply("\u221A", 144));
        print("50 %", CalculatorEngine.apply("%", 50));

        try {
            CalculatorEngine.calculate(5, "\u00F7", 0);
        } catch (ArithmeticException e) {
            System.out.println("5 / 0 -> " + e.getMessage());
        }
        try {
            CalculatorEngine.apply("tan", 90);
        } catch (ArithmeticException e) {
            System.out.println("tan 90 -> " + e.getMessage());
        }
    }
}