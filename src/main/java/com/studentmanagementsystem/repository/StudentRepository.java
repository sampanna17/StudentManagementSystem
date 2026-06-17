package com.studentmanagementsystem.repository;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.util.FileStorage;

import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = FileStorage.read();

    public void add(Student student){
        students.add(student);
        FileStorage.write(students);
    }

    public List<Student> getALl(){
        return students;
    }

    public Optional<Student> findById(int student_id){
        return students.stream().filter(s -> s.getStudent_id() == student_id).findFirst();
    }

    public boolean delete(int student_id){

        boolean removed = students.removeIf(
                s -> s.getStudent_id() == student_id
        );

        if (removed) {
            FileStorage.write(students);
        }

        return removed;
    }

    public boolean existsById(int id) {
        return students.stream()
                .anyMatch(s -> s.getStudent_id() == id);
    }

}
