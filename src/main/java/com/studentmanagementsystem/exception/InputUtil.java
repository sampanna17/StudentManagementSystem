package com.studentmanagementsystem.exception;

import java.util.Scanner;

public class InputUtil {

    public static int readInt(String label, Scanner sc) {
        System.out.print(label);
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            throw new CustomException.EmptyFieldException("Please enter the value in the field");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CustomException.InvalidNumberException("Please input valid number");
        }
    }

    public static String readString(String label, Scanner sc) {
        System.out.print(label);
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            throw new CustomException.EmptyFieldException("Please enter the value in the field");
        }

        if (!input.matches("[a-zA-Z ]+")) {
            throw new CustomException.InvalidTextException("Please input valid text");
        }

        return input;
    }
}