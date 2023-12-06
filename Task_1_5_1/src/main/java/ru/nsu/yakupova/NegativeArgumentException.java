package ru.nsu.yakupova;

/**
 * Exception for negative arguments.
 */
public class NegativeArgumentException extends IllegalArgumentException {
    public NegativeArgumentException(String message) {
        super(message);
    }
}
