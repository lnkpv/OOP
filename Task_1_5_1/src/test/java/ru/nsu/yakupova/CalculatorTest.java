package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}