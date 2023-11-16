package com.division;

import com.division.exception.DivisonByZeroException;
import com.division.logic.DivisionInColumn;
import com.division.output.DivisionInColumnConsoleOutput;
import com.division.output.impl.DivisionInColumnConsoleOutputImpl;

import java.util.Scanner;

public class Division {

	public static void main(String[] args) {
		DivisionInColumnConsoleOutput divisionInColumnConsoleOutput = new DivisionInColumnConsoleOutputImpl();
		DivisionInColumn divisionInColumn = new DivisionInColumn(divisionInColumnConsoleOutput);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the first any integer: ");
		int dividend = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the second any integer: ");
		int divider = Integer.parseInt(scanner.nextLine());
		try {
			System.out.println(divisionInColumn.makeDivision(dividend, divider));
		} catch (DivisonByZeroException e) {
			System.out.println("Division by zero");
		}

	}
}