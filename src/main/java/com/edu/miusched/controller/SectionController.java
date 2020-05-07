package com.edu.miusched.controller;

import com.edu.miusched.domain.Section;
import com.edu.miusched.service.impl.Sectionimpl;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SectionController {

    @Autowired
    Sectionimpl sectionimpl;

    @GetMapping("/admin/section")
    public  String index(Model model) {

        model.addAttribute("sections",sectionimpl.getAllSection());
        return "Admin/ManageSection";
    }
    @GetMapping("/admin/Viewsection")
    public  String show(Model model) {

        return "Admin/ViewSection";
    }
    @PostMapping("/admin/createSection")
    public  String create(@ModelAttribute("section") @Valid Section section ) {

        return "redirect:/Admin/createSection";
    }
    @PostMapping("/admin/Updatesection")
    public  String update(Model model) {

        return "redirect:/Admin/ManageSection";
    }
}
