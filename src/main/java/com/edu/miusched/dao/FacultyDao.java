package com.edu.miusched.dao;

import com.edu.miusched.domain.Course;
import com.edu.miusched.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository

public interface FacultyDao extends JpaRepository<Faculty ,Long> {

         // to mark delete or update query
    @Transactional
    @Modifying
    @Query(
            value = "DELETE  FROM faculty_block_preference u WHERE u.block_preference_blockid =:id ",
            nativeQuery = true)
    void deleteBlockByID(@Param("id")Long id);


    @Transactional
    @Modifying
    @Query(
            value = "DELETE  FROM faculty_course_preference u WHERE u.course_preference_id =:id AND u.faculty_id=1",
            nativeQuery = true)
    void deleteCourseById(@Param("id") Long id);
//    "insert into Logger (redirect,user_id) VALUES (:insertLink,:id)"
    @Transactional
    @Modifying
    @Query(
            value = "INSERT  INTO faculty_course_preference VALUES (1,:courseid)",
            nativeQuery = true)
    void addCourse(@Param("courseid") Long id);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT  INTO faculty_block_preference VALUES (1,:blockid)",
            nativeQuery = true)
    void addBlock(@Param("blockid") Long id);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE Faculty  set interval_between_blocks = :intr,start_date=:start WHERE id = :facid",
            nativeQuery = true)
    void UpdateFaculty(@Param("facid") Long id,
                @Param("intr") Integer val,
                @Param("start")LocalDate date);


    @Transactional
    @Query(
            value = "SELECT * FROM faculty_course_preference WHERE faculty_id = 1 AND course_preference_id = :courid ",
            nativeQuery = true)
    Integer getOneCourse(@Param("courid") Long id
                       );
}

//"update Customer c set c.name = :name WHERE c.id = :customerId"
