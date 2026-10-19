package com.addnumbers;

public class NumberAdder {
	public static double add(double... numbers) {
		double sum = 0;
		for (double n: numbers) {
			sum += n;
		}
		return sum;
	}

}
