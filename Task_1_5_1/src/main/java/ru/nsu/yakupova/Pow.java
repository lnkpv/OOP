package ru.nsu.yakupova;

/**
 * Class for pow.
 */
class Pow implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber b;
    private final ComplexNumber result;

    public Pow(ComplexNumber a, ComplexNumber b) {
        this.a = a;
        this.b = b;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        //z^n=r^n(cos(nf)+isin(nf))
        double n = b.getReal();
        double newReal = Math.pow(a.getMod(), n) * Math.cos(n * a.getArg());
        double newImaginary = Math.pow(a.getMod(), n) * Math.sin(n * a.getArg());
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
