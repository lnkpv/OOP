package ru.nsu.yakupova;

/**
 * Class for log.
 */
class Log implements Operation {
    private final ComplexNumber a;
    private final ComplexNumber result;

    public Log(ComplexNumber a) {
        this.a = a;
        this.result = findResult();
    }

    /**
     * Method for finding result.
     */
    public ComplexNumber findResult() {
        if (a.getImaginary() == 0 && a.getReal() <= 0) {
            throw new NegativeArgumentLogException("[log] can only contain a positive number!");
        }
        //ln(z) = ln|z| + i*arg(z)
        return new ComplexNumber(Math.log(a.getMod()), a.getArg());
    }

    /**
     * Method for getting result.
     */
    public ComplexNumber getResult() {
        return this.result;
    }
}
