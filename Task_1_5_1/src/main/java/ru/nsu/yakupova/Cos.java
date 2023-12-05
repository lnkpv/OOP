package ru.nsu.yakupova;

/**
 * Class for cos.
 */
class Cos implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber result;

    public Cos(ComplexNumber a) {
        this.a = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        double realRad = Math.toRadians(a.getReal());
        double imagRad = Math.toRadians(a.getImaginary());
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
