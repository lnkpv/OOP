package ru.nsu.yakupova;

/**
 * Class for divide.
 */
class Divide implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber b;
    private final ComplexNumber result;

    public Divide(ComplexNumber a, ComplexNumber b) {
        this.a = a;
        this.b = b;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {

        // (a+bi)/(c+di) = (ac+bd)/(c^2+d^2) + (bc-ad)/(c^2+d^2)i
        double a = this.a.getReal();
        double b = this.a.getImaginary();
        double c = this.b.getReal();
        double d = this.b.getImaginary();

        if (c == 0) {
            throw new DividingByZeroException("Dividing by zero!");
        }

        return new ComplexNumber((a * c + b * d) / (c * c + d * d),
                (a * d + b * c) / (c * c + d * d));
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
