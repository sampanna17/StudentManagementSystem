package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.service.StudentService;

import java.util.Scanner;

public class StudentController {

    private final StudentService service = new StudentService();
    private final Scanner sc = new  Scanner(System.in);

    public void start(){

        while (true){
            System.out.println("\n====== STUDENT MANAGEMENT SYSTEM ========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Sort By Student Name");
            System.out.println("6. Sort By Student Marks");
            System.out.println("7. Top Student");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> add();
                case 2 -> view(service.getAllStudents());
                case 3 -> update();
                case 4 -> delete();
                case 5 -> view(service.sortByName());
                case 6 -> view(service.sortByMarks());
                case 7 -> System.out.println(service.topStudent());
                case 0 -> System.exit(0);
            }
        }
    }

    private void add(){
        System.out.print("ID : ");
        int student_id =  Integer.parseInt(sc.nextLine().trim());
        System.out.print("Name : ");
        String student_name = sc.nextLine();
        System.out.print("Marks : ");
        int marks = Integer.parseInt(sc.nextLine().trim());

        service.addStudent(student_id, student_name, marks);
    }

    private void update(){
        System.out.print("ID : ");
        int student_id =  sc.nextInt();
        System.out.print("New Marks : ");
        int marks = sc.nextInt();

        service.updateMarks(student_id, marks);
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
