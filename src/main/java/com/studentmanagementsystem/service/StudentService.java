package com.studentmanagementsystem.service;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.repository.StudentRepository;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    // Add Student Logic
    public void addStudent(int student_id, String student_name, int marks){
        repo.add(new Student(student_id,student_name,marks));
    }

    // Get All Student Logic
    public List<Student> getAllStudents(){
        return repo.getALl();
    }

    // Update Marks Logic
    public void updateMarks(int student_id, int marks){
        Optional<Student> student = repo.findById(student_id);
        student.ifPresent(s -> s.setMarks(marks));
    }

    // Delete Student Logic
    public void deleteStudent(int student_id){
        repo.delete(student_id);
    }

    public List<Student> sortByName(){
        return repo.getALl().stream()
                .sorted(Comparator.comparing(Student::getName))
                .toList();
    }

    public Student topStudent(){
        return repo.getALl().stream()
                .max(Comparator.comparing(Student::getMarks))
                .orElse(null);
    }

}

