package com.edu.miusched.controller.AuthController;

import com.edu.miusched.domain.Admin;
import com.edu.miusched.domain.Faculty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacultyLoginController {


    @PostMapping("/faculty/index")
    public String index() {

        return "redirect:/faculty/preference";
    }

    @GetMapping("/faculty/login")
    String login(Model model) {

        Faculty faculty = new Faculty();
        model.addAttribute("faculty",faculty);
        return "Auth/FacultyLogin";
    }

    @PostMapping("/faculty/login")
    public String loginn(@ModelAttribute("admin") Faculty faculty) {

        return "redirect:/faculty/preference";
    }
}
