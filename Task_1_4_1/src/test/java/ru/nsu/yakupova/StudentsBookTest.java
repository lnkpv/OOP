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
        studentsBook.addSem(subj1.semester);
        studentsBook.addSubject(subj1.semester, subj1);

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
        studentsBook.addSem(subj1.semester);
        studentsBook.addSubject(subj1.semester, subj1);
        studentsBook.addQualifyingWork(5);

        assertTrue(studentsBook.canGetMoreMoney());
        assertTrue(studentsBook.canGetRedDiploma());
        assertEquals(studentsBook.getAverageMark(), 5.0);
    }

//    @Test
//    void printAllTest() {
//        Student student = new Student(
//                "Ivanov Ivan Ivanovich"
//        );
//        Subject subj1 = new Subject(
//                "Imperative programming",
//                5,
//                1
//        );
//        Subject subj2 = new Subject(
//                "Declarative programming",
//                4,
//                1
//        );
//        Subject subj3 = new Subject(
//                "Declarative programming",
//                5,
//                2
//        );
//        StudentsBook studentsBook = new StudentsBook(
//                student
//        );
//        studentsBook.addSem(subj1.semester);
//        studentsBook.addSubject(subj1.semester, subj1);
//
//        studentsBook.addSem(subj2.semester);
//        studentsBook.addSubject(subj2.semester, subj2);
//
//        studentsBook.addSem(subj3.semester);
//        studentsBook.addSubject(subj3.semester, subj3);
//
//        String s1 = "Student's Book\n"
//                + "==============\n"
//                + "Name: Ivanov Ivan Ivanovich\n"
//                + "Current Semester: 2\n"
//                + "Qualifying Work: None\n"
//                + "Average Mark: 5,0\n";
//
//        assertEquals(studentsBook.printData(), s1);
//
//        String s2 = "All Marks\n"
//                + "===============\n"
//                + "\tSemester 1\n"
//                + "Imperative programming: 5\n"
//                + "Declarative programming: 4\n"
//                + "\tSemester 2\n"
//                + "Declarative programming: 5\n";
//
//        assertEquals(studentsBook.printAllMarks(), s2);
//
//        String s3 = "Final Marks\n"
//                + "===============\n"
//                + "Imperative programming: 5\n"
//                + "Declarative programming: 5\n";
//
//        assertEquals(studentsBook.printFinalMarks(), s3);
//
//        String s4 = s1
//                + "----------------------------\n"
//                + s2
//                + "----------------------------\n"
//                + s3;
//        assertEquals(studentsBook.printAll(), s4);
//    }
}
