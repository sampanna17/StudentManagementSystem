package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.service.StudentService;
import com.studentmanagementsystem.util.ExceptionHandler;
import com.studentmanagementsystem.util.InputUtil;

import java.util.Scanner;

public class StudentController {

    private final StudentService service = new StudentService();
    private final Scanner sc = new  Scanner(System.in);

    public void start(){

        while (true){
            System.out.println("\n====== STUDENT MANAGEMENT SYSTEM ========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View By Student ID");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort By Student Name");
            System.out.println("7. Sort By Student Marks");
            System.out.println("8. Top Student");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> add();
                case 2 -> view(service.getAllStudents());
                case 3 -> viewById();
                case 4 -> update();
                case 5 -> delete();
                case 6 -> view(service.sortByName());
                case 7 -> view(service.sortByMarks());
                case 8 -> System.out.println(service.topStudent());
                case 0 -> System.exit(0);
            }
        }
    }

    private void add(){
        try{
            int student_id = InputUtil.readInt("ID : ", sc);
            String student_name = InputUtil.readString("Name : ", sc);
            int marks = InputUtil.readInt("Marks : ", sc);

            service.addStudent(student_id, student_name, marks);
        } catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }

    private void update(){
        System.out.print("ID : ");
        int student_id =  sc.nextInt();
        System.out.print("New Marks : ");
        int marks = sc.nextInt();

        service.updateMarks(student_id, marks);
    }

    private void viewById(){
        System.out.print("ID : ");
        int student_id = Integer.parseInt(sc.nextLine().trim());

        service.getStudentById(student_id)
                .ifPresentOrElse(student -> view(java.util.List.of(student)),
                        () -> System.out.println("Student Not found")
                );
    }

    private void delete(){
        System.out.print("ID : ");
        int student_id = sc.nextInt();

        service.deleteStudent(student_id);
    }

    private void view(java.util.List<?> list) {
        list.forEach(System.out::println);
    }

}
