package com.example.calc.calculator;

import android.util.Log;

public final class CalculatorImpl implements Calculator {
    private final static String DEFAULT_ERROR_MESSAGE = "Ошибка ввода";
    private final static String DIVISION_BY_ZERO_MESSAGE = "Ошибка деления на ноль";
    private final static String GREATER_SYMBOL = ">";
    private final static String LESS_SYMBOL = "<";

    @Override
    public String calculateResult(String leftOperandAsString,
                                  String rightOperandAsString,
                                  Operator operator
    ) {
        if (operator == Operator.UNKNOWN) {
            return DEFAULT_ERROR_MESSAGE;
        }

        Float leftOperand;
        Float rightOperand;

        try {
            leftOperand = Float.parseFloat(leftOperandAsString);
            rightOperand = Float.parseFloat(rightOperandAsString);
        } catch (NumberFormatException e) {
            Log.d(getClass().getSimpleName(), e.getMessage());
            return DEFAULT_ERROR_MESSAGE;
        }
        Float result = null;
        try {
            switch (operator) {
                case PLUS:
                    result = leftOperand + rightOperand;
                    break;
                case MINUS:
                    result = leftOperand - rightOperand;
                    break;
                case DIVIDE:
                    result = leftOperand / rightOperand;
                    break;
                case MULTIPLY:
                    result = leftOperand * rightOperand;
                    break;
                case COMPARE:
                    return formatCompareOutput(leftOperandAsString, rightOperandAsString, leftOperand > rightOperand);

            }
        } catch (ArithmeticException e) {
            Log.d(getClass().getSimpleName(), e.getMessage());
            return DEFAULT_ERROR_MESSAGE;
        }
        return formatOutput(leftOperandAsString, rightOperandAsString, operator, result);
    }

    private String formatOutput(String leftOperandAsString,
                                String rightOperandAsString,
                                Operator operator,
                                Float result
    ) {
        if (result == null) {
            return DEFAULT_ERROR_MESSAGE;
        }

        StringBuilder builder = new StringBuilder();
        builder
                .append(leftOperandAsString)
                .append(" ")
                .append(operator.description)
                .append(" ")
                .append(rightOperandAsString)
                .append(" ")
                .append(" = ")
                .append(result);

        return builder.toString();
    }

    private String formatCompareOutput(String leftOperandAsString,
                                       String rightOperandAsString,
                                       boolean isGreater
    ) {
        StringBuilder builder = new StringBuilder();
        builder
                .append(leftOperandAsString)
                .append(" ");
        if (isGreater) {
            builder.append(GREATER_SYMBOL);
        } else {
            builder.append(LESS_SYMBOL);
        }

        builder
                .append(" ")
                .append(rightOperandAsString);

        return builder.toString();
    }
}
