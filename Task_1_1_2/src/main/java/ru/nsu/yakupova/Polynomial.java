package ru.nsu.yakupova;

/**
 * This is the class for polynomial (Task 1_1_2).
 */
public class Polynomial {

    private int[] coefficients;


    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients;
    }


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

    public int evaluate(int x) {
        int result = 0;
        int power = 1;

        for (int i = 0; i <= coefficients.length - 1; i++) {
            result += coefficients[i] * power;
            power *= x;
        }
        return result;
    }

    public boolean equals(Polynomial other) {
        if (coefficients.length != other.coefficients.length) {
            return false;
        }
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != other.coefficients[i]) {
                return false;
            }
        }
        return true;
    }

    public String to_string() {
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

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        System.out.println(p1.plus(p2.differentiate(1)).to_string());
        System.out.println(p1.times(p2).evaluate(2));
    }
}
