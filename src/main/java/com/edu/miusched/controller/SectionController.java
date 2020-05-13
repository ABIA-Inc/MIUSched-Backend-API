package com.edu.miusched.controller;

import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Section;
import com.edu.miusched.service.impl.BlockServiceImpl;
import com.edu.miusched.service.impl.Sectionimpl;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SectionController {



    boolean status = false;
    int i = 0;
//    public JavaMailSender emailSender;

    Sectionimpl sectionimpl;
    BlockServiceImpl blockimp;

    @Autowired

    public SectionController(Sectionimpl sectionimpl, BlockServiceImpl blockimp) {
        this.sectionimpl = sectionimpl;
        this.blockimp = blockimp;
    }



    @GetMapping("/admin/section")
    public  String index(Model model) {
//        message.setTo("isala@miu.edu");
//        message.setSubject("SpringTest");
//        message.setText("sddasfdasf dfsafddfg");
//        emailSender.send(message);
        Section section = new Section();
        model.addAttribute("sections",sectionimpl.getAllSection());
        model.addAttribute("blocks",blockimp.getAllBlocks());
        model.addAttribute("section",section);
        status =i==0?false:true;
        model.addAttribute("status",status);
        return "Admin/ManageSection";
    }
    @GetMapping("/admin/Viewsection")
    public  String show(Model model) {

        return "Admin/ViewSection";
    }
    @PostMapping("/admin/createSection")
    public  String create(@ModelAttribute("section") Section section, BindingResult result, Model model  ) {

        if (result.hasErrors()) {

            return "redirect:/admin/section";
        }
        sectionimpl.SaveSection(section);
        i=1;
        return "redirect:/admin/section";
    }
    @PostMapping("/admin/Updatesection")
    public  String update(@ModelAttribute("section") Section section, BindingResult result, Model model ) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "redirect:/admin/section";

        }
//        System.out.println(section);
        sectionimpl.SaveSection(section);
        i=1;
        return "redirect:/admin/section";
    }
    @GetMapping("/admin/section/delete/{id}")
    public  String delete(@PathVariable("id") Long id, Model model) {
        if(sectionimpl.findSectionById(id) == null) {

            new IllegalArgumentException("Section not found");
        }
       sectionimpl.deleteSectionById(id);
        return "redirect:/admin/section";
    }
}
