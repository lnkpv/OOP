package ru.nsu.yakupova;

/**
 * Class for sin.
 */
class Sin implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber result;

    public Sin(ComplexNumber a) {
        this.first = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        double realRad = Math.toRadians(first.getReal());
        double imagRad = Math.toRadians(first.getImaginary());
        return new ComplexNumber(Math.sin(realRad) * Math.cosh(imagRad),
                Math.cos(realRad) * Math.sinh(imagRad));
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
