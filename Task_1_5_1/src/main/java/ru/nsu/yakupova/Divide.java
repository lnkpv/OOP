package ru.nsu.yakupova;

/**
 * Class for divide.
 */
public class Divide implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber second;
    private final ComplexNumber result;

    public Divide(ComplexNumber a, ComplexNumber b) {
        this.first = a;
        this.second = b;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {

        // (a+bi)/(c+di) = (ac+bd)/(c^2+d^2) + (bc-ad)/(c^2+d^2)i
        double a = this.first.getReal();
        double b = this.first.getImaginary();
        double c = this.second.getReal();
        double d = this.second.getImaginary();

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
