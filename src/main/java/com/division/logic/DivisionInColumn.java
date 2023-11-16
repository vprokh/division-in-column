package com.division.logic;

import com.division.exception.DivisonByZeroException;
import com.division.output.DivisionInColumnConsoleOutput;

public class DivisionInColumn {

	private static final int DIGITS_COUNT_ONE = 1;

	private final DivisionInColumnConsoleOutput divisionInColumnConsoleOutput;

	public DivisionInColumn(DivisionInColumnConsoleOutput divisionInColumnConsoleOutput) {
		this.divisionInColumnConsoleOutput = divisionInColumnConsoleOutput;
	}

	private static final String DIVIDEND_IS_LESS_MESSAGE_FORMAT = "%s/%s=0";

	private final StringBuilder quotient = new StringBuilder();
	private final StringBuilder reminder = new StringBuilder();


	public String makeDivision(int dividend, int divisor) throws DivisonByZeroException {

		if (divisor == 0) {
			throw new DivisonByZeroException("Divisor cannot be 0, division by zero");
		}

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		if (dividend < divisor) {
			return String.format(DIVIDEND_IS_LESS_MESSAGE_FORMAT, dividend, divisor);
		}

		char[] digits = String.valueOf(dividend).toCharArray();

		int reminderNumber;
		int multiplyResult;
		int currentDivisionDigitsCount = getCurrentDigitsCountOfDivision(divisor);
		int restOfDivision;

		for (int i = 0; i < digits.length; i++) {
			reminder.append(digits[i]);
			reminderNumber = Integer.parseInt(reminder.toString());

			if (reminderNumber >= divisor) {
				restOfDivision = reminderNumber % divisor;
				multiplyResult = reminderNumber / divisor * divisor;

				divisionInColumnConsoleOutput.addDivisionStep(i, String.valueOf(reminderNumber));
				divisionInColumnConsoleOutput.addDivisionStepMinus(
						i, String.valueOf(multiplyResult), getCurrentDigitsCountOfDivision(multiplyResult));

				result.append(makeDivider(reminderNumber, tab)).append("\n");

				quotient.append(reminderNumber / divisor);

				reminder.replace(0, reminder.length(), String.valueOf(restOfDivision));
				reminderNumber = Integer.parseInt(reminder.toString());
			} else {
				if (i >= currentDivisionDigitsCount)
					quotient.append(0);
			}

			if (i == digits.length - 1) {
				result.append(String.format("%" + (i + 2) + "s", reminderNumber.toString())).append("\n");
			}
		}
		modifyResultToView(dividend, divisor);
		return result.toString();
	}


	private void modifyResultToView(Integer dividend, Integer divisor) {
		int[] index = new int[3];
		for (int i = 0, j = 0; i < result.length(); i++) {
			if (result.charAt(i) == '\n') {
				index[j] = i;
				j++;
			}

			if (j == 3)
				break;
		}

		int tab = getCurrentDigitsCountOfDivision(dividend) + 1 - index[0];
		result.insert(index[2], assemblyString(tab, ' ') + "│" + quotient.toString());
		result.insert(index[1], assemblyString(tab, ' ') + "│" + assemblyString(quotient.length(), '-'));
		result.insert(index[0], "│" + divisor);
		result.replace(1, index[0], dividend.toString());
	}

	private int getCurrentDigitsCountOfDivision(int digitsCount) {
		return (int) Math.log10(digitsCount) + DIGITS_COUNT_ONE;
	}
}