package com.studentmanagementsystem.exception;

public class GlobalExceptionHandler {

    public static void handle(Exception e) {

        if (e instanceof StudentManagementException) {
            System.out.println("Error: " + e.getMessage());
        } else {
            System.out.println("Unexpected error occurred.");
        }
    }
}