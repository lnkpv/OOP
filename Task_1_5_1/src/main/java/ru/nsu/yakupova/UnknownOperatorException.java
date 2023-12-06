package ru.nsu.yakupova;

/**
 * Exception for unknown operator.
 */
public class UnknownOperatorException extends IllegalArgumentException {
    public UnknownOperatorException(String message) {
        super(message);
    }
}

