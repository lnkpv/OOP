package ru.nsu.yakupova;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for Calculator (Task_1_5_1).
 */
public class Calculator {

    /**
     * Method for calculating.
     */
    public static String calculate(String expression) {
        String[] tokens = expression.split(" ");
        Stack<ComplexNumber> stack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (isNumeric(token)) {
                stack.push(new ComplexNumber(Double.parseDouble(token), 0));
            } else if (isComplexNumber(token)) {
                stack.push(parseComplexNumber(token));
            } else if (OperationType.fromString(token) != null) {
                var a = stack.pop();
                var b = stack.isEmpty() ? new ComplexNumber(0, 0) : stack.pop();
                OperationType oper = OperationType.fromString(token);
                Operation operation = SimpleCalculator.useOperator(oper, a, b);
                stack.push(operation.getResult());
            } else {
                throw new UnknownOperatorException("Unknown operator: " + token);
            }
        }

        return stack.pop().toString();
    }

    /**
     * Method for checking numbers.
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method for checking complex numbers.
     */
    private static boolean isComplexNumber(String str) {
        try {
            parseComplexNumber(str);
            return true;
        } catch (ParsingComplexNumberException e) {
            return false;
        }
    }

    /**
     * Method for parsing complex numbers.
     */
    private static ComplexNumber parseComplexNumber(String str) {
        Pattern pattern = Pattern.compile("(-?[0-9]+\\.?[0-9]?)([-|+]+[0-9]+\\.?[0-9]?)[i$]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            double real = Double.parseDouble(matcher.group(1));
            double imaginary = Double.parseDouble(matcher.group(2));
            return new ComplexNumber(real, imaginary);
        } else {
            throw new ParsingComplexNumberException("Cannot parse complex number");
        }
    }

}