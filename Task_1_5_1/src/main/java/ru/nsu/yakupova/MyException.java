package ru.nsu.yakupova;

class DividingByZeroException extends IllegalArgumentException {
    public DividingByZeroException(String message) {
        super(message);
    }
}

class UnknownOperatorException extends IllegalArgumentException {
    public UnknownOperatorException(String message) {
        super(message);
    }
}

class NegativeArgumentException extends IllegalArgumentException {
    public NegativeArgumentException(String message) {
        super(message);
    }
}
class NegativeArgumentSqrtException extends NegativeArgumentException {
    public NegativeArgumentSqrtException(String message) {
        super(message);
    }
}

class NegativeArgumentLogException extends NegativeArgumentException {
    public NegativeArgumentLogException(String message) {
        super(message);
    }
}

class ParsingComplexNumberException extends NumberFormatException {
    public ParsingComplexNumberException(String message) {
        super(message);
    }
}
