package com.edu.miusched.service;

import com.edu.miusched.domain.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    List<Course> findAllPrerequsite();
    Course save(Course coursde);
    Course findCoursebyId(Long id);
    void deleteCourse(Long id);
    Course findCourseByCode(String code);
}

