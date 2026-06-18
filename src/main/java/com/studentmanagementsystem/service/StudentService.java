package com.studentmanagementsystem.service;

import com.studentmanagementsystem.exception.DuplicateStudentException;
import com.studentmanagementsystem.exception.StudentNotFoundException;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.repository.StudentRepository;
import com.studentmanagementsystem.util.ValidationUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    // CREATE
    public void addStudent(int id, String name, int marks) {

        Set<Integer> ids =
                repo.getALl()
                        .stream()
                        .map(Student::getStudent_id)
                        .collect(Collectors.toSet());

        if (ids.contains(id)) {
            throw new DuplicateStudentException(
                    "Student already exists with id: " + id
            );
        }

        ValidationUtil.validateText(name);
        ValidationUtil.validateMarks(marks);

        repo.add(new Student(id, name, marks));
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repo.getALl();
    }

    // READ ONE
    public Student getStudentById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    // UPDATE
    public void updateMarks(int id, int marks) {
        Student student = getStudentById(id);
        student.setMarks(marks);
        repo.saveChanges();
    }

    // DELETE
    public void deleteStudent(int id) {

        boolean exists = repo.getALl().stream()
                .anyMatch(s -> s.getStudent_id() == id);

        if (!exists) {
            throw new StudentNotFoundException("Student not found with id: " + id);
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
                .orElseThrow(() -> new StudentNotFoundException("No students found"));
    }

    //Count Students by Grade
    public Map<String, Long> getGradeWiseStudentCount() {

        return repo.getALl()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Student::getGrade,
                                Collectors.counting()
                        )
                );
    }

    //Update Student Details
    public void updateStudent(int oldId, int newId, String newName) {

        Map<Integer, Student> studentMap = repo.getALl()
                .stream()
                .collect(Collectors.toMap(
                        Student::getStudent_id,
                        student -> student
                ));

        Student student = studentMap.get(oldId);

        if (student == null) {
            throw new StudentNotFoundException("Student not found with id: " + oldId);
        }

        if (oldId != newId && studentMap.containsKey(newId)) {
            throw new DuplicateStudentException("Student already exists with id: " + newId);
        }

        student.setStudent_id(newId);
        student.setStudent_name(newName);

        repo.saveChanges();
    }

    //Get Total Students
    public long getTotalPassStudents() {
        return repo.getALl()
                .stream()
                .filter(s -> s.getMarks() >= 40)
                .count();
    }

}