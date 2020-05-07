package com.edu.miusched.dao.Auth;

import com.edu.miusched.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface AdminDao extends JpaRepository<Admin, Id> {
    Admin findAdminByAccount_Username(String username);
}
