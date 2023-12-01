package ru.nsu.yakupova;


/**
 * Class for complex numbers.
 */
public class ComplexNumber {
    private double real;
    private double imaginary;
    private double mod;
    private double arg;

    /**
     * Constructor for complex numbers.
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        this.mod = Math.sqrt(real * real + imaginary * imaginary);
        this.arg = Math.atan(imaginary);
    }

    /**
     * Method for adding.
     */
    public ComplexNumber add(ComplexNumber x) {
        return new ComplexNumber(real + x.getReal(),
                imaginary + x.getImaginary());
    }

    /**
     * Method for subtracting.
     */
    public ComplexNumber subtract(ComplexNumber x) {
        return new ComplexNumber(real - x.getReal(),
                imaginary - x.getImaginary());
    }

    /**
     * Method for multiplying.
     */
    public ComplexNumber multiply(ComplexNumber x) {
        // (a+bi)(c+di)=(ac-bd) + (ad+bc)i
        double a = real;
        double b = imaginary;
        double c = x.getReal();
        double d = x.getImaginary();

        return new ComplexNumber(a * c - b * d, a * d + b * c);
    }

    /**
     * Method for dividing.
     */
    public ComplexNumber divide(ComplexNumber x) {
        if (imaginary == 0 && real == 0) {
            throw new IllegalArgumentException("Dividing by zero!");
        }
        // (a+bi)/(c+di) = (ac+bd)/(c^2+d^2) + (bc-ad)/(c^2+d^2)i
        double a = real;
        double b = imaginary;
        double c = x.getReal();
        double d = x.getImaginary();

        return new ComplexNumber((a * c + b * d) / (c * c + d * d),
                (a * d + b * c) / (c * c + d * d));
    }

    /**
     * Method for sin.
     */
    public ComplexNumber sin() {
        return new ComplexNumber(Math.sin(real) * Math.cosh(imaginary),
                Math.cos(real) * Math.sinh(imaginary));
    }

    /**
     * Method for cos.
     */
    public ComplexNumber cos() {
        return new ComplexNumber(Math.cos(real) * Math.cosh(imaginary),
                -Math.sin(real) * Math.sinh(imaginary));
    }

    /**
     * Method for sqrt.
     */
    public ComplexNumber sqrt() {
        if (imaginary == 0 && real < 0) {
            throw new IllegalArgumentException("[sqrt] can only contain a non-negative number!");
        }
        // only k = 0 !!!
        double newReal = Math.pow(mod, 0.5) * Math.cos(arg / 2);
        double newImaginary = Math.pow(mod, 0.5) * Math.sin(arg / 2);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Method for log.
     */
    public ComplexNumber log() {
        if (imaginary == 0 && real <= 0) {
            throw new IllegalArgumentException("[log] can only contain a positive number!");
        }
        //ln(z) = ln|z| + i*arg(z)
        return new ComplexNumber(Math.log(mod), arg);
    }

    /**
     * Method for pow.
     */
    public ComplexNumber pow(ComplexNumber x) {
        //z^n=r^n(cos(nf)+isin(nf))
        double n = x.getReal();
        double newReal = Math.pow(mod, n) * Math.cos(n * arg);
        double newImaginary = Math.pow(mod, n) * Math.sin(n * arg);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * ToString for complex numbers.
     */
    @Override
    public String toString() {
        if (imaginary == 0.0) {
            return "" + real;
        }
        if (real == 0.0) {
            return imaginary + "i";
        }
        return real + " + " + imaginary + "i";
    }

    /**
     * Getter for real.
     */
    public double getReal() {
        return this.real;
    }

    /**
     * Setter for real.
     */
    public void setReal(double real) {
        this.real = real;
    }

    /**
     * Getter for imaginary.
     */
    public double getImaginary() {
        return this.imaginary;
    }

    /**
     * Setter for imaginary.
     */
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    /**
     * Getter for mod.
     */
    public double getMod() {
        return this.mod;
    }

    /**
     * Setter for mod.
     */
    public void setMod() {
        this.mod = Math.sqrt(real * real + imaginary * imaginary);
    }

    /**
     * Getter for arg.
     */
    public double getArg() {
        return this.arg;
    }

    /**
     * Setter for arg.
     */
    public void setArg() {
        this.arg = Math.atan(imaginary);
    }
}
