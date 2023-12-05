package ru.nsu.yakupova;

/**
 * Class for sqrt.
 */
class Sqrt implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber result;

    public Sqrt(ComplexNumber a) {
        this.a = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        if (a.getImaginary() == 0 && a.getReal() < 0) {
            throw new NegativeArgumentSqrtException("[sqrt] can only contain a non-negative number!");
        }
        // only k = 0 !!!
        double newReal = Math.pow(a.getMod(), 0.5) * Math.cos(a.getArg() / 2);
        double newImaginary = Math.pow(a.getMod(), 0.5) * Math.sin(a.getArg() / 2);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}


