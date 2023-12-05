package ru.nsu.yakupova;

/**
 * Interface for basic math.
 */
public interface Operation {

    /**
     * Method for finding result.
     */
    abstract ComplexNumber findResult();

    /**
     * Method for getting result.
     */
    abstract ComplexNumber getResult();
}