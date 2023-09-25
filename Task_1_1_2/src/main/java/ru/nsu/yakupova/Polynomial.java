package ru.nsu.yakupova;

import java.util.Objects;

/**
 * This is the class for polynomial (Task 1_1_2).
 */
public class Polynomial {

    private int[] coefficients;

    /**
     * Create a polynomial.
     */
    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    /**
     * Addition of polynomials.
     */
    public Polynomial plus(Polynomial other) {
        int maxDegree = Math.max(coefficients.length, other.coefficients.length);
        int[] result = new int[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            int a = i < coefficients.length ? coefficients[i] : 0;
            int b = i < other.coefficients.length ? other.coefficients[i] : 0;
            result[i] = a + b;
        }
        return new Polynomial(result);
    }

    /**
     * Subtraction of polynomials.
     */
    public Polynomial minus(Polynomial other) {
        int maxDegree = Math.max(coefficients.length, other.coefficients.length);
        int[] result = new int[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            int a = i < coefficients.length ? coefficients[i] : 0;
            int b = i < other.coefficients.length ? other.coefficients[i] : 0;
            result[i] = a - b;
        }
        return new Polynomial(result);
    }

    /**
     * Multiplicity of polynomials.
     */
    public Polynomial times(Polynomial other) {
        int maxLength = coefficients.length + other.coefficients.length - 1;
        int[] result = new int[maxLength];
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                result[i + j] = result[i + j] + coefficients[i] * other.coefficients[j];
            }
        }
        return new Polynomial(result);
    }

    /**
     * Differentiation of polynomials.
     */
    public Polynomial differentiate(int times) {
        int[] result = coefficients;
        for (int t = 0; t < times; t++) {
            int[] newResult = new int[result.length - 1];
            for (int i = 1; i < result.length; i++) {
                newResult[i - 1] = result[i] * i;
            }
            result = newResult;
        }
        return new Polynomial(result);
    }

    /**
     * Finding a value at the point.
     */
    public int evaluate(int x) {
        int result = 0;
        int power = 1;

        for (int i = 0; i <= coefficients.length - 1; i++) {
            result += coefficients[i] * power;
            power *= x;
        }
        return result;
    }

    /**
     * Polynomial equality check.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Polynomial pol = (Polynomial) other;
        if (coefficients.length != pol.coefficients.length) {
            return false;
        }
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != pol.coefficients[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Rewriting hashCode
     */
    @Override
    public int hashCode() {
        int prime = 31;
        return prime * Objects.hash((Object) coefficients);
    }

    /**
     * Method for turning a polynomial into string.
     */
    @Override
    public String toString() {
        String ans = "";

        for (int i = coefficients.length - 1; i >= 0; i--) {
            int coef = coefficients[i];

            if (coef != 0) {
                if (!ans.isEmpty()) {
                    ans = ans + " + ";
                }
                ans = ans + coef;
                if (i > 0) {
                    ans += "x";
                    if (i > 1) {
                        ans += "^" + i;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Main.
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        System.out.println(p1.plus(p2.differentiate(1)).toString());
        System.out.println(p1.times(p2).evaluate(2));
    }
}
