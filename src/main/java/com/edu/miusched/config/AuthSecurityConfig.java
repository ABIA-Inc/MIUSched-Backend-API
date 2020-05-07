package com.edu.miusched.config;

import com.edu.miusched.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    AccountDao accountDao;
//
//    String username = accountDao.getOne(1l).getRole().toString();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .authorities("ROLE_ADMIN")
//                .and().withUser("user").password(passwordEncoder()
//                .encode("user"))
//                .authorities("ROlE_USER");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().permitAll();

    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
