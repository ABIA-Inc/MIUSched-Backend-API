package com.edu.miusched.service.impl;

import com.edu.miusched.dao.FacultyDao;
import com.edu.miusched.domain.Faculty;
import com.edu.miusched.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
