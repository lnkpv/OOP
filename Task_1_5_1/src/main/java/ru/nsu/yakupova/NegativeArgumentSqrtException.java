package ru.nsu.yakupova;

/**
 * Exception for negative arguments in sqrt.
 */
public class NegativeArgumentSqrtException extends NegativeArgumentException {
    public NegativeArgumentSqrtException(String message) {
        super(message);
    }
}
