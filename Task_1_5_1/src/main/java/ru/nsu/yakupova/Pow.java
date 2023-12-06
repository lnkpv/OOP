package ru.nsu.yakupova;

/**
 * Class for pow.
 */
public class Pow implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber second;
    private final ComplexNumber result;

    /**
     * Constructor.
     */
    public Pow(ComplexNumber a, ComplexNumber b) {
        this.first = a;
        this.second = b;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        //z^n=r^n(cos(nf)+isin(nf))
        double n = second.getReal();
        double newReal = Math.pow(first.getMod(), n) * Math.cos(n * first.getArg());
        double newImaginary = Math.pow(first.getMod(), n) * Math.sin(n * first.getArg());
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
