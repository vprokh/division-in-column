package com.division.output;

public interface DivisionInColumnConsoleOutput {

    void printToConsole();

    void addDivisionStep(int intendNumber, String divisionStepValue);

    void addDivisionStepMinus(int intendNumber, String divisionStepValue, int lengthOfDigits);
}
