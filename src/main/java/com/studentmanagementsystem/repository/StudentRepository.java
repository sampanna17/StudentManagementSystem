package com.studentmanagementsystem.repository;

import com.studentmanagementsystem.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void add(Student student){
        students.add(student);
    }

    public List<Student> getALl(){
        return students;
    }

    public Optional<Student> findById(int student_id){
        return students.stream().filter(s -> s.getId() == student_id).findFirst();
    }

    public void delete(int student_id){
        students.removeIf(s -> s.getId() == student_id
        );
    }

}
