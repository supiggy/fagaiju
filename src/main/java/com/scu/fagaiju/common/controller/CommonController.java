package com.scu.fagaiju.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommonController {
    @GetMapping("/")
    public String toIndex(){

        return "/views/login";
    }


    @GetMapping("/page/{path}")
    public String toPage(@PathVariable("path") String path){
        return "/views/"+path;
    }
}

