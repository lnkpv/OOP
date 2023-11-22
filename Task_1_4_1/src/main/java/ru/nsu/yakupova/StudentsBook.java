package ru.nsu.yakupova;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class for Student's Book (Task_1_4_1).
 */
public class StudentsBook {
    private final Student student;
    private final Map<Integer, List<Subject>> semesters;
    private Integer qualifyingWork;
    private final Map<String, Subject> finalMarks;

    /**
     * Constructor for Student's Book.
     */
    public StudentsBook(Student student) {
        this.student = student;
        this.semesters = new HashMap<>();
        this.qualifyingWork = 0;
        this.finalMarks = new HashMap<>();
    }

    /**
     * Method for adding subjects.
     */
    public void addSubject(Integer semester, Subject subject) {
        if (!this.finalMarks.containsKey(subject.getName())
                || this.finalMarks.get(subject.getName()).getSemester() < semester) {
            this.finalMarks.put(subject.getName(), subject);
        }
        this.semesters.get(semester).add(subject);
    }

    /**
     * Method for adding semesters.
     */
    public void addSem(Integer semesterNumber) {
        if (!this.semesters.containsKey(semesterNumber)) {
            this.semesters.put(semesterNumber, new ArrayList<>());
        }
    }

    /**
     * Method for setting qualifying work mark.
     */
    public void setQualifyingWork(Integer mark) {
        this.qualifyingWork = mark;
    }

    /**
     * Method for getting average mark.
     */
    public double getAverageMark() {
        var keys = this.finalMarks.keySet();
        double count = this.finalMarks.size() * 1.0;
        var mmap = this.finalMarks.entrySet().stream();
        var markSum = mmap.mapToInt(x -> x.getValue().getMark()).sum();
        return markSum / count;
    }

    /**
     * Method for find possibility to get red diploma.
     */
    public boolean canGetRedDiploma() {
        if (!Objects.equals(this.qualifyingWork, 5)) {
            return false;
        }
        double count = this.finalMarks.size() * 1.0;

        var rule1 = this.finalMarks.entrySet().stream();
        if (rule1.mapToInt(x -> x.getValue().getMark()).anyMatch(x -> x == 3)) {
            return false;
        }

        var rule2 = this.finalMarks.entrySet().stream();
        var markSum = rule2.mapToInt(x -> x.getValue().getMark()).filter(x -> x == 5).count();

        return markSum * 100 / count >= 75;
    }

    /**
     * Method for find possibility to get more money.
     */
    public boolean canGetMoreMoney() {
        Set<Integer> keys = this.semesters.keySet();
        List<Subject> currentSubjects = this.semesters.get(Collections.max(keys));

        var list = currentSubjects.stream();
        return list.mapToInt(Subject::getMark).noneMatch(x -> x <= 3);
    }

    /**
     * Method for print personal data.
     */
    public String printData() {
        String qualify = "";
        if (this.qualifyingWork == 0) {
            qualify = "None";
        } else {
            qualify = this.qualifyingWork.toString();
        }
        return "Student's Book\n"
                + "==============\n"
                + "Name: "
                + this.student.name
                + "\nCurrent Semester: "
                + Collections.max(this.semesters.keySet())
                + "\nQualifying Work: "
                + qualify
                + "\nAverage Mark: "
                + getAverageMark()
                + "\n";
    }

    /**
     * Method for print final marks.
     */
    public String printFinalMarks() {
        StringBuilder result = new StringBuilder("Final Marks\n"
                + "===============\n");
        for (var key : this.finalMarks.keySet()) {
            result.append(key).append(": ").append(this.finalMarks.get(key).getMark()).append("\n");
        }
        return result.toString();
    }

    /**
     * Method for print all marks.
     */
    public String printAllMarks() {
        StringBuilder result = new StringBuilder("All Marks\n"
                + "===============\n");
        for (var key : this.semesters.keySet()) {
            result.append("\tSemester ").append(key).append("\n");
            for (var subj : this.semesters.get(key)) {
                result.append(subj.getName()).append(": ").append(subj.getMark()).append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Method for print all data.
     */
    public String printAll() {
        var data = printData();
        var allMarks = printAllMarks();
        var finalMarks = printFinalMarks();
        return data
                + "----------------------------\n"
                + allMarks
                + "----------------------------\n"
                + finalMarks;
    }

}
