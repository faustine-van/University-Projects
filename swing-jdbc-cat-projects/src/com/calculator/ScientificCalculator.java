package com.calculator;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ScientificCalculator extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField display;
    private double firstNumber = 0;
    private String operator = null;
    private boolean startNewNumber = true;
    private JLabel lblMode;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ScientificCalculator().setVisible(true));
    }

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 420);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(5, 5));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));
        setContentPane(root);

        root.add(buildDisplay(), BorderLayout.NORTH);
        root.add(buildKeys(), BorderLayout.CENTER);
    }

    // top: mode label + display field
    private JPanel buildDisplay() {
        JPanel p = new JPanel(new BorderLayout(8, 0));
        lblMode = new JLabel("DEG");
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        p.add(lblMode, BorderLayout.WEST);
        p.add(display, BorderLayout.CENTER);
        return p; 
    }

    // bottom: number pad (left, wider) + scientific pad (right)
    private JPanel buildKeys() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;

        c.gridx = 0;
        c.weightx = 5;
        p.add(buildNumberPad(), c);

        c.gridx = 1;
        c.weightx = 3;
        c.insets = new Insets(0, 12, 0, 0);
        p.add(buildSciPad(), c);
        return p;
    }

    private JPanel buildNumberPad() {
        JPanel p = new JPanel(new GridBagLayout());
        String[][] rows = {
                {"7", "8", "9", "\u00F7", "DEL"},   // \u00F7 is the divide sign
                {"4", "5", "6", "\u00D7", "C"},     // \u00D7 is the multiply sign
                {"1", "2", "3", "-", "="},
                {".", "0", "\u00B1", "+", null}
            };

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(2, 2, 2, 2);

        for (int r = 0; r < rows.length; r++) {
            for (int col = 0; col < rows[r].length; col++) {
                if (rows[r][col] == null) continue;     // empty cell
                c.gridx = col;
                c.gridy = r;
                c.gridheight = rows[r][col].equals("=") ? 2 : 1;   // = spans two rows
                p.add(makeButton(rows[r][col]), c);

            }
        }
        return p;
    }

    private JPanel buildSciPad() {
        JPanel p = new JPanel(new GridLayout(4, 3, 4, 4));
        String[] keys = {
            "sin", "cos", "tan",
            "ln",  "log", "!",
            "\u03C0", "e", "^",
            "\u221A", "x\u00B2", "%"
        };
        for (String k : keys) {
        	p.add(makeButton(k));
        }
        return p;
    }
    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.addActionListener(e -> handle(text));
        return b;
    }
    private void handle(String key) {
        try {
            switch (key) {
                case "C":      clearAll(); break;
                case "DEL":    backspace(); break;
                case "=":      pressEquals(); break;
                case "\u00B1": toggleSign(); break;
                case "\u03C0": showValue(Math.PI); break;
                case "e":      showValue(Math.E); break;
                case "+": case "-": case "\u00D7": case "\u00F7": case "^":
                    pressOperator(key);
                    break;
                default:
                    if (key.matches("[0-9.]")) {
                        appendDigit(key);
                    } else {
                        showValue(CalculatorEngine.apply(key, current()));  // sin, cos, ln, ...
                    }
            }
        } catch (ArithmeticException ex) {
            showError(ex.getMessage());
        }
    }
    private double current() {
        return Double.parseDouble(display.getText());
    }

    private void appendDigit(String d) {
        String text = display.getText();
        if (startNewNumber) {
            display.setText(d.equals(".") ? "0." : d);
            startNewNumber = false;
        } else if (d.equals(".")) {
            if (!text.contains(".")) {
                display.setText(text + ".");
            }
        } else if (text.equals("0")) {
            display.setText(d);
        } else {
            display.setText(text + d);
        }
    }

    private void backspace() {
        if (startNewNumber) return;                  // do not edit a result
        String text = display.getText();
        text = text.substring(0, text.length() - 1);
        if (text.isEmpty() || text.equals("-")) {
            text = "0";
        }
        display.setText(text);
    }

    private void toggleSign() {
        String text = display.getText();
        if (text.equals("0")) return;
        display.setText(text.startsWith("-") ? text.substring(1) : "-" + text);
    }

    private void clearAll() {
        display.setText("0");
        firstNumber = 0;
        operator = null;
        startNewNumber = true;
    }

    private void showValue(double v) {
        if (Double.isNaN(v) || Double.isInfinite(v)) {
            throw new ArithmeticException("Result is too large");
        }
        display.setText(CalculatorEngine.format(v));
        startNewNumber = true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
        clearAll();
    }
    
    private void pressOperator(String op) {
        if (operator != null && !startNewNumber) {
            // chain: in 2 + 3 + 4, the second + first computes 2 + 3
            firstNumber = CalculatorEngine.calculate(firstNumber, operator, current());
            showValue(firstNumber);
        } else {
            firstNumber = current();
        }
        operator = op;
        startNewNumber = true;
    }

    private void pressEquals() {
        if (operator == null) return;
        showValue(CalculatorEngine.calculate(firstNumber, operator, current()));
        operator = null;
    }
}