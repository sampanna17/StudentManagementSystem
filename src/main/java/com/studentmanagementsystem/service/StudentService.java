package com.studentmanagementsystem.service;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.repository.StudentRepository;
import com.studentmanagementsystem.util.CustomException;

import java.util.Comparator;
import java.util.List;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    // CREATE
    public void addStudent(int id, String name, int marks) {
        if (repo.existsById(id)) {
            throw new CustomException.DuplicateIdException(
                    "Student already exists with id: " + id
            );
        }
        repo.add(new Student(id, name, marks));
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repo.getALl();
    }

    // READ ONE
    public Student getStudentById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // UPDATE
    public void updateMarks(int id, int marks) {
        Student student = getStudentById(id);
        student.setMarks(marks);
    }

    // DELETE
    public void deleteStudent(int id) {

        boolean exists = repo.getALl().stream()
                .anyMatch(s -> s.getStudent_id() == id);

        if (!exists) {
            throw new RuntimeException("Student not found with id: " + id);
        }

        repo.delete(id);
    }

    // SORT BY NAME
    public List<Student> sortByName() {
        return repo.getALl().stream()
                .sorted(Comparator.comparing(Student::getStudent_name))
                .toList();
    }

    // SORT BY MARKS
    public List<Student> sortByMarks() {
        return repo.getALl().stream()
                .sorted(Comparator.comparing(Student::getMarks).reversed())
                .toList();
    }

    // TOP STUDENT
    public Student topStudent() {
        return repo.getALl().stream()
                .max(Comparator.comparing(Student::getMarks))
                .orElseThrow(() -> new RuntimeException("No students found"));
    }
}