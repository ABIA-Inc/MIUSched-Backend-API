package com.edu.miusched.controller;


import com.edu.miusched.domain.Section;
import com.edu.miusched.domain.Student;
import com.edu.miusched.service.EntryService;
import com.edu.miusched.service.SectionService;
import com.edu.miusched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentController {
    @Autowired
    SectionService sectionService;

    @Autowired
    StudentService studentService;
    @Autowired
    EntryService entryService;


    private Student student;

    private Section section;
    @RequestMapping("/student/view/{id}")
    public String getProduct(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));
        return "student/studenthome";
    }


    @RequestMapping(value = "/student/studentprofile/id", method = RequestMethod.GET)
    public String studentProfile(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("entriy", entryService.findByEntryId(student.getId()));
        model.addAttribute("sections", student.getSections());
        return "student/studenthome";
    }


    @RequestMapping(value = "/student/register/{id}", method = RequestMethod.POST)
    public String registerForSection(@ModelAttribute("section") Section section, Model model,
                                     @PathVariable Long id) throws Exception {
        section = sectionService.findSectionById(id);
        Student student = studentService.getStudentById(id);


        if (student.getSections() == null)
            student.setSections((List<Section>) new HashSet<Section>());

        student.getSections().add(section);
        studentService.save(student);

        return "redirect:/student/studentprofile";

    }
}
/*        private boolean isPrereqChosen(Section section, Student student) {
            if( section.getCourse().hasPreRequisite() ){
                List<Section> l = sectionService.findByCourseAndEnrolledStudents(section.getCourse().getPreRequisite(), student);
                if( l == null || l.size() == 0 ) {
                    return false;
                }
            }

            return true;
        }*/



