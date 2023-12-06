package ru.nsu.yakupova;

/**
 * Exception for dividing by zero.
 */
public class DividingByZeroException extends IllegalArgumentException {
    public DividingByZeroException(String message) {
        super(message);
    }
}
