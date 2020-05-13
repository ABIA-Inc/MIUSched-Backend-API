///*
//package com.edu.miusched.controller;
//
//
//
//import com.edu.miusched.dao.scheduleDao;
//import com.edu.miusched.domain.Faculty;
//import com.edu.miusched.domain.Schedule;
//import com.edu.miusched.domain.ScheduleStatus;
//import com.edu.miusched.service.impl.BlockServiceImpl;
//import com.edu.miusched.service.impl.FacultyServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class facultyController {
//
//
//    FacultyServiceImpl facultyService;
//
//    scheduleDao scheduleDao;
//
//    BlockServiceImpl blockService;
//
//    @Autowired
//    public facultyController(FacultyServiceImpl facultyService,scheduleDao scheduleDao, BlockServiceImpl blockService) {
//        this.blockService  = blockService;
//        this.scheduleDao = scheduleDao;
//        this.facultyService =facultyService;
//
//    }
//
//    @GetMapping("faculty/preference")
//    public String showPreference(Model model) {
//
////        System.out.println(nx);
//        model.addAttribute("blocks",blockService.getAllBlocks());
//        model.addAttribute("preference",facultyService.getById(1l));
//        return "Faculty/Preference";
//    }
//
//   @PostMapping("faculty/setBlock")
//   public String create(@ModelAttribute("faculty")Faculty faculty, BindingResult result, Model model ) {
//
//       return "redirect:/faculty/preference";
//
//
//}
//}
//*/
