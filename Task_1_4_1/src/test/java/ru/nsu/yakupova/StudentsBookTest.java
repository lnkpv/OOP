package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for Tests (Task_1_4_1).
 */
public class StudentsBookTest {

    @Test
    void createStudentsBook() {
        Student student = new Student(
                "Ivanov Ivan Ivanovich"
        );
        Subject subj1 = new Subject(
                "Imperative programming",
                5,
                1
        );
        StudentsBook studentsBook = new StudentsBook(
                student
        );
        studentsBook.addSem(subj1.getSemester());
        studentsBook.addSubject(subj1.getSemester(), subj1);

        assertTrue(studentsBook.canGetMoreMoney());
        assertFalse(studentsBook.canGetRedDiploma());
        assertEquals(studentsBook.getAverageMark(), 5.0);
    }

    @Test
    void addingQualifyWork() {
        Student student = new Student(
                "Ivanov Ivan Ivanovich"
        );
        Subject subj1 = new Subject(
                "Imperative programming",
                5,
                1
        );
        StudentsBook studentsBook = new StudentsBook(
                student
        );
        studentsBook.addSem(subj1.getSemester());
        studentsBook.addSubject(subj1.getSemester(), subj1);
        studentsBook.setQualifyingWork(5);

        assertTrue(studentsBook.canGetMoreMoney());
        assertTrue(studentsBook.canGetRedDiploma());
        assertEquals(studentsBook.getAverageMark(), 5.0);
    }

    @Test
    void printAllTest() {
        Student student = new Student(
                "Ivanov Ivan Ivanovich"
        );
        Subject subj1 = new Subject(
                "Imperative programming",
                5,
                1
        );
        Subject subj2 = new Subject(
                "Declarative programming",
                4,
                1
        );
        Subject subj3 = new Subject(
                "Declarative programming",
                5,
                2
        );
        StudentsBook studentsBook = new StudentsBook(
                student
        );
        studentsBook.addSem(subj1.getSemester());
        studentsBook.addSubject(subj1.getSemester(), subj1);

        studentsBook.addSem(subj2.getSemester());
        studentsBook.addSubject(subj2.getSemester(), subj2);

        studentsBook.addSem(subj3.getSemester());
        studentsBook.addSubject(subj3.getSemester(), subj3);

        String s1 = "Student's Book\n"
                + "==============\n"
                + "Name: Ivanov Ivan Ivanovich\n"
                + "Current Semester: 2\n"
                + "Qualifying Work: None\n"
                + "Average Mark: 5.0\n"
                + "----------------------------\n"
                + "All Marks\n"
                + "===============\n"
                + "\tSemester 1\n"
                + "Imperative programming: 5\n"
                + "Declarative programming: 4\n"
                + "\tSemester 2\n"
                + "Declarative programming: 5\n"
                + "----------------------------\n"
                + "Final Marks\n"
                + "===============\n"
                + "Imperative programming: 5\n"
                + "Declarative programming: 5\n";


        assertEquals(studentsBook.printAll(), s1);
    }

    @Test
    void redDiplomaQualifyingWork() {
        Student student = new Student(
                "Ivanov Ivan Ivanovich"
        );
        Subject subj1 = new Subject(
                "Imperative programming",
                5,
                1
        );
        Subject subj2 = new Subject(
                "Declarative programming",
                4,
                1
        );
        Subject subj3 = new Subject(
                "Declarative programming",
                5,
                2
        );
        StudentsBook studentsBook = new StudentsBook(
                student
        );
        studentsBook.addSem(subj1.getSemester());
        studentsBook.addSubject(subj1.getSemester(), subj1);

        studentsBook.addSem(subj2.getSemester());
        studentsBook.addSubject(subj2.getSemester(), subj2);

        studentsBook.addSem(subj3.getSemester());
        studentsBook.addSubject(subj3.getSemester(), subj3);


        assertFalse(studentsBook.canGetRedDiploma());  //None

        studentsBook.setQualifyingWork(2);
        assertFalse(studentsBook.canGetRedDiploma());  //2

        studentsBook.setQualifyingWork(3);
        assertFalse(studentsBook.canGetRedDiploma()); //3

        studentsBook.setQualifyingWork(4);
        assertFalse(studentsBook.canGetRedDiploma()); //4

        studentsBook.setQualifyingWork(5);
        assertTrue(studentsBook.canGetRedDiploma());
    }

    @Test
    void redDiplomaSeventyFivePercents() {
        Student student = new Student(
                "Ivanov Ivan Ivanovich"
        );
        Subject subj1 = new Subject(
                "Imperative programming",
                5,
                1
        );
        Subject subj2 = new Subject(
                "Declarative programming",
                4,
                1
        );
        Subject subj3 = new Subject(
                "Declarative programming",
                5,
                2
        );
        Subject subj4 = new Subject(
                "Physical Education",
                4,
                3
        );

        StudentsBook studentsBook = new StudentsBook(
                student
        );
        studentsBook.addSem(subj1.getSemester());
        studentsBook.addSubject(subj1.getSemester(), subj1);

        studentsBook.addSem(subj2.getSemester());
        studentsBook.addSubject(subj2.getSemester(), subj2);

        studentsBook.addSem(subj3.getSemester());
        studentsBook.addSubject(subj3.getSemester(), subj3);

        studentsBook.addSem(subj4.getSemester());
        studentsBook.addSubject(subj4.getSemester(), subj4);

        studentsBook.setQualifyingWork(5);

        assertFalse(studentsBook.canGetRedDiploma());

        Subject subj5 = new Subject(
                "Math",
                5,
                4
        );
        studentsBook.addSem(subj5.getSemester());
        studentsBook.addSubject(subj5.getSemester(), subj5);

        assertTrue(studentsBook.canGetRedDiploma());
    }

    @Test
    void redDiplomaBadMark() {
        Student student = new Student(
                "Ivanov Ivan Ivanovich"
        );
        Subject subj1 = new Subject(
                "Imperative programming",
                5,
                1
        );
        Subject subj2 = new Subject(
                "Declarative programming",
                4,
                1
        );
        Subject subj3 = new Subject(
                "Declarative programming",
                5,
                2
        );
        Subject subj4 = new Subject(
                "Physical Education",
                3,
                3
        );

        StudentsBook studentsBook = new StudentsBook(
                student
        );
        studentsBook.addSem(subj1.getSemester());
        studentsBook.addSubject(subj1.getSemester(), subj1);

        studentsBook.addSem(subj2.getSemester());
        studentsBook.addSubject(subj2.getSemester(), subj2);

        studentsBook.addSem(subj3.getSemester());
        studentsBook.addSubject(subj3.getSemester(), subj3);

        studentsBook.addSem(subj4.getSemester());
        studentsBook.addSubject(subj4.getSemester(), subj4);

        studentsBook.setQualifyingWork(5);

        assertFalse(studentsBook.canGetRedDiploma());

        subj4.setMark(5);
        assertTrue(studentsBook.canGetRedDiploma());
    }
}
