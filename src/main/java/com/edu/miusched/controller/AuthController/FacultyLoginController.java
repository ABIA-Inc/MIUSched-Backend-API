package com.edu.miusched.controller.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyLoginController {


    @GetMapping("/faculty/index")
    public String index() {

        return "Faculty/dashboard";
    }
}
