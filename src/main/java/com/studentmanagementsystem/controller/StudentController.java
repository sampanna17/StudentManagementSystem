package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.exception.GlobalExceptionHandler;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.service.StudentService;
import com.studentmanagementsystem.util.InputUtil;
import com.studentmanagementsystem.view.StudentView;

import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final StudentService service = new StudentService();
    private final StudentView view = new StudentView();
    private final Scanner sc = new Scanner(System.in);

    public void start() {

        while (true) {

            view.showMenu();

            System.out.print("Select the number from the menu : ");

            int choice;

            try {
                choice = Integer.parseInt(
                        sc.nextLine().trim()
                );
            } catch (NumberFormatException e) {
                view.showMessage(
                        "Invalid input! Please enter a number from menu."
                );
                continue;
            }

            switch (choice) {

                case 1 -> add();
                case 2 -> view.displayStudents(service.getAllStudents());
                case 3 -> viewById();
                case 4 -> updateStudent();
                case 5 -> updateMarks();
                case 6 -> delete();
                case 7 -> view.displayStudents(service.sortByName());
                case 8 -> view.displayStudents(service.sortByMarks());
                case 9 -> view.displayStudents(List.of(service.topStudent()));
                case 10 -> gradeWiseCount();
                case 11 -> showTotalPassCount();
                case 0 -> System.exit(0);
                default -> view.showMessage("Invalid choice! Please select number from menu.");
            }
        }
    }

    private void add() {

        try {
            int id = InputUtil.readInt("ID : ", sc);
            String name = InputUtil.readString("Name : ", sc);
            int marks = InputUtil.readInt("Marks : ", sc);

            service.addStudent(id, name, marks);

            view.showMessage("Student successfully added with id: " + id);
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    private void viewById() {
        try {
            int id = InputUtil.readInt("ID : ", sc);
            Student student = service.getStudentById(id);

            view.displayStudents(List.of(student));

        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    private void updateMarks() {
        try {
            int id = InputUtil.readInt("ID : ", sc);
            int marks = InputUtil.readInt("New Marks : ", sc);

            service.updateMarks(id, marks);

            view.showMessage(
                    "Student marks updated successfully."
            );

        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    private void updateStudent() {

        try {
            int oldId = InputUtil.readInt("Current ID : ", sc);
            int newId = InputUtil.readInt("New ID : ", sc);
            String newName = InputUtil.readString("New Name : ", sc);

            service.updateStudent(oldId, newId, newName
            );

            view.showMessage("Student updated successfully.");

        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    private void delete() {

        try {

            int id = InputUtil.readInt("ID : ", sc);

            service.deleteStudent(id);

            view.showMessage("Student deleted successfully with id: " + id);

        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    private void gradeWiseCount() {

        try {
            view.displayGradeWiseCount(service.getGradeWiseStudentCount());

        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    private void showTotalPassCount() {
        try {
            long count = service.getTotalPassStudents();

            view.displayPassCount(count);

        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }
}