package com.division;

import com.division.exception.DivisonByZeroException;
import com.division.logic.DivisionInColumn;
import com.division.output.DivisionInColumnConsoleOutput;
import com.division.output.impl.DivisionInColumnConsoleOutputImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Division {

    private static final Logger LOG = LoggerFactory.getLogger(Division.class);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LOG.info("Enter the first any integer: ");
            int dividend = Integer.parseInt(scanner.nextLine());
            LOG.info("Enter the second any integer: ");
            int divider = Integer.parseInt(scanner.nextLine());

            DivisionInColumnConsoleOutput divisionInColumnConsoleOutput = new DivisionInColumnConsoleOutputImpl(dividend, divider);
            DivisionInColumn divisionInColumn = new DivisionInColumn(divisionInColumnConsoleOutput);

            divisionInColumn.makeDivision(dividend, divider);
        } catch (DivisonByZeroException e) {
            LOG.error("Division by zero");
        }
    }
}

