package ru.nsu.yakupova;

class MyException extends IllegalArgumentException {
    public MyException(String message) {
        super(message);
    }
}