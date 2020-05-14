
package com.edu.miusched.controller;


import com.edu.miusched.dao.scheduleDao;
import com.edu.miusched.domain.*;
import com.edu.miusched.service.BlockService;
import com.edu.miusched.service.CourseService;
import com.edu.miusched.service.FacultyService;
import com.edu.miusched.service.impl.BlockServiceImpl;
import com.edu.miusched.service.impl.FacultyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class facultyController {

    boolean status = false;

    Faculty faculty = new Faculty();
    FacultyService facultyService;

    scheduleDao scheduleDao;

    BlockService blockService;


    CourseService courseSErvice;

    @Autowired
    public facultyController(FacultyServiceImpl facultyService, scheduleDao scheduleDao,
                             BlockService blockService, CourseService courseSErvice) {
        this.blockService = blockService;
        this.scheduleDao = scheduleDao;
        this.facultyService = facultyService;
        this.courseSErvice = courseSErvice;

    }

    @GetMapping("faculty/preference")
    public String showPreference(Model model) {
        faculty.setId(1l);

//        System.out.println(courseSErvice.findAll());
        model.addAttribute("courses", courseSErvice.findAll());
        model.addAttribute("status", status);
        model.addAttribute("blocks", blockService.getAllBlocks());
        model.addAttribute("preference", facultyService.getById(1l));
        model.addAttribute("faculty", faculty);
        return "Faculty/Preference";
    }

    @PostMapping("faculty/setBlock")
    public String create(@ModelAttribute("faculty") Faculty faculty, BindingResult result, Model model) {

        if (result.hasFieldErrors()) {
            System.out.println(faculty);
        }


        for (Block block : faculty.getBlockPreference()
        ) {
            facultyService.addBlock(block.getId());

        }
        facultyService.UpdateFaculty(faculty.getId(), faculty.getIntervalBetweenBlocks()
                , faculty.getStartDate());
        status = true;
        return "redirect:/faculty/preference";
    }

    @PostMapping("faculty/setCourse")
    public String createCourse(@ModelAttribute("faculty") Faculty faculty, BindingResult result, Model model) {

        if (result.hasFieldErrors()) {
            System.out.println(faculty);
        }

        for (Course course : faculty.getCoursePreference()) {
            if (facultyService.getOneCourse(course.getId()) == null) {
                    facultyService.addCourse(course.getId());
            }
        }
        status = true;
        return "redirect:/faculty/preference";


    }

    @GetMapping("/faculty/deleteCourse/{id}")
    public String DeleteCoursePreference(@PathVariable("id") Long id, Model model) {


        facultyService.deleteCourseById(id);
        status = true;
        return "redirect:/faculty/preference";
    }


}

