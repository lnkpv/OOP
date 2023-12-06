package ru.nsu.yakupova;

/**
 * Class for plus.
 */
public class Plus implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber second;
    private final ComplexNumber result;

    /**
     * Constructor.
     */
    public Plus(ComplexNumber a, ComplexNumber b) {
        this.first = a;
        this.second = b;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        return new ComplexNumber(first.getReal() + second.getReal(),
                first.getImaginary() + second.getImaginary());
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
