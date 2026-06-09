package com.studentmanagementsystem.util;

public class CustomException {
    public static class EmptyFieldException extends RuntimeException {
        public EmptyFieldException(String message) {
            super(message);
        }
    }

    public static class InvalidNumberException extends RuntimeException {
        public InvalidNumberException(String message) {
            super(message);
        }
    }

    public static class InvalidTextException extends RuntimeException {
        public InvalidTextException(String message) {
            super(message);
        }
    }
}
