package com.edu.miusched.dao;

import com.edu.miusched.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyDao extends JpaRepository<Faculty ,Long> {

}
