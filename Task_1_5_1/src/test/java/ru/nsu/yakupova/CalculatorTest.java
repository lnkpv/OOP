package ru.nsu.yakupova;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class for Tests (Task_1_5_1).
 */
public class CalculatorTest {
    @Test
    void realAndComplexNumbers() {
        String input = "sin + - 1 2 1";
        assertEquals("0.0", Calculator.calculate(input));

        input = "sin + - 1+0i 2+0i 1+0i";
        assertEquals("0.0", Calculator.calculate(input));
    }

    @Test
    void dividingByZero() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("11 / 0"));

        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("11+5.8i / 0"));
    }

    @Test
    void manyOperators() {
        String input = "log pow * / 2 4 8 2";
        assertEquals("2.772588722239781", Calculator.calculate(input));

        input = "log pow * / 2+0i 4 8 2";
        assertEquals("2.772588722239781", Calculator.calculate(input));

        input = "cos sqrt 3600 ";
        assertEquals("0.5000000000000001", Calculator.calculate(input));

        input = "cos sqrt 3600+0i ";
        assertEquals("0.5000000000000001", Calculator.calculate(input));
    }

    @Test
    void unknownOperator() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("aba + 2 4"));

        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("+ = 2+0i 4"));
    }

    @Test
    void negativeSqrt() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("sqrt -2"));

        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("sqrt -2+0i"));
    }

    @Test
    void negativeLog() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("log -2"));

        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate("log 0+0i"));
    }

    @Test
    void complexNumbers() {
        var x = new ComplexNumber(1, 0);
        assertEquals(0, x.getArg());
        assertEquals(1, x.getMod());
        assertEquals("1.0", x.toString());

        x.setReal(2);
        x.setImaginary(1);
        x.setArg();
        x.setMod();
        assertEquals(0.7853981633974483, x.getArg());
        assertEquals(2.23606797749979, x.getMod());
        assertEquals("2.0 + 1.0i", x.toString());
    }

    @Test
    void degreesTest() {
        String input = "sin 30";
        assertEquals("0.49999999999999994", Calculator.calculate(input));
    }
}