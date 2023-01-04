package com.kimsiddiqi.calculatorproject;

/**
 * Main class (contains main method) of Calculator application.
 * Only used to start up the application.
 *
 * @author kimsiddiqi
 */
public class CalculatorMain {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		CalculatorGUI calculatorGUI = new CalculatorGUI(calculator);
		calculatorGUI.start();
	}
}
