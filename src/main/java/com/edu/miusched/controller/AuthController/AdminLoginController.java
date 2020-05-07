package com.edu.miusched.controller.AuthController;

import com.edu.miusched.dao.AccountDao;
import com.edu.miusched.service.AccountService;
import com.edu.miusched.service.impl.AccountImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AdminLoginController {


   @Autowired
    AccountImp accountImp;





    @GetMapping("/adminloginPage")
    String login(Model model) {

        model.addAttribute("user",accountImp.getAccById(1l).getRole().name());
        return "Auth/Login";
    }

    @GetMapping("/admin/admindashboard")

    String index(Model model) {

        return "/Admin/Admindashboard";
    }

    @GetMapping("/admin/profile")

    String show(Model model) {

        return "/Admin/profile";
    }
}
