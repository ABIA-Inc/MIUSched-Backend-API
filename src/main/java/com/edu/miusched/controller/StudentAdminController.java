package com.edu.miusched.controller;


import com.edu.miusched.domain.Entry;
import com.edu.miusched.domain.Student;
import com.edu.miusched.domain.Student;
import com.edu.miusched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentAdminController {

    @Autowired
    StudentService studentService;
    @RequestMapping(value = {"/admin/student"}, method = RequestMethod.GET)
    public String getStudentHome(@ModelAttribute("newStudent") Student student, Model model) {
        // AjaxResponse response = new AjaxResponse();
        //response.success =false ?false :true;

        Student  student1 = new Student();
        List<Student> students = new ArrayList<Student>();
        students.addAll(studentService.getAllStudents());

        model.addAttribute("students", students);
        model.addAttribute("newStudent", student);
        model.addAttribute("student1",student1);

        return "Admin/ManageStudent";
    }
    @RequestMapping(value = "/admin/student/new")
    public String studentRegForm(@ModelAttribute("newStudent") Student student,BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors());
            return "Admin/ManageStudent";
        }else{
            studentService.save(student);
            return "redirect:/admin/student";
        }
    }


    @RequestMapping("/admin/student/view/{id}")
    public String getProduct(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));
        return "redirect:/admin/student";
    }



    @RequestMapping("/admin/student/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudentById(id);

        return "redirect:/admin/student";
    }

    @RequestMapping(value = "/admin/student/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model){
        Student student = new Student();
        model.addAttribute("entry", studentService.getStudentById(id));
        return "Admin/ManageStudent";
    }
    @RequestMapping(value="/admin/student/update",method=RequestMethod.POST)
    public String saveUpdate (@ModelAttribute("student") Student studentupdate, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "Admin/ManageEntry";
        }
        studentService.save(studentupdate);
        return "redirect:/admin/student";


    }
}