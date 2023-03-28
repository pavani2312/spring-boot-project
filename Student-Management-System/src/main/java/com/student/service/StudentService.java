package com.student.service;

import com.student.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student saveStudents(Student student);

    Student getById(Long id);
    Student updateStudent(Student student);

    void deleteStudent(Long id);
}
