package com.edu.miusched.controller;


import com.edu.miusched.domain.Course;
import com.edu.miusched.domain.Entry;
import com.edu.miusched.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
  @Autowired
    CourseService courseService;
    @RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
    public String ListCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());

        return "Admin/ManageCourse";
    }


    @RequestMapping(value = {"/admin/course"}, method = RequestMethod.GET)
    public String getCourseHome(@ModelAttribute("newCourse") Course course, Model model) {
        Course  course1 = new Course();
        List<Course> courses = new ArrayList<Course>();
        courses.addAll(courseService.findAll());
        model.addAttribute("courses", courses);
        model.addAttribute("newCourse", course);
        model.addAttribute("course1",course1);

        return "Admin/ManageCourse";
    }

    @RequestMapping(value={"/admin/course/add"},method=RequestMethod.POST)
    public String courseSave(@ModelAttribute("newCourse") @Validated Course courseObj, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors());
            return "Admin/ManageCourse";
        }else{
            courseService.save(courseObj);
            return "redirect:/admin/course";
        }
    }

    @RequestMapping(value = "/admin/course/prereq", method = RequestMethod.GET)
    public String prereq( Model model) {
        //List<Course> courses = courseService.findAllPrerequsite();
        model.addAttribute("courses", courseService.findAllPrerequsite());
        return "Admin/ManageCourse";
    }

    @RequestMapping(value = "/admin/course/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Long id, ModelMap model) {
        Course course = new Course();
        model.addAttribute("course", courseService.findCoursebyId(id));

        //Course course = courseService.findCoursebyId(id);
        if (course == null) {
            // not found
            return "404";
        }
        model.addAttribute("course", course);

        return "Admin/ManageCourse";
    }
    @RequestMapping(value = "/admin/course/updatecourse", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("course") Course courseupdate, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "Admin/ManageCourse";
        }

        courseService.save(courseupdate);
        return "redirect:/admin/course";
    }

    @RequestMapping(value="/admin/course/delete/{id}", method=RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/course";
    }




}
