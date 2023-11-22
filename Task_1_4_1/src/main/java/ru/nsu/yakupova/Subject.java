package ru.nsu.yakupova;

/**
 * Class for Subject.
 */
public class Subject {

    private String name;
    private Integer mark;
    private Integer semester;

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

    /**
     * Setter for name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for mark.
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    /**
     * Getter for mark.
     */
    public Integer getMark() {
        return this.mark;
    }

    /**
     * Setter for semester.
     */
    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    /**
     * Getter for semester.
     */
    public Integer getSemester() {
        return this.semester;
    }
}
