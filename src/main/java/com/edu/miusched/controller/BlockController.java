package com.edu.miusched.controller;

import com.edu.miusched.domain.Block;
import com.edu.miusched.domain.Entry;
import com.edu.miusched.service.BlockService;
import com.edu.miusched.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlockController {

    @Autowired
    BlockService blockService;

    @Autowired
    EntryService entryService;

    @RequestMapping(value = "/admin/block", method = RequestMethod.GET)
    public String getBlockForm(@ModelAttribute("newBlock") Block block, Model model){

        model.addAttribute("newBlock", block);
        model.addAttribute("block1", new Block());
        model.addAttribute("blocks", blockService.getAllBlocks());

        return "Admin/ManageBlock";
    }

    @RequestMapping(value = "/admin/block/addblock", method = RequestMethod.POST)
    public String addBlock(@ModelAttribute("newBlock") Block newBlock, Model model){

        newBlock.setEntry(entryService.findEntryByName(newBlock.getEntryName()));
        blockService.save(newBlock);

        return "redirect:/admin/block";
    }

        @RequestMapping("/admin/blocks")
    public String listBlocks(Model model) {

        model.addAttribute("blocks", blockService.getAllBlocks());

        return "Admin/ManageBlock";
    }

    @RequestMapping(value = "/admin/block/edit/{id}", method = RequestMethod.GET)
    public String editBlock(@PathVariable Long id, ModelMap model) {

        model.addAttribute("editBlock", blockService.getBlockById(id));
        return "Admin/ManageBlock";
    }

    @RequestMapping(value = "/admin/block/updateblock", method = RequestMethod.POST)
    public String updateBlock(@ModelAttribute("updateBlock") Block blockupdate, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "Admin/ManageBlock";
        }

        blockupdate.setEntry(entryService.findEntryByName(blockupdate.getEntryName()));
        blockService.save(blockupdate);
        return "redirect:/admin/block";
    }

    @RequestMapping(value = "/admin/block/delete/{id}", method = RequestMethod.GET)
    public String deleteBlock(@PathVariable Long id, Model model){

        blockService.deleteBlockById(id);

        return "redirect:/admin/block";
    }
}
