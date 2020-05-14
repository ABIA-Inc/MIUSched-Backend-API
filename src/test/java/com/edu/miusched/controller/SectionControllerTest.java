package com.edu.miusched.controller;

import com.edu.miusched.domain.*;
import com.edu.miusched.service.BlockService;
import com.edu.miusched.service.EntryService;
import com.edu.miusched.service.SectionService;
import com.edu.miusched.service.impl.Sectionimpl;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionControllerTest {
    private MockMvc mockMvc;


    @Mock
    private SectionService sectionService;
    @Mock
    private Sectionimpl sectionimpl;

    @InjectMocks
    private SectionController sectionController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(sectionController)
                .build();
    }



    @Test
    public void testShow() throws Exception {

       verifyNoMoreInteractions(sectionService);

        mockMvc.perform(get("/admin/Viewsection"))
                .andExpect(status().isOk())
              //  .andExpect(model().attribute("section", instanceOf(Section.class)))
                .andExpect(view().name("Admin/ViewSection"));


    }
    @Test
    public void testCreate() throws Exception {

        Long id = 2l;
        String sectionName = "SE-A";
        LocalDate startDate= LocalDate.of(2020, 10, 13);
        LocalDate endDate = LocalDate.of(2021, 10, 13);
        String classRoom="Verill 42";
        Integer capacity=20;
        List<Grade> grades = new ArrayList<>();

        Section section =new Section();
        section.setId(id);
        section.setSectionName(sectionName);
        section.setStartDate(startDate);
        section.setEndDate(endDate);
        section.setCapacity(capacity);
        section.setClassRoom(classRoom);
        section.setGrades(grades);

       when(sectionService.SaveSection(ArgumentMatchers.<Section>any())).thenReturn(section);


        mockMvc.perform(post("/admin/createSection")
                .param("id", "2")

                .param("sectionName", "SE-A")
                .param("classRoom", "Verill 42")
                .param("capacity", "20")
                .param("MPPNum", "60")
                .param("startDate", String.valueOf(LocalDate.of(2020, 10, 13)))
                .param("endDate", String.valueOf(LocalDate.of(2021, 10, 13))))


                 .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/section"))

                .andExpect(model().attribute("section", instanceOf(Section.class)))
                .andExpect(model().attribute("section", hasProperty("id", is(id))))
                .andExpect(model().attribute("section", hasProperty("sectionName", is(sectionName))))
                .andExpect(model().attribute("section", hasProperty("classRoom", is(classRoom))))
                .andExpect(model().attribute("section", hasProperty("capacity", is(capacity))))


                .andExpect(model().attribute("section", hasProperty("startDate", is(startDate))))
                .andExpect(model().attribute("section", hasProperty("endDate", is(endDate))));


        //verify properties of bound object
        ArgumentCaptor<Section> boundSection = ArgumentCaptor.forClass(Section.class);
        verify(sectionimpl).SaveSection(boundSection.capture());

        assertEquals(id, boundSection.getValue().getId());
        assertEquals(sectionName, boundSection.getValue().getSectionName());
        assertEquals(capacity, boundSection.getValue().getCapacity());
        assertEquals(classRoom, boundSection.getValue().getClassRoom());
        assertEquals(startDate, boundSection.getValue().getStartDate());
        assertEquals(endDate, boundSection.getValue().getEndDate());
    }
    @Test
    public void testDelete() throws Exception{
        Long id = 2l;

        mockMvc.perform(get("/admin/section/delete/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/section"));

        verify(sectionimpl, times(1)).deleteSectionById(id);
    }


}
