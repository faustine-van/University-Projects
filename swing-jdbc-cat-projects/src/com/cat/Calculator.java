package com.cat;

// Q1
public class Calculator extends MathOperation {
	@Override 
	int add(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		Calculator cal = new Calculator();
		System.out.println("Sum: " + cal.add(45, 9));
		System.out.println("Product: " + cal.multiply(45, 9));
	}

}
