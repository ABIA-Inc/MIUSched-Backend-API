package com.edu.miusched.service.impl;

import com.edu.miusched.dao.FacultyDao;
import com.edu.miusched.domain.Course;
import com.edu.miusched.domain.Faculty;
import com.edu.miusched.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyDao facultyDao;

    @Override
    public Faculty getById(Long id) {
        return facultyDao.getOne(id);
    }
    @Override
    public List<Faculty> getAllFaculty() {
        return facultyDao.findAll();
    }
    @Override
    public void deleteFacultyById(long id) {

        facultyDao.deleteById(id);
    }

    @Override
    public void SaveFaculty(Faculty faculty) {

        facultyDao.save(faculty);
    }

    @Override
    public void deleteBlockById(Long id) {
        facultyDao.deleteBlockByID(id);
    }

    @Override
    public void deleteCourseById(Long id) {
        facultyDao.deleteCourseById(id);
    }

    @Override
    public void addCourse(Long id) {
        facultyDao.addCourse(id);
    }

    @Override
    public void addBlock(Long id) {
        facultyDao.addBlock(id);
    }

    @Override
    public void UpdateFaculty(Long id, Integer i, LocalDate date) {
        facultyDao.UpdateFaculty(id,i,date);
    }

    @Override
    public Integer getOneCourse(Long id) {
       return facultyDao.getOneCourse(id);
    }


}
