package com.edu.miusched.service.impl;

import com.edu.miusched.dao.AccountDao;
import com.edu.miusched.domain.Account;
import com.edu.miusched.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountImp implements AccountService {


    @Autowired
    AccountDao accountDao;

    @Override
    public Account getAccById(Long id) {
        return accountDao.getOne(id);
    }
}
