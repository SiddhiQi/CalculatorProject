package com.kimsiddiqi.calculatorproject;

/**
*
* The Calculator class performs calculation of two 'double' numbers and
* returns the result. Users of the class provide the two numbers and which
* operator to use.
*
* @author Kim Siddiqi
*/

class Calculator {

	private double add(double firstNumber, double secondNumber) {
		return firstNumber + secondNumber;
	}

	private double subtract(double firstNumber, double secondNumber) {
		return firstNumber - secondNumber;
	}

	private double multiply(double firstNumber, double secondNumber) {
		return firstNumber * secondNumber;
	}

	private double divide(double firstNumber, double secondNumber) {
		if (secondNumber == 0) {
			throw new ArithmeticException("Division by zero not allowed");
		}
		return firstNumber / secondNumber;
	}

	public double calculate(char operator, double firstNumber, double secondNumber) {
		switch (operator) {
		case '+':
			return add(firstNumber, secondNumber);
		case '-':
			return subtract(firstNumber, secondNumber);
		case '*':
			return multiply(firstNumber, secondNumber);
		case '/':
			return divide(firstNumber, secondNumber);
		default :
			throw new IllegalArgumentException("\"" + operator
											  + "\" unrecognized or unsupported operator.");
		}		
	}
}
