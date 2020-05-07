package com.edu.miusched.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BlockController {

    @GetMapping("/admin/block/")
   public String index() {

        return "block/manageBlock";
    }
}
