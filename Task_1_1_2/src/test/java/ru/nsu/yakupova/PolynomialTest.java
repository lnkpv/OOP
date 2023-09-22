package ru.nsu.yakupova;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PolynomialTest {

    @Test
    void checkMain() {
        Polynomial.main(new String[]{});
        assertTrue(true);
    }

    @Test
    void checkEquals() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{4, 3, 6});
        assertTrue(p1.equals(p2));
        Polynomial p3 = new Polynomial(new int[]{4});
        assertFalse(p1.equals(p3));
    }

    @Test
    void checkPlus() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{3, 1});
        Polynomial p3 = new Polynomial(new int[]{7, 4, 6});
        assertTrue(p3.equals(p1.plus(p2)));
    }

    @Test
    void checkMinus() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{5, 1});
        Polynomial p3 = new Polynomial(new int[]{-1, 2, 6});
        assertTrue(p3.equals(p1.minus(p2)));
    }

    @Test
    void checkTimes() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        Polynomial p2 = new Polynomial(new int[]{3, 2});
        Polynomial p3 = new Polynomial(new int[]{12, 17, 24, 12});
        assertTrue(p3.equals(p1.times(p2)));
    }

    @Test
    void checkDiff() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 12, 21});
        Polynomial p3 = new Polynomial(new int[]{12, 42});
        assertTrue(p2.equals(p1.differentiate(1)));
        assertTrue(p3.equals(p1.differentiate(2)));
    }

    @Test
    void checkEvaluate() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6});
        int y1 = 34;
        int y2 = 4;
        assertEquals(y1, p1.evaluate(2));
        assertEquals(y2, p1.evaluate(0));
    }

}