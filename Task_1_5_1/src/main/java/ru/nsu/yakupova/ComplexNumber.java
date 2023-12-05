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
