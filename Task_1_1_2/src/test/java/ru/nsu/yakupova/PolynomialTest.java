package ru.nsu.yakupova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PolynomialTest {


    @Test
    void checkEqualsPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{4, 3, 6});
        assertEquals(p1, p2);
        Polynomial p3 = new Polynomial(new int[]{4});
        assertNotEquals(p1, p3);
    }

    @Test
    void checkPlusPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{3, 1});
        Polynomial p3 = new Polynomial(new int[]{7, 4, 6});
        assertEquals(p3, p1.plus(p2));
    }

    @Test
    void checkMinusPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{5, 1});
        Polynomial p3 = new Polynomial(new int[]{-1, 2, 6});
        assertEquals(p3, p1.minus(p2));
    }

    @Test
    void checkTimesPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{3, 2});
        Polynomial p3 = new Polynomial(new int[]{12, 17, 24, 12});
        assertEquals(p3, p1.times(p2));
    }

    @Test
    void checkDiffPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 12, 21});
        Polynomial p3 = new Polynomial(new int[]{12, 42});
        assertEquals(p2, p1.differentiate(1));
        assertEquals(p3, p1.differentiate(2));
    }

    @Test
    void checkEvaluatePolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        int y1 = 34;
        int y2 = 4;
        assertEquals(y1, p1.evaluate(2));
        assertEquals(y2, p1.evaluate(0));
    }

}