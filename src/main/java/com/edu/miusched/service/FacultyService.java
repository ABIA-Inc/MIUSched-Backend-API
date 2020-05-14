package com.edu.miusched.service;

import com.edu.miusched.domain.Course;
import com.edu.miusched.domain.Faculty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface FacultyService  {

    Faculty getById(Long id);
    List<Faculty> getAllFaculty();
    void deleteFacultyById(long id);
    void SaveFaculty(Faculty faculty);
    void deleteBlockById(Long id);
    void deleteCourseById(Long id);
    void addCourse(Long id);
    void addBlock(Long id);
    void UpdateFaculty(Long id , Integer i, LocalDate date);
    Integer getOneCourse(Long id);
}
