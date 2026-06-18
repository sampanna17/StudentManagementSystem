package com.studentmanagementsystem.util;

import com.studentmanagementsystem.exception.EmptyFieldException;
import com.studentmanagementsystem.exception.InvalidInputException;
import com.studentmanagementsystem.exception.StudentManagementException;

public class ValidationUtil {

    public static void validateText(String text) {

        if (text.isEmpty()) {
            throw new EmptyFieldException("Field cannot be empty");
        }

        if (!text.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException("Only letters are allowed on text");
        }
    }

    public static void validateMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new StudentManagementException(
                    "Marks must be between 0 and 100."
            );
        }
    }
}