package ru.nsu.yakupova;

/**
 * Class for cos.
 */
public class Cos implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber result;

    /**
     * Constructor.
     */
    public Cos(ComplexNumber a) {
        this.first = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        double realRad = Math.toRadians(first.getReal());
        double imagRad = Math.toRadians(first.getImaginary());
        return new ComplexNumber(Math.cos(realRad) * Math.cosh(imagRad),
                -Math.sin(realRad) * Math.sinh(imagRad));
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
