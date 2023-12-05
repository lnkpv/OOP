package ru.nsu.yakupova;

/**
 * Class for multiply.
 */
class Multiply implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber second;
    private final ComplexNumber result;

    public Multiply(ComplexNumber x, ComplexNumber y) {
        this.first = x;
        this.second = y;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        // (a+bi)(c+di)=(ac-bd) + (ad+bc)i
        double a = this.first.getReal();
        double b = this.first.getImaginary();
        double c = this.second.getReal();
        double d = this.second.getImaginary();

        return new ComplexNumber(a * c - b * d, a * d + b * c);
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
