package ru.nsu.yakupova;

public class DividingByZeroException extends IllegalArgumentException {
    public DividingByZeroException(String message) {
        super(message);
    }
}
