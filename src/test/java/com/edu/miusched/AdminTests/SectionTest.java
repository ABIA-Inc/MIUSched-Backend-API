package com.edu.miusched.AdminTests;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.edu.miusched.controller.SectionController;
import com.edu.miusched.dao.SectionDao;
import com.edu.miusched.domain.Section;
import com.edu.miusched.service.SectionService;
import com.edu.miusched.service.impl.BlockServiceImpl;
import com.edu.miusched.service.impl.Sectionimpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
public class SectionTest {
    private MockMvc mockMvc;
    @Mock
    Sectionimpl sectionimpl;
    @Mock
    SectionDao sectionDao;
    @InjectMocks
    SectionController sectionController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sectionController).build();
    }

  @Test
    public  void showAllSections() throws Exception {

        boolean status =false;
        List<Section> testSection = new ArrayList<>();
        testSection.add(new Section());
        testSection.add(new Section());
        System.out.println(sectionimpl.getAllSection().size());

        when(sectionimpl.getAllSection()).thenReturn((List)testSection);

        mockMvc.perform(get("/admin/section"))
                      .andExpect(status().isOk())
                      .andExpect(view().name("Admin/ManageSection"))
                      .andExpect(model().attribute("sections",hasSize(3) ))
//        .andExpect(model().attribute("blocks", blockService.findAll()))
        .andExpect(model().attribute("section",instanceOf(Section.class) ))
        .andExpect(model().attribute("status",status));



//        verify(sectionController, times(1)).index(Model model);

    }



}
