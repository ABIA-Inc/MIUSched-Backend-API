package com.edu.miusched.dao;


import com.edu.miusched.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao  extends JpaRepository<Student, Long> {

    public Student findStudentById(Long id);
    public Student findStudentByEmail(String email);

}
