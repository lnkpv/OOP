package ru.nsu.yakupova;

/**
 * Class for multiply.
 */
class Multiply implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber b;
    private final ComplexNumber result;

    public Multiply(ComplexNumber x, ComplexNumber y) {
        this.a = x;
        this.b = y;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        // (a+bi)(c+di)=(ac-bd) + (ad+bc)i
        double a = this.a.getReal();
        double b = this.a.getImaginary();
        double c = this.b.getReal();
        double d = this.b.getImaginary();

        return new ComplexNumber(a * c - b * d, a * d + b * c);
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
