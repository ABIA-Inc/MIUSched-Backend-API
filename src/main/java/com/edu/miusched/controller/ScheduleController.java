package com.edu.miusched.controller;

import com.edu.miusched.domain.*;
import com.edu.miusched.service.BlockService;
import com.edu.miusched.service.EntryService;
import com.edu.miusched.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EntryService entryService;

    @Autowired
    BlockService blockService;

    @RequestMapping(value = "/admin/schedule", method = RequestMethod.GET)
    public String getScheduleForm(@ModelAttribute("newSchedule") Schedule schedule, Model model){

        schedule = scheduleService.getScheduleById(1L);

        model.addAttribute("newSchedule", schedule);
        model.addAttribute("schedule1", new Schedule());
        model.addAttribute("schedules", scheduleService.getAllSchedules());

        List<String> entryNames = new ArrayList<>();
        for (Entry entry: entryService.getAllEntries()) {
            entryNames.add(entry.getEntryName());
        }
        model.addAttribute("entryNames", entryNames);

        model.addAttribute("blocks", blockService.getAllBlocks());

        model.addAttribute("block1", new Block());

//        model.addAttribute("newBlock", new Block());

        model.addAttribute("objectHolder", new ObjectHolder());

        return "Admin/ManageSchedule";
    }

    @RequestMapping(value = "/admin/schedule/addschedule", method = RequestMethod.POST)
    public String addBlock(@ModelAttribute("newSchedule") Schedule newSchedule, Model model){

//        model.addAttribute("entryName", entryNames);

//        newSchedule.setStatus(ScheduleStatus.valueOf());

        scheduleService.save(newSchedule);

        return "redirect:/admin/schedule";
    }

    @Autowired
    BlockController blockController;

//    @RequestMapping(value = "/admin/schedule/addblock", method = RequestMethod.POST)
//    public String addBlock(@ModelAttribute("newBlock") Block newBlock, Model model){
//
//        blockController.addBlock(newBlock, model);
////        blockService.save(newBlock);
//
////        System.out.println("Sched ID: " + id);
////        Schedule scheduleX = scheduleService.getScheduleById(id);
////        Entry entryX = scheduleX.getEntry();
//
////        newBlock = new Block(1L, entryX, "September","MAY2020" , 25, 25, LocalDate.of(2020, 5, 25),
////                            LocalDate.of(2020, 6, 30), new ArrayList<Section>(), scheduleX);
////
////        blockController.addBlock(newBlock, model);
////
////        scheduleService.addBlock(id, newBlock);
//
//        return "redirect:/admin/schedule";
//    }

    @RequestMapping(value = "/admin/schedule/addblock/{id}", method = RequestMethod.POST)
    public String addBlock(@PathVariable("id") Long id, Block newBlock, Model model) {

        System.out.println("Sched ID: " + id);
        Schedule scheduleX = scheduleService.getScheduleById((long) id);
        System.out.println(scheduleX.getScheduleStatus().name());
        Entry entryX = scheduleX.getEntry();
        System.out.println(entryX.getEntryName());
        System.out.println(newBlock.getBlockName());

        newBlock = new Block(1L, entryX, "September","MAY2020" , 25, 25, LocalDate.of(2020, 5, 25),
                LocalDate.of(2020, 6, 30), new ArrayList<Section>(), scheduleX);

        blockController.addBlock(newBlock, model);

        scheduleService.addBlock((long) id, newBlock);

        return "redirect:/admin/schedule";
    }


//    //    @RequestMapping(value = "/admin/schedule/addblock", method = RequestMethod.POST)
////    public String addBlock(@ModelAttribute("newBlock") Block newBlock, Long id, Model model){
//    @RequestMapping(value = "/admin/schedule/addblock/{id:Long}", method = RequestMethod.POST)
//    public String addBlock([FromUri()] Long id,  [FromBody()] Block newBlock, Model model){
//
//        System.out.println("Sched ID: " + id);
//        Schedule scheduleX = scheduleService.getScheduleById(id);
//        Entry entryX = scheduleX.getEntry();
//
//        newBlock = new Block(1L, entryX, "September","MAY2020" , 25, 25, LocalDate.of(2020, 5, 25),
//                            LocalDate.of(2020, 6, 30), new ArrayList<Section>(), scheduleX);
//
//        blockController.addBlock(newBlock, model);
//
//        scheduleService.addBlock(id, newBlock);
//
//        return "redirect:/admin/schedule";
//    }

    @RequestMapping(value = "/admin/schedule/edit/{id}", method = RequestMethod.GET)
    public String editSchedule(@PathVariable Long id, ModelMap model) {

        model.addAttribute("editSchedule", scheduleService.getScheduleById(id));
        return "Admin/ManageSchedule";
    }

    @RequestMapping(value = "/admin/schedule/updateschedule", method = RequestMethod.POST)
    public String updateSchedule(@ModelAttribute("updateSchedule") Schedule scheduleupdate, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "Admin/ManageSchedule";
        }

        scheduleupdate.setEntry(entryService.findEntryByName(scheduleupdate.getEntry().getEntryName()));
        scheduleService.save(scheduleupdate);
        return "redirect:/admin/schedule";
    }

    @RequestMapping(value = "/admin/schedule/delete/{id}", method = RequestMethod.GET)
    public String deleteSchedule(@PathVariable Long id, Model model){

//        scheduleService.getScheduleById(id).setEntry(null);
        scheduleService.deleteScheduleById(id);

        return "redirect:/admin/schedule";
    }
}
