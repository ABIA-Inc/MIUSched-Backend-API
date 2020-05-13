
package com.edu.miusched.controller;



import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Entry;
import com.edu.miusched.domain.EntryType;
import com.edu.miusched.domain.Student;
import com.edu.miusched.service.EntryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryControllerTest {

    private MockMvc mockMvc;


    @Mock
    private EntryService entryService;


    @InjectMocks
    private EntryController entryController;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entryController)
                .build();
    }

    @Test
    public void testListentry() throws Exception {
        ObjectMapper om = new ObjectMapper();
       Entry entry =  new Entry(1l, "February", 12, 17, LocalDate.of(2020, 10, 13),
               LocalDate.of(2021, 10, 13), EntryType.AUGUST, new ArrayList<Student>(), new ArrayList<Block>());
        Entry entry1 =  new Entry(2l, "August", 12, 17, LocalDate.of(2020, 02, 13),
                LocalDate.of(2020, 9, 18), EntryType.AUGUST, new ArrayList<Student>(), new ArrayList<Block>());
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry1);

        //specific Mockito interaction, tell stub to return list of entries
        when(entryService.getAllEntries()).thenReturn((List) entries); //need to strip generics to keep Mockito happy.

        mockMvc.perform(get("/admin/entries"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin/ManageEntry"))
                .andExpect(model().attribute("entries", hasSize(2)));

    }

    @Test
    public void testDeleteEntry() throws Exception{
        Long id = 1l;

        mockMvc.perform(get("/admin/entry/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/entry"));

        verify(entryService, times(1)).deleteEntry(id);
    }

    @Test
    public void testNewEntityform() throws Exception {
        Integer id = 1;

        //should not call service
       verifyNoMoreInteractions(entryService);

        mockMvc.perform(get("/admin/entry"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin/ManageEntry"))
                .andExpect(model().attribute("newEntry", instanceOf(Entry.class)));
    }

    @Test
    public void testEdit() throws Exception{
        Long id = 1l;

        //Tell Mockito stub to return new Entry for ID 1
        when(entryService.findByEntryId(id)).thenReturn(new Entry());

        mockMvc.perform(get("/admin/entry/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin/ManageEntry"))
                .andExpect(model().attribute("entry", instanceOf(Entry.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Long id = 2l;
        String entryname = "February2020";
        int FPPNum= 49;
        int MPPNum = 60;
        LocalDate startDate= LocalDate.of(2020, 10, 13);
        LocalDate endDate = LocalDate.of(2021, 10, 13);


        Entry entry = new Entry();
        entry.setId(id);
         entry.setEntryName(entryname);
         entry.setFPPNum(FPPNum);
         entry.setMPPNum(MPPNum);
         entry.setStartDate(startDate);
         entry.setEndDate(endDate);


        when(entryService.save(ArgumentMatchers.<Entry>any())).thenReturn(entry);

        mockMvc.perform(post("/admin/entry/updateentry")
                .param("id", "2")
                .param("entryName", entryname)
                .param("FPPNum", "49")
                .param("MPPNum", "60")
                .param("startDate", String.valueOf(LocalDate.of(2020, 10, 13)))
                .param("endDate", String.valueOf(LocalDate.of(2021, 10, 13))))


                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/entry"))
                .andExpect(model().attribute("entry", instanceOf(Entry.class)))
                .andExpect(model().attribute("entry", hasProperty("id", is(id))))
                .andExpect(model().attribute("entry", hasProperty("entryName", is(entryname))))
                .andExpect(model().attribute("entry", hasProperty("FPPNum", is(FPPNum))))
                .andExpect(model().attribute("entry", hasProperty("MPPNum", is(MPPNum))))
                .andExpect(model().attribute("entry", hasProperty("startDate", is(startDate))))
                .andExpect(model().attribute("entry", hasProperty("endDate", is(endDate))));


        //verify properties of bound object
        ArgumentCaptor<Entry> boundEntry = ArgumentCaptor.forClass(Entry.class);
        verify(entryService).save(boundEntry.capture());

        assertEquals(id, boundEntry.getValue().getId());
        assertEquals(entryname, boundEntry.getValue().getEntryName());
        assertEquals(FPPNum, boundEntry.getValue().getFPPNum());
        assertEquals(MPPNum, boundEntry.getValue().getMPPNum());
        assertEquals(startDate, boundEntry.getValue().getStartDate());
        assertEquals(endDate, boundEntry.getValue().getEndDate());
    }




}
