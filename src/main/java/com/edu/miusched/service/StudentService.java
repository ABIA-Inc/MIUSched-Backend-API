package com.edu.miusched.service;

import com.edu.miusched.domain.Student;

import java.util.List;

public interface StudentService {
    public void save(Student student);
    public Student getStudentByEmail(String email);
    public Student getStudentByid(Long studentID);
    public List<Student> getAllStudents();
    void deleteStudentyId(Long id);

}
