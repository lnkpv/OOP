package ru.nsu.yakupova;

/**
 * Exception for negative arguments in log.
 */
public class NegativeArgumentLogException extends NegativeArgumentException {
    public NegativeArgumentLogException(String message) {
        super(message);
    }
}
