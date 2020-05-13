package com.edu.miusched.service;

import com.edu.miusched.domain.Faculty;

import java.util.List;

public interface FacultyService  {

    Faculty getById(Long id);
    List<Faculty> getAllFaculty();
    void deleteFacultyById(long id);
}
