package ru.nsu.yakupova;

class DividingByZeroException extends IllegalArgumentException {
    public DividingByZeroException(String message) {
        super(message);
    }
}
