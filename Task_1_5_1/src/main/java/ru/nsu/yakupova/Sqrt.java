package ru.nsu.yakupova;

/**
 * Class for sqrt.
 */
public class Sqrt implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber result;

    public Sqrt(ComplexNumber a) {
        this.first = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        if (first.getImaginary() == 0 && first.getReal() < 0) {
            throw new NegativeArgumentSqrtException("[sqrt] "
                    + "can only contain a non-negative number!");
        }
        // only k = 0 !!!
        double newReal = Math.pow(first.getMod(), 0.5) * Math.cos(first.getArg() / 2);
        double newImaginary = Math.pow(first.getMod(), 0.5) * Math.sin(first.getArg() / 2);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}


