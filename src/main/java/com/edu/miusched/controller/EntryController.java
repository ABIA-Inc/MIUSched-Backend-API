package com.edu.miusched.controller;


import com.edu.miusched.domain.Entry;
import com.edu.miusched.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EntryController {
    @Autowired
    EntryService entryService;

    @RequestMapping(value = {"/admin/entry"}, method = RequestMethod.GET)
    public String getForm(@ModelAttribute("newEntry") Entry entry, Model model) {
        Entry  entry1 = new Entry();
        List<Entry> entries = new ArrayList<Entry>();
        entries.addAll(entryService.getAllEntries());
        System.out.println(entries);
        model.addAttribute("entries", entries);
        model.addAttribute("newEntry", entry);
        model.addAttribute("entry1",entry1);
        return "Admin/ManageEntry";
    }

    @RequestMapping(value = {"/addnewentry"}, method = RequestMethod.POST)
    public String registerEntry(@ModelAttribute("newEntry") @Validated Entry entryObj, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Admin/ManageEntry";
        } else {
            entryObj.setEntryName(entryObj.getEntryType().name()+entryObj.getStartDate().getYear());
            entryService.save(entryObj);
            return "redirect:/admin/entry";
        }
    }

    @RequestMapping("/admin/entries")
    public String listProducts(Model model) {

        model.addAttribute("entries", entryService.getAllEntries());

        return "Admin/ManageEntry";
    }

    @RequestMapping("/admin/entry/delete/{id}")
    public String delete(@PathVariable Long id) {
        entryService.deleteEntry(id);
        return "redirect:/admin/entry";
    }

    @RequestMapping(value = "/admin/entry/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Entry entry = new Entry();
        model.addAttribute("entry", entryService.findByEntryId(id));
        return "Admin/ManageEntry";
    }

    @RequestMapping(value = "/updateentry", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("entry") Entry entryupdate, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "Admin/ManageEntry";
        }
        //System.out.println( "id : " +entryupdate.getId());
        entryService.save(entryupdate);
        return "redirect:/admin/entry";
    }
}

