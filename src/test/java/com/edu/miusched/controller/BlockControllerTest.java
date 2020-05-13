package com.edu.miusched.controller;

import com.edu.miusched.domain.*;
import com.edu.miusched.service.BlockService;
import com.edu.miusched.service.EntryService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockControllerTest {
    private MockMvc mockMvc;


    @Mock
    private BlockService blockService;

 @Mock
 private EntryService entryService;
    @InjectMocks
    private BlockController blockController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(blockController)
                .build();
    }
    @Test
    public void testListblock() throws Exception {

      Block block=new Block(12l,new Entry(),"testBlock","FEB2020",4,5,LocalDate.of(2021, 10, 13),
              LocalDate.of(2021, 10, 29),new ArrayList<Section>());
      Block block1=new Block(14l,new Entry(),"testblock1","AUG2020",13, 12,LocalDate.of(2020, 02, 13),
              LocalDate.of(2020, 9, 18),new ArrayList<Section>());

      List<Block> blocks=new ArrayList<Block>();
      blocks.add(block);
        blocks.add(block1);

        when(blockService.getAllBlocks()).thenReturn((List) blocks); //need to strip generics to keep Mockito happy.

        mockMvc.perform(get("/admin/blocks"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin/ManageBlock"))
                .andExpect(model().attribute("blocks", hasSize(2)));



    }

    @Test
    public void testDeleteBlockById() throws Exception{
       long id=1l;
        mockMvc.perform(get("/admin/block/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/block"));
       verify(blockService, times(1)).deleteBlockById(id);
    }


    @Test
     public void testNewBlockForm() throws Exception {
        String entryName= "name";
         verifyNoMoreInteractions(blockService);

          mockMvc.perform(get("/admin/block/"))
                  .andExpect(status().isOk())
                  .andExpect(view().name("Admin/ManageBlock"))
                  .andExpect(model().attribute("newBlock", instanceOf(Block.class)));

    }

    @Test
    public void testadd() throws Exception {
        Long id = 2l;
        String blockName = "JuneBlock";
        String entryname="FEBRUARY2020";
        int FPPNum= 49;
        int MPPNum = 60;
        LocalDate startDate= LocalDate.of(2020, 10, 13);
        LocalDate endDate = LocalDate.of(2021, 10, 13);
        Entry entry =  new Entry(1l, "FEBRUARY", 12, 17, LocalDate.of(2020, 10, 13),
                LocalDate.of(2021, 10, 13), EntryType.AUGUST, new ArrayList<Student>(), new ArrayList<Block>());

        Block block=new Block();
        block.setId(id);
        block.setBlockName(blockName);
        block.setFPPNum(FPPNum);
        block.setMPPNum(MPPNum);
        block.setEntryName(entryname);
        block.setEntry(entry);
        block.setStartDate(startDate);
        block.setEndDate(endDate);
        when(entryService.findEntryByName(any())).thenReturn(entry);

        when(blockService.save(ArgumentMatchers.<Block>any())).thenReturn(block);




        //  when(entryService.save(ArgumentMatchers.<Entry>any())).thenReturn(entry);

        mockMvc.perform(post("/admin/block/addblock")
                .param("id", "2")

                .param("blockName", "JuneBlock")
                .param("entryName", "FEBRUARY2020")
                .param("FPPNum", "49")
                .param("MPPNum", "60")
                // .param("entry", entry.toString())
                .param("startDate", String.valueOf(LocalDate.of(2020, 10, 13)))
                .param("endDate", String.valueOf(LocalDate.of(2021, 10, 13))))


                //  .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/block"))
                //.andExpect(model().attribute("updateBlock", instanceOf(Block.class)))
                .andExpect(model().attribute("newBlock", instanceOf(Block.class)))
                .andExpect(model().attribute("newBlock", hasProperty("id", is(id))))
                .andExpect(model().attribute("newBlock", hasProperty("blockName", is(blockName))))
                .andExpect(model().attribute("newBlock", hasProperty("entryName", is(entryname))))
                .andExpect(model().attribute("newBlock", hasProperty("FPPNum", is(FPPNum))))
                .andExpect(model().attribute("newBlock", hasProperty("MPPNum", is(MPPNum))))

                .andExpect(model().attribute("newBlock", hasProperty("startDate", is(startDate))))
                .andExpect(model().attribute("newBlock", hasProperty("endDate", is(endDate))));


        //verify properties of bound object
        ArgumentCaptor<Block> boundBlock = ArgumentCaptor.forClass(Block.class);
        verify(blockService).save(boundBlock.capture());

        assertEquals(2, boundBlock.getValue().getId());
        assertEquals(blockName, boundBlock.getValue().getBlockName());
        assertEquals(entryname, boundBlock.getValue().getEntryName());
        assertEquals(FPPNum, boundBlock.getValue().getFPPNum());
        assertEquals(MPPNum, boundBlock.getValue().getMPPNum());
        assertEquals(startDate, boundBlock.getValue().getStartDate());
        assertEquals(endDate, boundBlock.getValue().getEndDate());
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = 2l;
        String blockName = "JuneBlock";
        String entryname="FEBRUARY2020";
        int FPPNum= 49;
        int MPPNum = 60;
        LocalDate startDate= LocalDate.of(2020, 10, 13);
        LocalDate endDate = LocalDate.of(2021, 10, 13);
        Entry entry =  new Entry(1l, "FEBRUARY", 12, 17, LocalDate.of(2020, 10, 13),
                LocalDate.of(2021, 10, 13), EntryType.AUGUST, new ArrayList<Student>(), new ArrayList<Block>());

        Block block=new Block();
        block.setId(id);
        block.setBlockName(blockName);
        block.setFPPNum(FPPNum);
        block.setMPPNum(MPPNum);
        block.setEntryName(entryname);
        block.setEntry(entry);
        block.setStartDate(startDate);
        block.setEndDate(endDate);
       when(entryService.findEntryByName(any())).thenReturn(entry);

        when(blockService.save(ArgumentMatchers.<Block>any())).thenReturn(block);




      //  when(entryService.save(ArgumentMatchers.<Entry>any())).thenReturn(entry);

        mockMvc.perform(post("/admin/block/updateblock")
                .param("id", "2")

                .param("blockName", "JuneBlock")
              .param("entryName", "FEBRUARY2020")
                .param("FPPNum", "49")
                .param("MPPNum", "60")
               // .param("entry", entry.toString())
                .param("startDate", String.valueOf(LocalDate.of(2020, 10, 13)))
                .param("endDate", String.valueOf(LocalDate.of(2021, 10, 13))))


              //  .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/block"))
              //.andExpect(model().attribute("updateBlock", instanceOf(Block.class)))
                .andExpect(model().attribute("updateBlock", instanceOf(Block.class)))
                .andExpect(model().attribute("updateBlock", hasProperty("id", is(id))))
                .andExpect(model().attribute("updateBlock", hasProperty("blockName", is(blockName))))
                .andExpect(model().attribute("updateBlock", hasProperty("entryName", is(entryname))))
                .andExpect(model().attribute("updateBlock", hasProperty("FPPNum", is(FPPNum))))
                .andExpect(model().attribute("updateBlock", hasProperty("MPPNum", is(MPPNum))))

                .andExpect(model().attribute("updateBlock", hasProperty("startDate", is(startDate))))
                .andExpect(model().attribute("updateBlock", hasProperty("endDate", is(endDate))));


        //verify properties of bound object
        ArgumentCaptor<Block> boundBlock = ArgumentCaptor.forClass(Block.class);
        verify(blockService).save(boundBlock.capture());

        assertEquals(2, boundBlock.getValue().getId());
        assertEquals(blockName, boundBlock.getValue().getBlockName());
        assertEquals(entryname, boundBlock.getValue().getEntryName());
        assertEquals(FPPNum, boundBlock.getValue().getFPPNum());
        assertEquals(MPPNum, boundBlock.getValue().getMPPNum());
        assertEquals(startDate, boundBlock.getValue().getStartDate());
        assertEquals(endDate, boundBlock.getValue().getEndDate());
    }
    }
