package com.studentmanagementsystem.model;

public class Student {
    private int student_id;
    private String student_name;
    private int marks;
    private String grade;

    public Student() {
    }

    public Student(int student_id, String student_name, int marks){
        this.student_id = student_id;
        this.student_name = student_name;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    private String calculateGrade(int marks){
        if (marks >= 80 ) return "A";
        else if (marks >= 60 ) return "B";
        else if (marks >= 50 ) return "C";
        else if (marks >=40) return "D";
        else return "F";
    }

    public int getStudent_id() {return student_id; }
    public String getStudent_name() {return student_name;}
    public int getMarks() {return marks;}
    public String getGrade() {return grade;}

    public void setMarks(int marks){
        this.marks =  marks;
        this.grade = calculateGrade(marks);
    }

    @Override
    public String toString(){
        return String.format(
                "%-5d | %-20s | %-5d | %-2s",
                student_id,
                student_name,
                marks,
                grade
        );
    }

}
