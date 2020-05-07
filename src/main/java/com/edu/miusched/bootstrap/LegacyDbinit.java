package com.edu.miusched.bootstrap;

import com.edu.miusched.dao.AccountDao;
import com.edu.miusched.dao.AddressDao;
import com.edu.miusched.dao.Auth.AdminDao;
import com.edu.miusched.domain.Account;
import com.edu.miusched.domain.Address;
import com.edu.miusched.domain.Admin;
import com.edu.miusched.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

public class LegacyDbinit implements CommandLineRunner {

    AdminDao adminDao;
    AccountDao accountDao;
    AddressDao addressDao;

    public LegacyDbinit(AdminDao adminDao, AccountDao accountDao, AddressDao addressDao) {
        this.adminDao = adminDao;
        this.accountDao = accountDao;
        this.addressDao = addressDao;
    }

    @Override
    public void run(String... args) throws Exception {

    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        Address add1 = new Address();
//        add1.setCity("flor");
//        add1.setCountry("Ethiopia");
//        add1.setState("South Nation");
//        add1.setTelephone("09120232");
//        add1.setZipcode(232);
//        addressDao.save(add1);
//        Account account1 = new Account();
//        account1.setUsername("isam123");
//        account1.setPassword("24coding");
//        account1.setRole(Role.ADMIN);
//        accountDao.save(account1);
//        Admin isam = new Admin(new Address(),account1);
//        adminDao.save(isam);
//
//
//    }
}
