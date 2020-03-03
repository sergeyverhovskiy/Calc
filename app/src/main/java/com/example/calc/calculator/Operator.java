package com.example.calc.calculator;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    COMPARE(""),
    UNKNOWN("Unknown");

    Operator(String description) {
        this.description = description;
    }
    public String description;
}
