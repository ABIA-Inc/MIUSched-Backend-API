package com.edu.miusched.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//@Configuration
//@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.
//                     inMemoryAuthentication()
//                      .withUser("admin")
//                      .password("admin123")
//                      .roles("Admin")
//                 .and().withUser("user").password("user").roles("User");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/index.html").permitAll();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }
}
