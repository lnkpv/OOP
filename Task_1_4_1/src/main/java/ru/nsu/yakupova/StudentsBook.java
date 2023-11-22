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
        if (!this.finalMarks.containsKey(subject.name)
                || this.finalMarks.get(subject.name).semester < semester) {
            this.finalMarks.put(subject.name, subject);
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
     * Method for adding qualifying work mark.
     */
    public void addQualifyingWork(Integer mark) {
        this.qualifyingWork = mark;
    }

    /**
     * Method for getting average mark.
     */
    public double getAverageMark() {
        var keys = this.finalMarks.keySet();
        Double count = this.finalMarks.size() * 1.0;
        Integer markSum = 0;

        for (String key : keys) {
            var subject = this.finalMarks.get(key);
            markSum += subject.mark;
        }
        return markSum / count;
    }

    /**
     * Method for find possibility to get red diploma.
     */
    public boolean canGetRedDiploma() {
        if (!Objects.equals(this.qualifyingWork, 5)) {
            return false;
        }

        Set<String> keys = this.finalMarks.keySet();
        double count = this.finalMarks.size() * 1.0;
        int markSum = 0;

        for (String key : keys) {
            var subject = this.finalMarks.get(key);
            if (subject.mark == 3) {
                return false;
            }
            if (subject.mark == 5) {
                markSum++;
            }
        }
        return markSum * 100 / count >= 75;
    }

    /**
     * Method for find possibility to get more money.
     */
    public boolean canGetMoreMoney() {
        Set<Integer> keys = this.semesters.keySet();
        List<Subject> currentSubjects = this.semesters.get(Collections.max(keys));

        for (Subject subject : currentSubjects) {
            if (subject.mark <= 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method for print personal data.
     */
    public String printData() {
        return String.format("Student's Book\n" +
                        "==============\n" +
                        "Name: %s\n" +
                        "Current Semester: %d\n" +
                        "Qualifying Work: %s\n" +
                        "Average Mark: %.1f\n",
                this.student.name, Collections.max(this.semesters.keySet()),
                (this.qualifyingWork == 0 ? "None" : this.qualifyingWork),
                getAverageMark());
    }

    /**
     * Method for print final marks.
     */
    public String printFinalMarks() {
        StringBuilder result = new StringBuilder("Final Marks\n" +
                "===============\n");
        for (var key : this.finalMarks.keySet()) {
            result.append(key).append(": ").append(this.finalMarks.get(key).mark).append("\n");
        }
        return result.toString();
    }

    /**
     * Method for print all marks.
     */
    public String printAllMarks() {
        StringBuilder result = new StringBuilder("All Marks\n" +
                "===============\n");
        for (var key : this.semesters.keySet()) {
            result.append("\tSemester ").append(key).append("\n");
            for (var subj : this.semesters.get(key)) {
                result.append(subj.name).append(": ").append(subj.mark).append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Method for print all data.
     */
    public String printAll() {
        return printData()
                + "----------------------------\n"
                + printAllMarks()
                + "----------------------------\n"
                + printFinalMarks();
    }

}
