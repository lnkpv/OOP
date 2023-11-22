package ru.nsu.yakupova;

/**
 * Class for Subject.
 */
public class Subject {

    public String name;
    public Integer mark;
    public Integer semester;

    /**
     * Constructor for Subject.
     */
    public Subject(
            String name,
            Integer mark,
            Integer semester
    ) {
        this.name = name;
        this.mark = mark;
        this.semester = semester;
    }
}
