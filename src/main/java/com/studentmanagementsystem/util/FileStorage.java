package com.studentmanagementsystem.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentmanagementsystem.model.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorage {

    private static final String FILE_PATH = "data/students.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    // READ
    public static List<Student> read() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                return new ArrayList<>();
            }

            Student[] students = mapper.readValue(file, Student[].class);
            return new ArrayList<>(Arrays.asList(students));

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // WRITE
    public static void write(List<Student> students) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), students);
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}