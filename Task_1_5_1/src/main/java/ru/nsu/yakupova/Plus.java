package ru.nsu.yakupova;

/**
 * Class for plus.
 */
class Plus implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber b;
    private final ComplexNumber result;

    public Plus(ComplexNumber a, ComplexNumber b) {
        this.a = a;
        this.b = b;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        return new ComplexNumber(a.getReal() + b.getReal(),
                a.getImaginary() + b.getImaginary());
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
