package com.edu.miusched.service;

import com.edu.miusched.domain.Student;

import java.util.List;

public interface StudentService {
    public void save(Student student);
    public Student getStudentByEmail(String email);
    public Student getStudentById(Long studentID);
    public List<Student> getAllStudents();
    public void deleteStudentById(Long id);
    public void deleteStudentByStudentId(String studentId);
}
