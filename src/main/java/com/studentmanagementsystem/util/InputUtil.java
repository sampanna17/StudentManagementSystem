package com.studentmanagementsystem.util;

import com.studentmanagementsystem.exception.EmptyFieldException;
import com.studentmanagementsystem.exception.InvalidInputException;

import java.util.Scanner;

public class InputUtil {

    public static String readString(String label, Scanner sc) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    public static int readInt(String label, Scanner sc) {

        System.out.print(label);

        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            throw new EmptyFieldException("Field cannot be empty");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid number");
        }
    }
}