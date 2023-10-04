package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TreeTest {

    @Test
    void checkMain() {
        Tree.main(new String[]{});
        assertTrue(true);
    }

}