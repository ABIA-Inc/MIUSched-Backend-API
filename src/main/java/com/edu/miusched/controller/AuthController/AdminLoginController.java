package com.edu.miusched.controller.AuthController;

import com.edu.miusched.dao.AccountDao;
import com.edu.miusched.domain.Admin;
import com.edu.miusched.service.*;
import com.edu.miusched.service.impl.AccountImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class AdminLoginController {


    EntryService entryService;
    BlockService blockService;
    SectionService sectionService;
    CourseService courseService;
    @Autowired
    public AdminLoginController(EntryService entryService, BlockService blockService, SectionService sectionService, CourseService courseService) {
        this.entryService = entryService;
        this.blockService = blockService;
        this.sectionService = sectionService;
        this.courseService = courseService;
    }




    @GetMapping("/adminloginPage")
    String login(Model model) {


       Admin admin = new Admin();
       model.addAttribute("admin",admin);
        return "Auth/AdminLogin";
    }

    @PostMapping("/admin/login")
    public String loginn(@ModelAttribute("admin") Admin admin) {
        return "redirect:/admin/admindashboard";
    }

    @GetMapping("/admin/admindashboard")

    String index(Model model) {
        model.addAttribute("entries",entryService.getAllEntries().size());
        model.addAttribute("block",blockService.getAllBlocks().size());
        model.addAttribute("section",sectionService.getAllSection().size());
        model.addAttribute("course",courseService.findAll().size());


        return "/Admin/Admindashboard";
    }

    @GetMapping("/admin/profile")

    String show(Model model) {

        return "/Admin/profile";
    }
}
