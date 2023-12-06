package ru.nsu.yakupova;

/**
 * Exception for parsing complex numbers.
 */
public class ParsingComplexNumberException extends NumberFormatException {
    public ParsingComplexNumberException(String message) {
        super(message);
    }
}
