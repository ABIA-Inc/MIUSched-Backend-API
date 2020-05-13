package com.edu.miusched.service.impl;

import com.edu.miusched.dao.CourseDao;
import com.edu.miusched.domain.Course;
import com.edu.miusched.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findAllPrerequsite() {
        return courseDao.findPreRequisiteCourses();
    }

    @Override
    public Course save(Course coursde) {
        return courseDao.save(coursde);
    }

    @Override
    public Course findCoursebyId(Long id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteById(id);

    }

    @Override
    public Course findCourseByCode(String code) {
        return null;
    }
}
