package com.division.logic;

import com.division.exception.DivisonByZeroException;
import com.division.output.DivisionInColumnConsoleOutput;

public class DivisionInColumn {

	private static final int DIGITS_COUNT_ONE = 1;

	private final DivisionInColumnConsoleOutput divisionInColumnConsoleOutput;
	private final StringBuilder reminder;

	public DivisionInColumn(DivisionInColumnConsoleOutput divisionInColumnConsoleOutput) {
		this.divisionInColumnConsoleOutput = divisionInColumnConsoleOutput;
		reminder = new StringBuilder();
	}

	public void makeDivision(int dividend, int divisor) throws DivisonByZeroException {
		if (divisor == 0) {
			throw new DivisonByZeroException("Divisor cannot be 0, division by zero");
		}

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		if (dividend < divisor) {
			divisionInColumnConsoleOutput.printIncorrectDividendMessage();
		}

		char[] digits = String.valueOf(dividend).toCharArray();

		int reminderNumber;
		int multiplyResult;
		int restOfDivision;

		for (int i = 0; i < digits.length; i++) {
			reminder.append(digits[i]);
			reminderNumber = Integer.parseInt(reminder.toString());

			if (reminderNumber >= divisor) {
				restOfDivision = reminderNumber % divisor;
				multiplyResult = reminderNumber / divisor * divisor;

				divisionInColumnConsoleOutput.addDivisionStepDecreasingLine(i, String.valueOf(reminderNumber));
				divisionInColumnConsoleOutput.addDivisionStepDecreaseLine(i, String.valueOf(multiplyResult));
				divisionInColumnConsoleOutput.addDivisionStepDivider(reminder.length(), getCurrentDigitsCountOfDivision(multiplyResult));

				reminder.replace(0, reminder.length(), String.valueOf(restOfDivision));
				reminderNumber = Integer.parseInt(reminder.toString());
			}

			if (isLastIteration(i, digits)) {
				divisionInColumnConsoleOutput.printToConsole(i, String.valueOf(reminderNumber), getCurrentDigitsCountOfDivision(dividend));
			}
		}
	}

	private boolean isLastIteration(int iterationIndex, char[] digits) {
		return iterationIndex == digits.length - 1;
	}

	private int getCurrentDigitsCountOfDivision(int digitsCount) {
		return (int) Math.log10(digitsCount) + DIGITS_COUNT_ONE;
	}
}