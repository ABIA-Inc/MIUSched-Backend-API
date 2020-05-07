package com.edu.miusched.controller.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentLoginController {

    @GetMapping("student/login")
    String login() {
        return "student_login";
    }
}
