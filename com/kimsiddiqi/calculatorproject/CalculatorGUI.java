package com.kimsiddiqi.calculatorproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * The calculatorGUI class extends JFrame to present a window.
 * It builds and populates the window with a calculator graphical user
 * interface (GUI), with buttons and a display.
 * 
 * The class implements ActionListener interface to handle actionEvents
 * when buttons in the GUI are pressed and updates the display accordingly.
 * The class uses a Calculator object for arithmetic calculation.
 *
 * @author Kim Siddiqi
 */

@SuppressWarnings("serial")
class CalculatorGUI extends JFrame implements ActionListener {

	private Calculator calculator;
	private JLabel display;
	private JPanel buttonPanel;
	private StringBuffer currentNumberBuffer;
	private Character operator;
	private double savedResult;
	private boolean isDecimalNumber;

	public CalculatorGUI(Calculator calculator) {
		this.calculator = calculator;
		display = new JLabel("0");
		buttonPanel = new JPanel();
		currentNumberBuffer = new StringBuffer();
		operator = null;
		savedResult = 0;
		isDecimalNumber = false;
	}

	public void start() {
		buildDisplay();
		buildButtonPanel();
		add(display, BorderLayout.NORTH);
		add(buttonPanel);
		initializeWindow();
	}

	private void buildButtonPanel() {
		String[] buttonuttonLabels = { "7", "8", "9", "+", "4", "5", "6", "-", "1",
									   "2", "3", "*", "0", ".", "=", "/" };
		for (String label : buttonuttonLabels) {
			JButton button = new JButton(label);
			button.addActionListener(this);
			buttonPanel.add(button);
		}
		buttonPanel.setLayout(new GridLayout(4, 4));
	}

	private void buildDisplay() {
		display.setHorizontalAlignment(JLabel.RIGHT);
		display.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		display.setBackground(Color.WHITE);
		display.setOpaque(true);
	}

	private void initializeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		char inputChar = ((JButton) event.getSource()).getText().charAt(0);
		switch (inputChar) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			if (currentNumberBuffer.toString().equals("0")) {
				currentNumberBuffer.setLength(0);		// Trims leading "0" from currentNumberBuffer
			}
			appendToCurrentNumber(inputChar);
			break;
		case '.':
			// Only add a decimal point if "currentNumberBuffer"
			// doesn't already represent a decimal value.
			if (!isDecimalNumber) {
				if (currentNumberBuffer.length() == 0) {
					appendToCurrentNumber('0');
				}
				appendToCurrentNumber(inputChar);
				isDecimalNumber = true;
			}
			break;
		default: 		// Executes if arithmetic operator or equals is pressed
			if (currentNumberBuffer.length() > 0) {
				if (operator == null || operator == '=') {
					saveResult(Double.valueOf(currentNumberBuffer.toString()));
				} else {
					doCalculation(inputChar);
				}
			}
			operator = inputChar;
		}
	}

	private void doCalculation(char inputChar) {
		try {
			double currentNumber = Double.valueOf(currentNumberBuffer.toString());
			double result = calculator.calculate(operator, savedResult, currentNumber);
			saveResult(result);
		} catch (ArithmeticException exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage(), "Error",
										   JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveResult(Double result) {
		savedResult = result;
		currentNumberBuffer.setLength(0);
		isDecimalNumber = false;
		displayNumber(Double.toString(savedResult));
	}

	private void appendToCurrentNumber(char inputChar) {
		currentNumberBuffer.append(inputChar);
		displayNumber(currentNumberBuffer.toString());
	}

	private void displayNumber(String currentNumberString) {
		display.setText(currentNumberString);
	}
}
