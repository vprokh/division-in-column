package com.division.output.impl;

import com.division.output.DivisionInColumnConsoleOutput;

public class DivisionInColumnConsoleOutputImpl implements DivisionInColumnConsoleOutput {

    private final StringBuilder result = new StringBuilder();

    @Override
    public void printToConsole() {

    }

    @Override
    public void addDivisionStep(int intendNumber, String divisionStepValue) {
        result.append(String.format("%" + (intendNumber + 2) + "s", "_" + divisionStepValue));
    }

    @Override
    public void addDivisionStepMinus(int intendNumber, String divisionStepValue, int digitsLength) {
        result
                .append(String.format("%" + (intendNumber + 2) + "s", divisionStepValue))
                .append("\n")
                .append(makeDivider(divisionStepValue, divisionStepValue.length() - digitsLength));
    }

    private String makeDivider(Integer reminderNumber, Integer tab) {
        return assemblyString(tab, ' ') + assemblyString(reminderNumber, '-');
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }
}
