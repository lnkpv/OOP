package ru.nsu.yakupova;

/**
 * Class for log.
 */
public class Log implements Operation {
    private final ComplexNumber first;
    private final ComplexNumber result;

    /**
     * Constructor.
     */
    public Log(ComplexNumber a) {
        this.first = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        if (first.getImaginary() == 0 && first.getReal() <= 0) {
            throw new NegativeArgumentLogException("[log] can only contain a positive number!");
        }
        //ln(z) = ln|z| + i*arg(z)
        return new ComplexNumber(Math.log(first.getMod()), first.getArg());
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
