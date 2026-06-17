package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.service.StudentService;
import com.studentmanagementsystem.exception.ExceptionHandler;
import com.studentmanagementsystem.util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final StudentService service = new StudentService();
    private final Scanner sc = new Scanner(System.in);

    public void start() {

        while (true) {
            System.out.println("\n====== STUDENT MANAGEMENT SYSTEM ========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View By Student ID");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort By Student Name");
            System.out.println("7. Sort By Student Marks");
            System.out.println("8. Top Student");
            System.out.println("9. Grade Wise Student Count");
            System.out.println("0. Exit");
            System.out.println("========================================");

            System.out.print("Select the number from the menu : ");
            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number from menu.");
                continue;
            }

            switch (choice) {
                case 1 -> add();
                case 2 -> view(service.getAllStudents());
                case 3 -> viewById();
                case 4 -> update();
                case 5 -> delete();
                case 6 -> view(service.sortByName());
                case 7 -> view(service.sortByMarks());
                case 8 -> view(List.of(service.topStudent()));
                case 9 -> gradeWiseCount();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice! Please select number from menu.");
            }
        }
    }

    // Add Student UI
    private void add() {
        try {
            int id = InputUtil.readInt("ID : ", sc);
            String name = InputUtil.readString("Name : ", sc);
            int marks = InputUtil.readInt("Marks : ", sc);

            service.addStudent(id, name, marks);
            System.out.println("Student successfully Added with id: " + id);

        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    // READ BY ID UI
    private void viewById() {
        try {
            int id = InputUtil.readInt("ID : ", sc);

            Student student = service.getStudentById(id);

            view(List.of(student));

        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    // UPDATE Marks UI
    private void update() {
        try {
            int id = InputUtil.readInt("ID : ", sc);
            int marks = InputUtil.readInt("New Marks : ", sc);

            service.updateMarks(id, marks);

        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    // DELETE Student Data UI
    private void delete() {
        try {
            int id = InputUtil.readInt("ID : ", sc);

            service.deleteStudent(id);
            System.out.println("Student deleted successfully with id: " + id);

        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    private void gradeWiseCount() {

        System.out.println("-------------------------");
        System.out.printf("%-10s | %-10s%n",
                "Grade",
                "Count");
        System.out.println("-------------------------");

        service.getGradeWiseStudentCount()
                .forEach((grade, count) ->
                        System.out.printf(
                                "%-10s | %-10d%n",
                                grade,
                                count
                        ));

        System.out.println("-------------------------");
    }

    // PRINT TABLE
    private void view(List<Student> list) {
        System.out.println("-----------------------------------------------");
        System.out.printf("%-5s | %-20s | %-5s | %-5s%n",
                "ID", "Name", "Marks", "Grade");
        System.out.println("-----------------------------------------------");

        list.forEach(System.out::println);

        System.out.println("-----------------------------------------------");
    }
}