package com.edu.miusched.service.impl;

import com.edu.miusched.dao.StudentDao;
import com.edu.miusched.domain.Student;
import com.edu.miusched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentDao.findStudentByEmail(email);
    }

    @Override
    public Student getStudentById(Long studentID) {
        return studentDao.findStudentById(studentID);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public void deleteStudentById(Long id) { studentDao.deleteById(id);  }

    @Override
    public void deleteStudentByStudentId(String studentId) {
        studentDao.deleteStudentByStudentId(studentId);
    }
}
