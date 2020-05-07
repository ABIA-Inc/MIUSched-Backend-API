package com.edu.miusched.service;

import com.edu.miusched.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountService  {

    Account getAccById(Long id);
}
