package com.edu.miusched.controller;

import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Section;
import com.edu.miusched.service.CourseService;
//import com.edu.miusched.service.EntryService;
import com.edu.miusched.domain.Course;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseControllerTest {
    private MockMvc mockMvc;


    @Mock
    private CourseService courseService;


    @InjectMocks
    private CourseController courseController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(courseController)
                .build();
    }


    @Test
    public void testGetCourseHome() throws Exception {
        verifyNoInteractions(courseService);
        mockMvc.perform(get("/admin/course/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin/ManageCourse"))
                .andExpect(model().attribute("newCourse", instanceOf(Course.class)));


    }

    // @RequestMapping(value={"/admin/course/add"},method=RequestMethod.POST)
    //    public String courseSave(@ModelAttribute("newCourse") @Validated Course courseObj, BindingResult result, Model model){
    //        if(result.hasErrors()){
    //            model.addAttribute("errors", result.getAllErrors());
    //            return "Admin/ManageCourse";
    //        }else{
    //            courseService.save(courseObj);
    //            return "redirect:/admin/course";
    //        }
    //
    @Test
    public void testSave() throws Exception {
        Long id = 2L;
        String courseCode = "SE406";
        String courseTitle = "SoftwareEngineering";
        int credits = 6;
        int maxStudent = 20;
        List<Course> prerequisites = new ArrayList<>();
        List<Section> sections = new ArrayList<>();
        int level = 500;
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseTitle(courseTitle);
        course.setCredits(6);
        course.setId(id);
        course.setMaxStudent(maxStudent);
        course.setLevel(level);
        course.setPrerequisites(prerequisites);


        when(courseService.save(ArgumentMatchers.any())).thenReturn(course);


        mockMvc.perform(post("/admin/course/add")
                .param("id", "2")
                .param("courseCode", "SE406")
                .param("courseTitle", "SoftwareEngineering")
                .param("credits", "6")
                .param("level", "500")

                .param("maxStudent", "20"))

                .andExpect(view().name("redirect:/admin/course"))

                .andExpect(model().attribute("newCourse", instanceOf(Course.class)))
                .andExpect(model().attribute("newCourse", hasProperty("id", is(id))))
                .andExpect(model().attribute("newCourse", hasProperty("courseCode", is(courseCode))))
                .andExpect(model().attribute("newCourse", hasProperty("courseTitle", is(courseTitle))))
                .andExpect(model().attribute("newCourse", hasProperty("credits", is(credits))))
                .andExpect(model().attribute("newCourse", hasProperty("maxStudent", is(maxStudent))))
                .andExpect(model().attribute("newCourse", hasProperty("level", is(level))));


        ArgumentCaptor<Course> boundCourse = ArgumentCaptor.forClass(Course.class);
        verify(courseService).save(boundCourse.capture());

        assertEquals(id, boundCourse.getValue().getId());
        assertEquals(courseCode, boundCourse.getValue().getCourseCode());
        assertEquals(courseTitle, boundCourse.getValue().getCourseTitle());
        assertEquals(credits, boundCourse.getValue().getCredits());
        assertEquals(maxStudent, boundCourse.getValue().getMaxStudent());
        assertEquals(level, boundCourse.getValue().getLevel());
    }

    /*@Test
    public void testDelete() throws Exception {
        Long id = 2l;
        mockMvc.perform(get("/admin/course/delete/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/course"));
        verify(courseService, times(1)).deleteCourse(id);

    }*/

    @Test
    public void testListCourse() throws Exception {
        Course course = new Course();
        Course course1 = new Course();
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(course);
        courseList.add(course1);
        when(courseService.findAll()).thenReturn(courseList);
        mockMvc.perform(get("/admin/courses/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin/ManageCourse"))
                .andExpect(model().attribute("courses", hasSize(2)));
    }

}
