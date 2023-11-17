package com.division.output;

public interface DivisionInColumnConsoleOutput {

    void addDivisionStepDecreasingLine(int intendNumber, String divisionStepValue);

    void addDivisionStepDecreaseLine(int intendNumber, String divisionStepValue);

    void addDivisionStepDivider(int intendNumber, int hyphenNumber);

    void printIncorrectDividendMessage();

    void printToConsole(int indentNumber, String value, int spaceIdent);
}
