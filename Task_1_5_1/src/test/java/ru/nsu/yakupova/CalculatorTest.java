package ru.nsu.yakupova;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class for Tests (Task_1_5_1).
 */
public class CalculatorTest {

    @Test
    void checkPlus() {
        String input = "+ 2 1";
        assertEquals("3.0", Calculator.calculate(input));

        input = "+ 2+3i 1+1i";
        assertEquals("3.0+4.0i", Calculator.calculate(input));
    }

    @Test
    void checkMinus() {
        String input = "- 2 1";
        assertEquals("1.0", Calculator.calculate(input));

        input = "- 2+3i 1+1i";
        assertEquals("1.0+2.0i", Calculator.calculate(input));
    }

    @Test
    void checkMultiply() {
        String input = "* 2 1";
        assertEquals("2.0", Calculator.calculate(input));

        input = "* 2+3i 1+1i";
        assertEquals("-1.0+5.0i", Calculator.calculate(input));
    }

    @Test
    void checkDivide() {
        String input = "/ 2 1";
        assertEquals("2.0", Calculator.calculate(input));

        input = "/ 2+3i 1+1i";
        assertEquals("2.5+2.5i", Calculator.calculate(input));

        assertThrows(DividingByZeroException.class, () -> Calculator.calculate("11 / 0"));

        assertThrows(DividingByZeroException.class, () -> Calculator.calculate("11+5.8i / 0"));
    }

    @Test
    void checkSin() {
        String input = "sin 30";
        assertEquals("0.49999999999999994", Calculator.calculate(input));

        input = "sin 2+3i";
        assertEquals("0.03494734710892126+0.05235189463687143i", Calculator.calculate(input));
    }

    @Test
    void checkCos() {
        String input = "cos 60";
        assertEquals("0.5000000000000001", Calculator.calculate(input));

        input = "cos 2+3i";
        assertEquals("0.9989329149319672i", Calculator.calculate(input));
    }

    @Test
    void checkSqrt() {
        String input = "sqrt 4";
        assertEquals("2.0", Calculator.calculate(input));

        input = "sqrt 2+3i";
        assertEquals("1.5404101240518915+1.1102647995782022i", Calculator.calculate(input));

        assertThrows(NegativeArgumentSqrtException.class, () -> Calculator.calculate("sqrt -2"));

        assertThrows(NegativeArgumentSqrtException.class, () -> Calculator.calculate("sqrt -2+0i"));
    }

    @Test
    void checkLog() {
        String input = "log 30";
        assertEquals("3.4011973816621555", Calculator.calculate(input));

        input = "log 2+3i";
        assertEquals("1.2824746787307684+1.2490457723982544i", Calculator.calculate(input));

        assertThrows(NegativeArgumentLogException.class, () -> Calculator.calculate("log -2"));

        assertThrows(NegativeArgumentLogException.class, () -> Calculator.calculate("log 0+0i"));
    }

    @Test
    void checkPow() {
        String input = "pow 4 2";
        assertEquals("16.0", Calculator.calculate(input));

        input = "pow 2+3i 3";
        assertEquals("-65.21803431567068i", Calculator.calculate(input));
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
        assertThrows(UnknownOperatorException.class, () -> Calculator.calculate("aba + 2 4"));

        assertThrows(UnknownOperatorException.class, () -> Calculator.calculate("+ = 2+0i 4"));
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
        assertEquals("2.0+1.0i", x.toString());
    }

}