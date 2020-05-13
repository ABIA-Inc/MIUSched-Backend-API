package com.edu.miusched.dao;

import com.edu.miusched.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao  extends JpaRepository<Course,Long> {
    Course findByCourseCode(String code);
    @Query(value = "SELECT c1.* FROM course c1 INNER JOIN course c2 ON c1.id = c2.prereq_course_id "
            ,nativeQuery=true)
    public List<Course> findPreRequisiteCourses();
    Course  findCourseById(Long id);



}

