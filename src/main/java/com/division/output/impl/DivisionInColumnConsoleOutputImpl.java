package com.division.output.impl;

import com.division.output.DivisionInColumnConsoleOutput;

public class DivisionInColumnConsoleOutputImpl implements DivisionInColumnConsoleOutput {

    private static final char NEW_LINE = '\n';
    private static final int ADDITIONAL_INDENT_LEVEL = 2;
    private static final String FORMAT_PATTERN = "%";
    private static final String FORMAT_PATTERN_STRING = "s";
    private static final String MINUS_SIGN = "_";
    private static final char SPACE_SYMBOL = ' ';
    private static final char HYPHEN_SYMBOL = '-';
    private static final int HEADER_LINES_QUANTITY = 3;
    private static final String VERTICAL_BAR = "│";
    private static final String DIVIDEND_IS_LESS_MESSAGE_FORMAT = "%s/%s=0";

    private final int dividend;
    private final int divisor;

    private final StringBuilder result;
    private int currentIndentNumber;

    public DivisionInColumnConsoleOutputImpl(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.result = new StringBuilder();
    }

    @Override
    public void printToConsole(int indentNumber, String divisionReminder, int spaceIndent) {
        addReminderLine(indentNumber, divisionReminder);
        modifyResultToView(spaceIndent);

        System.out.println(result);
    }

    private void addReminderLine(int indentNumber, String divisionReminder) {
        String reminderNumberLine = String.format(getDecresingNumberLineFormat(indentNumber), divisionReminder);
        result
                .append(reminderNumberLine)
                .append(NEW_LINE);
    }

    @Override
    public void addDivisionStepDecreasingLine(int intendNumber, String divisionStepValue) {
        String decreasingNumberLineFormat = getDecresingNumberLineFormat(intendNumber);
        String decreasingNumberLine = String.format(decreasingNumberLineFormat, MINUS_SIGN + divisionStepValue);
        currentIndentNumber = decreasingNumberLine.length();

        result
                .append(decreasingNumberLine)
                .append(NEW_LINE);
    }

    @Override
    public void addDivisionStepDecreaseLine(int intendNumber, String divisionStepValue) {
        result
                .append(String.format(getDecresingNumberLineFormat(intendNumber), divisionStepValue))
                .append(NEW_LINE);
    }

    @Override
    public void addDivisionStepDivider(int intendNumber, int hyphenNumber) {
        result
                .append(makeDivider(intendNumber, currentIndentNumber - hyphenNumber))
                .append(NEW_LINE);
    }

    @Override
    public void printIncorrectDividendMessage() {
        System.out.printf(DIVIDEND_IS_LESS_MESSAGE_FORMAT, dividend, divisor);
    }

    private String getDecresingNumberLineFormat(int intendNumber) {
        return new StringBuilder(FORMAT_PATTERN)
                .append(intendNumber + ADDITIONAL_INDENT_LEVEL)
                .append(FORMAT_PATTERN_STRING).toString();
    }

    private String makeDivider(Integer hyphenLength, Integer indentLength) {
        return assemblyString(indentLength, SPACE_SYMBOL) + assemblyString(hyphenLength, HYPHEN_SYMBOL);
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(numberOfSymbols);
    }

    private void modifyResultToView(int spaceIndent) {
        String quotient = String.valueOf(dividend / divisor);
        int[] headerLineIndexes = getHeaderLinesIndexes();

        int tab = spaceIndent + 1 - headerLineIndexes[0];
        result.insert(headerLineIndexes[2], assemblyString(tab, SPACE_SYMBOL) + VERTICAL_BAR + quotient);
        result.insert(headerLineIndexes[1], assemblyString(tab, SPACE_SYMBOL) + VERTICAL_BAR + assemblyString(quotient.length(), HYPHEN_SYMBOL));
        result.insert(headerLineIndexes[0], VERTICAL_BAR + divisor);
        result.replace(1, headerLineIndexes[0], String.valueOf(dividend));
    }

    private int[] getHeaderLinesIndexes() {
        int[] index = new int[HEADER_LINES_QUANTITY];

        for (int i = 0, j = 0; i < result.length(); i++) {
            if (result.charAt(i) == NEW_LINE) {
                index[j] = i;
                j++;
            }

            if (j == HEADER_LINES_QUANTITY) {
                break;
            }
        }

        return index;
    }
}
