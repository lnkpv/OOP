package ru.nsu.yakupova;


import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class for calculator (Task_1_5_1).
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
            } else {
                var a = stack.pop();
                var b = stack.isEmpty() ? new ComplexNumber(0, 0) : stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a.add(b));
                        break;
                    case "-":
                        stack.push(a.subtract(b));
                        break;
                    case "*":
                        stack.push(a.multiply(b));
                        break;
                    case "/":
                        stack.push(a.divide(b));
                        break;
                    case "sin":
                        stack.push(a.sin());
                        break;
                    case "cos":
                        stack.push(a.cos());
                        break;
                    case "sqrt":
                        stack.push(a.sqrt());
                        break;
                    case "log":
                        stack.push(a.log());
                        break;
                    case "pow":
                        stack.push(a.pow(b));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
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
        } catch (MyException e) {
            return false;
        }
    }

    /**
     * Method for parsing complex numbers.
     */
    private static ComplexNumber parseComplexNumber(String str) throws MyException {
        Pattern pattern = Pattern.compile("(-?[0-9]+\\.?[0-9]?)([-|+]+[0-9]+\\.?[0-9]?)[i$]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            double real = Double.parseDouble(matcher.group(1));
            double imaginary = Double.parseDouble(matcher.group(2));
            return new ComplexNumber(real, imaginary);
        } else {
            throw new MyException("Cannot parse complex number");

        }
    }
}