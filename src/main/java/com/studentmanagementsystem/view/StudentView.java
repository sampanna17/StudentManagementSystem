package com.studentmanagementsystem.view;

import com.studentmanagementsystem.model.Student;

import java.util.List;
import java.util.Map;

public class StudentView {

    public void showMenu() {

        System.out.println("\n====== STUDENT MANAGEMENT SYSTEM ========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. View By Student ID");
        System.out.println("4. Update Student Name & ID");
        System.out.println("5. Update Marks");
        System.out.println("6. Delete Student");
        System.out.println("7. Sort By Student Name");
        System.out.println("8. Sort By Student Marks");
        System.out.println("9. Top Student");
        System.out.println("10. Grade Wise Student Count");
        System.out.println("11. Total Pass Student Count");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void displayStudents(List<Student> students) {

        System.out.println("-----------------------------------------------");
        System.out.printf(
                "%-5s | %-20s | %-5s | %-5s%n",
                "ID",
                "Name",
                "Marks",
                "Grade"
        );
        System.out.println("-----------------------------------------------");

        students.forEach(System.out::println);

        System.out.println("-----------------------------------------------");
    }

    public void displayGradeWiseCount(Map<String, Long> gradeCounts) {

        System.out.println("-------------------------");
        System.out.printf(
                "%-10s | %-10s%n",
                "Grade",
                "Count"
        );
        System.out.println("-------------------------");

        gradeCounts.forEach(
                (grade, count) ->
                        System.out.printf(
                                "%-10s | %-10d%n",
                                grade,
                                count
                        )
        );

        System.out.println("-------------------------");
    }

    public void displayPassCount(long count) {
        System.out.println(
                "Total Passed Students : " + count
        );
    }
}