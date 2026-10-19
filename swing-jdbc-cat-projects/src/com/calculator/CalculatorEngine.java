package com.calculator;

public class CalculatorEngine {

    // two numbers and an operator: + - x / ^
    public static double calculate(double a, String op, double b) {
        switch (op) {
            case "+":      return a + b;
            case "-":      return a - b;
            case "\u00D7": return a * b;
            case "\u00F7":
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            case "^":      return Math.pow(a, b);
            default:       throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    // one number and a function: sin cos tan ln log ! sqrt x2 %
    public static double apply(String fn, double x) {
        switch (fn) {
            case "sin": return Math.sin(Math.toRadians(x));
            case "cos": return Math.cos(Math.toRadians(x));
            case "tan":
                if (x % 180 == 90 || x % 180 == -90) {
                    throw new ArithmeticException("tan is undefined here");
                }
                return Math.tan(Math.toRadians(x));
            case "ln":
                if (x <= 0) throw new ArithmeticException("Input must be above 0");
                return Math.log(x);
            case "log":
                if (x <= 0) throw new ArithmeticException("Input must be above 0");
                return Math.log10(x);
            case "\u221A":
                if (x < 0) throw new ArithmeticException("No square root of a negative");
                return Math.sqrt(x);
            case "x\u00B2": return x * x;
            case "%":       return x / 100;
            case "!":       return factorial(x);
            default:        throw new IllegalArgumentException("Unknown function " + fn);
        }
    }

    private static double factorial(double x) {
        if (x < 0 || x != Math.floor(x)) {
            throw new ArithmeticException("Factorial needs a whole number, 0 or more");
        }
        if (x > 170) {
            throw new ArithmeticException("Number too large");
        }
        double result = 1;
        for (int i = 2; i <= (int) x; i++) {
            result *= i;
        }
        return result;
    }

    // text for the display: 10 instead of 10.0, and 0.3 instead of 0.30000000000000004
    public static String format(double v) {
        if (Math.abs(v) < 1e8) {
            v = Math.round(v * 1e10) / 1e10;
        }
        if (v == Math.floor(v) && Math.abs(v) < 1e15) {
            return String.valueOf((long) v);
        }
        return String.valueOf(v);
    }
}