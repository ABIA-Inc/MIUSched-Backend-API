package com.edu.miusched.controller;


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

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/new")
    public String studentRegForm(@ModelAttribute("newStudent") Student student, Model model) {
        model.addAttribute("newStudent", student);
        return "Studentregform";
    }

    @RequestMapping(value = "/addnewstudent", method = RequestMethod.POST)
    public String registerStudent(@ModelAttribute("newstudent") Student student, Model model) {
        studentService.save(student);
//        model.addAttribute(studentService.getStudentByEmail(student.getEmail()));

        return "addsuccess";
    }

    @RequestMapping("/student/view/{id}")
    public String getProduct(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));

        return "display";
    }

    @RequestMapping("/students")
    public String listProducts(Model model) {

        model.addAttribute("students", studentService.getAllStudents());

        return "studentlist";
    }

    @RequestMapping("/students/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudentById(id);

        return "redirect:/students";
    }

    @RequestMapping(value = "/student/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit";
    }
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String saveUpdate (@ModelAttribute("student") Student studentupdate, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "edit";
        }
        studentService.save(studentupdate);
        return "redirect:/students";
    }
}